package com.example.laundry_app.USERS.Admin.MainFragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.laundry_app.ADAPTERS.Customer.BookingAdapter;
import com.example.laundry_app.ADAPTERS.RecordAdapter;
import com.example.laundry_app.API.INTERFACE.BookingInterface;
import com.example.laundry_app.API.MODELCLASS.BookingModel;
import com.example.laundry_app.API.MODELCLASS.BookingsRequest;
import com.example.laundry_app.Global;
import com.example.laundry_app.USERS.Admin.AdminDashboard;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Staff.MapActivity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class AdminRecordFragment extends Fragment {



    AdminDashboard adminDashboard;
    BookingInterface bookingInterface;
    RecordAdapter recordAdapter;
    RecyclerView recyclerView;
    Spinner spinner;
    Button btnDatePicker, btnDateSearch, btnAll;

    private DatePickerDialog datePickerDialog;
    private BookingAdapter.RecyclerViewClickListener listener;

    private ArrayList<BookingModel> bookingModelList = new ArrayList<BookingModel>();
    ArrayList<BookingModel> filteredList = new ArrayList<>();
    ArrayList<BookingModel> itemList = new ArrayList<>();

    String token, finalToken, role, type, datePicked;
    Intent intent;

    String ip = Global.getIp();
    Retrofit retrofit =Global.setIpRetrofit(ip);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_status_staff, null);


        recyclerView = root.findViewById(R.id.rv_staff_status);
        spinner = (Spinner) root.findViewById(R.id.spinner_out_for_delivery);
        btnDatePicker = root.findViewById(R.id.btn_date);
        btnDateSearch = root.findViewById(R.id.btn_search);
        btnAll = root.findViewById(R.id.btn_all);

        spinner.setVisibility(View.GONE);
        btnDatePicker.setVisibility(View.VISIBLE);
        btnDatePicker.setText("Pick a date");
        btnDateSearch.setVisibility(View.VISIBLE);
        btnAll.setVisibility(View.VISIBLE);


        bookingInterface = retrofit.create(BookingInterface.class);

        // ====================================== EXECUTE METHODS ====================================== //
        // ====================================== EXECUTE METHODS ====================================== //

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recordAdapter = new RecordAdapter();


        getDataFromActivity();
        getBookings();
        spinnerExecution();
        setOnClickListerner();

        Global.setDate(datePicked);


        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDatePicker();
                datePickerDialog.show();
            }
        });

        btnDateSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filteredList.clear();
                itemList.clear();
                datePicked = btnDatePicker.getText().toString();
                filter(datePicked);
            }
        });

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filteredList.clear();
                itemList.clear();

                for(BookingModel item: bookingModelList){
                    if(item.getDate().contains("/")){
                        itemList.add(item);
                        btnDatePicker.setText("Pick a date");
                    }
                }
                recordAdapter.filteredList(itemList);
            }
        });



        return root;
    }

    private void filter(String s){

        for (BookingModel item: bookingModelList){
            if(item.getDate().contains(s)){
                filteredList.add(item);
            }
        }
        recordAdapter.filteredList(filteredList);
    }




    private void getBookings(){
        finalToken = "Bearer " + token;
        Call<BookingsRequest> call = bookingInterface.getBookings(finalToken);
        call.enqueue(new Callback<BookingsRequest>() {
            @Override
            public void onResponse(Call<BookingsRequest> call, Response<BookingsRequest> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Admin Sale Fragment Code:" + response.code(), Toast.LENGTH_SHORT).show();
                    bookingModelList.clear();
                    return;
                }

                BookingsRequest  bookingsRequestResponse= response.body();
                bookingModelList = bookingsRequestResponse.getBookings();

                recordAdapter.setBookingsListDataInRecord(bookingModelList, listener);
                recyclerView.setAdapter(recordAdapter);

            }

            @Override
            public void onFailure(Call<BookingsRequest> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    // ______________________________ SPINNER EXECUTION ______________________________ //
    // ______________________________ SPINNER EXECUTION ______________________________ //

    private void spinnerExecution(){
        Log.d("STATS_FRAG", "GOT HERE");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.laudry_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int type = (int) adapterView.getSelectedItemPosition();
                ArrayList<BookingModel> newBookings = new ArrayList<BookingModel>();
                for(int x = 0; x < newBookings.size(); x++){
                    BookingModel booking = newBookings.get(x);
                    if(booking.getStatus() == type){
                        newBookings.add(booking);
                    }
                }
                bookingModelList = newBookings;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void setOnClickListerner(){
        listener = (v, position) -> sendTokenToMap(v, position);
    }


    private void getDataFromActivity(){
        adminDashboard = (AdminDashboard) getActivity();
        token = adminDashboard.getMyTokenAdmin();
    }


    private void sendTokenToMap(View view, int position){
        BookingModel booking = bookingModelList.get(position);
        intent = new Intent(getActivity(), MapActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("bookingID", booking.get_id());
        startActivity(intent);

    }


    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);

                btnDatePicker.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(getActivity(), style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return year + "/" + getMonthFormat(month) + "/" + day;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "01";
        if(month == 2)
            return "02";
        if(month == 3)
            return "03";
        if(month == 4)
            return "04";
        if(month == 5)
            return "05";
        if(month == 6)
            return "06";
        if(month == 7)
            return "07";
        if(month == 8)
            return "08";
        if(month == 9)
            return "09";
        if(month == 10)
            return "10";
        if(month == 11)
            return "11";
        if(month == 12)
            return "12";

        //default should never happen
        return "JAN";
    }

    public String getDatePicked(){
        return datePicked;
    }

}