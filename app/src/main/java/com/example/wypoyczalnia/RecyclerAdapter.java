package com.example.wypoyczalnia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Bike> BikesArray;
    private RecyclerViewClickListener listener;

    public RecyclerAdapter(ArrayList<Bike> BikesArray, RecyclerViewClickListener listener){
        this.BikesArray = BikesArray;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView BikeID;
        private TextView BikeStatus;

        public MyViewHolder(final View view){
            super(view);
            BikeID = view.findViewById(R.id.bikeID);
            BikeStatus = view.findViewById(R.id.bikestatus);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.station_list_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        int number = BikesArray.get(position).getBikeID();
        boolean status = BikesArray.get(position).getAvailable();
        holder.BikeID.setText(String.valueOf(number));
        if(status){
            holder.BikeStatus.setText("dostepny");
        }
        else
            holder.BikeStatus.setText("niedostepny");
    }

    @Override
    public int getItemCount() {
        return BikesArray.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View V, int position);

    }
}
