package com.hackerkernel.newsreader;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rohit Jain on 12/26/2016.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context) {
        super(context, 0,new ArrayList<News>());
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item,parent,false);
        }
        News currentNews = getItem(position);
        TextView title = (TextView) listItemView.findViewById(R.id.news_title);
        title.setText(currentNews.getmTitle());
        TextView description = (TextView) listItemView.findViewById(R.id.news_description);
        description.setText(currentNews.getmDescription());
        TextView author = (TextView) listItemView.findViewById(R.id.author);
        author.setText(currentNews.getmAuthor());
        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(currentNews.getmDate());
        return listItemView;
    }
}
