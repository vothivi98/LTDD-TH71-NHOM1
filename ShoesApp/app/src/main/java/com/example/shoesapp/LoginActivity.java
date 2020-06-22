package com.example.shoesapp;

import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    public static FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private EditText emailID, passWord;
    private Button btnSignIn;
    private TextView tvSignUp, tvForgotPass;
    private ProgressDialog dg;
    String inf = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Đăng nhập");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            inf = bundle.getString("inf");

        }
        emailID =findViewById(R.id.edittext_taikhoan);
        passWord = findViewById(R.id.edittext_matkhau);
        btnSignIn = (Button)findViewById(R.id.btnDangNhap);
        tvSignUp  = findViewById(R.id.dangki);
        tvForgotPass = findViewById(R.id.quenmatkhau);

        mAuth =FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailID.getText().toString().trim();
                String pass = passWord.getText().toString().trim();
                if(email.isEmpty() && pass.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Bạn chưa nhập email và mật khẩu!",Toast.LENGTH_SHORT).show();
                }
                else if(pass.isEmpty()){
                    passWord.setError("Bạn chưa nhập mật khẩu!");
                    passWord.requestFocus();
                }
                else if(email.isEmpty() ){
                    emailID.setError("Bạn chưa nhập Email!");
                    emailID.requestFocus();
                } else if(!email.isEmpty() && pass.length() <6){
                    Toast.makeText(LoginActivity.this,"Mật khẩu bạn dưới 6 ký tự!",Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pass.isEmpty())){
                    dg.setMessage("Đang vào....");
                    dg.show();
                    mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            dg.dismiss();
                            if(!task.isSuccessful()){
//                                Toast.makeText(LoginActivity.this,"Email sai hoặc Mật Khẩu sai!! Xin thử lại.",Toast.LENGTH_SHORT).show();
                                Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();


                            } else {
//                                dg.dismiss();
//                                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công!!",Toast.LENGTH_SHORT).show();
                                if (mAuth.getCurrentUser().isEmailVerified()) {
//                                    if(!inf.trim().isEmpty()){
                                    mUser = FirebaseAuth.getInstance().getCurrentUser();
                                    finish();
//                                    }else {
//                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    ////                                        intent.putExtra("login", "Y");
                                    //                                        //s/tartActivity(intent);
//                                    }
                                   // finish();


                                } else {
                                    Toast.makeText(LoginActivity.this,"Bạn chưa xác nhận Email!!",Toast.LENGTH_SHORT).show();

                                }
                                finish();


                            }
                        }
                    });


                }
                else{
                    dg.dismiss();
                    Toast.makeText(LoginActivity.this,"Lỗi!!",Toast.LENGTH_SHORT).show();
                }


            }
        });
        dg = new ProgressDialog(this);


        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });


        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

