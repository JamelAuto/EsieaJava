package com.example.esieajava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");
        DisplayCountryName(id);
    }

    public void DisplayCountryName(final int position){
        String[] arrayCountrie = {"Autriche","Bénin","Cameroun","Cuba","Egypte","Finlande","France","Allemagne","Irlande","Jordanie"
                ,"Lettonie","Malte","Méxique","Népal","Rwanda","Serbie","Singapoure","Espagne","Togo","Uruguay"};
        final TextView countryName = (TextView) findViewById(R.id.countryDetail);
        countryName.setText(arrayCountrie[position]);
    }
}