package com.example.abbas.cshoponline.activity_classes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abbas.cshoponline.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private FirebaseAuth mAuth;
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignin);
        textViewSignup  = (TextView) findViewById(R.id.textViewSignUp);

        progressDialog = new ProgressDialog(this);

        //attaching click listener
        buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Signing in Please Wait...");
        progressDialog.show();
        //logging in the user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //progressDialog.dismiss();
                        //if the task is successfull
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){
            userLogin();
        }

        if(view == textViewSignup){
            finish();
            startActivity(new Intent(this, SignupActivity.class));
        }
    }

    }


   /* public void goToRegister(View view) {
        Intent regIntent=new Intent(this,SignupActivity.class);
        startActivity(regIntent);
    }*/
