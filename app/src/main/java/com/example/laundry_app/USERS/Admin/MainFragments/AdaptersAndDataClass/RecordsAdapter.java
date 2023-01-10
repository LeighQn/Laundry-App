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


import com.example.laundry_app.API.MODELCLASS.Login;
import com.example.laundry_app.USERS.Admin.UnitActivity;
import com.example.laundry_app.R;

import java.util.ArrayList;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.RecordViewHolder>  {

    Context context;

    ArrayList<Records> recordArrayList;

    public RecordsAdapter(Context context, ArrayList<Records> recordArrayList) {
        this.context = context;
        this.recordArrayList = recordArrayList;

    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.record_item, parent, false);

        return new RecordViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        Records records = recordArrayList.get(position);
        holder.txtMaterial.setText(records.material);
        holder.txtWeight.setText(records.materialWeight);
        holder.txtAvailability.setText(records.materialAvailability);

        holder.rlAdminRecordItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // initialize intent and set intent value
                Intent intent = new Intent(context, UnitActivity.class);
                // create bundle for easier call on intent.putExtras(b); --> see below
                Bundle b = new Bundle();
                // b.putString or b.putInt or whatever blat you want, just change the key and value
                b.putString("reservationStatusId", records.material);
                // butngag sulod ang intent nimo
                intent.putExtras(b);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {

        return recordArrayList.size();
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder{


        TextView txtMaterial;
        TextView txtWeight;
        TextView txtAvailability;
        RelativeLayout rlAdminRecordItem;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            rlAdminRecordItem = itemView.findViewById(R.id.rl_customer_record_item);
            txtMaterial = itemView.findViewById(R.id.txt_material_record);
            txtWeight = itemView.findViewById(R.id.txt_weight_record);
            txtAvailability = itemView.findViewById(R.id.txt_availability_record);
        }
    }

}
