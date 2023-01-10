package com.example.laundry_app.USERS.Customer.MainFragments.AdaptersAndDataClass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.R;

import java.util.List;

public class LaundryTypeBookAdapter extends RecyclerView.Adapter<LaundryTypeBookViewHolder>{

    List<String> items;

    public LaundryTypeBookAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public LaundryTypeBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.laundry_type_book_item, parent, false);
        return new LaundryTypeBookViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull LaundryTypeBookViewHolder holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class LaundryTypeBookViewHolder extends RecyclerView.ViewHolder{

    TextView textView;
    private LaundryTypeBookAdapter adapter;

    public LaundryTypeBookViewHolder(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.txt_card);
        itemView.findViewById(R.id.btn_card_delete).setOnClickListener(view -> {
            adapter.items.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
    }

    public LaundryTypeBookViewHolder linkAdapter(LaundryTypeBookAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}