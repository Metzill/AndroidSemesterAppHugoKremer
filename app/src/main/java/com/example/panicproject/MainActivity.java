package com.example.panicproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int clickCount = 1;
    String cCString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void wokeClick(View view) {
        cCString = String.valueOf(clickCount);
        Intent wokeIntent = new Intent(getBaseContext(), Woke.class);
        wokeIntent.putExtra("slTime", cCString);
        startActivity(wokeIntent);
    }

    public void sleepyClick(View view) {
        clickCount = clickCount + 1;
        Toast.makeText(getApplicationContext(),"GET UP YOU SLEEPY WRIGGLER",Toast.LENGTH_SHORT).show();
    }
}
