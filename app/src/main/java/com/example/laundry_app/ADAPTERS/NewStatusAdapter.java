package com.example.laundry_app.ADAPTERS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.API.MODELCLASS.NewStatusModel;
import com.example.laundry_app.R;

import java.util.List;

public class NewStatusAdapter extends RecyclerView.Adapter<NewStatusAdapter.NewStatusVH> {
    private List<NewStatusModel> newStatusModelList;
    private Context context;
    private RecyclerViewClickListener listener;

    public NewStatusAdapter() {
    }


    public void setNewStatusData(List<NewStatusModel> newStatusModelList, RecyclerViewClickListener listener) {
        this.newStatusModelList = newStatusModelList;
        this.listener = listener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewStatusAdapter.NewStatusVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new NewStatusAdapter.NewStatusVH(LayoutInflater.from(context).inflate(R.layout.staff_status_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewStatusAdapter.NewStatusVH holder, int position) {

        NewStatusModel newStatusModel = newStatusModelList.get(position);

        String date = newStatusModel.getDate();
        String customerName = newStatusModel.getCustomerName();
        String total = newStatusModel.getTotal();

        holder.date.setText(date);
        holder.customerName.setText(customerName);
        holder.total.setText(total);
    }

    @Override
    public int getItemCount() {
        return newStatusModelList.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }



    public class NewStatusVH extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView date, customerName, total;


        public NewStatusVH(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.rv_staff_status_date);
            customerName = itemView.findViewById(R.id.rv_staff_status_customer_name);
            total = itemView.findViewById(R.id.rv_staff_status_total);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }
}
