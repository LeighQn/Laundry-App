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
    private List<BookingModel> bookingModelList;

    AdminDashboard adminDashboard;
    BookingInterface bookingInterface;
    SalesInterface salesInterface;
    BookingAdapter bookingAdapter;


    String token, finalToken, role;
    Retrofit retrofit = APIClient.getClient();


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        // ====================================== INITIALIZE ====================================== //
        // ====================================== INITIALIZE ====================================== //

        recyclerView = view.findViewById(R.id.rv_sales_admin);


        bookingInterface = retrofit.create(BookingInterface.class);

        Toast.makeText(getActivity(), "Get token here: " + token, Toast.LENGTH_SHORT).show();

        // ====================================== EXECUTE METHODS ====================================== //
        // ====================================== EXECUTE METHODS ====================================== //



        // ====================================== FUNCTIONS ====================================== //
        // ====================================== FUNCTIONS ====================================== //

        // ====================================== RecyclerView ====================================== //

//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setHasFixedSize(true);
//        BookingAdapter bookingAdapter = new BookingAdapter(getContext(), bookingModelList);
//        recyclerView.setAdapter(salesAdapter);
//        salesAdapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        bookingAdapter = new BookingAdapter(bookingModelList, getContext());

        getDataFromActivity();
        getBookings();



//        getDataFromActivity();
//        getCustomerProfile();

    }
//
//    private void salesDataInitialize() {
//        salesArrayList = new ArrayList<Sales>();
//
//        salesDate = new String[]{
//                getString(R.string.date1),
//                getString(R.string.date2),
//                getString(R.string.date3),
//        };
//        salesCustomerName = new String[]{
//                getString(R.string.customer1),
//                getString(R.string.customer2),
//                getString(R.string.customer3),
//        };
//
//        salesTotal = new String[]{
//                getString(R.string.total1),
//                getString(R.string.total2),
//                getString(R.string.total3),
//        };
//
//        for(int i=0; i < salesDate.length; i++){
//
//            Sales sales = new Sales(salesDate[i], salesCustomerName[i], salesTotal[i] );
//            salesArrayList.add(sales);
//        }
//    }

    private void getDataFromActivity(){
        adminDashboard = (AdminDashboard) getActivity();
        token = adminDashboard.getMyToken();

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