package com.example.laundry_app.USERS.Admin.MainFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.laundry_app.USERS.Admin.MainFragments.AdaptersAndDataClass.Staff;
import com.example.laundry_app.USERS.Admin.MainFragments.AdaptersAndDataClass.StaffAdapter;
import com.example.laundry_app.R;

import java.util.ArrayList;


public class AdminStaffFragment extends Fragment {

    private ArrayList<Staff> staffArrayList;
    private String[] staffName;
    private String[] staffPosition;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_staff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        staffDataInitialize();

        recyclerView = view.findViewById(R.id.rv_staff);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        StaffAdapter staffAdapter = new StaffAdapter(getContext(), staffArrayList);
        recyclerView.setAdapter(staffAdapter);
        staffAdapter.notifyDataSetChanged();
    }

    private void staffDataInitialize() {
        staffArrayList = new ArrayList<Staff>();
        staffName = new String[]{
                getString(R.string.staff1),
                getString(R.string.staff2),
                getString(R.string.staff3),
        };

        staffPosition = new String[]{
                getString(R.string.position3),
                getString(R.string.position1),
                getString(R.string.position2),


        };

        for(int i=0; i < staffName.length; i++){

            Staff staff = new Staff(staffName[i], staffPosition[i] );
            staffArrayList.add(staff);
        }
    }
}
