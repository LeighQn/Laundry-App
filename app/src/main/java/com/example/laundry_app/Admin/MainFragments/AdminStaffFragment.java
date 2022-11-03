package com.example.laundry_app.Admin.MainFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.laundry_app.Admin.MainFragments.Adapters.Sales;
import com.example.laundry_app.Admin.MainFragments.Adapters.SalesAdapter;
import com.example.laundry_app.Admin.MainFragments.Adapters.Staff;
import com.example.laundry_app.Admin.MainFragments.Adapters.StaffAdapter;
import com.example.laundry_app.R;

import java.util.ArrayList;


public class AdminStaffFragment extends Fragment {

    private ArrayList<Staff> staffArrayList;
    private String[] staffTitle;
    private String[] staffDescription;
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
        staffTitle = new String[]{
                getString(R.string.head_1),
                getString(R.string.head_2),
                getString(R.string.head_3),
                getString(R.string.head_4),
                getString(R.string.head_5),
                getString(R.string.head_6),
                getString(R.string.head_7),
                getString(R.string.head_8),
                getString(R.string.head_9),
                getString(R.string.head_10)
        };

        staffDescription = new String[]{
                getString(R.string.news_a),
                getString(R.string.news_b),
                getString(R.string.news_c),
                getString(R.string.news_d),
                getString(R.string.news_e),
                getString(R.string.news_f),
                getString(R.string.news_g),
                getString(R.string.news_h),
                getString(R.string.news_i),
                getString(R.string.news_j)

        };

        for(int i=0; i < staffTitle.length; i++){

            Staff staff = new Staff(staffTitle[i], staffDescription[i] );
            staffArrayList.add(staff);
        }
    }
}
