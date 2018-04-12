package info.saniulahsan.omdbapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends Activity implements MovieAdaptar.OnItemClickListener {

    private String ScreenTitle;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<MovieItem> MovieItems;
    private ArrayList<MovieItem> mExampleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Bundle bundle = getIntent().getExtras();
        ScreenTitle = bundle.getString("movieName");

        if(getActionBar() != null){
            getActionBar().setDisplayShowHomeEnabled(true);
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setTitle(ScreenTitle);
        }

        recyclerView = (RecyclerView) findViewById(R.id.movieList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MovieItems = new ArrayList<>();
        try {
            loadRecyclerViewData();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void loadRecyclerViewData() throws UnsupportedEncodingException {

        String URL = "http://www.omdbapi.com/?s="+ URLEncoder.encode(ScreenTitle, "UTF-8")+"&apikey=8f4b89e3&page=1";

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Loading From Server....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("Search");
                            int array_size = array.length();

                            for(int i=0; i<array_size; i++){
                                JSONObject obj = array.getJSONObject(i);
                                String Poster = obj.getString("Poster").equals( "N/A" ) ? "http://via.placeholder.com/300x450" : obj.getString("Poster");
                                MovieItem singleMovie = new MovieItem(
                                        obj.getString("Title"),
                                        obj.getString("Year"),
                                        obj.getString("Type"),
                                        obj.getString("imdbID"),
                                        Poster
                                );
                                MovieItems.add(singleMovie);
                            }

                            progressDialog.dismiss();
                            adapter = new MovieAdaptar(MovieActivity.this, MovieItems);
                            recyclerView.setAdapter(adapter);
                            new MovieAdaptar(MovieActivity.this, MovieItems).setOnItemClickListener(MovieActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        showMessage(error.getMessage());
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_settings){
            Intent searchactivity  = new Intent(MovieActivity.this, SearchActivity.class);
            startActivity(searchactivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position) {

        Intent detailsactivity = new Intent( this, DetailsActivity.class );
        MovieItem clickedItem = MovieItems.get(position);

        detailsactivity.putExtra("movieTitle", clickedItem.getTitle());
        detailsactivity.putExtra("imdbId", clickedItem.getImdbID());
        detailsactivity.putExtra("ScreenTitle", ScreenTitle);
        startActivity(detailsactivity);

    }
}
