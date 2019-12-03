package com.example.travelshare;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.travelshare.Fragments.CarryFragment;
import com.example.travelshare.Fragments.RequestsFragment;
import com.example.travelshare.Fragments.SendFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabsAdapter(FragmentManager fm, int NoofTabs){
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                HomeScreen home = new HomeScreen();
                return home;
            case 1:
                SendFragment send = new SendFragment();
                return send;
            case 2:
                CarryFragment carry = new CarryFragment();
                return carry;
            case 3:
                RequestsFragment requestsFragment = new RequestsFragment();
                return requestsFragment;
            default:
                return null;
        }
    }
}

