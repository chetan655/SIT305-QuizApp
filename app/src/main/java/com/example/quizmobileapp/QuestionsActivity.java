package com.example.quizmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionsActivity extends AppCompatActivity{

    private TextView myQuestionTextView, welcomeTextView;
    private Button optionButton1, optionButton2, optionButton3, submitButton;
    private ProgressBar progress;


    private String answer;
    private String userNameEnd;
    private int currentScore = 0;
    private int counter = 0;

    private final String[] questionsArray = {
            "Which IDE Do We Use For Android Development?",
            "Which Language Do Use For Android Development?",
            "What is the Entry Point For An Android Program?",
            "In Which File Format Do We Store Layout files",
            "On Which Architecture Android Is Based On?"
    };
    private final String[] correctAnswers = {
            "Android Studio",
            "Java",
            "MainActivity.java",
            ".XML",
            "linux kernel"

    };
    private final String[][] choices = {
            {"Android Studio", "PyCharm", "Visual Studio"},
            {"kotlin", "Java", "Ruby"},
            {"MainActivity.java", "AndroidManifest.xml", "Strings.xml"},
            {".kt", ".java", ".XML"},
            {"linux kernel", "windows kernel", "mac os kernel"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent intent = getIntent();
        userNameEnd = intent.getStringExtra("userNameMain");

        progress = findViewById(R.id.progressBar);
        myQuestionTextView = findViewById(R.id.myQuestionTextView);
        welcomeTextView = findViewById(R.id.welcomeTextView);
        welcomeTextView.setText("Welcome " + userNameEnd);
        optionButton1 = findViewById(R.id.optionButton1);
        optionButton2 = findViewById(R.id.optionButton2);
        optionButton3 = findViewById(R.id.optionButton3);
        submitButton = findViewById(R.id.submitButton);

        viewQuestion(counter);
        progress.setProgress(0);
    }

    private void gameOver(){
        String score = String.valueOf(currentScore);
        Intent finalIntent = new Intent(this, EndActivity.class);
        finalIntent.putExtra("userName", userNameEnd);
        finalIntent.putExtra("score", score);
        startActivity(finalIntent);
    }

    private String getRandomQuestion(int num) { return questionsArray[num]; }
    private String getOption1(int num) { return choices[num][0]; }
    private String getOption2(int num) { return choices[num][1]; }
    private String getOption3(int num) { return choices[num][2]; }
    private String getCorrectAnswer(int num) { return correctAnswers[num]; }


    private void viewQuestion(int num){
        if(counter > 0) welcomeTextView.setVisibility(View.GONE);
        optionButton1.setEnabled(true);
        optionButton2.setEnabled(true);
        optionButton3.setEnabled(true);
        myQuestionTextView.setText(getRandomQuestion(num));
        optionButton1.setText(getOption1(num));
        optionButton1.setBackgroundColor(Color.parseColor("#5194B3"));
        optionButton2.setText(getOption2(num));
        optionButton2.setBackgroundColor(Color.parseColor("#5194B3"));
        optionButton3.setText(getOption3(num));
        optionButton3.setBackgroundColor(Color.parseColor("#5194B3"));
        answer = getCorrectAnswer(num);


        optionButton1.setOnClickListener(view1 -> {
            //counter++;
            optionButton1.setEnabled(false);
            optionButton2.setEnabled(false);
            optionButton3.setEnabled(false);
            if(optionButton1.getText().equals(answer)){
                optionButton1.setBackgroundColor((Color.parseColor("#66B643")));
                currentScore++;
            }else{
                optionButton1.setBackgroundColor((Color.parseColor("#FF0606")));
                if(optionButton2.getText().equals(answer)) optionButton2.setBackgroundColor((Color.parseColor("#66B643")));
                if(optionButton3.getText().equals(answer)) optionButton3.setBackgroundColor((Color.parseColor("#66B643")));
            }
        });

        optionButton2.setOnClickListener(view2 -> {
            //counter++;
            optionButton1.setEnabled(false);
            optionButton2.setEnabled(false);
            optionButton3.setEnabled(false);
            if(optionButton2.getText().equals(answer)){
                optionButton2.setBackgroundColor((Color.parseColor("#66B643")));
                currentScore++;
            }else{
                optionButton2.setBackgroundColor((Color.parseColor("#FF0606")));
                if(optionButton1.getText().equals(answer)) optionButton1.setBackgroundColor((Color.parseColor("#66B643")));
                if(optionButton3.getText().equals(answer)) optionButton3.setBackgroundColor((Color.parseColor("#66B643")));
            }
        });

        optionButton3.setOnClickListener(view3 -> {
            optionButton1.setEnabled(false);
            optionButton2.setEnabled(false);
            optionButton3.setEnabled(false);
            if(optionButton3.getText().equals(answer)){
                optionButton3.setBackgroundColor((Color.parseColor("#66B643")));
                currentScore++;
            }else{
                optionButton3.setBackgroundColor((Color.parseColor("#FF0606")));
                if(optionButton2.getText().equals(answer)) optionButton2.setBackgroundColor((Color.parseColor("#66B643")));
                if(optionButton1.getText().equals(answer)) optionButton1.setBackgroundColor((Color.parseColor("#66B643")));
            }
        });

        submitButton.setOnClickListener(view4 -> {
            if ((!optionButton1.isEnabled()) || !(optionButton2.isEnabled()) || !(optionButton3.isEnabled())) {
                if (counter < questionsArray.length - 1) {
                    counter++;
                    viewQuestion(counter);
                } else {
                    gameOver();
                }
            }
            else Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
        });

        progress.setProgress(counter);
        progress.setMax(5);
    }
}