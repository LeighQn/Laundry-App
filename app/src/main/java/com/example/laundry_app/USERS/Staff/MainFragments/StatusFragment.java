package com.example.laundry_app.USERS.Staff.MainFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundry_app.ADAPTERS.Customer.BookingAdapter;
import com.example.laundry_app.ADAPTERS.NewStatusAdapter;
import com.example.laundry_app.API.INTERFACE.BookingInterface;
import com.example.laundry_app.API.INTERFACE.NewStatusInterface;
import com.example.laundry_app.API.INTERFACE.SalesInterface;
import com.example.laundry_app.API.MODELCLASS.BookingModel;
import com.example.laundry_app.API.MODELCLASS.BookingsRequest;
import com.example.laundry_app.API.MODELCLASS.NewStatusModel;
import com.example.laundry_app.APIClient;
import com.example.laundry_app.Global;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Admin.AdminDashboard;
import com.example.laundry_app.USERS.Customer.Screens.CustomerProfileUpdate;
import com.example.laundry_app.USERS.Staff.DashboardActivity;
import com.example.laundry_app.USERS.Staff.MapActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class StatusFragment extends Fragment implements BookingAdapter.RecyclerViewClickListener {

//    NewStatusAdapter newStatusAdapter;
//    NewStatusInterface newStatusInterface;
//    private RecyclerView recyclerView;
//    private Button btnTry;
//    int userId = 25;
//    private NewStatusAdapter.RecyclerViewClickListener listener;
//    Spinner spinnerType;
//    String bookingType;
//
//    String token, finalToken, latitude, longitude;
//
//    Retrofit retrofit = Global.retrofitConnectFakeApi();

    DashboardActivity dashboardActivity;
    BookingInterface bookingInterface;
    SalesInterface salesInterface;
    BookingAdapter bookingAdapter;
    RecyclerView recyclerView;
    Spinner spinner;
    private BookingAdapter.RecyclerViewClickListener listener;

    private ArrayList<BookingModel> bookingModelList = new ArrayList<BookingModel>();

    String token, finalToken, role, type;
    Intent intent;

    String ip = Global.getIp();
    Retrofit retrofit =Global.setIpRetrofit(ip);




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_status_staff, null);

        recyclerView = root.findViewById(R.id.rv_staff_status);
        spinner = (Spinner) root.findViewById(R.id.spinner_out_for_delivery);


        bookingInterface = retrofit.create(BookingInterface.class);

        // ====================================== EXECUTE METHODS ====================================== //
        // ====================================== EXECUTE METHODS ====================================== //

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        bookingAdapter = new BookingAdapter();


        getDataFromActivity();
        getBookings();
        spinnerExecution();
        setOnClickListerner();




        return root;
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

                bookingAdapter.setBookingsListDatas(bookingModelList, listener);
                recyclerView.setAdapter(bookingAdapter);

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
        dashboardActivity = (DashboardActivity) getActivity();
        token = dashboardActivity.getMyToken();
        role = dashboardActivity.getMyRole();

    }


    private void sendTokenToMap(View view, int position){
        BookingModel booking = bookingModelList.get(position);
        intent = new Intent(getActivity(), MapActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("bookingID", booking.get_id());
        startActivity(intent);

    }


    @Override
    public void onClick(View v, int position) {

    }


//
//
//
//
//
//
//
//
//
//
//    private void getCustomerDeliverList(){
//        finalToken = "Bearer " + token;
//
//        Call<List<NewStatusModel>> call = newStatusInterface.getAllForDeliveryStatus();
//        call.enqueue(new Callback<List<NewStatusModel>>() {
//            @Override
//            public void onResponse(Call<List<NewStatusModel>> call, Response<List<NewStatusModel>> response) {
//                if(!response.isSuccessful()){
//                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                List<NewStatusModel> newStatusModelResponse = response.body();
//
//                newStatusAdapter.setNewStatusData(newStatusModelResponse, listener);
//                recyclerView.setAdapter(newStatusAdapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<NewStatusModel>> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }
//
//    private void setOnClickListener(){
//        listener = new NewStatusAdapter.RecyclerViewClickListener() {
//            @Override
//            public void onClick(View v, int position) {
//                Intent intent = new Intent(getActivity(),  MapActivity.class);
//                intent.putExtra("latitude", 7.6742);
//                intent.putExtra("longitude", 124.9897);
//                startActivity(intent);
//            }
//        };
//    }
//
//    // ______________________________ SPINNER EXECUTION ______________________________ //
//    // ______________________________ SPINNER EXECUTION ______________________________ //
//
//    private void spinnerExecution(){
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.type, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerType.setAdapter(adapter);
//        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                bookingType = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(adapterView.getContext(), bookingType, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }
}