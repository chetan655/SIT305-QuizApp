package com.example.quizmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity{

    TextView thankTextView, scoreTextView;
    Button newQuizButton, finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        thankTextView = findViewById(R.id.thankTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        newQuizButton = findViewById(R.id.newQuizButton);
        finishButton = findViewById(R.id.finishButton);

        Intent incomingQuizIntent = getIntent();

        String userName = incomingQuizIntent.getStringExtra("userName");
        String score = incomingQuizIntent.getStringExtra("score");
        thankTextView.setText("Congratulations " + userName);
        scoreTextView.setText("Your Score is: " + score + "\\5");

        newQuizButton.setOnClickListener(view1 -> {
            Intent outMainIntent = new Intent(this, MainActivity.class);
            outMainIntent.putExtra("userNameEnd", userName);
            startActivity(outMainIntent);
        });

        finishButton.setOnClickListener(view2 ->  {
            finishAffinity();
            System.exit(0);
        });
    }
}