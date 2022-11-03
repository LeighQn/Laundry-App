package com.example.laundry_app.Customer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.R;

import java.util.ArrayList;

public class CustomerAdapter extends  RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>{

    Context context;
    ArrayList<Status> statusArrayList;


    public CustomerAdapter(Context context, ArrayList<Status> statusArrayList) {

        this.context = context;
        this.statusArrayList = statusArrayList;

    }
    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.status_item, parent, false);

        return new CustomerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {

        Status status = statusArrayList.get(position);
        holder.txtDescription.setVisibility(View.INVISIBLE);
        holder.txtTitle.setText(status.statusTitle);
        //holder.txtDescription.setText(status.statusDescription);

        holder.rlCustomerStatusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // initialize intent and set intent value
                Intent intent = new Intent(context, DetailedReservationStatus.class);
                // create bundle for easier call on intent.putExtras(b); --> see below
                Bundle b = new Bundle();
                // b.putString or b.putInt or whatever blat you want, just change the key and value
                b.putString("reservationStatusId", status.statusTitle);
                // butngag sulod ang intent nimo
                intent.putExtras(b);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return statusArrayList.size();
    }

    public static class CustomerViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout rlCustomerStatusItem;
        TextView txtTitle;
        TextView txtDescription;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            rlCustomerStatusItem = itemView.findViewById(R.id.rl_customer_status_item);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
        }
    }
}
