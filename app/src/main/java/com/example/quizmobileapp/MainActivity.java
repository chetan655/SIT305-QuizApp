package com.example.quizmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        EditText editTextPersonName = findViewById(R.id.editTextPersonName);
        //String userNameMain = editTextPersonName.getText().toString();

        Intent incomingFinalIntent = getIntent();
        String userName = incomingFinalIntent.getStringExtra("userNameEnd");
        editTextPersonName.setText(userName);

        startButton.setOnClickListener(view1 -> {
            if (!(editTextPersonName.getText().toString().equals(""))){
                Intent intent = new Intent(getApplicationContext(), QuestionsActivity.class);
                intent.putExtra("userNameMain", editTextPersonName.getText().toString());
                startActivity(intent);
            }
            else Toast.makeText(MainActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
        });
    }
}