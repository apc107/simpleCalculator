package com.example.mycs.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private String operand;
    private String operator;
    private Set<String> numbers;
    private Set<String> operators;
    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
    }

    private void initNumbers() {
        numbers = new HashSet<String>();
        for (int i = 0; i < 10; i++)
            numbers.add(Integer.toString(i));
        numbers.add(".");
    }

    private void initOperators() {
        operators = new HashSet<String>();
        String[] operators = {"+", "-", "*", "/"};
        this.operators.addAll(Arrays.asList(operators));
    }

    public void handleClick(View view) {
        Button clicked = (Button) view;
        String label = clicked.getText().toString();
        String display = result.getText().toString();

        if (isClear(label))
            result.setText(R.string.result_default);
        else if (isNumerical(label)) {
            if (isDefaultResult(display) || isOperator(display))
                result.setText(label);
            else
                result.setText(String.format("%s%s", display, label));
        }
        else if (isOperator(label)) {
            operator = label;
            operand = display;
            result.setText(label);
        }
        else if (label.equals("=")) {
            double a, b, c;

            if (operator == null || operand == null)
                return;

            try {
                a = Double.parseDouble(operand);
                b = Double.parseDouble(display);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            if (operator.equals("+"))
                c = a + b;
            else if (operator.equals("-"))
                c = a - b;
            else if (operator.equals("*"))
                c = a * b;
            else
                c = a / b;

            operand = Double.toString(c);
            result.setText(operand);
        }
    }

    private boolean isClear(String value) {
        this.value = value;
        return value.equals(getString(R.string.buttonClear));
    }

    private boolean isOperator(String value) {
        if (operators == null)
            initOperators();
        return operators.contains(value);
    }

    private boolean isDefaultResult(String value) {
        this.value = value;
        return value.equals(getString(R.string.result_default));
    }

    private boolean isNumerical(String value) {
        if (numbers == null)
            initNumbers();
        return numbers.contains(value);
    }
}