package com.example.travelshare.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.travelshare.Classes.RVAdapter;
import com.example.travelshare.Classes.RetrofitClient;
import com.example.travelshare.Classes.Trip;
import com.example.travelshare.Classes.TripsData;
import com.example.travelshare.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendFragment extends Fragment {

    List<Trip> Ttrips = new ArrayList<>();
    RecyclerView recyclerViewDemo;
    RVAdapter myRVAdapter;
    String user_id;

    Button btn_send;


    //List<TripsData> Ttrips= new ArrayList<>();
    public SendFragment() {
        // Required empty public constructor
    }

    public static SendFragment newInstance() {
        SendFragment fragment = new SendFragment();
        Bundle mybundle = new Bundle();
        fragment.setArguments(mybundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPreferences shared = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = (shared.getString("id", ""));



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);



        recyclerViewDemo = view.findViewById(R.id.myRV1);

        LoadData();

        return view;
    }


    public void LoadData() {
        Call<List<Trip>> resp = RetrofitClient.getClient().getOtherTrips(user_id);
        resp.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                //showMessage(response.body().get(0).getUserId());
                //showMessage(Integer.toString( response.body().size()));

                recyclerViewDemo.setLayoutManager(new LinearLayoutManager(getContext()));
                myRVAdapter = new RVAdapter(response.body(), getContext());
                recyclerViewDemo.setAdapter(myRVAdapter);
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                showMessage("failure");
            }
        });
    }

    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
