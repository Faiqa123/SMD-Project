package com.example.travelshare.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

import com.example.travelshare.AddCarryTrip;
import com.example.travelshare.Classes.RVAdapterRequests;
import com.example.travelshare.Classes.RequestData;
import com.example.travelshare.Classes.RetrofitClient;
import com.example.travelshare.Classes.TripRequest;
import com.example.travelshare.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestsFragment extends Fragment {

    List<RequestData> myReqList=new ArrayList<>();
    Context context;
    RecyclerView ReqRV ;
    RVAdapterRequests myReqAdapter;
    String user_id;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blank_fragment3,container,false);
        ReqRV= v.findViewById(R.id.myRV3);

        SharedPreferences shared = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = (shared.getString("id", ""));



        LoadData();
        return v;
    }

    public void LoadData() {
        Call<List<TripRequest>> TR = RetrofitClient.getClient().getTripRequests(user_id);
        TR.enqueue(new Callback<List<TripRequest>>() {
            @Override
            public void onResponse(Call<List<TripRequest>> call, Response<List<TripRequest>> response) {
                ReqRV.setLayoutManager(new LinearLayoutManager(context));
                myReqAdapter= new RVAdapterRequests(response.body(),context);
                ReqRV.setAdapter(myReqAdapter);
            }

            @Override
            public void onFailure(Call<List<TripRequest>> call, Throwable t) {

            }
        });

    }

    public List<RequestData> FillData()
    {
        RequestData newReq= new RequestData("Harry Potter","Lahore->Peshore","03233234345");
        myReqList.add(newReq);
        return myReqList;
        //Get Data from API and add here. [Will be saved in the requests tab of the person who posted the trip that got clicked on]
    }
}
