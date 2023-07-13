package com.example.flashapp;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class http extends AsyncTask<Void, Void, String> {

    private TextView textView;

    public http(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String out = "";

        try {
            URL url = new URL("http://10.0.2.2:8081/employees/list");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");

            conn.setRequestMethod("POST");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";

            StringBuilder response = new StringBuilder();

            while((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            System.out.println("Response : " + response.toString());

            out = response.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return out;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        textView.setText(result);
    }
}
