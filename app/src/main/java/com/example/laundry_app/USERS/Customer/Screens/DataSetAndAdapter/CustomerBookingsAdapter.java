package com.example.laundry_app.USERS.Customer.Screens.DataSetAndAdapter;

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

import com.example.laundry_app.USERS.Customer.Screens.DetailedBookingActivity;
import com.example.laundry_app.R;

import java.util.ArrayList;

public class CustomerBookingsAdapter extends RecyclerView.Adapter<CustomerBookingsAdapter.CustomerBookingViewHolder> {

    Context context;
    ArrayList<CustomerBookings> cBookingsArrayList;

    public CustomerBookingsAdapter(Context context, ArrayList<CustomerBookings> cBookingsArrayList) {

        this.context = context;
        this.cBookingsArrayList = cBookingsArrayList;

    }

    @NonNull
    @Override
    public CustomerBookingsAdapter.CustomerBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.customer_bookings_items, parent, false);

        return new CustomerBookingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerBookingsAdapter.CustomerBookingViewHolder holder, int position) {

        CustomerBookings cBookings = cBookingsArrayList.get(position);
        holder.txtDate.setText(cBookings.cBookingDates);
        holder.txtPayables.setText(cBookings.cBookingPayables);
        holder.txtStatus.setText(cBookings.cBookingStatus);

        holder.rlCustomerBookingsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // initialize intent and set intent value
                Intent intent = new Intent(context, DetailedBookingActivity.class);
                // create bundle for easier call on intent.putExtras(b); --> see below
                Bundle b = new Bundle();
                // b.putString or b.putInt or whatever blat you want, just change the key and value
                b.putString("reservationStatusId", cBookings.cBookingDates);
                // butangan sulod ang intent nimo
                intent.putExtras(b);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cBookingsArrayList.size();
    }

    public class CustomerBookingViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlCustomerBookingsItem;
        TextView txtDate;
        TextView txtPayables;
        TextView txtStatus;


        public CustomerBookingViewHolder(@NonNull View itemView) {
            super(itemView);

            rlCustomerBookingsItem = itemView.findViewById(R.id.rl_customer_booking_item);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtPayables = itemView.findViewById(R.id.txt_payables);
            txtStatus = itemView.findViewById(R.id.txt_status);
        }
    }
}
