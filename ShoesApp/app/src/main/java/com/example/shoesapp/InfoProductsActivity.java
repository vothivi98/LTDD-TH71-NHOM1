package com.example.shoesapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.model.Cart;
import com.example.model.ImageProduct;
import com.example.model.Products;
import com.example.myadapter.SliderAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class InfoProductsActivity extends AppCompatActivity {
    TextView gia, thongtin, tenSp;
    ArrayList<ImageProduct> arr;
    SliderAdapter sliderAdapter;
    String id = "";
    Products products;
    boolean btnSelected = false; //mặc  định size k dc cho
    ViewPager2 viewPager2;
    private DatabaseReference mData;

    private Button btntru, btncong, btnuk7, btnuk8, btnuk9, btnuk10, btnDh, btnMuaNgay;

    private TextView txtsoluong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //fullscreen
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_infoproducts);
        mapping1();

        //congsl
        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btntru.setEnabled(true);
                int sl = Integer.parseInt(txtsoluong.getText().toString()) + 1;
                txtsoluong.setText(String.valueOf(sl));
                btncong.setVisibility(View.VISIBLE);
                btntru.setVisibility(View.VISIBLE);
                if(sl >= 10) {
                    btncong.setVisibility(View.INVISIBLE);
                    btntru.setVisibility(View.VISIBLE);

                }
            }
        });
        //trusl
        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btncong.setEnabled(true);
                int sl = Integer.parseInt(txtsoluong.getText().toString()) - 1;
                btncong.setVisibility(View.VISIBLE);
                btntru.setVisibility(View.VISIBLE);
                txtsoluong.setText(String.valueOf(sl));
                if(sl <= 0) {
                    btntru.setVisibility(View.INVISIBLE);
                    btncong.setVisibility(View.VISIBLE);
                }
            }
        });
        txtsoluong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(Integer.parseInt(txtsoluong.getText().toString()) == 10){
                    btncong.setEnabled(false);
                }
                if(Integer.parseInt(txtsoluong.getText().toString()) == 0){
                    btntru.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        AddCartProduct();
    }
    View.OnClickListener uListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.btn7uk:
                    boolean uk8 = btnuk8.isEnabled();//k vô hiệu hóa
                    btnuk8.setEnabled(!uk8);// vô hiệu hóa
                    boolean uk9 = btnuk9.isEnabled();
                    btnuk9.setEnabled(!uk9);
                    boolean uk10 = btnuk10.isEnabled();
                    btnuk10.setEnabled(!uk10);
                    btnSelected = !btnuk8.isEnabled();
                    break;
                case R.id.btn8uk:
                    boolean uk7 = btnuk7.isEnabled();
                    btnuk7.setEnabled(!uk7);
                    boolean a = btnuk9.isEnabled();
                    btnuk9.setEnabled(!a);
                    boolean b = btnuk10.isEnabled();
                    btnuk10.setEnabled(!b);
                    btnSelected = !btnuk7.isEnabled();
                    break;
                case R.id.btn9uk:
                    boolean c = btnuk8.isEnabled();
                    btnuk8.setEnabled(!c);
                    boolean d = btnuk7.isEnabled();
                    btnuk7.setEnabled(!d);
                    boolean e = btnuk10.isEnabled();
                    btnuk10.setEnabled(!e);
                    btnSelected = !btnuk8.isEnabled();
                    break;
                case R.id.btn10uk:
                    boolean f = btnuk8.isEnabled();
                    btnuk8.setEnabled(!f);
                    boolean g = btnuk9.isEnabled();
                    btnuk9.setEnabled(!g);
                    boolean h = btnuk7.isEnabled();
                    btnuk7.setEnabled(!h);
                    btnSelected = !btnuk8.isEnabled();
                    break;
                default:
                    break;
            }
        }
    };
    public void AddCartProduct(){
        btnDh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.mUser != null || LoginActivity.mUser != null){
                    if(btnSelected && Integer.parseInt(txtsoluong.getText().toString()) != 0){ //chọn ca hai
                        Toast.makeText(InfoProductsActivity.this, "Thêm vào giỏ hàng thành công",
                                Toast.LENGTH_SHORT).show();
                        themVaoGioHang();
                    }else if(!btnSelected && Integer.parseInt(txtsoluong.getText().toString()) != 0){// chọn sl, k chọn size
                        Toast.makeText(InfoProductsActivity.this, "Vui lòng chọn size",
                                Toast.LENGTH_SHORT).show();
                    }else if(btnSelected && Integer.parseInt(txtsoluong.getText().toString()) == 0){ //chọn size, k chọn sl
                        Toast.makeText(InfoProductsActivity.this, "Vui lòng chọn số lượng",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(InfoProductsActivity.this, "Vui lòng chọn số lượng và size",
                                Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("inf", "yes");
                    startActivity(intent);
                }


            }
        });

        btnMuaNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.mUser != null || LoginActivity.mUser != null){
                    if(btnSelected && Integer.parseInt(txtsoluong.getText().toString()) != 0){ //chọn ca hai
                        themVaoGioHang();
                        Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }else if(!btnSelected && Integer.parseInt(txtsoluong.getText().toString()) != 0){// chọn sl, k chọn size
                        Toast.makeText(InfoProductsActivity.this, "Vui lòng chọn size",
                                Toast.LENGTH_SHORT).show();
                    }else if(btnSelected && Integer.parseInt(txtsoluong.getText().toString()) == 0){ //chọn size, k chọn sl
                        Toast.makeText(InfoProductsActivity.this, "Vui lòng chọn số lượng",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(InfoProductsActivity.this, "Vui lòng chọn số lượng và size",
                                Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("inf", "yes");
                    startActivity(intent);
                }

            }
        });
    }

    public void themVaoGioHang(){
        String size = "";

        if(btnuk7.isEnabled()){
            size = btnuk7.getText().toString();
        }else if(btnuk8.isEnabled()){
            size = btnuk8.getText().toString();
        }else if(btnuk9.isEnabled()){
            size = btnuk9.getText().toString();
        }else
            size = btnuk10.getText().toString();
        //Order
        boolean exists = false;
        if(MainActivity.arrayCart.size() > 0){
            int soluong = Integer.parseInt(txtsoluong.getText().toString());
            //kiểm tra nếu sp đã đặt thì tăng sl
            for(int i = 0; i < MainActivity.arrayCart.size(); i++){
                if(MainActivity.arrayCart.get(i).getProductId().equals(products.getProductId())){
                    // cap nhat tong sl
                    MainActivity.arrayCart.get(i).setQuantity(
                            MainActivity.arrayCart.get(i).getQuantity() + soluong);
                    //k đặt quá 50sp
                    if(MainActivity.arrayCart.get(i).getQuantity() >= 10)
                        MainActivity.arrayCart.get(i).setQuantity(10);
                    // cap nhat tong gia tien
//                                MainActivity.arrayCart.get(i).setPrice(
//                                        products.getPrice() * MainActivity.arrayCart.get(i).getQuantity());
                    exists = true;
                }
            }
            if(exists == false){
                int sl = Integer.parseInt(txtsoluong.getText().toString());
                //int giaMoi = sl * products.getPrice();
                MainActivity.arrayCart.add(new Cart(products.getProductId(),
                        products.getProductName(), products.getImgProduct(),
                        products.getPrice(), sl, size, products.getCateId()));
            }
        }else {
            int sl = Integer.parseInt(txtsoluong.getText().toString());
            //int giaMoi = sl * products.getPrice();
            MainActivity.arrayCart.add(new Cart(products.getProductId(),
                    products.getProductName(), products.getImgProduct(),
                    products.getPrice(), sl, size, products.getCateId()));
        }
    }

    public  void mapping1(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mData = FirebaseDatabase.getInstance().getReference();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            products = (Products) bundle.getSerializable("productInfor");
            arr = new ArrayList<>();
            viewPager2 = findViewById(R.id.viewPagerImageSlider);

            sliderAdapter = new SliderAdapter(arr, viewPager2, this);

            viewPager2.setAdapter(sliderAdapter);

            mData.child("ProductImage").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dt : dataSnapshot.getChildren()){
                        ImageProduct img = dt.getValue(ImageProduct.class);
                        if(img.getProductId().equalsIgnoreCase(products.getProductId())) {
                            arr.add(img);
                            sliderAdapter.notifyDataSetChanged();
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(InfoProductsActivity.this, "Error:" +
                            databaseError.getMessage() , Toast.LENGTH_SHORT).show();
                }
            });
            //mapping(name, descripiton, price);
            mapping(products.getProductName(), products.getDescription(), products.getPrice());
            loadSlider();
        }

        btntru = (Button) findViewById(R.id.btntru);
        btncong = (Button) findViewById(R.id.btncong);
        txtsoluong = (TextView) findViewById(R.id.txtsoluong);
        btntru.setVisibility(View.INVISIBLE);

        btnuk7 = (Button) findViewById(R.id.btn7uk);
        btnuk8 = (Button) findViewById(R.id.btn8uk);
        btnuk9 = (Button) findViewById(R.id.btn9uk);
        btnuk10 = (Button) findViewById(R.id.btn10uk);
        btnDh = (Button) findViewById(R.id.btn_themGioHang);


        btnuk7.setOnClickListener(uListener);
        btnuk8.setOnClickListener(uListener);
        btnuk9.setOnClickListener(uListener);
        btnuk10.setOnClickListener(uListener);
        btnMuaNgay = findViewById(R.id.btn_muangay);
    }

    public void mapping(String name, String des, int pri){

        gia = findViewById(R.id.gia);
        tenSp = findViewById(R.id.tensanpham);
        thongtin = findViewById(R.id.thongtin);
        String[] splitted = des.split("=");

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < splitted.length; i++){
            //s += splitted[i] + System.getProperty("line.separator");
            stringBuilder.append(splitted[i]);
            stringBuilder.append(System.getProperty("line.separator"));
        }
        if(stringBuilder != null)
            thongtin.setText(stringBuilder);

        tenSp.setText(name);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        gia.setText(decimalFormat.format(pri));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_cart:
                //hiện giỏ hàng
                Intent intents = new Intent(this, OrderActivity.class);
                startActivity(intents);
                return true;
            case android.R.id.home:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadSlider() {
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
    }

    //Giỏ hàng
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navbottom_items,menu);
        return true;
    }
}
