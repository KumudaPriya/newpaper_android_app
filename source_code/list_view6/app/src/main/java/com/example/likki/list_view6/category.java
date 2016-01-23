package com.example.likki.list_view6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by likki on 11/16/15.
 */
public class category extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

    }



    public void displaytechnology(View view) {
        MainActivity.cat_string = "technology";
        startActivity(new Intent(category.this, technology.class));
    }

    public void displaybusiness(View view) {
        MainActivity.cat_string = "business";
        startActivity(new Intent(category.this,bussiness.class));
    }

    public void displayentertainment(View view) {
        MainActivity.cat_string = "entertainment";
        startActivity(new Intent(category.this, entertainment.class));
    }

    public void displaysports(View view) {
        MainActivity.cat_string = "sports";
        startActivity(new Intent(category.this, sports.class));
    }




}