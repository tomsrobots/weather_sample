package com.thomastaylor.yahooweather;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Gets the weather data from the Yahoo API.
 *
 * Created by tom on 8/11/16.
 */
public class WeatherRetriever extends AsyncTask<String, Void, String> {
    private Activity context;

    public WeatherRetriever(Activity context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url;
        HttpURLConnection urlConnection;
        String result = null;
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection)url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                result = readStream(urlConnection.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String readStream(InputStream stream) {
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(stream));
            String line = "";
            while((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            Log.e("web", e.toString());
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e)  {
                    Log.e("web", e.toString());
                }
            }
        }
        return response.toString();
    }

    public Activity getActivity() {
        return context;
    }
}
