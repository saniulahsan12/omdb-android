package info.saniulahsan.omdbapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class DetailsActivity extends Activity {

    private String ScreenTitle;
    private String movieTitle;
    private String imdbId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_details );

        Bundle bundle = getIntent().getExtras();

        ScreenTitle = bundle.getString("ScreenTitle");
        movieTitle = bundle.getString("movieTitle");
        imdbId = bundle.getString("imdbId");

        if(getActionBar() != null){
            getActionBar().setDisplayShowHomeEnabled(true);
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setTitle(movieTitle);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_settings){
            Intent detailsactivity  = new Intent(DetailsActivity.this, MovieActivity.class);
            detailsactivity.putExtra("movieName", ScreenTitle);
            startActivity(detailsactivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
