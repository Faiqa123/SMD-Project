package com.example.travelshare.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.travelshare.AddCarryTrip;
import com.example.travelshare.Classes.RVAdapter;
import com.example.travelshare.Classes.RVAdapterCarry;
import com.example.travelshare.Classes.RetrofitClient;
import com.example.travelshare.Classes.Trip;
import com.example.travelshare.Classes.TripsData;
import com.example.travelshare.R;
import com.example.travelshare.RegisterActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarryFragment extends Fragment {
    Button plus;
    Context context;
    RVAdapterCarry myAdapter;
    RecyclerView CarryRV;
    String user_id;

    public CarryFragment()
    {
        //empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_fragment2,container,false);
        SharedPreferences shared = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = (shared.getString("id", ""));
        //showMessage(user_id);

        CarryRV = view.findViewById(R.id.myRV2);

        LoadMyTrips();

        plus =  view.findViewById(R.id.plus_button);
        plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), AddCarryTrip.class));
            }
        });
        return view;
    }

    public void LoadMyTrips() {
        Call<List<Trip>> resp = RetrofitClient.getClient().getMyTrips(user_id);
        resp.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                CarryRV.setLayoutManager(new LinearLayoutManager(context));
                myAdapter = new RVAdapterCarry(response.body(), getContext());
                CarryRV.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                showMessage("Failure");
            }
        });


//        final Call<List<Trip>> resp = RetrofitClient.getClient().getTrips();
//        resp.enqueue(new Callback<List<Trip>>() {
//            @Override
//            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
//
//                CarryRV.setLayoutManager(new LinearLayoutManager(context));
//                myAdapter = new RVAdapterCarry(response.body(), getContext());
//                CarryRV.setAdapter(myAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Trip>> call, Throwable t) {
//                showMessage("failure");
//            }
//        });
    }

    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
