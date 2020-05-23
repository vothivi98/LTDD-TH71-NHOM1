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
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.myadapter.ImageAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    VideoView mvideoView;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoproducts);

//        dl = (DrawerLayout)findViewById(R.id.activity_main);
//        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
//
//        dl.addDrawerListener(t);
//        t.syncState();
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        nv = (NavigationView)findViewById(R.id.nv);
//        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                switch(id)
//                {
//                    case R.id.trangchu:
//                        Toast.makeText(MainActivity.this, "Trang chủ",Toast.LENGTH_SHORT).show();break;
//                    case R.id.taikhoan:
//                        Toast.makeText(MainActivity.this, "Tài khoản",Toast.LENGTH_SHORT).show();break;
//                    case R.id.giohang:
//                        Toast.makeText(MainActivity.this, "Giỏ hàng", Toast.LENGTH_SHORT).show();break;
//                    case R.id.dangxuat:
//                        Toast.makeText(MainActivity.this, "Đăng xuất", Toast.LENGTH_SHORT).show();break;
//                    default:
//                        return true;
//                }
//                return true;
//            }
//        });

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager_infoproducts);
        ImageAdapter adapter = new ImageAdapter(this);
        mViewPager.setAdapter(adapter);

//        //load video
//        mvideoView = (VideoView) findViewById(R.id.videoView);
//        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.gas );
//        try {
//            mvideoView.setVideoURI(uri);
//        } catch (NullPointerException techmaster1)
//        {
//            System.out.println("Loi khong load duoc video" + techmaster1);
//        }
//        mvideoView.start();

        //bottom navigation

//        mainNav = (BottomNavigationView) findViewById(R.id.main_nav);
//        mainNav.setOnNavigationItemSelectedListener(navListener);
//        mainNav.clearFocus();
//
////        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new HomeActivity()).commit();
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if(t.onOptionsItemSelected(item))
//            return true;
//        return super.onOptionsItemSelected(item);
//    }
//
//    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//            Fragment selectedFragment = null;
//
//            switch (menuItem.getItemId()){
//                case R.id.nav_home:
//                    selectedFragment = new HomeActivity();
//                    break;
//                case R.id.nav_cart:
//                    selectedFragment = new OrderActivity();
//                    break;
//                case R.id.nav_account:
//                    selectedFragment = new AccountActivity();
//                    break;
//            }
//            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, selectedFragment).commit();
//
//            return false;
//        }
//    };
    }
}
