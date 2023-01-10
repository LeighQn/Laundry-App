package com.example.laundry_app.ADAPTERS.Customer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.API.MODELCLASS.Customer.CustomerStatusModel;
import com.example.laundry_app.R;

import org.w3c.dom.Text;

import java.util.List;

public class CustomerStatusAdapter extends RecyclerView.Adapter<CustomerStatusAdapter.CustomerStatusVH> {

    private List<CustomerStatusModel> customerStatusModelList;
    private Context context;
    private ItemClickListener customerStatusItemClickListener;


    public CustomerStatusAdapter() {
    }

    public void setCustomerStatusData(List<CustomerStatusModel> customerStatusModelList, ItemClickListener customerStatusItemClickListener) {
        this.customerStatusModelList = customerStatusModelList;
        notifyDataSetChanged();
        this.customerStatusItemClickListener = customerStatusItemClickListener;
    }


    @NonNull
    @Override
    public CustomerStatusAdapter.CustomerStatusVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new CustomerStatusAdapter.CustomerStatusVH(LayoutInflater.from(context).inflate(R.layout.status_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerStatusAdapter.CustomerStatusVH holder, int position) {

        CustomerStatusModel customerStatusModel = customerStatusModelList.get(position);

        String date = customerStatusModel.getDate();
        String total = customerStatusModel.getTotal();
        String status = customerStatusModel.getStatus();

        holder.date.setText(date);
        holder.total.setText(total);
        holder.status.setText(status);
        holder.itemView.setOnClickListener(view -> {

            customerStatusItemClickListener.onItemClick(customerStatusModelList.get(position)); // this will get the position of the item in RecyclerView
        });

    }

    @Override
    public int getItemCount() {
        return customerStatusModelList.size();
    }

    public interface ItemClickListener{
        void onItemClick(CustomerStatusModel customerStatusModel);
    }

    public class CustomerStatusVH extends RecyclerView.ViewHolder {

        TextView date, total, status;

        public CustomerStatusVH(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.txt_date_status);
            total = itemView.findViewById(R.id.txt_payables_status);
            status = itemView.findViewById(R.id.txt_status_status);
        }
    }
}
