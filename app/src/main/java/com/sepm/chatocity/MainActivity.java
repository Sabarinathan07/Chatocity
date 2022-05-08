package com.sepm.chatocity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editTextEmail,editTextPassword;
    private Button buttonSignin,buttonSignup;
    private TextView textViewForget;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null){
            Intent intent = new Intent(MainActivity.this,Navigation.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSignin = findViewById(R.id.loginButton);
        buttonSignup = findViewById(R.id.signUpButton);
        textViewForget = findViewById(R.id.forgotPassword);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        auth = FirebaseAuth.getInstance();

        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
//                validateEmail(email);
//                validatePassword(password);

                if (!email.equals("") && !password.equals(""))
                {
                    signin(email,password);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please enter an email and password.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        buttonSignup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(i);

            }
        });

        textViewForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                if(!email.equals("")){
                    passwordReset(email);
                }

            }
        });



    }

    private Boolean validateEmail(String email) {
        if(email.isEmpty()){
            editTextEmail.setError("Enter your Email address");
            return false;
        }else{
            return true;
        }

    }

    private Boolean validatePassword(String password) {
        if(password.isEmpty()){
            editTextPassword.setError("Enter your Password");
            return false;
        }else{
            return true;
        }

    }

    public void signin(String email,String password)
    {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    Intent intent = new Intent(MainActivity.this,Navigation.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Sign in is successful.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Sign in is not successful.", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void passwordReset(String email){
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Password reset email sent! Please check your inbox.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "There is a problem! Please try again later", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
