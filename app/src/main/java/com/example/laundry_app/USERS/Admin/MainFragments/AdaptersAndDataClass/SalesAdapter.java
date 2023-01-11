package com.example.laundry_app.USERS.Admin.MainFragments.AdaptersAndDataClass;

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
import com.example.laundry_app.USERS.Admin.DetailedActivities.DetailedSales;
import com.example.laundry_app.R;


import java.util.ArrayList;
import java.util.List;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.SalesViewHolder>{

    Context context;
    ArrayList<Sales> salesArrayList;


    public SalesAdapter(Context context, List<BookingModel> salesArrayList) {

        this.context = context;
//        this.salesArrayList = salesArrayList;

    }

    @NonNull
    @Override
    public SalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.sales_item, parent, false);

        return new SalesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesViewHolder holder, int position) {

        Sales sales = salesArrayList.get(position);
        holder.txtSalesDate.setText(sales.salesDate);
        holder.txtSalesCustomerName.setText(sales.salesCustomerName);
        holder.txtSalesTotal.setText(sales.salesTotal);
        //holder.txtDescription.setText(status.statusDescription);

        holder.rlAdminStatusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // initialize intent and set intent value
                Intent intent = new Intent(context, DetailedSales.class);
                // create bundle for easier call on intent.putExtras(b); --> see below
                Bundle b = new Bundle();
                // b.putString or b.putInt or whatever blat you want, just change the key and value
                b.putString("reservationStatusId", sales.salesDate);
                // butngag sulod ang intent nimo
                intent.putExtras(b);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {

        return salesArrayList.size();
    }

    public static class SalesViewHolder extends RecyclerView.ViewHolder{

        // Change or Add more variable that you want to display in recyclerview
        // Make sure that the data must be present in the DB
        // Change RelativeLayout rlCustomerStatusItem to rlStatusAdapter

        RelativeLayout rlAdminStatusItem;
        TextView txtSalesDate;
        TextView txtSalesCustomerName;
        TextView txtSalesTotal;

        public SalesViewHolder(@NonNull View itemView) {
            super(itemView);
            rlAdminStatusItem = itemView.findViewById(R.id.rl_customer_sales_item);
            txtSalesDate = itemView.findViewById(R.id.txt_date_admin);
            txtSalesCustomerName = itemView.findViewById(R.id.txt_customer_name_admin);
            txtSalesTotal = itemView.findViewById(R.id.txt_payables_admin);
        }
    }
}
