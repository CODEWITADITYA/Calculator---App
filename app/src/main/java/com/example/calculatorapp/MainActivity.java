package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.gms.tflite.support.Empty;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonBraceOpen, buttonBraceClose;
    MaterialButton buttonDivide, buttonMultiply, buttonPlus, buttonMinus, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        assignID(buttonC, R.id.button_C);
        assignID(buttonBraceOpen, R.id.button_open_bracket);
        assignID(buttonBraceClose, R.id.button_close_bracket);
        assignID(buttonDivide, R.id.button_divide);
        assignID(buttonMultiply, R.id.button_mul);
        assignID(buttonPlus, R.id.button_add);
        assignID(buttonMinus, R.id.button_sub);
        assignID(buttonEquals, R.id.button_equal);
        assignID(button0, R.id.button_0);
        assignID(button1, R.id.button_1);
        assignID(button2, R.id.button_2);
        assignID(button3, R.id.button_3);
        assignID(button4, R.id.button_4);
        assignID(button5, R.id.button_5);
        assignID(button6, R.id.button_6);
        assignID(button7, R.id.button_7);
        assignID(button8, R.id.button_8);
        assignID(button9, R.id.button_9);
        assignID(buttonAC, R.id.button_AC);
        //assignID(buttonC,R.id.button_C);
        assignID(buttonDot, R.id.button_dot);

    }


    void assignID(MaterialButton btn, int id) {

        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        // solutionTv.setText(buttonText);
        String datatocalcute = solutionTv.getText().toString();

        if (buttonText.equals("Cl")) {
            solutionTv.setText("");
            resultTv.setText("0");

            return;
        }

        if (buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());

            return;
        }

        if (buttonText.equals("Del")) {

            datatocalcute = datatocalcute.substring(0, datatocalcute.length() - 1);


        } else {


            // String datatocalcuate = solutionTv.getText().toString();
            datatocalcute = datatocalcute + buttonText;
            // solutionTv.setText(datatocalcute);
            // String finalResult = getResult(datatocalcute);

        }

        solutionTv.setText(datatocalcute);
        String finalResult = getResult(datatocalcute);
        if (!finalResult.equals("Err")) {

            resultTv.setText(finalResult);
        }




    }


    String getResult(String data) {

        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);

            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {

                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;

        } catch (Exception e) {
            return "Err";
        }
    }

}




