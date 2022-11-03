package com.example.laundry_app.Customer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.laundry_app.R;

import java.util.ArrayList;

public class StatusFragment extends Fragment {


    private ArrayList<Status> statusArrayList;
    private String[] statusTitle;
    private String[] statusDescription;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialized();

        recyclerView = view.findViewById(R.id.rv_status);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        CustomerAdapter customerAdapter = new CustomerAdapter(getContext(), statusArrayList);
        recyclerView.setAdapter(customerAdapter);
        customerAdapter.notifyDataSetChanged();
    }

    private void dataInitialized() {
        statusArrayList = new ArrayList<>();

        statusTitle = new String[]{
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

        statusDescription = new String[]{
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

        for(int i=0; i < statusTitle.length; i++){

            Status status = new Status(statusTitle[i], statusDescription[i] );
            statusArrayList.add(status);
        }

    }
}