package com.example.mycalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mycalculator.Model.Calculator;

public class MainActivity extends AppCompatActivity {

    String text = null;
    Intent intent = null;
    TextView displayText = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayText = findViewById(R.id.displayText);
    }

    //显示菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    //点击菜单
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.conversion_of_number_systems:
                intent = new Intent(MainActivity.this,ConversionOfNumberSystemsActivity.class);
                startActivity(intent);
                break;
            case R.id.math_function:
                intent = new Intent(MainActivity.this,MathFunctionActivity.class);
                startActivity(intent);
                break;
            case R.id.unit_conversion:
                intent = new Intent(MainActivity.this,UnitConversionActivity.class);
                startActivity(intent);
                break;
        }

        return true;
    }
    //点击数字部分
    public void handleClickNum(View view){
        //点击数字按钮
        String display = String.valueOf(displayText.getText());
        String tag = String.valueOf(view.getTag());
        text = Calculator.addNum(display,tag);
        displayText.setText(text);
    }
    //点击上方按钮
    public void handleClickTop(View view){
        //拿到当前屏幕上的值
        String display = String.valueOf(displayText.getText());
        //拿到按钮的tag
        String tag = String.valueOf(view.getTag());


        //点击AC
        if(tag.equals("1")){
            text = "0";
            //取反
        }else if(tag.equals("2")){
            text = Calculator.negation(display);
            //百分数
        }else{
            text = Calculator.percent(display);
        }
        displayText.setText(text);
    }
    //点击小数点
    public void handleClickDot(View view){
        String display = String.valueOf(displayText.getText());
        text = Calculator.addDot(display);
        displayText.setText(text);
    }
    //点击右侧按钮
    public void handleClickRight(View view){
        String tag = String.valueOf(view.getTag());
        String display = String.valueOf(displayText.getText());
        Double result = 0.0;
        //除法
        if(tag.equals("1")){
            Calculator.setCalculatorNum1(display);
            Calculator.clickOnceEqual = true;
            Calculator.setOperatorSign("/");
            //乘法
        }else if(tag.equals("2")){
            Calculator.setCalculatorNum1(display);
            Calculator.clickOnceEqual = true;
            Calculator.setOperatorSign("*");
            //减法
        }else if(tag.equals("3")){
            Calculator.setCalculatorNum1(display);
            Calculator.clickOnceEqual = true;
            Calculator.setOperatorSign("-");
            //加法
        }else if(tag.equals("4")){
            Calculator.setCalculatorNum1(display);
            Calculator.clickOnceEqual = true;
            Calculator.setOperatorSign("+");
            //等于
        }else{
            Calculator.setCalculatorNum2(display);
            if(Calculator.getOperatorSign() == null){
                return;
            }else if(Calculator.getOperatorSign().equals("/")){
                result = Calculator.divide();
            }else if(Calculator.getOperatorSign().equals("*")){
                result = Calculator.multiply();
            }else if(Calculator.getOperatorSign().equals("+")){
                result = Calculator.add();
            }else if(Calculator.getOperatorSign().equals("-")){
                result = Calculator.sub();
            }
            displayText.setText(Calculator.processResult(result));
            Calculator.setIsComplicated(true);
            Calculator.clickOnceEqual = false;
        }
    }

}