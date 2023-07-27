package com.example.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ProgressBar progressBar;
    PostsAdapter adapter;
    List<Posts> postsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.Recycler);
        progressBar = findViewById(R.id.Pro_bar);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PostsAdapter(postsList);
        recyclerView.setAdapter(adapter);

        //call method
        fetchposts();


    }
    private void fetchposts(){
        progressBar.setVisibility(View.VISIBLE);
        RetrofitClient.getRetroFitClient().getPosts().enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
               if (response.isSuccessful() && response.body() != null){
                   postsList.addAll(response.body());
                   adapter.notifyDataSetChanged();
                   progressBar.setVisibility(View.VISIBLE);
               }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}