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

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class AdminHomeFragment extends Fragment {

    // ============================== COMPONENTS ============================== //
    // ============================== COMPONENTS ============================== //

    TextView btnNoOfBookings, btnNoOfPickup, btnDailyIncome, btnMonthlyIncome, txtChartTitleStart,txtChartTitleEnd;


    // ============================== VARIABLES ============================== //
    // ============================== VARIABLES ============================== //

    ArrayList barArrayList;
    ArrayList<BookingModel> bookings = new ArrayList<BookingModel>();
    ArrayList dataX = new ArrayList<>();
    ArrayList dataY = new ArrayList<>();
    int entry1 = 10, entry2 = 5, entry3 = 7, entry4 = 2, entry5=4, entry6=6, entry7=17;
    String barDescription = "";
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

    BarChart barChart;

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
        txtChartTitleStart = root.findViewById(R.id.bar_chart_start_date);
        txtChartTitleEnd = root.findViewById(R.id.bar_chart_end_date);
        barChart = root.findViewById(R.id.bar_chart);

        adminHomeInterface = retrofit.create(AdminHomeInterface.class);
        bookingInterface = retrofit.create(BookingInterface.class);


        // ============================== FUNCTIONS ============================== //
        // ============================== FUNCTIONS ============================== //

        // ============================================== CALLING METHODS ================================================//

        getDataFromActivityAdmin();
//        getAdminHomeInfo();
        btnMonthlyIncome.setText("0");
        monthlyIncom = 0;
        totalPickups = 0;
        getAllBookings();




        // ============================== Calling Methods ============================== //


        // ============================== Bar View ============================== //



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


        BarDataSet barDataSet = new BarDataSet(barArrayList, "Sales");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
//        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(true);
        Description description = barChart.getDescription();
        description.setText(barDescription);
    }

    private void getDataFromActivityAdmin(){
        adminDashboard = (AdminDashboard) getActivity();
        token = adminDashboard.getMyTokenAdmin();

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
                try {
                    setEntries(bookings);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                totalBookings = bookings.size();
                btnNoOfBookings.setText(String.valueOf(totalBookings));
                for(int i = 0; i < bookings.size(); i++){
                    if(bookings.get(i).getStatus() == 1){
                        totalPickups += 1;
                    }
                    BookingModel booking = bookings.get(0);
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

    private void setEntries(ArrayList<BookingModel> bookings) throws ParseException {
        //? get all days from today + 6
        //? get bookings for that day

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        String[] days = new String[7];
        int delta = -cal.get(GregorianCalendar.DAY_OF_WEEK) + 2;
        cal.add(Calendar.DAY_OF_MONTH, delta );
        for (int i = 0; i < 7; i++)
        {
            days[i] = format.format(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        Date monday = new Date(days[0]);
        Date  tuesday = new Date(days[1]);
        Date wednesday = new Date(days[2]);
        Date thursday = new Date(days[3]);
        Date friday = new Date(days[4]);
        Date saturday = new Date(days[5]);
        Date sunday = new Date(days[6]);

        SimpleDateFormat sdfTitle = new SimpleDateFormat("MMM dd, yyyy");
        txtChartTitleStart.setText(sdfTitle.format(monday));
        txtChartTitleEnd.setText(sdfTitle.format(sunday));
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
        barDescription = sdf.format(monday) + "          " + sdf.format(tuesday) +"            " + sdf.format(wednesday)+
                "         " + sdf.format(thursday)+"          " + sdf.format(friday)+"          " +  sdf.format(saturday)+"           " +sdf.format(sunday)+"    ";

        Log.d("TEST_BOOKINGS", format.format(monday));
        Log.d("TEST_BOOKINGS", format.format(tuesday));
        Log.d("TEST_BOOKINGS", format.format(wednesday));
        Log.d("TEST_BOOKINGS", format.format(thursday));
        Log.d("TEST_BOOKINGS", format.format(friday));
        Log.d("TEST_BOOKINGS", format.format(saturday));
        Log.d("TEST_BOOKINGS", format.format(sunday));
        ArrayList<BookingModel> bookingDay1 = getBookingsPerDay(bookings, format.format(monday));
        ArrayList<BookingModel> bookingDay2 = getBookingsPerDay(bookings, format.format(tuesday));
        ArrayList<BookingModel> bookingDay3 = getBookingsPerDay(bookings, format.format(wednesday));
        ArrayList<BookingModel> bookingDay4 = getBookingsPerDay(bookings, format.format(thursday));
        ArrayList<BookingModel> bookingDay5 = getBookingsPerDay(bookings, format.format(friday));
        ArrayList<BookingModel> bookingDay6 = getBookingsPerDay(bookings, format.format(saturday));
        ArrayList<BookingModel> bookingDay7 = getBookingsPerDay(bookings, format.format(sunday));
        entry1 = getEntryValue(bookingDay1);
        entry2 = getEntryValue(bookingDay2);
        entry3 = getEntryValue(bookingDay3);
        entry4 = getEntryValue(bookingDay4);
        entry5 = getEntryValue(bookingDay5);
        entry6 = getEntryValue(bookingDay6);
        entry7 = getEntryValue(bookingDay7);

        getData();
    }


    private int getEntryValue(ArrayList<BookingModel> bookings){
        int value = 0;
        for(BookingModel item: bookings){
            value += item.getTotal();
        }
        return value;
    }

    private ArrayList<BookingModel> getBookingsPerDay(ArrayList<BookingModel> bookings, String dateString){
        ArrayList<BookingModel> dayBookings = new ArrayList<BookingModel>();
        for (BookingModel item: bookings){
            if(item.getDate().contains(dateString)){
                dayBookings.add(item);
            }
        }
        return dayBookings;
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