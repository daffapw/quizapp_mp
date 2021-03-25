package com.example.quizapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quizapp.R;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    TextView title;
    EditText et1;
    Button bt1, bt2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.title);
        bt1 = findViewById(R.id.register);
        bt2 = findViewById(R.id.login);
    }

    public void submit(View view){
        String s= et1.getText().toString();
        title.setText(s);
    }

    public void toRegister (View view){
        Intent intent = new Intent(getApplicationContext(),register.class);
        startActivity(intent);
    }

    public void toLogin (View view){
        Intent intent2 = new Intent(getApplicationContext(),login.class);
        startActivity(intent2);
    }

    public void toQuiz (View view){
        Intent intent = new Intent(getApplicationContext(),gabung.class);
        startActivity(intent);
    }

}