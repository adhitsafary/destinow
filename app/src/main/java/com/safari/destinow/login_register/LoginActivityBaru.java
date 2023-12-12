package com.safari.destinow.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.safari.destinow.MainActivity;
import com.safari.destinow.R;

import java.util.Objects;

public class LoginActivityBaru extends AppCompatActivity {
    EditText email, password;
    TextView textView_Register,txtforgotPWLogin;
    Button button_login;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;

    private DatabaseReference usersRef;

    //autologin admin
   // private SharedPreferences sharedPreferences;
  //  private SharedPreferences.Editor editor;
 //   private static final String PREF_NAME = "usersRef";
  //  private static final String KEY_USERNAME = "username";
    //private static final String KEY_PASSWORD = "password";

    private SessionManager sessionManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        //loginAdmin
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        usersRef = firebaseDatabase.getReference().child("data_admin");
        // Inisialisasi SharedPreferences
       // sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        //editor = sharedPreferences.edit();
        // Cek otomatis login saat aplikasi dimulai


        sessionManager = new SessionManager(this);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();


        email = findViewById(R.id.edtEmailLoginBaru);
        password = findViewById(R.id.edtPasswordlLoginBaru);
        button_login = findViewById(R.id.btnLoginbaru);
        progressBar = findViewById(R.id.progresbarLogin);
        textView_Register = findViewById(R.id.txtRegisterBaru);
        txtforgotPWLogin = findViewById(R.id.txtforgotPWLogin);


        txtforgotPWLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        if (mAuth.getCurrentUser() != null && firebaseUser.isEmailVerified()){
            String EMAIL= mAuth.getCurrentUser().getEmail();
            if (!EMAIL.equals("21aswicwnonwvwllrirvrvrisonwascasacscnvonwv@gmail.com")){
                startActivity(new Intent(LoginActivityBaru.this, MainActivity.class));
                finish();
            }
        }


        firebaseAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.GONE);

        textView_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivityBaru.this, RegisterActivity.class));
            }
        });

      button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                String username = email.getText().toString();
                String passwordd = password.getText().toString();
                login();

            }
        });


        checkAutoLogin();

    }

    private void login() {

        String emaill = email.getText().toString();
        String pass = password.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (TextUtils.isEmpty(emaill) && TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        else if (TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        else if (TextUtils.isEmpty(emaill)){
            Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(emaill, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            firebaseUser = firebaseAuth.getCurrentUser();
                            if (firebaseUser != null && firebaseUser.isEmailVerified()) {
                                // Jika pengguna sudah masuk dan email sudah diverifikasi, lanjutkan ke halaman utama aplikasi
                                startActivity(new Intent(LoginActivityBaru.this, MainActivity.class));
                            } else {
                                Toast.makeText(LoginActivityBaru.this, "Mohon verifikasi email Anda terlebih dahulu.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivityBaru.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    private void checkAutoLogin() {
        if (sessionManager != null && sessionManager.isLoggedIn()) {
            // Ambil credentials dari SessionManager dan lakukan login otomatis
            String username = sessionManager.getUsername();
            String password = sessionManager.getPassword();

        }
    }

}
