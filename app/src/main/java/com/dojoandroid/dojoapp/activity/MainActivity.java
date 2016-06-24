package com.dojoandroid.dojoapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dojoandroid.dojoapp.R;

import utils.Constants;

/**
 *
 * Created by Victor on 6/01/16.
 */
public class MainActivity extends Activity {

    private EditText mUserInput;
    private EditText mPassInput;
    private String mUserName;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyIsLogged();
    }

    private void loadUI() {
        mUserInput = (EditText) findViewById(R.id.main_user_input);
        mPassInput = (EditText) findViewById(R.id.main_password_input);
        Button loginButton = (Button) findViewById(R.id.main_button_login);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mUserName = mUserInput.getText().toString();
                mPassword = mPassInput.getText().toString();

                if (mUserName != null && mUserName.equals("android")
                        && mPassword != null && mPassword.equals("android123")) {

                    enableLogin();
                    verifyIsLogged();
                    Toast.makeText(MainActivity.this, getString(R.string.welcome), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.wrong_data), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void enableLogin() {
        SharedPreferences.Editor editor = getSharedPreferences(Constants.EXTRA_USERNAME, 0).edit();
        editor.putBoolean(Constants.PREF_LOGGED, true);
        editor.apply();
    }

    private void verifyIsLogged() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.EXTRA_USERNAME, 0);
        if (sharedPreferences.getBoolean(Constants.PREF_LOGGED, false)) {
            Intent intent = new Intent(MainActivity.this, AuthenticatedActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            loadUI();
        }
    }
}
