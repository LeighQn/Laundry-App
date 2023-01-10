package com.example.laundry_app.ADAPTERS.Customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.API.MODELCLASS.Customer.LaundryBookModel;
import com.example.laundry_app.API.MODELCLASS.LaundryModel;
import com.example.laundry_app.API.MODELCLASS.LaundryPriceModel;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Customer.Screens.BookLaundryTypeActivity;

import java.util.ArrayList;
import java.util.List;

public class LaundryBookAdapter extends RecyclerView.Adapter<LaundryBookAdapter.BookActivityVH> {

    private List<LaundryBookModel> laundryBookModelList;
    private Context context;

    public LaundryBookAdapter() {
    }

    public LaundryBookAdapter(BookLaundryTypeActivity bookLaundryTypeActivity, ArrayList<LaundryPriceModel> laundryPriceModelArrayList) {
    }

    public void setLaundryBookData(List<LaundryBookModel> laundryBookModelList) {
        this.laundryBookModelList = laundryBookModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LaundryBookAdapter.BookActivityVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        return new BookActivityVH(LayoutInflater.from(context).inflate(R.layout.laundry_price_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LaundryBookAdapter.BookActivityVH holder, int position) {

        LaundryBookModel laundryBookModel = laundryBookModelList.get(position);
        

    }

    @Override
    public int getItemCount() {
        return laundryBookModelList.size();
    }

    public class BookActivityVH extends RecyclerView.ViewHolder {
        TextView laundryName, laundryWeight, laundryPrice;

        public BookActivityVH(@NonNull View itemView) {
            super(itemView);

            laundryName = itemView.findViewById(R.id.rv_laundry_type);
            laundryWeight = itemView.findViewById(R.id.rv_laundry_weight);
            laundryPrice = itemView.findViewById(R.id.rv_laundry_price);
        }
    }
}
