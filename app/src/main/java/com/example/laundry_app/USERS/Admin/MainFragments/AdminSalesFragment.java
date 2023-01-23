package com.example.laundry_app.USERS.Admin.MainFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundry_app.ADAPTERS.Customer.BookingAdapter;
import com.example.laundry_app.ADAPTERS.NewStatusAdapter;
import com.example.laundry_app.API.INTERFACE.BookingInterface;
import com.example.laundry_app.API.INTERFACE.Customer.CustomerProfileInterface;
import com.example.laundry_app.API.INTERFACE.SalesInterface;
import com.example.laundry_app.API.MODELCLASS.BookingModel;
import com.example.laundry_app.API.MODELCLASS.BookingsRequest;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerProfileModel;
import com.example.laundry_app.API.MODELCLASS.SalesModel;
import com.example.laundry_app.APIClient;
import com.example.laundry_app.Global;
import com.example.laundry_app.USERS.Admin.AdminDashboard;
import com.example.laundry_app.USERS.Admin.MainFragments.AdaptersAndDataClass.Sales;
import com.example.laundry_app.USERS.Admin.MainFragments.AdaptersAndDataClass.SalesAdapter;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Staff.DashboardActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class AdminSalesFragment extends Fragment {

    // ====================================== COMPONENTS ====================================== //
    // ====================================== COMPONENTS ====================================== //

    //  private ArrayList<Sales> salesArrayList;
    private String[] salesDate;
    private String[] salesCustomerName;
    private String[] salesTotal;
    private RecyclerView recyclerView;
    private ArrayList<BookingModel> bookingModelList;

    AdminDashboard adminDashboard;
    BookingInterface bookingInterface;
    SalesInterface salesInterface;
    BookingAdapter bookingAdapter;
    TextView txtTitle;


    String token, finalToken, role;
    Retrofit retrofit = APIClient.getClient();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_status_staff, null);


        // ====================================== INITIALIZE ====================================== //
        // ====================================== INITIALIZE ====================================== //

        recyclerView = root.findViewById(R.id.rv_sales_admin);
        txtTitle = root.findViewById(R.id.txt_out_for_delivery);

        txtTitle.setText("SALES");


        bookingInterface = retrofit.create(BookingInterface.class);

        Toast.makeText(getActivity(), "Get token here: " + token, Toast.LENGTH_SHORT).show();

        // ====================================== EXECUTE METHODS ====================================== //
        // ====================================== EXECUTE METHODS ====================================== //



        // ====================================== FUNCTIONS ====================================== //
        // ====================================== FUNCTIONS ====================================== //

        // ====================================== RecyclerView ====================================== //


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        bookingAdapter = new BookingAdapter();

        getDataFromActivity();
        getBookings();





        return root;

    }

    private void getDataFromActivity(){
        adminDashboard = (AdminDashboard) getActivity();
        token = adminDashboard.getMyTokenAdmin();

    }

    private void getBookings(){
        finalToken = "Bearer " + token;
        Call<BookingsRequest> call = bookingInterface.getBookings(finalToken);
        call.enqueue(new Callback<BookingsRequest>() {
            @Override
            public void onResponse(Call<BookingsRequest> call, Response<BookingsRequest> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Admin Sale Fragment Code:" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                BookingsRequest  bookingsRequestResponse= response.body();
                bookingModelList = bookingsRequestResponse.getBookings();

                recyclerView.setAdapter(bookingAdapter);

            }

            @Override
            public void onFailure(Call<BookingsRequest> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}