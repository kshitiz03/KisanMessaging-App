package com.example.lenovo.kisanapp;


import java.util.HashMap;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

//for navigation through two tabs
public class fragment extends FragmentStatePagerAdapter {
    private String tabTitles[] = new String[] { "Contacts", "Messages" };
    private Map<Integer, String> mFragmentTags;
    private FragmentManager mfragmentManager;
    private Context mContext;

    public fragment(FragmentManager fm, Context context)
    {
        super(fm);

        mfragmentManager= fm;
        mFragmentTags = new HashMap<Integer,String>();
        mContext = context;

    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ContactsFragment();
        }
        else {
            return new MessagesFragment();
        }

    }

    @Override
    public int getCount()
    {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
//instantiate item with latest updates
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        if (obj instanceof Fragment) {
            // record the fragment tag here.
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTags.put(position, tag);
        }
        return obj;
    }
//to send to main activity for updating the particular fragment
    public Fragment getFragment(int position) {
        String tag = mFragmentTags.get(position);
        if (tag == null)
            return null;
        return mfragmentManager.findFragmentByTag(tag);
    }
}
