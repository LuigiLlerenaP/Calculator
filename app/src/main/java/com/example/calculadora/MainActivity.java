package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        //
        TextView result = findViewById(R.id.result);
        TextView solution = findViewById(R.id.solution);
        //
        Button btnEquals = findViewById(R.id.btn_equals);
        Button btnDivide = findViewById(R.id.btn_divide);
        Button btnExponents = findViewById(R.id.btn_exponents);
        Button btnMinus = findViewById(R.id.btn_minus);
        Button btnParentheses = findViewById(R.id.btn_parentheses);
        Button btnTimes = findViewById(R.id.btn_times);
        Button btnAc = findViewById(R.id.btn_ac);
        Button btnDecimalPoint = findViewById(R.id.btn_decimal_point);
        Button btnPlus = findViewById(R.id.btn_plus);
        //
        Button btnOne = findViewById(R.id.btn_one);
        Button btnTwo = findViewById(R.id.btn_two);
        Button btnThree = findViewById(R.id.btn_three);
        Button btnFour = findViewById(R.id.btn_four);
        Button btnFive = findViewById(R.id.btn_five);
        Button btnSix = findViewById(R.id.btn_six);
        Button btnSeven = findViewById(R.id.btn_seven);
        Button btnEight = findViewById(R.id.btn_eight);
        Button btnNine = findViewById(R.id.btn_nine);
        Button btnZero = findViewById(R.id.btn_zero);
        //
        updateTextView(btnPlus,solution);
        updateTextView(btnDivide, solution);
        updateTextView(btnExponents, solution);
        updateTextView(btnMinus, solution);
        updateTextView(btnParentheses, solution);
        updateTextView(btnTimes, solution);
        updateTextView(btnZero ,solution);
        updateTextView(btnDecimalPoint, solution);
        updateTextView(btnOne, solution);
        updateTextView(btnTwo, solution);
        updateTextView(btnThree, solution);
        updateTextView(btnFour, solution);
        updateTextView(btnFive, solution);
        updateTextView(btnSix, solution);
        updateTextView(btnSeven, solution);
        updateTextView(btnEight, solution);
        updateTextView(btnNine, solution);
        //
        btnAc.setOnClickListener(v->{
            solution.setText("");
            result.setText("0");
        });
        btnEquals.setOnClickListener(v -> {
            String expression = solution.getText().toString();
            try {
                double resultValue = evaluateMathExpression(expression);
                result.setText(String.valueOf(resultValue));
                solution.setText(" ");
            } catch (Exception e) {
                result.setText("Error");
            }
        });
    }
    private void updateTextView(Button button, TextView textView) {
        button.setOnClickListener(v -> {
            String buttonText = button.getText().toString();
            String currentText = textView.getText().toString();
            currentText += buttonText;
            textView.setText(currentText);
        });
    }
    private double evaluateMathExpression(String expression) {
        expression = expression.replaceAll("\\s", "");
        String[] tokens = expression.split("(?<=\\d)(?=[+\\-*/^])|(?<=[+\\-*/^])(?=\\d)");

        double resultValue = 0;
        String currentOperator = "+";

        for (String token : tokens) {
            if (token.matches("[+\\-*/^]")) {
                currentOperator = token;
            }
            if (!token.matches("[+\\-*/^]")){
                double value = Double.parseDouble(token);
                resultValue = operation(resultValue, value, currentOperator);
            }
        }

        return resultValue;
    }

    private double operation(double operand1, double operand2, String operator) {
        return operator.equals("+") ? operand1 + operand2
                : operator.equals("-") ? operand1 - operand2
                : operator.equals("*") ? operand1 * operand2
                : operator.equals("^") ? Math.pow(operand1, operand2)
                : (operator.equals("/") && operand2 != 0) ? operand1 / operand2
                : Double.NaN;
    }



}