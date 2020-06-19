package com.example.shoesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AccountActivity extends AppCompatActivity {

    private EditText txttenNguoiDung;
    private EditText txtSdt;
    private EditText txtDiaChi;
    private EditText txtEmail;
    private ImageView imgNguoiDung;
    private Button btnAnhNguoiDung;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;

    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Thông tin cá nhân");

        txttenNguoiDung =(EditText)findViewById(R.id.edit_TenNguoiDung);
        txtDiaChi =(EditText) findViewById(R.id.edit_DiaChi);
        txtSdt = (EditText)findViewById(R.id.edit_Sdt);
        txtEmail = (EditText)findViewById(R.id.edit_EmailNguoiDung);
        btnAnhNguoiDung = (Button)findViewById(R.id.btn_ChonAnh);
        imgNguoiDung = findViewById(R.id.avt_account);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("UserInfor");

        storageReference = FirebaseStorage.getInstance().getReference();

        Query query = mReference.orderByChild("emailUser").equalTo(mUser.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    //get data
                    String ten = ""+ds.child("fullName").getValue();
                    String dc = ""+ds.child("address").getValue();
                    String sdt = ""+ds.child("phoneNumber").getValue();
                    String e = ""+ds.child("emailUser").getValue();

                    //set data
                    txttenNguoiDung.setText(ten);
                    txtDiaChi.setText(dc);
                    txtSdt.setText(sdt);
                    txtEmail.setText(e);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnAnhNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mở gallery
                Intent opGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                opGallery.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); // để clear acivity khi back
                opGallery.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(opGallery, 1000);
            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =  item.getItemId();
        if(android.R.id.home == id) {
                this.finish();
        }
        return super.onOptionsItemSelected(item);

    }
}

