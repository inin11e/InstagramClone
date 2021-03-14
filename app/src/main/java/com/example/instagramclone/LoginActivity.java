package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.parse.LogInCallback;
//import com.parse.ParseException;
//import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    //creating variable for name and password
    //private such only MainActivity can access these variables
    private EditText eName;
    private EditText ePassword;
    private Button eLogin;
    boolean isValid = false;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        if(ParseUser.getCurrentUser() != null) {
            goMainActivity();

        }


        //creating an instance of asynchronious
        //AsyncHttpClient client = new AsyncHttpClient();

        //this will bind variable to elements in LoginActivity
        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);
        signUp = findViewById(R.id.btnSignUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LoginActivity.this,"Signing up now",Toast.LENGTH_SHORT).show();
                //setContentView(R.layout.create_new_user);
                goCreateNewUser();
            }
        });
        //setting up listener for pressing the button login
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //once button is click this will determine what next will happen

                //need to capture what user has entered
                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();
                //Toast.makeText(MainActivity.this,"You click on Login button",Toast.LENGTH_SHORT).show();

                //enter logic to check if user enter both login and password
                if (inputName.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill out both Login and Password", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(LoginActivity.this, inputPassword, Toast.LENGTH_SHORT).show();
                    loginUser(inputName,inputPassword);

                }

            }
        });
    }

    private void goCreateNewUser() {
        Intent ii = new Intent( this,createNewUser.class);
        startActivity(ii);
        finish();
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user" + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with login", e);
                    return;
                }
                //TODO: navigate to the main activitiy if the user has signed in properly
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Success!",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}