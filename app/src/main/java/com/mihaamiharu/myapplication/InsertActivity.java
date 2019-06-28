package com.mihaamiharu.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mihaamiharu.myapplication.Models.CRUDMessage;
import com.mihaamiharu.myapplication.REST.RetrofitClient;
import com.mihaamiharu.myapplication.REST.RetrofitInterface;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {
    Integer flagCode;
    Button btnSimpan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        btnSimpan = findViewById(R.id.btn_SimpanData);


        Bundle data = getIntent().getExtras();
        if (data != null) {
            flagCode = data.getInt("flag");
            if (flagCode == 1) {
                btnSimpan.setText("Edit");
            }
        } else {
            flagCode = 0;
            btnSimpan.setText("Simpan");
        }
    }

    public void simpanBerita(View view) {
        EditText inputID = findViewById(R.id.inpIdBerita);
        EditText inputJudul = findViewById(R.id.inpJudulBerita);
        EditText inputIsi= findViewById(R.id.inpIsiBerita);

        String id = inputID.getText().toString();
        String judul = inputJudul.getText().toString();
        String isi = inputIsi.getText().toString();

        RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);

        if (flagCode == 0) {
            Call<CRUDMessage> insertBerita = retrofitInterface.insertBerita(judul, isi);
            insertBerita.enqueue(new Callback<CRUDMessage>() {
                @Override
                public void onResponse(Call<CRUDMessage> call, Response<CRUDMessage> response) {
                    Toast.makeText(InsertActivity.this,
                            "Insert Berita " + response.body().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    finish();;
                }

                @Override
                public void onFailure(Call<CRUDMessage> call, Throwable t) {
                    Log.d("failure", t.getMessage());
                }
            });
        } else {
            Call<CRUDMessage> editBerita = retrofitInterface.editBerita(id, judul, isi);
            editBerita.enqueue(new Callback<CRUDMessage>() {
                @Override
                public void onResponse(Call<CRUDMessage> call, Response<CRUDMessage> response) {
                    Toast.makeText(InsertActivity.this,
                            "Edit Berita " + response.body().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<CRUDMessage> call, Throwable t) {
                    Log.d("failure", t.getMessage());
                }
            });
        }
    }//end fungsi simpanBerita
} //end class InsertActivity
