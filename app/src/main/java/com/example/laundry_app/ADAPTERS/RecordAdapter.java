package com.example.laundry_app.ADAPTERS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.ADAPTERS.Customer.BookingAdapter;
import com.example.laundry_app.API.MODELCLASS.BookingModel;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Admin.AdminDashboard;
import com.example.laundry_app.USERS.Staff.DashboardActivity;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordAdapterVH> {

    private ArrayList<BookingModel> bookingsList;
    private Context context;
    private BookingAdapter.RecyclerViewClickListener listener;

    public RecordAdapter() {
    }

    public void setBookingsListData(ArrayList<BookingModel> bookingsList) {
        this.bookingsList = bookingsList;
    }

    public void setBookingsListDataInRecord(ArrayList<BookingModel> bookingsList, BookingAdapter.RecyclerViewClickListener listener){
        this.bookingsList = bookingsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecordAdapter.RecordAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new RecordAdapter.RecordAdapterVH(LayoutInflater.from(context).inflate(R.layout.staff_status_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.RecordAdapterVH holder, int position) {
        BookingModel bookingModel = bookingsList.get(position);

        String date = bookingModel.getDate();
        String customerName = bookingModel.getCustomer().getName();
        int total = bookingModel.getTotal();
        ArrayList<String> converter = new ArrayList<>();

        converter.add("Pick-up");
        converter.add("Washing");
        converter.add("Preparing for Delivery");
        converter.add("Out for Delivery");
        converter.add("Delivered");


        holder.date.setText(date);
        holder.customerName.setText(customerName);
        holder.total.setText(String.valueOf(total));

    }

    @Override
    public int getItemCount() {
        return bookingsList.size();
    }

    public void filteredList(ArrayList<BookingModel> filteredList){
        bookingsList = filteredList;
        notifyDataSetChanged();
    }

    public class RecordAdapterVH extends RecyclerView.ViewHolder {
        TextView date, customerName, total, bookingID;

        public RecordAdapterVH(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.rv_staff_status_date);
            customerName = itemView.findViewById(R.id.rv_staff_status_customer_name);
            total = itemView.findViewById(R.id.rv_staff_status_total);
        }
    }
}
