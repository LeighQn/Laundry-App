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

    public void setStaffStatusAData(List<StaffStatus> staffStatusList) {
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

//        String date = staffStatus.getLaundryBookModel().getDate();
//        String name = staffStatus.getUser().getName();
//        float total = staffStatus.getLaundryBookModel().getTotal();

        String date = staffStatus.getDate();
        String name = staffStatus.getName();
        String total = staffStatus.getName();

        holder.date.setText(date);
        holder.customerName.setText(name);
        holder.total.setText(String.valueOf(total));

    }

    @Override
    public int getItemCount() {
        return staffStatusList.size();
    }

    public class StaffStatusVH extends RecyclerView.ViewHolder {

        TextView date;
        TextView customerName;
        TextView total;

        public StaffStatusVH(@NonNull View itemView) {
            super(itemView);

            // using status_item layout that's why total is equal to txt_status_status

            date = itemView.findViewById(R.id.txt_date_status);
            customerName = itemView.findViewById(R.id.txt_payables_status);
            total = itemView.findViewById(R.id.txt_status_status);
        }
    }
}
