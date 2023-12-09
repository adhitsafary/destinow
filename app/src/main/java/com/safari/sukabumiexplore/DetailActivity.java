package com.safari.sukabumiexplore;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
public class DetailActivity extends AppCompatActivity {
    TextView detailjudul, detaildeskripsi, detailjambuka, detaillokasi, detailharga, detaillinkMaps, detailtentang;
    ImageView detailImage;
    FloatingActionButton deleteButton, editButton;
    String key = "";
    String imageUrl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailImage = findViewById(R.id.gambarItemDetail);

        detailjudul = findViewById(R.id.judulItemdetil);
        detaildeskripsi = findViewById(R.id.deskripsiItemdetil);
        detailjambuka = findViewById(R.id.jambukaItemdetil);
        detaillokasi = findViewById(R.id.lokasiItemdetil);
        detailharga = findViewById(R.id.hargaItemdetil);
        detaillinkMaps = findViewById(R.id.linkMapsItemdetil);
        detailtentang = findViewById(R.id.tentangItemdetil);

        editButton = findViewById(R.id.editButon);
        deleteButton = findViewById(R.id.deleteButon);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailjudul.setText(bundle.getString("Judul"));
            detaildeskripsi.setText(bundle.getString("Deskripsi"));
            detailjambuka.setText(bundle.getString("JamBuka"));
            detaillokasi.setText(bundle.getString("Lokasi"));
            detailharga.setText(bundle.getString("Harga"));
            detaillinkMaps.setText(bundle.getString("LinkMaps"));
            detailtentang.setText(bundle.getString("Tentang"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Gambar");
            Glide.with(this).load(bundle.getString("Gambar")).into(detailImage);
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Data Pariwisata");
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                });
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, UpdateActivity.class)
                        .putExtra("Judul", detailjudul.getText().toString())
                        .putExtra("Deskripsi", detaildeskripsi.getText().toString())
                        .putExtra("JamBuka", detailjambuka.getText().toString())
                        .putExtra("Lokasi", detaillokasi.getText().toString())
                        .putExtra("Harga", detailharga.getText().toString())
                        .putExtra("LinkMaps", detaillinkMaps.getText().toString())
                        .putExtra("Tentang", detailtentang.getText().toString())
                        .putExtra("Gambar", imageUrl)
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });
    }
}