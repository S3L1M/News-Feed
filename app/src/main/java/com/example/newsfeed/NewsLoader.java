package com.example.newsfeed;

import android.content.Context;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class NewsLoader extends AsyncTaskLoader<ArrayList<News>> {

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<News> loadInBackground() {
        ArrayList<News> news = null;
        try {
            URL url = NetworkUtils.createUrl();
            String jsonResponse = NetworkUtils.makeHttpRequest(url);
            news = NetworkUtils.parseJson(jsonResponse);
        } catch (IOException | JSONException e) {
            Log.e("NetworkUtils", "Error Loader LoadInBackground: ", e);
        }
        return news;
    }
}
