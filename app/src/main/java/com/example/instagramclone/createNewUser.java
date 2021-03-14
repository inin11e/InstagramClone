package com.example.instagramclone;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


import androidx.appcompat.app.AppCompatActivity;

public class createNewUser extends AppCompatActivity {

    public static final String TAG = "createNewUser";

    private EditText eNewUser;
    private EditText eNewPassword;
    private Button btnnewCreateUserPswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_user);

        eNewUser = findViewById(R.id.editTextTextPersonName);
        eNewPassword = findViewById(R.id.editTextTextPassword);
        btnnewCreateUserPswd = findViewById(R.id.btnCreateNewUser);

       btnnewCreateUserPswd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               //Toast.makeText(createNewUser.this,"Clicked on create button",Toast.LENGTH_SHORT).show();
               String name = eNewUser.getText().toString();
               String password = eNewPassword.getText().toString();

               Log.i(TAG, name);
               Log.i(TAG, password);
               Toast.makeText(createNewUser.this, "Creating login", Toast.LENGTH_SHORT).show();
               //Toast.makeText(createNewUser.this, name + " " + password,Toast.LENGTH_SHORT).show();
               ParseUser user = new ParseUser();
               // Set the user's username and password, which can be obtained by a forms
               user.setUsername( name);
               user.setPassword( password);
               user.signUpInBackground(new SignUpCallback() {
                   @Override
                   public void done(ParseException e) {
                       if (e == null) {
                           showAlert("Successful Sign Up!", "Welcome" + "<Your username here>" +"!");
                       } else {
                           ParseUser.logOut();
                           Toast.makeText(createNewUser.this, e.getMessage(), Toast.LENGTH_LONG).show();
                       }
                   }
               });
           }
       });

    }

    private void showAlert(String s, String s1) {
        AlertDialog.Builder builder = new AlertDialog.Builder(createNewUser.this)
                .setTitle("success")
                .setMessage("Congratulations You have created a new Account")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        // don't forget to change the line below with the names of your Activities
                        Intent intent = new Intent(createNewUser.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
}
