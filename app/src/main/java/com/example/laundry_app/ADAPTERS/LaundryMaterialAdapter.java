package com.example.laundry_app.ADAPTERS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.ADAPTERS.Customer.BookingAdapter;
import com.example.laundry_app.API.MODELCLASS.LaundryMaterialsModel;
import com.example.laundry_app.R;

import java.util.ArrayList;

public class LaundryMaterialAdapter extends RecyclerView.Adapter<LaundryMaterialAdapter.LaundryMaterialVH> {

    private ArrayList<LaundryMaterialsModel> materialList;
    private Context context;
    private BookingAdapter.RecyclerViewClickListener listener;

    public LaundryMaterialAdapter(ArrayList<LaundryMaterialsModel> materialList, Context context) {
        this.materialList = materialList;
        this.context = context;
    }

    @NonNull
    @Override
    public LaundryMaterialAdapter.LaundryMaterialVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new LaundryMaterialAdapter.LaundryMaterialVH(LayoutInflater.from(context).inflate(R.layout.staff_status_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LaundryMaterialAdapter.LaundryMaterialVH holder, int position) {
        LaundryMaterialsModel laundryMaterialsModel = materialList.get(position);

        String material = laundryMaterialsModel.getMaterial();
        String unit = String.valueOf(laundryMaterialsModel.getUnit());

        holder.material.setText(material);
        holder.unit.setText(unit);

    }

    @Override
    public int getItemCount() {
        return materialList.size();
    }

    public class LaundryMaterialVH extends RecyclerView.ViewHolder {

        TextView material, unit;
        public LaundryMaterialVH(@NonNull View itemView) {
            super(itemView);

            material = itemView.findViewById(R.id.rv_staff_status_date);
            unit = itemView.findViewById(R.id.rv_staff_status_customer_name);
        }
    }
}
