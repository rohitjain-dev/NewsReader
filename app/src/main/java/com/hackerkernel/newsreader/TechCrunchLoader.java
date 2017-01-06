package com.hackerkernel.newsreader;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

class TechCrunchLoader extends AsyncTaskLoader<List<News>> {

    public TechCrunchLoader(Context context) {
        super(context);
    }
    String createStringUrl() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .encodedAuthority("newsapi.org")
                .appendPath("v1")
                .appendPath("articles")
                .appendQueryParameter("source","techcrunch")
                .appendQueryParameter("apiKey","6fac7bd1b99441ff8b38922f4889b49b");
        String url = builder.build().toString();
        return url;
    }
    private String source = createStringUrl();
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        List<News> listofNews = null;
        try {
            URL url = QueryUtils.createUrl(source);
            String jsonResponse = QueryUtils.makeHttpRequest(url);
            listofNews = QueryUtils.extractFeatureFromJson(jsonResponse);
        }catch (IOException e){
            Log.e("Queryutils", "Error Loader LoadInBackground: ", e);
        }
        return listofNews;
    }
}
