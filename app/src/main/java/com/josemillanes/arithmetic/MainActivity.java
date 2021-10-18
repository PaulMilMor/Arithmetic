package com.josemillanes.arithmetic;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button multiplicationButton;
    private Button divisionButton;
    private Button plusButton;
    private Button minusButton;
    private Button scoreText;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        multiplicationButton = (Button) findViewById(R.id.multiplication_button);
        multiplicationButton.setOnClickListener(this);
        divisionButton = (Button) findViewById(R.id.division_button);
        divisionButton.setOnClickListener(this);
        plusButton = (Button) findViewById(R.id.plus_button);
        plusButton.setOnClickListener(this);
        minusButton = (Button) findViewById(R.id.minus_button);
        minusButton.setOnClickListener(this);
        scoreText = (Button) findViewById(R.id.score_text);
        scoreText.setText(""+score);


    }

    @Override
    public void onClick(View view) {
        Button tempButton = (Button) view;
        Intent intentOperation = new Intent(MainActivity.this, OperationActivity.class);
        Bundle bundle = new Bundle();
        String operand="";
        switch(tempButton.getText().toString()) {
            case "Multiplicación":
                operand = "×";
                break;
            case "División":
                operand = "÷";
                break;
            case "Suma":
                operand = "+";
                break;
            case "Resta":
                operand = "-";
                break;
        }
        bundle.putString("operation", operand);
        bundle.putInt("score", score);
        intentOperation.putExtras(bundle);
        getResult.launch(intentOperation);
    }

   ActivityResultLauncher<Intent> getResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                score = data.getIntExtra("score",0);
                scoreText.setText(""+score);
            }
        }
    });



}