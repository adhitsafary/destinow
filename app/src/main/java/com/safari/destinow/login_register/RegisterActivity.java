package com.safari.destinow.login_register;


import static com.safari.destinow.R.id;
import static com.safari.destinow.R.layout;

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
import com.google.firebase.database.FirebaseDatabase;
import com.safari.destinow.DataClass;

public class RegisterActivity extends AppCompatActivity {
    EditText editText_nama,editText_email, editText_pass;
    Button button_register;
    FirebaseAuth firebaseAuth;
    TextView textView_sign;
    FirebaseDatabase database;
    ProgressBar progressBar;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_register2);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();


        editText_nama = findViewById(id.nama_user_baru);
        editText_email = findViewById(id.edtEmailRegister_baru);
        editText_pass = findViewById(id.etxpasswordRegister_baru);
        button_register = findViewById(id.btnRegister_baru);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar =findViewById(id.progresbarRegister);
        progressBar.setVisibility(View.GONE);
        textView_sign = findViewById(id.txtloginregister_baru);

        textView_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivityBaru.class));
            }
        });


        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                createUser();

            }
        });


    }

    private void createUser() {
        String userName = editText_nama.getText().toString();
        String email = editText_email.getText().toString();
        String pass = editText_pass.getText().toString();

        if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(email) && TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Nama email dan password kosong", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        else if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        else if (TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        else if (pass.length() < 6 ){
            Toast.makeText(this, "Password tidak boleh kurang dari 6", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Register Berhasil Mohon verifikasi email Anda", Toast.LENGTH_SHORT).show();
                            DataClass dataClass = new DataClass(userName, email, pass);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(dataClass);
                            goToLoginActivity();
                            firebaseUser = firebaseAuth.getCurrentUser();
                            sendVerificationEmail();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }

    private void sendVerificationEmail() {
        firebaseUser.sendEmailVerification();
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivityBaru.class);
        startActivity(intent);
        finish();
    }
  /*  private void sendVerificationEmail() {
        firebaseUser.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Email verifikasi telah dikirim", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal mengirim email verifikasi", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    } */
}