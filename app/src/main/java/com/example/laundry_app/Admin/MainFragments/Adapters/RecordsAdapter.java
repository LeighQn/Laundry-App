package com.example.laundry_app.Admin.MainFragments.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.R;

import java.util.ArrayList;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.RecordViewHolder>  {

    Context context;
    ArrayList<Records> recordArrayList;

    public RecordsAdapter(Context context, ArrayList<Records> recordArrayList) {
        this.context = context;
        this.recordArrayList = recordArrayList;

    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.status_item, parent, false);

        return new RecordViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        Records records = recordArrayList.get(position);
        holder.txtTitle.setText(records.recordTitle);
        holder.txtDescription.setVisibility(View.INVISIBLE);
        //holder.txtDescription.setText(status.statusDescription);
    }

    @Override
    public int getItemCount() {

        return recordArrayList.size();
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder{


        TextView txtTitle;
        TextView txtDescription;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
        }
    }

}
