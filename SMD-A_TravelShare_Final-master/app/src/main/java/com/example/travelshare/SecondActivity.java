package com.example.travelshare;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.travelshare.Classes.User;
import com.google.android.material.tabs.TabLayout;


public class SecondActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        user = (User) getIntent().getSerializableExtra("User");

        init();
    }

    private void init() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("TravelShare");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Send"));
        tabLayout.addTab(tabLayout.newTab().setText("Carry"));
        tabLayout.addTab(tabLayout.newTab().setText("Requests"));

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_flight_takeoff_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_shopping_cart_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_contacts_black_24dp);
        final ViewPager viewPager =(ViewPager)findViewById(R.id.view_pager);

        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabsAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}

