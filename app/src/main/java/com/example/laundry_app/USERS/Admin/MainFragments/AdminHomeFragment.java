package com.example.laundry_app.USERS.Admin.MainFragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundry_app.API.INTERFACE.Admin.AdminHomeInterface;
import com.example.laundry_app.API.INTERFACE.BookingInterface;
import com.example.laundry_app.API.MODELCLASS.Admin.AdminHomeModel;
import com.example.laundry_app.API.MODELCLASS.BookingModel;
import com.example.laundry_app.API.MODELCLASS.BookingsRequest;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerProfileModel;
import com.example.laundry_app.Global;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Admin.AdminDashboard;
import com.example.laundry_app.USERS.Staff.DashboardActivity;
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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class AdminHomeFragment extends Fragment {

    // ============================== COMPONENTS ============================== //
    // ============================== COMPONENTS ============================== //

    TextView btnNoOfBookings, btnNoOfPickup, btnDailyIncome, btnMonthlyIncome;


    // ============================== VARIABLES ============================== //
    // ============================== VARIABLES ============================== //

    ArrayList barArrayList;
    ArrayList<BookingModel> bookings = new ArrayList<BookingModel>();
    int entry1 = 10, entry2 = 5, entry3 = 7, entry4 = 2, entry5=4, entry6=6, entry7=17;
    int numberOfBookings1;
    String token, finalToken;
    int totalBookings = 0;
    int totalPickups = 0;
    int dailyIncome = 0;
    int monthlyIncom = 0;

    String ip = Global.getIp();
    Retrofit retrofit =Global.setIpRetrofit(ip);
    AdminHomeInterface adminHomeInterface;
    BookingInterface bookingInterface;
    AdminHomeModel adminHomeModel;
    AdminDashboard adminDashboard;


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

        adminHomeInterface = retrofit.create(AdminHomeInterface.class);
        bookingInterface = retrofit.create(BookingInterface.class);


        // ============================== FUNCTIONS ============================== //
        // ============================== FUNCTIONS ============================== //

        // ============================================== CALLING METHODS ================================================//

        getDataFromActivityAdmin();
//        getAdminHomeInfo();
        getAllBookings();




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

    private void getDataFromActivityAdmin(){
        adminDashboard = (AdminDashboard) getActivity();
        token = adminDashboard.getMyToken();

    }


//    private void getAdminHomeInfo(){
//        finalToken = "Bearer " + token;
//        Call<AdminHomeModel> call = adminHomeInterface.getAdminHomeInfo(finalToken);
//        call.enqueue(new Callback<AdminHomeModel>() {
//            @Override
//            public void onResponse(Call<AdminHomeModel> call, Response<AdminHomeModel> response) {
//                if(!response.isSuccessful()){
//                    Toast.makeText(getActivity(), "Admin Home Fragment: " + response.code(), Toast.LENGTH_LONG).show();
//                    return;
//                }
//////
//////                String name = String.valueOf(response.body().getUser().getName());
//////                String phone = String.valueOf(response.body().getUser().getMobileNumber());
//////                String username = String.valueOf(response.body().getUser().getUsername());
//////                String address = String.valueOf(response.body().getUser().getAddress());
//////
//////
//////                txtName.setText(name);
//////                txtPhone.setText(phone);
//////                txtUserName.setText(username);
//////                txtAddress.setText(address);
//
//                token = String.valueOf(response.body().getUser().getToken());
//                Global.setToken(token);
//
////
//            }
//
//            @Override
//            public void onFailure(Call<AdminHomeModel> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    private void getAllBookings(){
        finalToken = "Bearer " + token;
        Call<BookingsRequest> requestCall = bookingInterface.getBookings(finalToken);
        requestCall.enqueue(new Callback<BookingsRequest>() {
            @Override
            public void onResponse(Call<BookingsRequest> call, Response<BookingsRequest> response) {
                if(response.code() != 200){
                    Log.d("ADMIN_HOME", "Something went wrong with status " + String.valueOf(response.code()));
                    return;
                }
                // calculate
                BookingsRequest result = response.body();
                // data
                bookings = result.getBookings();
                totalBookings = bookings.size();
                btnNoOfBookings.setText(String.valueOf(totalBookings));
                for(int i = 0; i < bookings.size(); i++){
                    if(bookings.get(i).getStatus() == 1){
                        totalPickups += 1;
                    }
                    BookingModel booking = bookings.get(i);
                    String pattern = "yyyy/MM/dd";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
                    LocalDate date = LocalDate.parse(booking.getDate(), formatter);
                    Date bookedDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Date today = new Date();
                    // date is today means daily



                    Calendar c1 = Calendar.getInstance();
                    Calendar c2 = Calendar.getInstance();
                    c1.setTime(bookedDate);
                    c2.setTime(today);

                    int yearDiff = c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
                    int monthDiff = c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
                    int dayDiff = c1.get(Calendar.DAY_OF_MONTH) - c2.get(Calendar.DAY_OF_MONTH);

                    if(yearDiff == 0 && monthDiff == 0 && dayDiff ==0){
                        dailyIncome += booking.getTotal();
                    }

                    if(yearDiff == 0 && monthDiff == 0){
                        monthlyIncom += booking.getTotal();
                    }
                }
                btnDailyIncome.setText(String.valueOf(dailyIncome));
                btnMonthlyIncome.setText(String.valueOf(monthlyIncom));
                btnNoOfPickup.setText(String.valueOf(totalPickups));
            }

            @Override
            public void onFailure(Call<BookingsRequest> call, Throwable t) {

            }
        });
    }

//    private void getAdminHomeInfo(){
//
//        finalToken = "Bearer " + token;
//       // Call<AdminHomeModel> call = adminHomeInterface.getAllAdminHomeInfo(finalToken); //
//        Call<List<AdminHomeModel>> call = adminHomeInterface.getAllAdminHomeInfo();
//        call.enqueue(new Callback<List<AdminHomeModel>>() {
//            @Override
//            public void onResponse(Call<List<AdminHomeModel>> call, Response<List<AdminHomeModel>> response) {
//                if(!response.isSuccessful()){
//                    Toast.makeText(getActivity(), "Admin Home Fragment: " + response.code(), Toast.LENGTH_LONG).show();
//                    return;
//                }
////
////                String name = String.valueOf(response.body().getUser().getName());
////                String phone = String.valueOf(response.body().getUser().getMobileNumber());
////                String username = String.valueOf(response.body().getUser().getUsername());
////                String address = String.valueOf(response.body().getUser().getAddress());
////
////
////                txtName.setText(name);
////                txtPhone.setText(phone);
////                txtUserName.setText(username);
////                txtAddress.setText(address);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<AdminHomeModel>> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }

}