package com.example.datingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    AppCompatButton btnResetPassword;
    EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        etEmail = findViewById(R.id.edit_text_email_forgot_password);
    }

    public void onResetPasswordClicked(View view) {
        String email = String.valueOf(etEmail.getText());

        if (email.equals("")) {
            Toast.makeText(this, "Email is not empty", Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(this, EnterResetCodeActivity.class);
        intent.putExtra("EMAIL", email);
        startActivity(intent);
    }
}