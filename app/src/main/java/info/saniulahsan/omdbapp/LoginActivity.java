package info.saniulahsan.omdbapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    EditText userName;
    EditText passWord;
    public static final String PREFS_NAME = "MyPrefsFile";
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private boolean hasLoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        settings = getSharedPreferences(PREFS_NAME, 0); // 0 - for private mode
        hasLoggedIn = settings.getBoolean("hasLoggedIn", false);

        if(hasLoggedIn){
            Intent searchactivity = new Intent(LoginActivity.this, SearchActivity.class);
            startActivity(searchactivity);
        }
    }

    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public String getValue(EditText edittext ){

        return edittext.getText().toString();
    }

    public void setValue(EditText edittext ){
        edittext.setText(null);
    }
    public void setError(EditText edittext, String message ){
        edittext.setError(message);
    }

    public void doLogin(View view){
        userName = (EditText) findViewById(R.id.userName);
        passWord = (EditText) findViewById(R.id.passWord);
        Boolean flag = true;

        if( getValue(userName).equals("") ){
            setError(userName, "Username required");
            flag = false;

        }
        if( getValue(passWord).equals("") ){
            setError(passWord, "Password required");
            flag = false;

        }

        if(flag){
            if( getValue(userName).equals("123") && getValue(passWord).equals("123") ){

                editor = settings.edit();
                editor.putBoolean("hasLoggedIn", true);
                editor.commit();

                Intent searchactivity = new Intent(LoginActivity.this, SearchActivity.class);
                startActivity(searchactivity);
            } else{
                showMessage("value did not matched");
            }
        }
    }
}
