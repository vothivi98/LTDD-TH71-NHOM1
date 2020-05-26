package com.example.shoesapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.model.Products;
import com.example.myadapter.ImageAdapter;
import com.example.myadapter.RecyclerViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity<ProductAdapter> extends AppCompatActivity {
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
    private ArrayList<Integer> imageURLs = new ArrayList<>();
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

//        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new HomeActivity()).commit();
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

        listImageLogo();
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

    private void listImageLogo() {

        imageURLs.add(R.drawable.round_lens_black_48);
        imageURLs.add(R.drawable.round_lens_black_48);
        imageURLs.add(R.drawable.round_lens_black_48);
        imageURLs.add(R.drawable.round_lens_black_48);
        imageURLs.add(R.drawable.round_lens_black_48);
        imageURLs.add(R.drawable.round_lens_black_48);

        initRecyclerView();
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        androidx.recyclerview.widget.RecyclerView recyclerView = findViewById(R.id.recyclerView_Logo);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerViewAdapter adapter  = new RecyclerViewAdapter(this, imageURLs);
        recyclerView.setAdapter(adapter);
    }
}
