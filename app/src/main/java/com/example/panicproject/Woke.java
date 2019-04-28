package com.example.panicproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Woke extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woke);

        String savedExtra = getIntent().getStringExtra("slTime");
        String finalText = savedExtra.concat(" Attempts");
        TextView myText = (TextView) findViewById(R.id.timeToWake);
        myText.setText(finalText);

    }

    public void pocketClick(View view) {
        Intent pocketIntent = new Intent(getBaseContext(), PocketActivity.class);
        startActivity(pocketIntent);
    }
}
