package com.example.travelshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Set;

public class HomeScreen extends Fragment {
    ImageView ProfileIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
        Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT);
        View v = inflater.inflate(R.layout.home_screen,viewGroup,false);
        ProfileIcon = v.findViewById(R.id.profile_icon);
        ProfileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Setting.class));
            }
        });
        return v;
    }

}
