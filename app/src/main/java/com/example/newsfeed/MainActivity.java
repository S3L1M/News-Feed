package com.example.newsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<News> news = new ArrayList<News>();
        for(int i=0;i<20;i++) {
            news.add(new News("This is a long headline filled with relatively lengthy text",
                    "Mark Nickolson", "Technology", "25/05/2020"));
        }

        final NewsAdapter adapter = new NewsAdapter(this, news);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = adapter.getItem(position);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://google.com"));
                startActivity(i);
            }
        });
    }
}
