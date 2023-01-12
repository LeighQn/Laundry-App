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

import java.util.ArrayList;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.SalesAdapterVH>{

    private ArrayList<BookingModel> bookingsList;
    private Context context;
    private RecyclerViewClickListener listener;

    public BookingAdapter() {
    }

    public void setBookingsListData(ArrayList<BookingModel> bookingsList) {
        this.bookingsList = bookingsList;
    }

    public void setBookingsListDatas(ArrayList<BookingModel> bookingsList, RecyclerViewClickListener listener){
        this.bookingsList = bookingsList;
        this.listener = listener;
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
        ArrayList<String> converter = new ArrayList<>();

        converter.add("Pick-up");
        converter.add("Washing");
        converter.add("Preparing for Delivery");
        converter.add("Out for Delivery");
        converter.add("Delivered");


        holder.date.setText(date);
        holder.customerName.setText(customerName);
        holder.status.setText(converter.get(status -1));

    }

    @Override
    public int getItemCount() {
        return bookingsList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }


    public class SalesAdapterVH extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView date, customerName, status, bookingID;



        public SalesAdapterVH(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.rv_staff_status_date);
            customerName = itemView.findViewById(R.id.rv_staff_status_customer_name);
            status = itemView.findViewById(R.id.rv_staff_status_total);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }
}
