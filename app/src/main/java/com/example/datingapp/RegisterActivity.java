package com.example.datingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.datingapp.ServerConnector.ServerConnector;
import com.example.datingapp.Utils.SHA256;

public class RegisterActivity extends AppCompatActivity {

    AppCompatButton btnRegister;
    EditText etEmail, etName, etPassword, etRetypePassword, etPhoneNumber;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.button_register);
        etEmail = findViewById(R.id.edit_text_email);
        etName = findViewById(R.id.edit_text_name);
        etPassword = findViewById(R.id.edit_text_password);
        etRetypePassword = findViewById(R.id.edit_text_retype_password);
        etPhoneNumber = findViewById(R.id.edit_text_phone);
        progressBar = findViewById(R.id.register_progress_bar);

        btnRegister.setOnClickListener(view -> onRegisterClicked());
    }

    public void onRegisterClicked() {
        progressBar.setVisibility(View.VISIBLE);

        String email = String.valueOf(etEmail.getText());
        if (email.equals("")) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_LONG).show();
            return;
        }
        int at = 0, dot = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                at++;
            } else if (email.charAt(i) == '.') {
                dot++;
            }
        }
        if ((at != 1) || (dot != 1)) {
            Toast.makeText(this, "Email format is not valid", Toast.LENGTH_LONG).show();
            return;
        }

        String name = String.valueOf(etName.getText());

        String password = String.valueOf(etPassword.getText());
        if (password.length() < 8) {
            Toast.makeText(this, "Password is too short, at least 8 characters", Toast.LENGTH_LONG).show();
            return;
        }
        int number = 0, normalCharacter = 0, capitalCharacter = 0, specialCharacter = 0;
        for (int i = 0; i < password.length(); i++) {
            if ((password.charAt(i) >= 'a') && (password.charAt(i) <= 'z')) {
                normalCharacter++;
            } else if ((password.charAt(i) >= 'A') && (password.charAt(i) <= 'Z')) {
                capitalCharacter++;
            } else if ((password.charAt(i) >= '0') && (password.charAt(i) <= '9')) {
                number++;
            } else {
                specialCharacter++;
            }
        }

        if (number == 0) {
            Toast.makeText(this, "Password must contain a number", Toast.LENGTH_LONG).show();
            return;
        }
        if (normalCharacter == 0) {
            Toast.makeText(this, "Password must contain a normal letter", Toast.LENGTH_LONG).show();
            return;
        }
        if (capitalCharacter == 0) {
            Toast.makeText(this, "Password must contain a capital letter", Toast.LENGTH_LONG).show();
            return;
        }
        if (specialCharacter == 0) {
            Toast.makeText(this, "Password must contain a special character", Toast.LENGTH_LONG).show();
            return;
        }

        String passwordRetype = String.valueOf(etRetypePassword.getText());
        if (!passwordRetype.equals(password)) {
            Toast.makeText(this, "Two password must be the same", Toast.LENGTH_LONG).show();
            return;
        }

        String hashedPassword = SHA256.sha256(password);

        String phoneNumber = String.valueOf(etPhoneNumber.getText());
        String formalizedPhoneNumber = "+84";
        if (phoneNumber.charAt(0) == '0') {
            for (int i = 1; i < phoneNumber.length(); i++) {
                formalizedPhoneNumber += phoneNumber.charAt(i);
            }
        } else if ((phoneNumber.charAt(0) == '8') && (phoneNumber.charAt(1)) == '4') {
            for (int i = 2; i < phoneNumber.length(); i++) {
                formalizedPhoneNumber += phoneNumber.charAt(i);
            }
        } else {
            for (int i = 0; i < phoneNumber.length(); i++) {
                formalizedPhoneNumber += phoneNumber.charAt(i);
            }
        }

        String registerResult = ServerConnector.Register(email, name, hashedPassword, formalizedPhoneNumber);
        progressBar.setVisibility(View.INVISIBLE);
        if (registerResult == null) {
            Toast.makeText(this, "There is an unknown error while registering. Please try again!", Toast.LENGTH_LONG).show();
        } else if (registerResult.equals("Missing fields")) {
            Toast.makeText(this, "All fields are not allowed to be empty", Toast.LENGTH_LONG).show();
        } else if (registerResult.equals("Email already exists")) {
            Toast.makeText(this, "This email is already been used", Toast.LENGTH_LONG).show();
        } else if (registerResult.equals("Phone number already exists")) {
            Toast.makeText(this, "This phone number is already been used", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Register successfully", Toast.LENGTH_LONG).show();
        }
    }
}