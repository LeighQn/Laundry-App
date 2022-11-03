package com.example.laundry_app.Admin.MainFragments.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.R;


import java.util.ArrayList;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.SalesViewHolder>{

    Context context;
    ArrayList<Sales> salesArrayList;


    public SalesAdapter(Context context, ArrayList<Sales> salesArrayList) {

        this.context = context;
        this.salesArrayList = salesArrayList;

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
        holder.txtTitle.setText(sales.salesTitle);
        holder.txtDescription.setVisibility(View.INVISIBLE);
        //holder.txtDescription.setText(status.statusDescription);
    }

    @Override
    public int getItemCount() {

        return salesArrayList.size();
    }

    public static class SalesViewHolder extends RecyclerView.ViewHolder{

        // Change or Add more variable that you want to display in recyclerview
        // Make sure that the data must be present in the DB
        // Change RelativeLayout rlCustomerStatusItem to rlStatusAdapter

        TextView txtTitle;
        TextView txtDescription;

        public SalesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
        }
    }
}
