package com.example.esieajava;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private int[] images = {R.drawable.austria,R.drawable.benin,R.drawable.cameroon,R.drawable.cuba,R.drawable.egypt,R.drawable.finland,R.drawable.france
            ,R.drawable.germany,R.drawable.ireland,R.drawable.jordan,R.drawable.latvia,R.drawable.malta,R.drawable.mexico,R.drawable.nepal
            ,R.drawable.rwanda,R.drawable.serbia,R.drawable.singapore,R.drawable.spain,R.drawable.togo,R.drawable.uruguay};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            input.add("Element " + i);
        }
        adapter = new ListAdapter(images);
        recyclerView.setAdapter(adapter);
    }
}