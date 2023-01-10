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
import android.widget.Spinner;

import com.example.laundry_app.USERS.Admin.MainFragments.AdaptersAndDataClass.Sales;
import com.example.laundry_app.USERS.Admin.MainFragments.AdaptersAndDataClass.SalesAdapter;
import com.example.laundry_app.R;

import java.util.ArrayList;


public class AdminSalesFragment extends Fragment {

    // ====================================== COMPONENTS ====================================== //
    // ====================================== COMPONENTS ====================================== //

    private ArrayList<Sales> salesArrayList;
    private String[] salesDate;
    private String[] salesCustomerName;
    private String[] salesTotal;
    private RecyclerView recyclerView;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ====================================== INITIALIZE ====================================== //
        // ====================================== INITIALIZE ====================================== //

        recyclerView = view.findViewById(R.id.rv_sales_admin);


        // ====================================== EXECUTE METHODS ====================================== //
        // ====================================== EXECUTE METHODS ====================================== //

        salesDataInitialize();


        // ====================================== FUNCTIONS ====================================== //
        // ====================================== FUNCTIONS ====================================== //

        // ====================================== RecyclerView ====================================== //

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        SalesAdapter salesAdapter = new SalesAdapter(getContext(), salesArrayList);
        recyclerView.setAdapter(salesAdapter);
        salesAdapter.notifyDataSetChanged();
    }

    private void salesDataInitialize() {
        salesArrayList = new ArrayList<Sales>();

        salesDate = new String[]{
                getString(R.string.date1),
                getString(R.string.date2),
                getString(R.string.date3),
        };
        salesCustomerName = new String[]{
                getString(R.string.customer1),
                getString(R.string.customer2),
                getString(R.string.customer3),
        };

        salesTotal = new String[]{
                getString(R.string.total1),
                getString(R.string.total2),
                getString(R.string.total3),
        };

        for(int i=0; i < salesDate.length; i++){

            Sales sales = new Sales(salesDate[i], salesCustomerName[i], salesTotal[i] );
            salesArrayList.add(sales);
        }
    }
}