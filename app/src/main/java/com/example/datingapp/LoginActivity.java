package com.example.datingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.datingapp.ServerConnector.ServerConnector;
import com.example.datingapp.Utils.SHA256;

public class LoginActivity extends AppCompatActivity {

    AppCompatButton btnLogin;
    EditText etEmail, etPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.button_login);
        etEmail = findViewById(R.id.edit_text_email_login);
        etPassword = findViewById(R.id.edit_text_password_login);
        progressBar = findViewById(R.id.login_progress_bar);
        progressBar.setVisibility(View.INVISIBLE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginClicked();
            }
        });
    }

    private void onLoginClicked() {
        progressBar.setVisibility(View.VISIBLE);

        String email = String.valueOf(etEmail.getText());
        if (email.equals("")) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_LONG).show();
            return;
        }

        String hashedEmail = SHA256.sha256(email);

        String password = String.valueOf(etPassword.getText());
        if (password.equals("")) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_LONG).show();
            return;
        }

        String hashedPassword = SHA256.sha256(password);

        String loginResult = ServerConnector.Login(hashedEmail, hashedPassword);
        progressBar.setVisibility(View.INVISIBLE);

        if (loginResult == null) {
            Toast.makeText(this, "There is an unknown error while registering. Please try again!", Toast.LENGTH_LONG).show();
        } else if (loginResult.equals("Missing fields")) {
            Toast.makeText(this, "All fields are not allowed to be empty", Toast.LENGTH_LONG).show();
        } else if (loginResult.equals("User does not exist")) {
            Toast.makeText(this, "This email is not used for any user", Toast.LENGTH_LONG).show();
        } else if (loginResult.equals("Wrong password")) {
            Toast.makeText(this, "Password is wrong", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Login successfully", Toast.LENGTH_LONG).show();
        }

    }
}