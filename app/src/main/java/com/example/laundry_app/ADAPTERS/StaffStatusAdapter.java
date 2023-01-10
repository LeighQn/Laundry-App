package com.example.laundry_app.ADAPTERS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.API.MODELCLASS.Staff.StaffStatus;
import com.example.laundry_app.R;

import java.util.List;

public class StaffStatusAdapter extends RecyclerView.Adapter<StaffStatusAdapter.StaffStatusVH> {

    //<StaffStatus> is the model class
    private List<StaffStatus> staffStatusList;
    private Context context;

    public StaffStatusAdapter() {
    }

    public void setStaffStatusData(List<StaffStatus> staffStatusList) {
        this.staffStatusList = staffStatusList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StaffStatusAdapter.StaffStatusVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        return new StaffStatusAdapter.StaffStatusVH(LayoutInflater.from(context).inflate(R.layout.staff_status_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StaffStatusAdapter.StaffStatusVH holder, int position) {

        StaffStatus staffStatus = staffStatusList.get(position);

        String date = staffStatus.getDate();
        String customerName = staffStatus.getCustomerName();
        int total = staffStatus.getTotal();

        holder.date.setText(date);
        holder.customerName.setText(customerName);
    }

    @Override
    public int getItemCount() {
        return staffStatusList.size();
    }

    public class StaffStatusVH extends RecyclerView.ViewHolder {

        TextView date, customerName, total;

        public StaffStatusVH(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.rv_staff_status_date);
            customerName = itemView.findViewById(R.id.rv_staff_status_customer_name);
            total = itemView.findViewById(R.id.rv_staff_status_total);
        }
    }
}
