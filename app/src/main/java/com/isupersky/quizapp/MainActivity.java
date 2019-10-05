package com.isupersky.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void wordGameButtonClick(View view){
        Intent intent = new Intent(this,WordGameActivity.class);
        startActivity(intent);
    }
    public void numberGameButtonClick(View view){
        Intent intent = new Intent(this,NumberGameActivity.class);
        startActivity(intent);
    }
}
