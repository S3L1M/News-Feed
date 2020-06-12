package com.example.newsfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(@NonNull Context context) {
        super(context, 0, new ArrayList<News>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        News currentNews = getItem(position);
        TextView headline = (TextView) listItemView.findViewById(R.id.tv_headline);
        TextView author = (TextView) listItemView.findViewById(R.id.tv_author);
        TextView section = (TextView) listItemView.findViewById(R.id.tv_section);
        TextView date = (TextView) listItemView.findViewById(R.id.tv_date);

        headline.setText(currentNews.getHeadline());
        author.setText(currentNews.getAuthor());
        section.setText(currentNews.getSection());
        date.setText(currentNews.getDate());

        return listItemView;
    }
}
