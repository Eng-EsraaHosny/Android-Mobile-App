package com.example.esraahosny.graduationapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.esraahosny.graduationapp.R;

public class MenuItemDetailsActivity extends AppCompatActivity {

    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item_details);

        image = (ImageView)findViewById(R.id.image);



    }
}
