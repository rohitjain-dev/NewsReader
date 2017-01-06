package com.hackerkernel.newsreader;

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
import java.util.List;

/**
 * Created by Rohit Jain on 12/26/2016.
 */

public final class QueryUtils {
     private static final String LOG_TAG = QueryUtils.class.getSimpleName();
    //Empty Constructor
    public QueryUtils() {
    }


     static URL createUrl(String mStringUrl)
    {
        String stringUrl = mStringUrl;
        try{
            return new URL(stringUrl);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG,"Problem Making Http request",e);
            return null;
        }
    }

    static String makeHttpRequest(URL url) throws IOException{
        String jsonResonse= "";
        if (url==null){
            return jsonResonse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200)
            {
                inputStream = urlConnection.getInputStream();
                jsonResonse = readFromStream(inputStream);
            }
            else {
                Log.e(LOG_TAG,"Error Response code:"+urlConnection.getResponseCode());
            }
        }catch (IOException e){
            Log.e(LOG_TAG,"Problem retriving data",e);
        } finally {
            if (urlConnection!=null){
                urlConnection.disconnect();
            }
            if (inputStream!=null){
                inputStream.close();
            }
        }
        return jsonResonse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
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

     static List<News> extractFeatureFromJson(String newsJson){
        List<News> newses = new ArrayList<>();
        try {
            JSONObject baseJsonResponse = new JSONObject(newsJson);
            JSONArray newsArray =baseJsonResponse.getJSONArray("articles");
            for (int i = 0; i < newsArray.length(); i++) {
                JSONObject currentNews = newsArray.getJSONObject(i);
                String title = currentNews.getString("title");
                String author = currentNews.getString("author");
                String description = currentNews.getString("description");
                String url   = currentNews.getString("url");
                String date = currentNews.getString("publishedAt");
                News news = new News(author,title,description,date,url);
                newses.add(news);
            }
        }catch (JSONException e){
            Log.e("QueryUtils", "Problem parsing the NewsApi JSON results", e);
        }
        return newses;
    }

}
