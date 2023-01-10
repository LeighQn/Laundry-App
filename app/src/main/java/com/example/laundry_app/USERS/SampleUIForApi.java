package com.example.laundry_app.USERS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundry_app.API.INTERFACE.LoginInterface;
import com.example.laundry_app.API.MODELCLASS.Login;
import com.example.laundry_app.LoginFragment;
import com.example.laundry_app.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SampleUIForApi extends AppCompatActivity {

    private TextView txtResult;
    private Button btnGet, btnPost, btnPut, btnPatch, btnDelete;
    //JsonPlaceHolderApi is a DataClass/ Interface
    private LoginInterface loginInterface;
    private RecyclerView recyclerView;
    SampleUIAdapter sampleUIAdapter;

    int userId = 25;
    String username = "The New TItle", password = "The New Text", password1 = "Try" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_uifor_api);

        txtResult = findViewById(R.id.txt_result);
        btnGet = findViewById(R.id.btn_get);
        btnPost = findViewById(R.id.btn_post);
        btnPut = findViewById(R.id.btn_put);
        btnPatch = findViewById(R.id.btn_patch);
        btnDelete = findViewById(R.id.btn_delete);
        recyclerView = findViewById(R.id.recyclerview1);


        // ====================================== RETROFIT ====================================== //
        // ====================================== RETROFIT ====================================== //

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        loginInterface = retrofit.create(LoginInterface.class);

        // ====================================== END RETROFIT ====================================== //
        // ====================================== END RETROFIT ====================================== //

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        sampleUIAdapter = new SampleUIAdapter();

        //getAllPost();


        //getPost();
//        createPost();
//        putPost();
//        patchPost();
//        deletePost();

    }

    // GET EXECUTION
    private void getPost(){

//        btnGet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Call<List<Login>> call = loginInterface.getLogin(userId);
//                call.enqueue(new Callback<List<Login>>() {
//                    @Override
//                    public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
//                        if(!response.isSuccessful()){
//                            txtResult.setText("Code: " + response.code());
//                            return;
//                        }
//
//                        List<Login> logins = response.body();
//
//                        for (Login login: logins){
//                            String content = "";
//                            content += "Username: " + login.getUsername() + "\n";
//                            content += "Password: " + login.getPassword() + "\n";
//
//                            txtResult.append(content);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Login>> call, Throwable t) {
//                        txtResult.setText(t.getMessage());
//                    }
//                });
//            }
//        });
////        Call<List<Login>> call = loginInterface.getLogin(userId);
////        call.enqueue(new Callback<List<Login>>() {
////            @Override
////            public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
////                if(!response.isSuccessful()){
////                    txtResult.setText("Code: " + response.code());
////                    return;
////                }
////
////                List<Login> logins = response.body();
////
////                for (Login login: logins){
////                    String content = "";
////                    content += "ID: " + login.getId() + "\n";
////                    content += "User ID: " + login.getUserId() + "\n";
////                    content += "Username: " + login.getUsername() + "\n";
////                    content += "Password: " + login.getPassword() + "\n";
////
////                    txtResult.append(content);
////                }
////            }
////
////            @Override
////            public void onFailure(Call<List<Login>> call, Throwable t) {
////                txtResult.setText(t.getMessage());
////            }
////        });
//    }
//
//
//    // POST EXECUTION
//    private void createPost(){
//
//        btnPost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Login login = new Login(username, password, userId);
//
//                Call<Login>call = loginInterface.createPost(login);
//                call.enqueue(new Callback<Login>() {
//                    @Override
//                    public void onResponse(Call<Login> call, Response<Login> response) {
//                        if(!response.isSuccessful()){
//                            Toast.makeText(SampleUIForApi.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                        Login loginResponse = response.body();
//
//                        String content = "";
//                        content += "ID: " + login.getId() + "\n";
//                        content += "User ID: " + login.getUserId() + "\n";
//                        content += "Username: " + login.getUsername() + "\n";
//                        content += "Password: " + login.getPassword() + "\n";
//
//                        Toast.makeText(SampleUIForApi.this, content, Toast.LENGTH_SHORT).show();
//                        txtResult.setText("Code: " + response.code());
//
//                        if(login.getPassword().equals(password1)){
//                            Toast.makeText(SampleUIForApi.this, "Goods", Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(SampleUIForApi.this, "Nah ah", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Login> call, Throwable t) {
//                        txtResult.setText(t.getMessage());
//                    }
//                });
//            }
//        });
////        Login login = new Login(username, password, userId);
////
////        Call<Login>call = loginInterface.createPost(login);
////        call.enqueue(new Callback<Login>() {
////            @Override
////            public void onResponse(Call<Login> call, Response<Login> response) {
////                if(!response.isSuccessful()){
////                    txtResult.setText("Code: " + response.code());
////                    return;
////                }
////
////                Login loginResponse = response.body();
////
////                String content = "";
////                content += "ID: " + login.getId() + "\n";
////                content += "User ID: " + login.getUserId() + "\n";
////                content += "Username: " + login.getUsername() + "\n";
////                content += "Password: " + login.getPassword() + "\n";
////
////                txtResult.setText(content);
////            }
////
////            @Override
////            public void onFailure(Call<Login> call, Throwable t) {
////                txtResult.setText(t.getMessage());
////            }
////        });
//
//    }
//
//    // PUT EXECUTION
//    private void putPost(){
//
//        btnPut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Login login = new Login("hannah", null, 25);
//
//                // .patchPost will only change the userId and text since title is initialized as null in the sample
//                // .putPost will completely change the entire value including changing the value of title to null
//
//                Call<Login> call = loginInterface.putLogin(5, login);
//                call.enqueue(new Callback<Login>() {
//                    @Override
//                    public void onResponse(Call<Login> call, Response<Login> response) {
//
//                        Login updatePost = response.body();
//
//                        String content = "";
//                        content += "ID: " + login.getId() + "\n";
//                        content += "User ID: " + login.getUserId() + "\n";
//                        content += "Username: " + login.getUsername() + "\n";
//                        content += "Password: " + login.getPassword() + "\n";
//
//                        txtResult.setText(content);
//                    }
//
//                    @Override
//                    public void onFailure(Call<Login> call, Throwable t) {
//                        txtResult.setText(t.getMessage());
//                    }
//                });
//            }
//        });
//    }
//
//    // PATCH EXECUTION
//    private void patchPost(){
//
//        btnPatch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Login login = new Login("hannah", "hannahspass", 25);
//
//                // .patchPost will only change the userId and text since title is initialized as null in the sample
//                // .putPost will completely change the entire value including changing the value of title to null
//
//                Call<Login> call = loginInterface.patchPost(5, login);
//                call.enqueue(new Callback<Login>() {
//                    @Override
//                    public void onResponse(Call<Login> call, Response<Login> response) {
//
//                        Login updatePost = response.body();
//
//                        String content = "";
//                        content += "ID: " + login.getId() + "\n";
//                        content += "User ID: " + login.getUserId() + "\n";
//                        content += "Username: " + login.getUsername() + "\n";
//                        content += "Password: " + login.getPassword() + "\n";
//
//                        txtResult.setText(content);
//                    }
//
//                    @Override
//                    public void onFailure(Call<Login> call, Throwable t) {
//                        txtResult.setText(t.getMessage());
//                    }
//                });
//            }
//        });
//    }
//
//    // ___________________ DELETE ___________________ //
//    // ___________________ DELETE ___________________ //
//
//    private void deletePost() {
//
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Call<Void> call = loginInterface.deletePOst(5);
//                call.enqueue(new Callback<Void>() {
//                    @Override
//                    public void onResponse(Call<Void> call, Response<Void> response) {
//                        txtResult.setText("Code: " + response.code());
//                    }
//
//                    @Override
//                    public void onFailure(Call<Void> call, Throwable t) {
//                        txtResult.setText(t.getMessage());
//                    }
//                });
//            }
//        });
//    }
//
//    private void getAllPost(){
//
//        Call<List<Login>> call = loginInterface.getLogin(userId);
//        call.enqueue(new Callback<List<Login>>() {
//            @Override
//            public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
//
//                if(!response.isSuccessful()){
//             //       txtResult.setText("Code: " + response.code());
//                    Toast.makeText(SampleUIForApi.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                List<Login> sampleUiResponse = response.body();
//
//                sampleUIAdapter.setSampleUIData(sampleUiResponse);
//
//                recyclerView.setAdapter(sampleUIAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Login>> call, Throwable t) {
//                Toast.makeText(SampleUIForApi.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}