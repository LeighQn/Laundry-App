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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.laundry_app.Global;
import com.example.laundry_app.USERS.Admin.MainFragments.AdaptersAndDataClass.Records;
import com.example.laundry_app.USERS.Admin.MainFragments.AdaptersAndDataClass.RecordsAdapter;
import com.example.laundry_app.R;

import java.util.ArrayList;

import retrofit2.Retrofit;


public class AdminRecordFragment extends Fragment {

    private ArrayList<Records> recordsArrayList;
    private String[] recordDate;
    private String[] recordCustomerName;
    private String[] recordTotal;
    private RecyclerView recyclerView;

    String ip = Global.getIp();
    Retrofit retrofit =Global.setIpRetrofit(ip);

    String type;
    Spinner spinner;
    TextView txtTitle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_status_staff, null);


        spinner = root.findViewById(R.id.spinner_out_for_delivery);
        txtTitle = root.findViewById(R.id.txt_out_for_delivery);

        txtTitle.setText("MATERIALS AVAILABILITY");



        recordDataInitialize();

        recyclerView = root.findViewById(R.id.rv_record);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        RecordsAdapter recordsAdapter = new RecordsAdapter(getContext(), recordsArrayList);
        recyclerView.setAdapter(recordsAdapter);
        recordsAdapter.notifyDataSetChanged();





        return root;
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

    // ______________________________ SPINNER EXECUTION ______________________________ //
    // ______________________________ SPINNER EXECUTION ______________________________ //

    private void spinnerExecution(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.barangay, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(adapterView.getContext(), barangay, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
