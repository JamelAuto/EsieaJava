package com.example.PocketCountry.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.PocketCountry.R;
import com.example.PocketCountry.presentation.controller.MainController;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListAdapter.OnItemClickListener {

    private ListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private int[] images = {R.drawable.germany,R.drawable.austria,R.drawable.benin,R.drawable.cameroon,R.drawable.cuba,R.drawable.egypt,R.drawable.spain,R.drawable.finland,R.drawable.france
            ,R.drawable.ireland, R.drawable.jordan,R.drawable.latvia,R.drawable.malta,R.drawable.mexico,R.drawable.nepal
            ,R.drawable.rwanda,R.drawable.serbia,R.drawable.singapore,R.drawable.togo,R.drawable.uruguay};
    private TextView textViewResult;
    private MainController controller;

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
        adapter = new ListAdapter(images, this::onItemClick);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, Activity2.class);
        intent.putExtra("id",position);
        startActivity(intent);
    }
}
