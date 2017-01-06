package com.hackerkernel.newsreader;

/**
 * Created by Rohit Jain on 12/26/2016.
 */

public class News  {
  private String mAuthor;
  private String mTitle;
  private String mDescription;
  private String mDate;
  private String mUrl;

  public News(String mAuthor, String mTitle, String mDescription, String mDate,String mUrl) {
    this.mAuthor = mAuthor;
    this.mTitle = mTitle;
    this.mDescription = mDescription;
    this.mDate = mDate;
    this.mUrl = mUrl;
  }

  public String getmAuthor() {
    return mAuthor;
  }

  public String getmTitle() {
    return mTitle;
  }

  public String getmDescription() {
    return mDescription;
  }

  public String getmDate() {
    return mDate;
  }

  public String getmUrl() {
    return mUrl;
  }
}
