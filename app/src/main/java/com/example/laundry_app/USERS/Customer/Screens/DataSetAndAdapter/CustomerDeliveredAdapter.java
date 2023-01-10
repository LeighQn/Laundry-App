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

import com.example.laundry_app.USERS.Customer.Screens.DetailedDeliveredActivity;
import com.example.laundry_app.R;

import java.util.ArrayList;

public class CustomerDeliveredAdapter extends RecyclerView.Adapter<CustomerDeliveredAdapter.CustomerDeliveredViewHolder> {

    Context context;
    ArrayList<CustomerDelivered> cDeliveredArrayList;

    public CustomerDeliveredAdapter(Context context, ArrayList<CustomerDelivered> cDeliveredArrayList) {

        this.context = context;
        this.cDeliveredArrayList = cDeliveredArrayList;

    }

    @NonNull
    @Override
    public CustomerDeliveredAdapter.CustomerDeliveredViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.customer_delivered_items, parent, false);

        return new CustomerDeliveredAdapter.CustomerDeliveredViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerDeliveredAdapter.CustomerDeliveredViewHolder holder, int position) {

        CustomerDelivered cDelivered = cDeliveredArrayList.get(position);
        holder.txtDateDelivered.setText(cDelivered.cDeliveredDates);
        holder.txtPayablesDelivered.setText(cDelivered.cDeliveredPayables);
        holder.txtStatusDelivered.setText(cDelivered.cDeliveredStatus);

        holder.rlCustomerDeliveredItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // initialize intent and set intent value
                Intent intent = new Intent(context, DetailedDeliveredActivity.class);
                // create bundle for easier call on intent.putExtras(b); --> see below
                Bundle b = new Bundle();
                // b.putString or b.putInt or whatever blat you want, just change the key and value
                b.putString("reservationStatusId", cDelivered.cDeliveredDates);
                // butangan sulod ang intent nimo
                intent.putExtras(b);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return cDeliveredArrayList.size();
    }

    public class CustomerDeliveredViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rlCustomerDeliveredItem;
        TextView txtDateDelivered;
        TextView txtPayablesDelivered;
        TextView txtStatusDelivered;

        public CustomerDeliveredViewHolder(@NonNull View itemView) {
            super(itemView);

            rlCustomerDeliveredItem = itemView.findViewById(R.id.rl_customer_delivered_item);
            txtDateDelivered = itemView.findViewById(R.id.txt_date_delivered);
            txtPayablesDelivered = itemView.findViewById(R.id.txt_payables_delivered);
            txtStatusDelivered = itemView.findViewById(R.id.txt_status_delivered);
        }
    }
}
