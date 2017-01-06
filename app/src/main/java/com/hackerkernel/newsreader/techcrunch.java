package com.hackerkernel.newsreader;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Rohit Jain on 12/26/2016.
 */

public class techcrunch extends Fragment implements LoaderManager.LoaderCallbacks<List<News>>,SwipeRefreshLayout.OnRefreshListener {

    private NewsAdapter adapter;
    private static int LOADER_ID = 1;
    private TextView mEmptyTextView;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.news_list,container,false);
        ListView listView =(ListView) rootview.findViewById(R.id.list_view);
        mEmptyTextView = (TextView) rootview.findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyTextView);
        adapter = new NewsAdapter(getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = adapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getmUrl());
                Intent i = new Intent(Intent.ACTION_VIEW,newsUri);
                startActivity(i);
            }
        });
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()){
            getLoaderManager().initLoader(LOADER_ID,null,this);
            SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootview.findViewById(R.id.swipe);
            swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        }
        else{
            mEmptyTextView.setText(R.string.no_internet_connection);
            SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootview.findViewById(R.id.swipe);
            swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        }

        return rootview;
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new TechCrunchLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        if (data != null) {
            adapter.setNotifyOnChange(false);
            adapter.clear();
            adapter.setNotifyOnChange(true);
            adapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

    }

    @Override
    public void onRefresh() {
        getLoaderManager().restartLoader(LOADER_ID, null, this);
    }
}
