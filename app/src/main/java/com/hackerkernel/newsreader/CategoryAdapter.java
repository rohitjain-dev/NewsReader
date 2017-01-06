package com.hackerkernel.newsreader;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Rohit Jain on 12/26/2016.
 */

public class CategoryAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public CategoryAdapter(Context context, FragmentManager fm){
        super(fm);
        mContext =context;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new techcrunch();
            case 1:
                return new bbc();
            case 2:
                return new thehindu();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Techcrunch";
        }else if(position == 1){
            return "BBC";
        }
        else {
            return "The Hindu";
        }
    }
}
