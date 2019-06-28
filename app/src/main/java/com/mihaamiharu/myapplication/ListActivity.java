package com.mihaamiharu.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.mihaamiharu.myapplication.Adapters.BeritaAdapter;
import com.mihaamiharu.myapplication.Models.Berita;
import com.mihaamiharu.myapplication.Models.Parsing.GetAllBerita;
import com.mihaamiharu.myapplication.REST.RetrofitClient;
import com.mihaamiharu.myapplication.REST.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final RecyclerView recyclerView = findViewById(R.id.recyclerBerita);
        final LinearLayout layoutProgress = findViewById(R.id.layoutProgress);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);

        layoutProgress.setVisibility(View.VISIBLE);
        Call<GetAllBerita> getAllBerita = retrofitInterface.getAllBerita();
        getAllBerita.enqueue(new Callback<GetAllBerita>() {
            @Override
            public void onResponse(Call<GetAllBerita> call, Response<GetAllBerita> response) {
                List<Berita> beritaList = response.body().getBerita();
                RecyclerView.Adapter adapter = new BeritaAdapter(beritaList);
                recyclerView.setAdapter(adapter);

                layoutProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GetAllBerita> call, Throwable t) {
                Log.d("failure", t.getMessage());
                layoutProgress.setVisibility(View.GONE);
            }
        });

    }
}
