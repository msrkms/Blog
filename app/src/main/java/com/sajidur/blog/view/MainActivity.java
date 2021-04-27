package com.sajidur.blog.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.sajidur.blog.R;
import com.sajidur.blog.database.RoomDB;
import com.sajidur.blog.model.RoomDB.CategoriesRoomModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,BlogActivity.class));
                MainActivity.this.finish();
            }
        }, 3000);
    }
}