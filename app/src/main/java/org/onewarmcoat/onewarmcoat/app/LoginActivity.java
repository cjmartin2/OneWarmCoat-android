package org.onewarmcoat.onewarmcoat.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.List;


public class LoginActivity extends Activity {
//    ProgressDialog progressDialog = new ProgressDialog(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //all API initialization should be done in some function
        initializeParseFacebook();
    }

    private void initializeParseFacebook() {
        Parse.initialize(this, "c8IKIZkRcbkiMkDqdxkM4fKrBymrX7p7glVQ6u8d", "EFY5RxFnVEKzNOMKGKa3JqLR6zJlS4P6z0OPF3Mt");
        ParseFacebookUtils.initialize(Integer.toString(R.string.app_id));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onLoginButtonClick(View view) {
//        LoginActivity.this.progressDialog = ProgressDialog.show(
//                LoginActivity.this, "", "Logging in...", true);
        List<String> permissions = Arrays.asList("basic_info", "user_about_me", "user_location");
        ParseFacebookUtils.logIn(permissions, this, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
//                LoginActivity.this.progressDialog.dismiss();
                if (user == null) {
                    Log.d("DEBUG", "Uh oh. The user cancelled the Facebook login.");
                } else if (user.isNew()) {
                    Log.d("DEBUG", "User signed up and logged in through Facebook!");
//                    showUserDetailsActivity();
                } else {
                    Log.d("DEBUG", "User logged in through Facebook!");
//                    showUserDetailsActivity();
                }
            }
        });
    }
}
