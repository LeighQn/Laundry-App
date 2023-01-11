package com.example.laundry_app.USERS.Customer.MainFragments;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.laundry_app.ADAPTERS.Customer.CustomerStatusAdapter;
import com.example.laundry_app.API.INTERFACE.Customer.CustomerProfileInterface;
import com.example.laundry_app.API.INTERFACE.Customer.CustomerStatusInterface;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerStatusModel;
import com.example.laundry_app.Global;
import com.example.laundry_app.USERS.Customer.MainFragments.AdaptersAndDataClass.CustomerAdapter;
import com.example.laundry_app.USERS.Customer.MainFragments.AdaptersAndDataClass.Status;
import com.example.laundry_app.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatusFragment extends Fragment {


    private ArrayList<Status> statusArrayList;
    private String[] statusDate;
    private String[] statusPayable;
    private String[] statusStatus;
    private RecyclerView recyclerView;

    Intent intent;
    CustomerStatusInterface customerStatusInterface;
    CustomerStatusAdapter customerStatusAdapter;

    Retrofit retrofit = Global.retrofitConnectFakeApi();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // ====================================== INITIALIZE RETROFIT ====================================== //
        // ====================================== INITIALIZE RETROFIT ====================================== //

        customerStatusInterface = retrofit.create(CustomerStatusInterface.class);



        // ==============================================================================================//



        //dataInitialized();

        recyclerView = view.findViewById(R.id.rv_status_customer);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        CustomerAdapter customerAdapter = new CustomerAdapter(getContext(), statusArrayList);
        recyclerView.setAdapter(customerAdapter);
        customerAdapter.notifyDataSetChanged();
    }

//    private void dataInitialized() {
//        statusArrayList = new ArrayList<>();
//
//        //statusPicture = new
//
//        statusDate = new String[]{
//                getString(R.string.date1),
//                getString(R.string.date2),
//                getString(R.string.date3),
//        };
//
//        statusPayable = new String[]{
//                getString(R.string.total1),
//                getString(R.string.total2),
//                getString(R.string.total3),
//
//        };
//        statusStatus = new String[]{
//                getString(R.string.status1),
//                getString(R.string.status2),
//                getString(R.string.status3),
//        };
//
//        for(int i=0; i < statusDate.length; i++){
//
//            Status status = new Status(statusDate[i], statusPayable[i], statusStatus[i] );
//            statusArrayList.add(status);
//        }
//
//    }


    // ============================== GET RETROFIT ============================== //
    // ============================== GET RETROFIT ============================== //

    private void getCustomerStatus(){
        Call<List<CustomerStatusModel>> call = customerStatusInterface.getCustomerStatus();
        call.enqueue(new Callback<List<CustomerStatusModel>>() {
            @Override
            public void onResponse(Call<List<CustomerStatusModel>> call, Response<List<CustomerStatusModel>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<CustomerStatusModel> customerStatusModelsResponse = response.body();

//                customerStatusAdapter.setCustomerStatusData (customerStatusModelsResponse);
                recyclerView.setAdapter(customerStatusAdapter);
            }

            @Override
            public void onFailure(Call<List<CustomerStatusModel>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}