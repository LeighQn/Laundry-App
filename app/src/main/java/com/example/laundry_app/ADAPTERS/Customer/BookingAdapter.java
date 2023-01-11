package com.example.laundry_app.ADAPTERS.Customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.API.MODELCLASS.BookingModel;
import com.example.laundry_app.R;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.SalesAdapterVH>{

    private List<BookingModel> bookingsList;
    private Context context;


    public BookingAdapter(List<BookingModel> bookingsList, Context context) {
        this.bookingsList = bookingsList;
        this.context = context;
    }

    @NonNull
    @Override
    public BookingAdapter.SalesAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new BookingAdapter.SalesAdapterVH(LayoutInflater.from(context).inflate(R.layout.staff_status_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.SalesAdapterVH holder, int position) {
            BookingModel bookingModel = bookingsList.get(position);

            String date = bookingModel.getDate();
            String customerName = bookingModel.getCustomer().getName();
            int status = bookingModel.getStatus();

            holder.date.setText(date);
            holder.customerName.setText(customerName);
            holder.status.setText(status);
    }

    @Override
    public int getItemCount() {
        return bookingsList.size();
    }


    public class SalesAdapterVH extends RecyclerView.ViewHolder {
        TextView date, customerName, status;


        public SalesAdapterVH(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.rv_staff_status_date);
            customerName = itemView.findViewById(R.id.rv_staff_status_customer_name);
            status = itemView.findViewById(R.id.rv_staff_status_total);
        }
    }
}
