package com.example.laundry_app.Admin.MainFragments.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewHolder> {

    Context context;
    ArrayList<Staff> staffArrayList;


    public StaffAdapter(Context context, ArrayList<Staff> staffArrayList) {
        this.context = context;
        this.staffArrayList = staffArrayList;
    }

    @NonNull
    @Override
    public StaffAdapter.StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.staff_item, parent, false);

        return new StaffViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffAdapter.StaffViewHolder holder, int position) {
        Staff staff = staffArrayList.get(position);
        holder.txtTitle.setText(staff.staffTitle);
        holder.txtDescription.setVisibility(View.INVISIBLE);
        //holder.txtDescription.setText(status.statusDescription);
    }

    @Override
    public int getItemCount() {
        return staffArrayList.size();
    }


    public static class StaffViewHolder extends RecyclerView.ViewHolder {


        // Change or Add more variable that you want to display in recyclerview
        // Make sure that the data must be present in the DB
        // Change RelativeLayout rlCustomerStatusItem to rlStatusAdapter

        TextView txtTitle;
        TextView txtDescription;

        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
        }
    }
}
