package com.example.laundry_app.USERS.Admin.MainFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.laundry_app.API.INTERFACE.AuthInterface;
import com.example.laundry_app.API.MODELCLASS.StaffsRequest;
import com.example.laundry_app.API.MODELCLASS.User;
import com.example.laundry_app.Global;
import com.example.laundry_app.USERS.Admin.AdminDashboard;
import com.example.laundry_app.USERS.Admin.MainFragments.AdaptersAndDataClass.Staff;
import com.example.laundry_app.USERS.Admin.MainFragments.AdaptersAndDataClass.StaffAdapter;
import com.example.laundry_app.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class AdminStaffFragment extends Fragment {

    private ArrayList<User> staffArrayList;
    private String[] staffName;
    private String[] staffPosition;
    private RecyclerView recyclerView;


    String ip = Global.getIp();
    Retrofit retrofit = Global.setIpRetrofit(ip);
    AuthInterface authInterface;
    String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_staff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        authInterface = retrofit.create(AuthInterface.class);
        AdminDashboard adminDashboard = (AdminDashboard) getActivity();
        token = adminDashboard.getMyToken();
//        staffDataInitialize();
        getAllStaffs();



        recyclerView = view.findViewById(R.id.rv_staff);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }


    private void getAllStaffs(){
        String ftoken = "Bearer " + token;
        Log.d("STAFF_HOME", ftoken);
        Call<StaffsRequest> request = authInterface.getStaffs(ftoken);
        request.enqueue(new Callback<StaffsRequest>() {
            @Override
            public void onResponse(Call<StaffsRequest> call, Response<StaffsRequest> response) {
                Log.d("STAFF_HOME", String.valueOf(response.code()));
                if(response.code() != 200){
                    Log.d("STAFF_HOME", String.valueOf(response.code()));
                    return;
                }
                StaffsRequest res = response.body();
                staffArrayList = (ArrayList<User>) res.getStaffs();
                StaffAdapter staffAdapter = new StaffAdapter(getContext(), staffArrayList);
                recyclerView.setAdapter(staffAdapter);
                staffAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<StaffsRequest> call, Throwable t) {

            }
        });
    }
}
