package com.example.newsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

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

        NewsAdapter adapter = new NewsAdapter(this, news);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
}
