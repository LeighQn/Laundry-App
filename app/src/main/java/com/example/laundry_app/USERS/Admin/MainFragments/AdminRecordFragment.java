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

import com.example.laundry_app.USERS.Admin.MainFragments.AdaptersAndDataClass.Records;
import com.example.laundry_app.USERS.Admin.MainFragments.AdaptersAndDataClass.RecordsAdapter;
import com.example.laundry_app.R;

import java.util.ArrayList;


public class AdminRecordFragment extends Fragment {

    private ArrayList<Records> recordsArrayList;
    private String[] recordDate;
    private String[] recordCustomerName;
    private String[] recordTotal;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_record, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recordDataInitialize();

        recyclerView = view.findViewById(R.id.rv_record);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        RecordsAdapter recordsAdapter = new RecordsAdapter(getContext(), recordsArrayList);
        recyclerView.setAdapter(recordsAdapter);
        recordsAdapter.notifyDataSetChanged();
    }

    private void recordDataInitialize() {

        recordsArrayList = new ArrayList<>();


        recordDate = new String[]{
                getString(R.string.date1),
                getString(R.string.date2),
                getString(R.string.date3),
        };

        recordCustomerName = new String[]{
                getString(R.string.customer1),
                getString(R.string.customer2),
                getString(R.string.customer3),
        };

        recordTotal = new String[]{
                getString(R.string.total1),
                getString(R.string.total2),
                getString(R.string.total3),
        };

        for(int i=0; i < recordDate.length; i++){

            Records record = new Records(recordDate[i], recordCustomerName[i], recordTotal[i] );
            recordsArrayList.add(record);
        }
    }
}
