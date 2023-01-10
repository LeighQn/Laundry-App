package com.example.laundry_app.USERS.Admin.MainFragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.laundry_app.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
//import com.github.mikephil.charting.charts.BarChart;
//import com.github.mikephil.charting.components.Description;
//import com.github.mikephil.charting.data.BarData;
//import com.github.mikephil.charting.data.BarDataSet;
//import com.github.mikephil.charting.data.BarEntry;
//import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class AdminHomeFragment extends Fragment {

    // ============================== COMPONENTS ============================== //
    // ============================== COMPONENTS ============================== //

    TextView btnNoOfBookings, btnNoOfPickup, btnDailyIncome, btnMonthlyIncome;


    // ============================== GLOBAL VARIABLES ============================== //
    // ============================== GLOBAL VARIABLES ============================== //

    ArrayList barArrayList;
    int entry1 = 10, entry2 = 5, entry3 = 7, entry4 = 2, entry5=4, entry6=6, entry7=17;
    int numberOfBookings1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_admin_home, null);


        // ============================== INITIALIZE ============================== //
        // ============================== INITIALIZE ============================== //

        btnNoOfBookings = root.findViewById(R.id.txt_display_number_of_bookings);
        btnNoOfPickup = root.findViewById(R.id.txt_display_number_of_pickup);
        btnDailyIncome = root.findViewById(R.id.txt_display_total_of_daily_booking);
        btnMonthlyIncome = root.findViewById(R.id.txt_display_monthly_income);
        BarChart barChart = root.findViewById(R.id.bar_chart);


        // ============================== FUNCTIONS ============================== //
        // ============================== FUNCTIONS ============================== //


        // ============================== Calling Methods ============================== //

        getData();

        // ============================== Bar View ============================== //

        BarDataSet barDataSet = new BarDataSet(barArrayList, "Sales");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
//        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(true);
        Description description = barChart.getDescription();
        description.setText("Jan 08          Jan 09           Jan 10 " +
                "         Jan 11          Jan 12          Jan 13           Jan 14    ");


        return root;
    }


    // ============================== METHODS ============================== //
    // ============================== METHODS ============================== //

    private void getData(){
        barArrayList = new ArrayList();
        barArrayList.add(new BarEntry(2f, entry1));
        barArrayList.add(new BarEntry(3f, entry2));
        barArrayList.add(new BarEntry(4f, entry3));
        barArrayList.add(new BarEntry(5f, entry4));
        barArrayList.add(new BarEntry(6f, entry5));
        barArrayList.add(new BarEntry(7f, entry6));
        barArrayList.add(new BarEntry(8f, entry7));
    }
}