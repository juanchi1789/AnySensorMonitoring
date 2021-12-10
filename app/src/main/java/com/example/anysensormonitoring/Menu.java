package com.example.anysensormonitoring;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    Button Men;
    Button fab;
    Button blue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Men = findViewById(R.id.men);
        fab = findViewById(R.id.fab);
        blue = findViewById(R.id.blue);

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent R = new Intent(Menu.this, MainActivity.class);
                startActivity(R);
              // finish();
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:+5492613670099"));
                startActivity(i);
            }
        });

        Men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent R = new Intent(Menu.this, Med.class);
                startActivity(R);
                //finish();
            }
        });



    }
}