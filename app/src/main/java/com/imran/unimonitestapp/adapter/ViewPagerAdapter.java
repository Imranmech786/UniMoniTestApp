package com.imran.unimonitestapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.imran.unimonitestapp.R;
import com.imran.unimonitestapp.fragment.MemberPlanFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String[] titleArray;
    private Context context;

    public ViewPagerAdapter(Context context,FragmentManager fm, String[] titleArray) {
        super(fm);
        this.titleArray = titleArray;
        this.context=context;
    }

    @Override
    public Fragment getItem(int index) {
        MemberPlanFragment memberPlanFragment;
        Bundle bundle;
        switch (index) {
            case 0:
                memberPlanFragment = new MemberPlanFragment();
                bundle = new Bundle();
                bundle.putInt(context.getString(R.string.member_count), 2);
                memberPlanFragment.setArguments(bundle);
                return memberPlanFragment;
            default:
                memberPlanFragment = new MemberPlanFragment();
                bundle = new Bundle();
                bundle.putInt(context.getString(R.string.member_count), 4);
                memberPlanFragment.setArguments(bundle);
                return memberPlanFragment;
        }
    }

    @Override
    public int getCount() {
        return titleArray.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleArray[position];
    }
}
