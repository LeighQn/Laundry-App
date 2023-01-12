package com.example.laundry_app.USERS.Customer.MainFragments.AdaptersAndDataClass;

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

import com.example.laundry_app.API.MODELCLASS.BookingModel;
import com.example.laundry_app.USERS.Customer.Screens.DetailedBookingActivity;
import com.example.laundry_app.R;

import java.util.ArrayList;

public class CustomerAdapter extends  RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>{

    Context context;
    ArrayList<BookingModel> statusArrayList;


    public CustomerAdapter(Context context, ArrayList<BookingModel> statusArrayList) {

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

        BookingModel status = statusArrayList.get(position);
        holder.txtDateStatus.setText(status.getDate());
        holder.txtPayableStatus.setText(status.getTotal());
        holder.txtStatusStatus.setText(status.getStatus());
        //holder.txtDescription.setText(status.statusDescription);

        holder.rlCustomerStatusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // initialize intent and set intent value
                Intent intent = new Intent(context, DetailedBookingActivity.class);
                // create bundle for easier call on intent.putExtras(b); --> see below
                Bundle b = new Bundle();
                // b.putString or b.putInt or whatever blat you want, just change the key and value
                b.putString("reservationStatusId", status.getDate());
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
        TextView txtDateStatus;
        TextView txtPayableStatus;
        TextView txtStatusStatus;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            rlCustomerStatusItem = itemView.findViewById(R.id.rl_customer_status_item);
            txtDateStatus = itemView.findViewById(R.id.txt_date_status);
            txtPayableStatus = itemView.findViewById(R.id.txt_payables_status);
            txtStatusStatus = itemView.findViewById(R.id.txt_status_status);
        }
    }
}
