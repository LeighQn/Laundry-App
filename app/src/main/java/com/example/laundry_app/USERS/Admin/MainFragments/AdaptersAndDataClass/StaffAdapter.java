package com.example.laundry_app.USERS.Admin.MainFragments.AdaptersAndDataClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.API.MODELCLASS.User;
import com.example.laundry_app.R;

import java.util.ArrayList;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewHolder> {

    Context context;
    ArrayList<User> staffArrayList;


    public StaffAdapter(Context context, ArrayList<User> staffArrayList) {
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
        User staff = staffArrayList.get(position);
        holder.txtStaffName.setText(staff.getName());
        holder.txtStaffPosition.setText(staff.getRole() == 1 ? "Admin": "Delivery");
    }

    @Override
    public int getItemCount() {
        return staffArrayList.size();
    }


    public static class StaffViewHolder extends RecyclerView.ViewHolder {


        // Change or Add more variable that you want to display in recyclerview
        // Make sure that the data must be present in the DB
        // Change RelativeLayout rlCustomerStatusItem to rlStatusAdapter

        TextView txtStaffName;
        TextView txtStaffPosition;

        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStaffName = itemView.findViewById(R.id.txt_staff_name);
            txtStaffPosition = itemView.findViewById(R.id.txt_staff_position);
        }
    }
}
