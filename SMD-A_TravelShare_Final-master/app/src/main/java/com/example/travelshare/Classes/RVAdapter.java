package com.example.travelshare.Classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelshare.R;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

//    private List<TripsData> myTTrips;
    private List<Trip> myTTrips;

    private Context context;
    private Button SendReq;

    public RVAdapter(List<Trip> mtrips,Context context1)
    {
        this.myTTrips=mtrips;
        this.context=context1;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_send, parent, false);
        SendReq= view.findViewById(R.id.sendbtn);
        SendReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "asddfasdf", Toast.LENGTH_LONG).show();
                // Store data in requests tab of this carry request's maker.
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
          final Trip tripsdata= myTTrips.get(position);
          holder.Name.setText(tripsdata.getDateTime());
          holder.Source_DEST.setText(tripsdata.getSourceLocId() +"->" + tripsdata.getDestinationLocId());
          holder.CDate.setText(tripsdata.getUserId());
          holder.Date.setText("");
          holder.Time.setText("");
          holder.btnSend.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  SharedPreferences shared = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                  String user_id = (shared.getString("id", ""));

                  JsonObject obj = new JsonObject();
                  obj.addProperty("user_id", user_id);
                  obj.addProperty("trip_id", tripsdata.getId());
                  obj.addProperty("status", "pending");


                Call<Response> resp = RetrofitClient.getClient().sendRequest(obj);
                resp.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Toast.makeText(context, "success", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(context, "failure", Toast.LENGTH_LONG).show();

                    }
                });
              }
          });
    }

    @Override
    public int getItemCount() {
        return myTTrips.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView Source_DEST;
        public TextView Name;
        public TextView Date;
        public TextView Time;
        public Button CDate;
        public Button btnSend;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Source_DEST=itemView.findViewById(R.id.Source_dest);
            Name= itemView.findViewById(R.id.Name);
            Date=itemView.findViewById(R.id.datee);
            Time=itemView.findViewById(R.id.timee);
            CDate=itemView.findViewById(R.id.Cdate);

            btnSend = itemView.findViewById(R.id.sendbtn);
            //btnSend.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


//            Call<List<TripRequest>> respo = RetrofitClient.getClient().getTripRequests();
            Toast.makeText(v.getContext(), Integer.toString(v.getId()), Toast.LENGTH_LONG).show();
        }

    }

}
