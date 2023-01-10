package com.example.laundry_app.USERS.Staff.MainFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.laundry_app.ADAPTERS.NewStatusAdapter;
import com.example.laundry_app.API.INTERFACE.NewStatusInterface;
import com.example.laundry_app.API.MODELCLASS.NewStatusModel;
import com.example.laundry_app.Global;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Staff.MapActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class StatusFragment extends Fragment {

    NewStatusAdapter newStatusAdapter;
    NewStatusInterface newStatusInterface;
    private RecyclerView recyclerView;
    private Button btnTry;
    int userId = 25;
    private NewStatusAdapter.RecyclerViewClickListener listener;

    String token, finalToken, latitude, longitude;

    Retrofit retrofit = Global.retrofitConnectFakeApi();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_admin_sales, null);

        recyclerView = root.findViewById(R.id.rv_sales_admin);


        // ====================================== RETROFIT ====================================== //
        // ====================================== RETROFIT ====================================== //

        newStatusInterface = retrofit.create(NewStatusInterface.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        newStatusAdapter = new NewStatusAdapter();


        getCustomerDeliverList();
        setOnClickListener();

        return root;


    }


    private void getCustomerDeliverList(){
        finalToken = "Bearer " + token;

        Call<List<NewStatusModel>> call = newStatusInterface.getAllForDeliveryStatus();
        call.enqueue(new Callback<List<NewStatusModel>>() {
            @Override
            public void onResponse(Call<List<NewStatusModel>> call, Response<List<NewStatusModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<NewStatusModel> newStatusModelResponse = response.body();

                newStatusAdapter.setNewStatusData(newStatusModelResponse, listener);
                recyclerView.setAdapter(newStatusAdapter);

            }

            @Override
            public void onFailure(Call<List<NewStatusModel>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setOnClickListener(){
        listener = new NewStatusAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getActivity(),  MapActivity.class);
                intent.putExtra("latitude", 7.6742);
                intent.putExtra("longitude", 124.9897);
                startActivity(intent);
            }
        };
    }
}