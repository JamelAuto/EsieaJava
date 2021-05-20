package com.example.esieajava;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements  RecyclerViewClickInterface{

    private ListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private int[] images = {R.drawable.austria,R.drawable.benin,R.drawable.cameroon,R.drawable.cuba,R.drawable.egypt,R.drawable.finland,R.drawable.france
            ,R.drawable.germany,R.drawable.ireland, R.drawable.jordan,R.drawable.latvia,R.drawable.malta,R.drawable.mexico,R.drawable.nepal
            ,R.drawable.rwanda,R.drawable.serbia,R.drawable.singapore,R.drawable.spain,R.drawable.togo,R.drawable.uruguay};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showList();
    }

    private void showList(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            input.add("Element " + i);
        }
        adapter = new ListAdapter(images, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        openActivity2(position);
    }

    public void openActivity2(int position){
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("id",position);
        startActivity(intent);
    }

}