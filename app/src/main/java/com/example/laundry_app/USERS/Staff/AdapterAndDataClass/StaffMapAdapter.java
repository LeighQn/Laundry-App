package com.example.laundry_app.USERS.Staff.AdapterAndDataClass;

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
import com.example.laundry_app.USERS.Staff.MapActivity;

import java.util.ArrayList;

public class StaffMapAdapter extends RecyclerView.Adapter<StaffMapAdapter.StaffMapViewHolder>{

    Context context;
    ArrayList<StaffMap> mapArrayList;


    public StaffMapAdapter(Context context, ArrayList<StaffMap> mapArrayList) {

        this.context = context;
        this.mapArrayList = mapArrayList;

    }

    @NonNull
    @Override
    public StaffMapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.map_item, parent, false);

        return new StaffMapViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffMapAdapter.StaffMapViewHolder holder, int position) {

        StaffMap staffMap = mapArrayList.get(position);
        holder.txtMapDate.setText(staffMap.mapDate);
        holder.txtMapCustomerName.setText(staffMap.mapCustomerName);
        holder.txtMapTotal.setText(staffMap.mapTotal);
        //holder.txtDescription.setText(status.statusDescription);

        holder.rlStaffMapItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // initialize intent and set intent value
                Intent intent = new Intent(context, MapActivity.class);
                // create bundle for easier call on intent.putExtras(b); --> see below
                Bundle b = new Bundle();
                // b.putString or b.putInt or whatever blat you want, just change the key and value
                b.putString("reservationStatusId", staffMap.mapDate);
                // butngag sulod ang intent nimo
                intent.putExtras(b);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mapArrayList.size();
    }

    public static class StaffMapViewHolder extends RecyclerView.ViewHolder{

        // Change or Add more variable that you want to display in recyclerview
        // Make sure that the data must be present in the DB
        // Change RelativeLayout rlCustomerStatusItem to rlStatusAdapter

        RelativeLayout rlStaffMapItem;
        TextView txtMapDate;
        TextView txtMapCustomerName;
        TextView txtMapTotal;

        public StaffMapViewHolder(@NonNull View itemView) {
            super(itemView);
            rlStaffMapItem = itemView.findViewById(R.id.rl_customer_map_item);
            txtMapDate = itemView.findViewById(R.id.txt_date_map);
            txtMapCustomerName = itemView.findViewById(R.id.txt_customer_name_map);
            txtMapTotal = itemView.findViewById(R.id.txt_payables_map);
        }
    }


}
