package com.example.a1helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView main = (TextView) findViewById(R.id.TextView_Main);

        int mark = 0;
        Random r = new Random();

        mark = r.nextInt(100);
        mark++;

        main.setText("Hello World!");
        main.append("\nYour mark is: " + mark);

        if (mark < 50)
        {
            main.append("\nYou have failed");
            main.setTextColor(Color.RED);
        }
        else if (mark >= 50 && mark < 70)
        {
            main.append("\nWell done you have passed");
            main.setTextColor(Color.GREEN);
        }
        else
        {
            main.append("\nBrilliant, you got a distinction");
            main.setTextColor(Color.BLUE);
        }
    }

}