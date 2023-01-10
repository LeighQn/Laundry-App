package com.example.laundry_app.USERS.Customer.MainFragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.laundry_app.ADAPTERS.Customer.CustomerStatusAdapter;
import com.example.laundry_app.API.INTERFACE.Customer.CustomerStatusInterface;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerStatusModel;
import com.example.laundry_app.MainActivity;
import com.example.laundry_app.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CustomerStatusFragment extends Fragment {

    CustomerStatusInterface customerStatusInterface;
    CustomerStatusAdapter customerStatusAdapter;
    CustomerStatusModel customerStatusModel;
    RecyclerView customerRecyclerViewStatus;
    CustomerStatusAdapter.ItemClickListener itemClickListener;


    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_customer_status, null);

        customerRecyclerViewStatus = root.findViewById(R.id.rv_customer_status);

        // ====================================== RETROFIT ====================================== //
        // ====================================== RETROFIT ====================================== //

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        customerStatusInterface = retrofit.create(CustomerStatusInterface.class);


        // ====================================== RECYCLERVIEW ====================================== //
        // ====================================== RECYCLERVIEW ====================================== //

        customerRecyclerViewStatus.setLayoutManager(new LinearLayoutManager(getActivity()));
        customerRecyclerViewStatus.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        customerStatusAdapter = new CustomerStatusAdapter();

        getCustomerLaundryStatus();
        return root;
    }

    private void getCustomerLaundryStatus(){

        Call<List<CustomerStatusModel>> call = customerStatusInterface.getCustomerStatus();
        call.enqueue(new Callback<List<CustomerStatusModel>>() {
            @Override
            public void onResponse(Call<List<CustomerStatusModel>> call, Response<List<CustomerStatusModel>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<CustomerStatusModel> customerStatusModelsResponse = response.body();

                // SHOW RESULT AND CLICKABLE RECYCLERVIEW

                customerStatusAdapter.setCustomerStatusData(customerStatusModelsResponse, new CustomerStatusAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(CustomerStatusModel customerStatusModel) {
            //            Toast.makeText(getActivity(), customerStatusModel.getStatus() + "Clicked", Toast.LENGTH_SHORT).show();
                        dialogBoxCustomerStatus(customerStatusModel.getDate(), customerStatusModel.getTotal(), customerStatusModel.getStatus());
                    }
                });
                customerRecyclerViewStatus.setAdapter(customerStatusAdapter);
            }

            @Override
            public void onFailure(Call<List<CustomerStatusModel>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void dialogBoxCustomerStatus(String date, String total, String status){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setMessage("Date:   " + date + "\n\n" + "Total:   " + total + "\n\n" + "Status  : " + status).setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setTitle("Dialog Header");
        alert.show();
    }
}