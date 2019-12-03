package com.example.travelshare.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelshare.R;

import java.util.List;

public class RVAdapterRequests extends RecyclerView.Adapter<RVAdapterRequests.RequestViewHolder> {

    List<TripRequest> myReqData;
    private Context context;

    public RVAdapterRequests(List<TripRequest> mreqdata, Context mcontext) {
        this.myReqData = mreqdata;
        this.context = mcontext;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_contacts, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        final TripRequest requestData= myReqData.get(position);
        holder.PName.setText(requestData.getName());
        holder.PNumber.setText(requestData.getTripId());
        holder.PPath.setText(requestData.getStatus());
    }

    @Override
    public int getItemCount() {
        return myReqData.size();
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        public TextView PName;
        public TextView PNumber;
        public TextView PPath;
        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);

            PName= itemView.findViewById(R.id.Pname);
            PNumber=itemView.findViewById(R.id.pnum);
            PPath=itemView.findViewById(R.id.Ppath);
        }
    }
}
