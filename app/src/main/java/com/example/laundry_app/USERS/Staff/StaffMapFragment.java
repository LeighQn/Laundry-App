package com.example.laundry_app.USERS.Staff;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundry_app.Global;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Staff.AdapterAndDataClass.StaffMap;
import com.example.laundry_app.USERS.Staff.AdapterAndDataClass.StaffMapAdapter;

import java.util.ArrayList;


public class StaffMapFragment extends Fragment {


    private ArrayList<StaffMap> mapArrayList;
    private String[] mapDate;
    private String[] mapCustomerName;
    private String[] mapTotal;
    private RecyclerView recyclerView;
    String token = Global.token;
    MapActivity mapActivity;




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapDataInitialize();

        recyclerView = view.findViewById(R.id.rv_map_admin);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        StaffMapAdapter mapAdapter = new StaffMapAdapter(getContext(), mapArrayList);
        recyclerView.setAdapter(mapAdapter);
        mapAdapter.notifyDataSetChanged();

        Toast.makeText(getActivity(), "Token from Navigation Map" + token, Toast.LENGTH_SHORT).show();
    }

    private void mapDataInitialize() {
        mapArrayList = new ArrayList<StaffMap>();

        mapDate = new String[]{
                getString(R.string.date1),
                getString(R.string.date2),
                getString(R.string.date3),
        };
        mapCustomerName = new String[]{
                getString(R.string.customer1),
                getString(R.string.customer2),
                getString(R.string.customer3),
        };

        mapTotal = new String[]{
                getString(R.string.total1),
                getString(R.string.total2),
                getString(R.string.total3),
        };

        for(int i=0; i < mapDate.length; i++){

            StaffMap staffMap = new StaffMap(mapDate[i], mapCustomerName[i], mapTotal[i] );
            mapArrayList.add(staffMap);
        }
    }
}