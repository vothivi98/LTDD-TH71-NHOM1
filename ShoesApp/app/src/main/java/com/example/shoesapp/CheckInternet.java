package com.example.shoesapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CheckInternet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_internet);


    }

//    protected void checkInterNet(){
//        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext()
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo network = connectivityManager.getActiveNetworkInfo();
//
//        if(network == null || !network.isConnected() || !network.isAvailable()){
//
//        }
//    }


}
