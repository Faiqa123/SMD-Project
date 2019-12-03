package com.example.travelshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.travelshare.Classes.Location;
import com.example.travelshare.Classes.Response;
import com.example.travelshare.Classes.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AddCarryTrip extends AppCompatActivity {
    private Spinner source_loc, destination_loc;
    private Button btn_save;
    private TextInputLayout input_date;
    private TextInputLayout input_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_carry_trip);

        source_loc = findViewById(R.id.source_loc);
        destination_loc = findViewById(R.id.destination_loc);
        btn_save = findViewById(R.id.add_trip);
        input_date = findViewById(R.id.textInput_date);
        input_time = findViewById(R.id.textInput_time);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String source = source_loc.getSelectedItem().toString();
                String destination = destination_loc.getSelectedItem().toString();
                String date = input_date.getEditText().getText().toString();
                String time = input_time.getEditText().getText().toString();

                SharedPreferences sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String id  = sharedPref.getString("id","");

                JsonObject obj = new JsonObject();
                obj.addProperty("source_loc", source);
                obj.addProperty("destination_loc", destination);
                obj.addProperty("date", date);
                obj.addProperty("time", time);
                obj.addProperty("user_id", id);

                //showMessage(source + "-" + destination + "-" + date + "-" + time );

                Call<Response> res =  RetrofitClient.getClient().postTrip(obj);
                res.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if(response.body().getError() != null) {
                            showMessage(response.body().getError());
                        }
                        else {
                            showMessage(response.body().getMessage());
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        showMessage("Network Error. Please try again later.");
                    }
                });

            }
        });

        loadLocations();
    }

    private void loadLocations() {

        Call<List<Location>> locations = RetrofitClient.getClient().getLocations();
//        locations.enqueue(new Callback<List<Location>>() {
//            @Override
//            public void onResponse(Call<List<Location>> call, retrofit2.Response<List<Location>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Location>> call, Throwable t) {
//
//            }
//        });

        locations.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, retrofit2.Response<List<Location>> response) {
                List<String> list = new ArrayList<String>();

                for (Location loc: response.body()
                     ) {
                    list.add(loc.getName());
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddCarryTrip.this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                source_loc.setAdapter(dataAdapter);

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(AddCarryTrip.this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                destination_loc.setAdapter(dataAdapter2);
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                showMessage("Network Error. Please try again later.");
//                showMessage(t.getMessage());
            }
        });





    }

    public void showMessage(String message) {
        Toast.makeText(AddCarryTrip.this, message, Toast.LENGTH_LONG).show();
    }

}
