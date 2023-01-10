package com.example.laundry_app.USERS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.API.MODELCLASS.Login;
import com.example.laundry_app.R;

import org.w3c.dom.Text;

import java.util.List;

public class SampleUIAdapter extends RecyclerView.Adapter<SampleUIAdapter.SampleUIViewHolder> {

    private List<Login> loginList;
    private Context context;

    public SampleUIAdapter() {
    }

    public void setSampleUIData(List<Login> loginList){
        this.loginList = loginList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SampleUIAdapter.SampleUIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        return new SampleUIAdapter.SampleUIViewHolder(LayoutInflater.from(context).inflate(R.layout.sample_list, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SampleUIAdapter.SampleUIViewHolder holder, int position) {

        Login sampleUIForApi = loginList.get(position);

//        String username = sampleUIForApi.getUsername();
//        String password = sampleUIForApi.getPassword();
//
//        holder.username.setText(username);
//        holder.password.setText(password);


    }

    @Override
    public int getItemCount() {
        return loginList.size();
    }

    public class SampleUIViewHolder extends RecyclerView.ViewHolder {


        TextView username, password;

        public SampleUIViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.rv_result_username);
            password = itemView.findViewById(R.id.rv_result_password);
        }
    }
}
