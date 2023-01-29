package com.example.datingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.datingapp.ServerConnector.ServerConnector;
import com.example.datingapp.Utils.SHA256;

public class EnterResetCodeActivity extends AppCompatActivity {

    public final int TIME_TO_LIVE_FOR_RECOVERY_CODE = 15;

    String email;
    String result;

    EditText etCodeToReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_reset_code);

        etCodeToReset = findViewById(R.id.edit_text_code_to_reset);

        email = getIntent().getExtras().getString("EMAIL");

        String hashedEmail = SHA256.sha256(email);

        result = ServerConnector.ResetPassword(hashedEmail);



        long time = System.currentTimeMillis();
        while(true) {
            long t = System.currentTimeMillis();



            if (t - time > 60000) {
                break;
            }
        }
    }

    public void onResetPasswordClickedWithCode(View view) {
        if ((result.equals("Missing fields")) || (result.equals("User does not exist"))) {
            return;
        }

        long time = System.currentTimeMillis();
        while (true) {
            long t = System.currentTimeMillis();

            String code = String.valueOf(etCodeToReset.getText());

            if (!code.equals("")) {
                String sha256Code = SHA256.sha256(code);

                if (sha256Code.equals(result)) {

                } else {
                    break;
                }
            }

            if (t - time > TIME_TO_LIVE_FOR_RECOVERY_CODE * 60000) {
                break;
            }
        }
    }
}