package com.example.yashladha.android_seller.classes;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yashladha.android_seller.fragments.DisplayFrag;
import com.example.yashladha.android_seller.fragments.OrdersFrag;
import com.example.yashladha.android_seller.fragments.SalesFrag;


/**
 * Created by dell pc on 16-10-2017.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {

        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DisplayFrag();
        } else if(position == 1){
            return new SalesFrag();
        } else {
            return new OrdersFrag();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Products";
        } else if(position == 1){
            return "Sales Analysis";
        } else{
            return "Orders";
        }
    }
}
