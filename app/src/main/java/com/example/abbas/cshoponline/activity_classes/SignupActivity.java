package com.example.abbas.cshoponline.activity_classes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abbas.cshoponline.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;
    private ProgressDialog progressDialog;
    TextView loginTxt;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextEmail =  findViewById(R.id.editTextEmail);
        editTextPassword =  findViewById(R.id.editTextPassword);

        buttonSignup = findViewById(R.id.buttonSignup);
        loginTxt=findViewById(R.id.loginText);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        buttonSignup.setOnClickListener(this);
        loginTxt.setOnClickListener(this);
    }
    private void registerUser(){
        //getting email and password from edit texts
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        mAuth.createUserWithEmailAndPassword("user email here", "user password here")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(SignupActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                            progressDialog.hide();

                        }else{
                            //display some message here
                            Toast.makeText(SignupActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonSignup) {
            registerUser();

        }
        if(view.getId()==R.id.loginText){
            Intent loginIntent=new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }
}
