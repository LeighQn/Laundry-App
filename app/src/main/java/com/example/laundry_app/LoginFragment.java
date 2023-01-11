package com.example.laundry_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundry_app.API.INTERFACE.LoginInterface;
import com.example.laundry_app.API.MODELCLASS.Login;
import com.example.laundry_app.USERS.Admin.AdminDashboard;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.USERS.Staff.DashboardActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LoginFragment extends Fragment{

    // ______________________________ VARIABLES ______________________________ //
    // ______________________________ VARIABLES ______________________________ //
    private String username, password, role, token, phone, name, address;



    // ______________________________ COMPONENTS ______________________________ //
    // ______________________________ COMPONENTS ______________________________ //
    private Button btnLogin;
    private EditText etxtUsername, etxtPassword;
    private TextView txtForgotPass;
    Retrofit retrofit = Global.retrofitConnect();





    // ______________________________ OBJECTS ______________________________ //
    // ______________________________ OBJECTS ______________________________ //
    //JsonPlaceHolderApi is a DataClass/ Interface
    private LoginInterface loginInterface;
    private Login login;
    private Intent intent;


    MainActivity m = (MainActivity) getActivity();


    // ======================================================================================================================================================== //
    // ======================================================================================================================================================== //


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login, null);


        // ============================== INITIALIZE ============================== //
        // ============================== INITIALIZE ============================== //

        btnLogin = (Button) root.findViewById(R.id.btn_login);
        etxtUsername = (EditText) root.findViewById(R.id.etxt_username);
        etxtPassword = (EditText) root.findViewById(R.id.etxt_password);
        txtForgotPass = (TextView) root.findViewById(R.id.txt_forgot_pass_link);



        // ============================== RETROFIT ============================== //
        // ============================== RETROFIT ============================== //

        loginInterface = retrofit.create(LoginInterface.class);


        // ============================================================ FUNCTIONS ============================================================ //
        // ============================================================ FUNCTIONS ============================================================ //


        // ______________________________ Login Button ______________________________ //
        // ______________________________ Login Button ______________________________ //

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLogin();
            }
        });

        return root;
    }

    // ============================================================ METHODS ============================================================ //
    // ============================================================ METHODS ============================================================ //

    private void getLogin(){

        username = etxtUsername.getText().toString();
        password = etxtPassword.getText().toString();

        login = new Login(username, password);


        Call<Login> call = loginInterface.handleLogin(login);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                if(username.isEmpty() || password.isEmpty()){

                    Toast.makeText(getActivity(), "The fields must not be empty", Toast.LENGTH_SHORT).show();

                }else if(!response.isSuccessful()){

                    Toast.makeText(getActivity(), "Wrong username or password.", Toast.LENGTH_LONG).show();

                    return;
                }else {

                    // add response.body().getUserId() to the parameter
                    Login loginResponse = new Login(response.body().getMessage(), response.body().getToken(), response.body().getUser());
                    role = String.valueOf(loginResponse.getUser().getRole());
                    token = response.body().getToken().toString();
                    phone = String.valueOf(loginResponse.getUser().getMobileNumber());
                    name = String.valueOf(loginResponse.getUser().getName());
                    address = String.valueOf(loginResponse.getUser().getAddress());
                    username = String.valueOf(loginResponse.getUser().getUsername());

                    //  userId = "24";

                    Global.setToken(token);
                    redirectedToAssignedRoleScreen(role, token);

                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(getActivity(),"Here" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    // ______________________________ OPEN TO ASSIGNED ROLE SCREEN ______________________________ //
    // ______________________________ OPEN TO ASSIGNED ROLE SCREEN______________________________ //
//
    private void redirectedToAssignedRoleScreen(String role, String token){

        if(role.equals("1")){

            passData(AdminDashboard.class, "Admin: ");

        }else if(role.equals("2")){

            passData(DashboardActivity.class, "Staff: ");

        }else if(role.equals("3")){

            passData(CustomerDashboard.class, "Customer: ");

        }else if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(getActivity(), "The username or password must not be empty", Toast.LENGTH_LONG);
        }else {
            Toast.makeText(getActivity(), "Invalid Username or Password.", Toast.LENGTH_LONG).show();
        }

    }

    private void passData(Class classes, String string){

        intent = new Intent(getActivity(), classes);
        intent.putExtra("token", token);
        startActivity(intent);
        Toast.makeText(getActivity(), string.toString() + name, Toast.LENGTH_SHORT).show();
    }

}