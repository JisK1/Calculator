package com.example.josh.calculator;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Stack;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    String equation = "";

    TextView equationText;
    TextView resultText;

    //region Button Definitions
    Button zeroBtn;
    Button oneBtn;
    Button twoBtn;
    Button threeBtn;
    Button fourBtn;
    Button fiveBtn;
    Button sixBtn;
    Button sevenBtn;
    Button eightBtn;
    Button nineBtn;

    Button lparBtn;
    Button rParBtn;
    Button dotBtn;
    Button clearBtn;
    Button divideBtn;
    Button timesBtn;
    Button minusBtn;
    Button plusBtn;
    Button backBtn;
    Button equalBtn;
    //endregion Button Definitions


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equationText = findViewById(R.id.equation);

        resultText = findViewById(R.id.result);

        equationText.setPaintFlags(equationText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //region Button initialize
        zeroBtn = findViewById(R.id.button0);
        oneBtn = findViewById(R.id.button1);
        twoBtn = findViewById(R.id.button2);
        threeBtn = findViewById(R.id.button3);
        fourBtn = findViewById(R.id.button4);
        fiveBtn = findViewById(R.id.button5);
        sixBtn = findViewById(R.id.button6);
        sevenBtn = findViewById(R.id.button7);
        eightBtn = findViewById(R.id.button8);
        nineBtn = findViewById(R.id.button9);
        lparBtn = findViewById(R.id.buttonLPar);
        rParBtn = findViewById(R.id.buttonRPar);
        dotBtn = findViewById(R.id.buttonDot);
        clearBtn = findViewById(R.id.buttonClear);
        divideBtn = findViewById(R.id.buttonDivide);
        timesBtn = findViewById(R.id.buttontimes);
        minusBtn = findViewById(R.id.buttonMinius);
        plusBtn = findViewById(R.id.buttonPlus);
        backBtn = findViewById(R.id.buttonBack);
        equalBtn = findViewById(R.id.buttonEquals);
        //endregion Button initialize

        input();


    }

    //Function to handel all the button inputs
    //all the number and symbol inputs add the number or symbol to the equation string
    // and set the equation textView to the equation string.
    private void input(){

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                equation = "";
                equationText.setText(equation);
                resultText.setText(" = ");
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(equation.length() > 0) {
                    equation = equation.substring(0, equation.length() - 1);
                    equationText.setText(equation);
                }
            }
        });

        zeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += "0";
                equationText.setText(equation);
            }
        });

        oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += "1";
                equationText.setText(equation);
            }
        });

        twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += "2";
                equationText.setText(equation);
            }
        });

        threeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += "3";
                equationText.setText(equation);
            }
        });

        fourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += "4";
                equationText.setText(equation);
            }
        });

        fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += "5";
                equationText.setText(equation);
            }
        });

        sixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += "6";
                equationText.setText(equation);
            }
        });

        sevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += "7";
                equationText.setText(equation);
            }
        });

        eightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += "8";
                equationText.setText(equation);
            }
        });

        nineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += "9";
                equationText.setText(equation);
            }
        });

        dotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += ".";
                equationText.setText(equation);
            }
        });

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += "+";
                equationText.setText(equation);
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += "-";
                equationText.setText(equation);
            }
        });

        timesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += "*";
                equationText.setText(equation);
            }
        });

        divideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += "/";
                equationText.setText(equation);
            }
        });

        lparBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += "(";
                equationText.setText(equation);
            }
        });

        rParBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation += ")";
                equationText.setText(equation);
            }
        });

        equalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(equation.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_LONG).show();
                }else {
                    //performs the calculation if the expression is valid.
                    if (isValid()) {
                        //Toast.makeText(getApplicationContext(), "Is valid", Toast.LENGTH_LONG).show();

                        try {
                            resultText.setText(" = " + eval());
                        } catch (ScriptException e) {
                            e.printStackTrace();
                        }

                        equation = "";
                        equationText.setText(equation);

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    //returns true if the equation string is a valid expression.
    private boolean isValid(){

       Stack s = new Stack();

       //check if the first or last character is an operator.
       if(!equation.isEmpty() && (isOp(0) || isOp(equation.length() - 1))){
            return false;
       }
       //loops through the string to make sure it is a valid expression.
       for(int i = 0; i < equation.length(); i++){
           if(equation.charAt(i) == '('){
               s.push(equation.charAt(i));
           }else if(equation.charAt(i) == ')'){
               if(!s.empty()) {
                   s.pop();
               }
               else{
                   return false;
               }
           }
           if((i + 1 < equation.length()) &&  (isOp(i) && isOp(i+1)) || (equation.charAt(i) == '.'&& isOp(i+1))){
                return false;
           }
           //checks if there is more then one dot in a number.
           if(equation.charAt(i) == '.'){
               int j = i + 1;
               while(j <= equation.length() - 1 && !isOp(j) && equation.charAt(j) != '(' && equation.charAt(j) != ')'){
                    if(equation.charAt(j) == '.'){
                        return false;
                    }
                    j++;
               }
           }

       }
       if(!s.empty()){
           return  false;
       }
       return true;
    }


    //returns true if the string equation has a +,-,X,or/ at the index.
    private  boolean isOp(int index){
        if(equation.charAt(index) == '+' || equation.charAt(index) == '-' || equation.charAt(index) == '*'
                || equation.charAt(index) == '/')
        {
            return true;
        } else{
            return false;
        }
    }

    //This calculates the value of the equation string by passing it through the rhino script engine
    //where it will be read as a script expression and there for be able to be calculated.
    private double eval() throws ScriptException {

        double result = 0.0;

        ScriptEngineManager mag = new ScriptEngineManager();
        ScriptEngine engine = mag.getEngineByName("rhino");

        result =  (Double)engine.eval(equation);

        return result;
    }




}
