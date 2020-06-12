package com.example.newsfeed;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class NetworkUtils {
    static URL createUrl() throws MalformedURLException {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .encodedAuthority("content.guardianapis.com")
                .appendPath("search")
                .appendQueryParameter("tag", "technology/technology")
                .appendQueryParameter("page-size", "20")
                .appendQueryParameter("order-by", "newest")
                .appendQueryParameter("api-key", "test");
        return new URL(builder.build().toString());
    }

    static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = read(inputStream);
            } else {
                Log.e("mainActivity", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("Queryutils", "Error making HTTP request: ", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    static String read(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    static ArrayList<News> parseJson(String json) throws JSONException {
        ArrayList<News> news = new ArrayList<News>();
        JSONObject jsonResponse = new JSONObject(json).getJSONObject("response");
        JSONArray results = jsonResponse.getJSONArray("results");

        for(int i=0;i<results.length();i++) {
            JSONObject obj = results.getJSONObject(i);
            String headline = obj.getString("webTitle");
            String url = obj.getString("webUrl");
            String date = obj.getString("webPublicationDate");
            String section = obj.getString("sectionName");
            news.add(new News(headline, "", section, date, url));
        }

        return news;
    }
}
