package com.example.shoesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.Discounts;
import com.example.model.Orders;
import com.example.myadapter.DiscountAdapter;
import com.example.myadapter.ViewOrdersAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ViewOrdersActivity extends AppCompatActivity {
    public RecyclerView rcViewOrders;
    public ArrayList<Orders> arrayViewOrders;
    public ViewOrdersAdapter viewOrderAdapter;
    public DatabaseReference mData;
    TextView txtThongbao;
    ImageView image;
    String co;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Đơn hàng");
        mapping();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            co = bundle.getString("order1");

        }
        if(co != null){
            loadOrders2();
        }else {
            loadOrders();
        }

    }
    //đơn hàng đã thanh toán
    public  void loadOrders2(){
        mData.child("Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    Orders orders = dt.getValue(Orders.class);

                    if(orders.isCheck() && MainActivity.mUser.getUid().trim().equals(orders.getUserId().trim())){
                        arrayViewOrders.add(orders);
                        viewOrderAdapter.notifyDataSetChanged();
                    }
                }
                checkData();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error:" +
                        databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }


        });
    }

    public void loadOrders() {
        mData.child("Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    Orders orders = dt.getValue(Orders.class);

                    if(!orders.isCheck() && MainActivity.mUser.getUid().trim().equals(orders.getUserId().trim())){
                        arrayViewOrders.add(orders);
                        viewOrderAdapter.notifyDataSetChanged();
                    }
                }
                checkData();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error:" +
                        databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }


        });

    }

    public void checkData() {
        if(arrayViewOrders.size() <= 0){
            viewOrderAdapter.notifyDataSetChanged();
            //hiện thông báo k có sp
            txtThongbao.setVisibility(View.VISIBLE);
            image.setVisibility(View.VISIBLE);
            rcViewOrders.setVisibility(View.INVISIBLE);

        }else {
            viewOrderAdapter.notifyDataSetChanged();
            //hiện thông báo k có sp
            txtThongbao.setVisibility(View.INVISIBLE);
            image.setVisibility(View.INVISIBLE);
            rcViewOrders.setVisibility(View.VISIBLE);

        }
    }


    public void mapping(){
        rcViewOrders = findViewById(R.id.rcViewOrders);
        txtThongbao = findViewById(R.id.txtThongBao);
        image = findViewById(R.id.imgThongBao);
        mData = FirebaseDatabase.getInstance().getReference();
        arrayViewOrders = new ArrayList<>();
        viewOrderAdapter = new ViewOrdersAdapter(getApplicationContext(), arrayViewOrders);
        rcViewOrders.setHasFixedSize(true);
        rcViewOrders.setLayoutManager(new GridLayoutManager(this, 1));
        rcViewOrders.setAdapter(viewOrderAdapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home)
        {
            if(MainActivity.anXacNhan){
                MainActivity.anXacNhan = false;
            }
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
