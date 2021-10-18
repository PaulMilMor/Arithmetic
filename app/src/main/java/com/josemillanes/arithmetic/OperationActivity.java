package com.josemillanes.arithmetic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class OperationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView firstNumberText;
    private TextView operandText;
    private TextView secondNumberText;
    private TextView resultText;
    private Button scoreText;
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button zeroButton;
    private Button eraseButton;
    private Button checkButton;
    private Button dotButton;
    private Button backButton;
    private String operand = "";
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        operandText = (TextView)  findViewById(R.id.operand_text);
        scoreText = (Button) findViewById(R.id.score_text);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            operand = bundle.getString("operation","");
            score = bundle.getInt("score",0);
        }
        backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        operandText.setText(operand);
        scoreText.setText(""+score);

        resultText = (TextView)  findViewById(R.id.result_number);
        firstNumberText = (TextView)  findViewById(R.id.first_number);
        secondNumberText = (TextView)  findViewById(R.id.second_number);
        GenerateValues();
        oneButton = (Button) findViewById(R.id.one_button);
        oneButton.setOnClickListener(this);
        twoButton = (Button) findViewById(R.id.two_button);
        twoButton.setOnClickListener(this);
        threeButton = (Button) findViewById(R.id.three_button);
        threeButton.setOnClickListener(this);
        fourButton = (Button) findViewById(R.id.four_button);
        fourButton.setOnClickListener(this);
        fiveButton = (Button) findViewById(R.id.five_button);
        fiveButton.setOnClickListener(this);
        sixButton = (Button) findViewById(R.id.six_button);
        sixButton.setOnClickListener(this);
        sevenButton = (Button) findViewById(R.id.seven_button);
        sevenButton.setOnClickListener(this);
        eightButton = (Button) findViewById(R.id.eight_button);
        eightButton.setOnClickListener(this);
        nineButton = (Button) findViewById(R.id.nine_button);
        nineButton.setOnClickListener(this);
        zeroButton = (Button) findViewById(R.id.zero_button);
        zeroButton.setOnClickListener(this);

        checkButton = (Button) findViewById(R.id.check_button);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resultText.getText().toString().isEmpty()) {
                    makeToast("Introduce un valor!");
                } else {
                    double firstNumber =  Double.parseDouble(firstNumberText.getText().toString());
                    double secondNumber = Double.parseDouble(secondNumberText.getText().toString());
                    double resultNumber = Double.parseDouble(resultText.getText().toString());

                    score = Integer.parseInt(scoreText.getText().toString());
                    switch(operand){
                        case "×":
                            if(resultNumber == firstNumber*secondNumber) {
                                makeToast("Correcto!");
                                score += 50;
                                scoreText.setText(""+score);
                            } else {
                                makeToast("Incorrecto, la respuesta correcta es: " +((int)(firstNumber*secondNumber)));
                            }
                            break;
                        case "÷":
                            double cociente = firstNumber / secondNumber;
                            DecimalFormat decimalFormat = new DecimalFormat("#.###");
                           cociente = Double.parseDouble(decimalFormat.format(cociente));
                            if(resultNumber == cociente) {
                                makeToast("Correcto!");
                                score += 50;
                                scoreText.setText(""+score);
                            } else {
                                makeToast("Incorrecto, la respuesta correcta es: " +(cociente));
                            }
                            break;
                        case "+":
                            if(resultNumber == firstNumber+secondNumber) {
                                makeToast("Correcto!");
                                score += 50;
                                scoreText.setText(""+score);
                            } else {
                                makeToast("Incorrecto, la respuesta correcta es: " +((int)(firstNumber+secondNumber)));
                            }
                            break;
                        case "-":
                            if(resultNumber == firstNumber-secondNumber) {
                                makeToast("Correcto!");
                                score += 50;
                                scoreText.setText(""+score);

                            } else {
                                makeToast("Incorrecto, la respuesta correcta es: " +((int)(firstNumber-secondNumber)));
                            }
                            break;
                    }
                    GenerateValues();
                }

            }
        });

        eraseButton = (Button) findViewById(R.id.erase_button);
        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resultString = resultText.getText().toString();
                if(!resultString.isEmpty()) {
                    resultString = resultString.substring(0,resultString.length()-1);
                    resultText.setText(resultString);

                }
            }
        });
        dotButton = (Button) findViewById(R.id.dot_button);
        dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!resultText.getText().toString().contains(".")) {
                    resultText.append(".");
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("score",score);
        setResult(RESULT_OK, intent);
        super.onBackPressed();

        finish();
    }

    @Override
    public void onClick(View view) {
        Button tempButton = (Button) view;
        String tempString = resultText.getText().toString();
        if( (!tempString.contains(".") && tempString.length() < 2)||tempString.isEmpty()) {
            resultText.append(tempButton.getText());

        } else if(tempString.contains(".")) {
            if(tempString.length() < tempString.indexOf(".")+4) {
                resultText.append(tempButton.getText());
            }
        }

    }

    public void makeToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void GenerateValues(){
        int firstNumber = (int)(Math.random()*9+1);
        int secondNumber = (int)(Math.random()*9+1);
        if(operand.equals("-") && secondNumber > firstNumber){
            int tempNumber = firstNumber;
            firstNumber = secondNumber;
            secondNumber = tempNumber;
        }
        firstNumberText.setText("" +firstNumber);
        secondNumberText.setText(""+secondNumber);
        resultText.setText("");
    }

}