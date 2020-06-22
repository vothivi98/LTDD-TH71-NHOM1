package com.example.shoesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.model.Categories;
import com.example.model.Products;
import com.example.myadapter.ProductAdapter;
import com.example.myadapter.RecyclerViewAdapter;
import com.example.ultil.VolleySingleton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
public class CategoryActivity extends AppCompatActivity  implements RecyclerViewAdapter.OnItemClickListener{
    ArrayList<Categories> imageURLs;
    ArrayList<Products> arrayProduct;
    ProductAdapter productAdapter;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerCate, listProduct;
    int cateId1 = 1;
    private DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mapping();
        //Get Cate
        mData.child("Category").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dt : dataSnapshot.getChildren()){
                    Categories cate = dt.getValue(Categories.class);
                    imageURLs.add(cate);
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CategoryActivity.this, "Error:" +
                        databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

        //mac dinh lay id cua loai 1
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            cateId1 = bundle.getInt("cateId");
        }
        mData.child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dt : dataSnapshot.getChildren()){
                    Products cate = dt.getValue(Products.class);
                    if(cate.getCateId() == cateId1) {
                        arrayProduct.add(cate);
                        productAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CategoryActivity.this, "Error:" +
                        databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_cart:
                //hiện giỏ hàng
                Intent intents = new Intent(this, OrderActivity.class);
                intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intents);
                return true;
            case android.R.id.home:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
                //this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Giỏ hàng
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navbottom_items,menu);
        return true;
    }

    public void mapping(){
        mData = FirebaseDatabase.getInstance().getReference();
        //cate
        imageURLs = new ArrayList<>();
        recyclerCate = (RecyclerView)findViewById(R.id.recyclerView_Logo);
        recyclerViewAdapter = new RecyclerViewAdapter(this, imageURLs, this);
        recyclerCate.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerCate.setAdapter(recyclerViewAdapter);

        //product
        arrayProduct = new ArrayList<>();
        listProduct = (RecyclerView)findViewById(R.id.listProduct);
        productAdapter = new ProductAdapter(this, arrayProduct);
        listProduct.setHasFixedSize(true);
        listProduct.setLayoutManager(new GridLayoutManager(this, 2));
        listProduct.setAdapter(productAdapter);
    }

    @Override
    public void onItemClickListener(int position) {
        final int idCate = imageURLs.get(position).getCateId();

        //clear
        arrayProduct.clear();
        productAdapter.notifyDataSetChanged();
        mData.child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dt : dataSnapshot.getChildren()){
                    Products pro = dt.getValue(Products.class);
                    if(pro.getCateId() == idCate) {
                        arrayProduct.add(pro);
                        productAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CategoryActivity.this, "Error:" +
                        databaseError.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
