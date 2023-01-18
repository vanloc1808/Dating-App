package com.example.datingapp.ServerConnector.AsyncTasks;

import static com.example.datingapp.ServerConnector.ServerConnector.API_PATH;
import static com.example.datingapp.ServerConnector.ServerConnector.HOST_NAME;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RegisterTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... registerInformation) {
        String apiURL = HOST_NAME + API_PATH + "register";

        try {
            URL url = new URL(apiURL);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);

            // Set the content type of the request
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            String requestBody = "{\n" +
                    "\"email\": \"" + registerInformation[0] + "\",\n" +
                    "\"name\": \"" + registerInformation[1] + "\",\n" +
                    "\"password\": \"" + registerInformation[2] + "\",\n" +
                    "\"phone_number\": \"" + registerInformation[3] + "\"\n" +
                    "}";

            Log.i("REQUEST BODY", requestBody);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(requestBody.getBytes());
            outputStream.flush();
            outputStream.close();

            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HTTP_CREATED) {
                // get the response body
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                return response.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
