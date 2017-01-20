package com.csoft.wing.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.csoft.wing.fragment.ContactsFragment;
import com.csoft.wing.fragment.FragmentTab;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int index){
        switch (index){
            case 0:
                return new ContactsFragment();
            case 1:
                return new FragmentTab();
            case 2:
                return new FragmentTab();
        }
        return null;
    }

    @Override
    public int getCount(){
        return 3;
    }
}
