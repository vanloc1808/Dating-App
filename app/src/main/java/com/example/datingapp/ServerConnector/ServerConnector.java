package com.example.datingapp.ServerConnector;

import com.example.datingapp.ServerConnector.AsyncTasks.RegisterTask;

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
}