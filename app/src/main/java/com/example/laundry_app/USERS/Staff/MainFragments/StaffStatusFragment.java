package com.example.laundry_app.USERS.Staff.MainFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.laundry_app.ADAPTERS.StaffStatusAdapter;
import com.example.laundry_app.API.INTERFACE.Staff.StaffStatusInterface;
import com.example.laundry_app.API.MODELCLASS.Staff.StaffStatus;
import com.example.laundry_app.Global;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Admin.MainFragments.AdaptersAndDataClass.Sales;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StaffStatusFragment extends Fragment {


    // ====================================== COMPONENTS ====================================== //
    // ====================================== COMPONENTS ====================================== //

    private Spinner spinnerStaffStatus;
    private RecyclerView recyclerView;

    // ====================================== OBJECTS ====================================== //
    // ====================================== OBJECTS ====================================== //

    StaffStatusAdapter staffStatusAdapter;
    StaffStatusInterface staffStatusInterface;
    Retrofit retrofit = Global.retrofitConnectFakeApi();

    // ====================================== VARIABLES ====================================== //
    // ====================================== VARIABLES ====================================== //

    int userId = 23;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_status_staff, null);


        // ====================================== INITIALIZE ====================================== //
        // ====================================== INITIALIZE ====================================== //

        spinnerStaffStatus = root.findViewById(R.id.spinner_out_for_delivery);
        recyclerView = root.findViewById(R.id.rv_staff_status);


        // ====================================== RETROFIT ====================================== //
        // ====================================== RETROFIT ====================================== //

        staffStatusInterface = retrofit.create(StaffStatusInterface.class);


        // ====================================== FUNCTIONS ====================================== //
        // ====================================== FUNCTIONS ====================================== //

        // ====================================== Spinner Implementation ====================================== //

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.laudry_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStaffStatus.setAdapter(adapter);
        spinnerStaffStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
              //  Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //getStaffStatus();
        //displayRecyclerView();

        return root;
    }



    // ====================================== RETROFIT EXECUTION ====================================== //
    // ====================================== RETROFIT EXECUTION ====================================== //

    private void getStaffStatus(){

        Call<List<StaffStatus>> call = staffStatusInterface.getStaffStatus(userId);
        call.enqueue(new Callback<List<StaffStatus>>() {
            @Override
            public void onResponse(Call<List<StaffStatus>> call, Response<List<StaffStatus>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<StaffStatus> staffStatusResponse = response.body();

                staffStatusAdapter.setStaffStatusData(staffStatusResponse);
                recyclerView.setAdapter(staffStatusAdapter);
            }

            @Override
            public void onFailure(Call<List<StaffStatus>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSpinnerValueIfStatement(String string){

       // if (string == )
    }
}