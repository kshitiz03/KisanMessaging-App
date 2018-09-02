package com.example.lenovo.kisanapp;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

//for navigation through two tabs
public class fragment extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "Contacts", "Messages" };
    public fragment(FragmentManager fm)
    {
        super(fm);
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
}
