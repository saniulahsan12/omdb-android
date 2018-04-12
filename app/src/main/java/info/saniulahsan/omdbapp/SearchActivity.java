package info.saniulahsan.omdbapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends Activity {

    EditText searchMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if(getActionBar() != null){
            getActionBar().setTitle("Search For Movies");
        }
    }

    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public String getValue(EditText edittext){

        return edittext.getText().toString();
    }

    public void setValue(EditText edittext){

        edittext.setText(null);
    }

    public void setError(EditText edittext, String message ){
        edittext.setError(message);
    }

    public void doSearch(View view){
        searchMovie = (EditText) findViewById(R.id.searchMovie);
        if( getValue(searchMovie).equals("") ){
            setError(searchMovie,"Please type a valid movie name");
        } else{
            Intent movieactivity = new Intent(SearchActivity.this, MovieActivity.class);
            movieactivity.putExtra("movieName", getValue(searchMovie));
            startActivity(movieactivity);
        }
    }
}
