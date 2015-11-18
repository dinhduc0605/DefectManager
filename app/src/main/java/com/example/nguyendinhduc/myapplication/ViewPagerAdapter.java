package com.example.nguyendinhduc.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by nguyendinhduc on 11/8/15.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    String[] titles = {"Issue", "Account", "Project"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new IssueFragment();
                break;
            case 1:
                fragment = new AccountFragment();
                break;
            case 2:
                fragment = new ProjectFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
