package com.example.datingapp.ServerConnector;

import android.util.Log;

import com.example.datingapp.ServerConnector.AsyncTasks.LoginTask;
import com.example.datingapp.ServerConnector.AsyncTasks.RegisterTask;
import com.example.datingapp.ServerConnector.AsyncTasks.ResetPasswordTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ServerConnector {
    public static final String HOST_NAME = "http://10.0.2.2:8000/";
    public static final String API_PATH = "api/";

    public static String Register(String email, String name, String hashedPassword, String phoneNumber) {

        RegisterTask registerTask = new RegisterTask();
        registerTask.execute(email, name, hashedPassword, phoneNumber);

        try {
            String result = registerTask.get();

            if (result == null) {
                return null;
            }

            JSONObject jsonObject = new JSONObject(result);
            String message = jsonObject.getString("message");

            if (message.equals("success")) {
                String stringData = jsonObject.getString("data");

                JSONObject dataJsonObject = new JSONObject(stringData);

                return dataJsonObject.getString("hash_email");
            }

            return message;

        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String Login(String hashedEmail, String hashedPassword) {

        LoginTask loginTask = new LoginTask();
        loginTask.execute(hashedEmail, hashedPassword);

        try {
            String result = loginTask.get();

            if (result == null) {
                //Log.i("RESULT", "NULL");
                return null;
            }

            JSONObject jsonObject = new JSONObject(result);

            return jsonObject.getString("message");

        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String ResetPassword(String hashedEmail) {
        ResetPasswordTask resetPasswordTask = new ResetPasswordTask();
        resetPasswordTask.execute(hashedEmail);

        try {
            String result = resetPasswordTask.get();

            if (result == null) {
                return null;
            }

            JSONObject jsonObject = new JSONObject(result);

            String message = jsonObject.getString("message");

            if (message.equals("successful")) {
                String strData = jsonObject.getString("data");
                JSONObject jsonData = new JSONObject(strData);

                return jsonData.getString("sha256_recovery_key");
            }

            return message;

        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
