package com.example.android.codetribeconnect;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by RP on 2017/09/02.
 */
public  class CategoryAdapter extends FragmentPagerAdapter {
    private Context context;

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return context.getString(R.string.portfolio);


        }else if (position == 1){
            return context.getString(R.string.codeTribes);


        }else{
            return context.getString(R.string.chat);

        }
    }

    public CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new PortfolioFragment();
        }else if (position == 1){
            return  new CodeTribesFragment();
        }else{
            return  new TribeChatFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
