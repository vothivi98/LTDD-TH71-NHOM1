package com.example.shoesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.model.Products;
import com.example.myadapter.ImageAdapter;
import com.example.myadapter.ProductAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class MainActivity extends AppCompatActivity {
    String id = "";
    String name = "";
    String img = "";
    VideoView mvideoView;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;
    private ListView lv;
    ArrayList<Products> arrayProduct;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dl = (DrawerLayout) findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.trangchu:
                        Toast.makeText(MainActivity.this, "Trang chủ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.taikhoan:
                        Toast.makeText(MainActivity.this, "Tài khoản", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.giohang:
                        Toast.makeText(MainActivity.this, "Giỏ hàng", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.dangxuat:
                        Toast.makeText(MainActivity.this, "Đăng xuất", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(this);
        mViewPager.setAdapter(adapter);

        //load video
        mvideoView = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.gas);
        try {
            mvideoView.setVideoURI(uri);
        } catch (NullPointerException techmaster1) {
            System.out.println("Loi khong load duoc video" + techmaster1);
        }
        mvideoView.start();

        //bottom navigation

        mainNav = (BottomNavigationView) findViewById(R.id.main_nav);
        mainNav.setOnNavigationItemSelectedListener(navListener);
        mainNav.clearFocus();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new HomeActivity()).commit();
        //
//        arrayProduct = new ArrayList<>();
//
//        //lv = (ListView) findViewById(R.id.lv);
//
//        arrayProduct = new ArrayList<>();
//        //khởi tạo bảng vẽ
//        productAdapter = new ProductAdapter(arrayProduct, getApplicationContext());
//        lv.setAdapter(productAdapter);
        //getDAta();
       // postData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeActivity();
                    break;
                case R.id.nav_cart:
                    selectedFragment = new OrderActivity();
                    break;
                case R.id.nav_account:
                    selectedFragment = new AccountActivity();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, selectedFragment).commit();

            return false;
        }
    };

//    public void getDAta() {
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "https://shoesdatabase-b8db9.firebaseio.com/Products.json";
//        //tạo hàm giúp thực hiện phương thức gửi lên server
//        //đọc dữ liệu json nhanh gọn hơn
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
//                Request.Method.GET, // phương thức
//                url,
//                null
//                , new Response.Listener<JSONArray>() {
//            @Override
//            // Dữ liệu truyền về response
//            public void onResponse(JSONArray response) {
//                if (response != null) // nếu dữ liệu có mới đọc về
//                {
//                    for (int j = 0; j < response.length(); j++) {
//                        try {
//                            JSONObject jsonObject = response.getJSONObject(j); //jsonArray == response
//                            //đọc dữ liệu gán vs tên cột trong csdl
//                            id = jsonObject.getString("productID");
//                            name = jsonObject.getString("productName");
//                            img = jsonObject.getString("imgProduct");
//                            arrayProduct.add(new Products(id, name, img));
//                            //update lại dữ liệu mới
//                            productAdapter.notifyDataSetChanged();
////
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }, new Response.ErrorListener() { // lỗi
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Lỗi", error.toString());
//            }
//        });
//        //gửi lên server thực hiện yêu cầu
//        queue.add(jsonArrayRequest);
//    }

//    public void postData() {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        String url = "https://shoesdatabase-b8db9.firebaseio.com/Products.json";
//        Products pro = new Products("5", "1", 25000,
//                "https://i.picsum.photos/id/704/200/300.jpg?blur=5",
//                "Nike2", "gdfgdf");
//        Map<String, Object> productMap = pro.toMap();
//
//        JSONObject jsonObject = new JSONObject(productMap);
//        final String requestBody = jsonObject.toString();
//
//        StringRequest stringRequest = new StringRequest(
//                Request.Method.POST,
//                url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.i("VOLLEY", response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("VOLLEY", error.toString());
//            }
//        }) {
//            @Override
//            public String getBodyContentType() {
//                return "application/json; charset=utf-8";
//            }
//
//            @Override
//            public byte[] getBody() throws AuthFailureError {
//                try {
//                    return requestBody == null ? null : requestBody.getBytes("utf-8");
//                } catch (UnsupportedEncodingException uee) {
//                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
//                            requestBody, "utf-8");
//                    return null;
//                }
//            }
//
//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                String responseString = "";
//                if (response != null) {
//                    responseString = String.valueOf(response.statusCode);
//                    // can get more details such as response.headers
//                }
//                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//            }
//        };
//
//        requestQueue.add(stringRequest);
//    }
}
