package com.example.abbas.cshoponline.activity_classes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abbas.cshoponline.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText mailEt, password1Et, password2Et;

    String mail, password1,password2,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        mailEt = findViewById(R.id.emailEt);
        password1Et = findViewById(R.id.pass1Et);
        password2Et = findViewById(R.id.pass2Et);

        mail = mailEt.getText().toString();
        password1 = password1Et.getText().toString();
        /*password2 = password2Et.getText().toString();
        if (password1.equals(password2)){
           password = password1;
        }else {
            password1Et.setError("Password is not matching");
        }*/

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {


    }

    public void singUp(View view) {
        try {
            mAuth.createUserWithEmailAndPassword(mail, password1);
        }catch (Exception e){
            Toast.makeText(this, "Something went wrong!"+e, Toast.LENGTH_LONG).show();
        }


    }
}
