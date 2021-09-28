package com.example.mycalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycalculator.Model.Calculator;

public class MainActivity extends AppCompatActivity {

    String text = null;
    TextView displayText = null;
    String tag = null;
    String display = null;
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
                startActivity(new Intent(MainActivity.this,ConversionOfNumberSystemsActivity.class));
                break;
            case R.id.math_function:
                startActivity(new Intent(MainActivity.this,MathFunctionActivity.class));
                break;
            case R.id.unit_conversion:
                startActivity(new Intent(MainActivity.this,UnitConversionActivity.class));
                break;
        }
        return true;
    }
    public void handleClickSuperTop(View view){
        tag = String.valueOf(view.getTag());
        display = String.valueOf(displayText.getText());
        switch (tag){
            //(
            case "1":
                Calculator.addSign(display,"(");
                text = display;
                break;
            //)
            case "2":
                Calculator.addSign(display,")");
                text = display;
                break;
            //√
            case "3":
                text = Calculator.sqrt(display);
                break;
            //X^2
            default:
                text = Calculator.square(display);
        }

        displayText.setText(text);
    }
    //点击数字部分
    public void handleClickNum(View view){
        //点击数字按钮
        display = String.valueOf(displayText.getText());
        tag = String.valueOf(view.getTag());
        text = Calculator.addNumInScreen(display,tag);
        displayText.setText(text);
    }
    //点击上方按钮
    public void handleClickTop(View view){
        //拿到当前屏幕上的值
        display = String.valueOf(displayText.getText());
        //拿到按钮的tag
        tag = String.valueOf(view.getTag());

        switch (tag) {
            //点击AC
            case "1":
                text = "0";
                Calculator.clear();
                break;
            //取反
            case "2":
                text = Calculator.negation(display);
                break;
            //百分数
            default:
                text = Calculator.percent(display);
        }
        displayText.setText(text);
    }
    //点击小数点
    public void handleClickDot(View view){
        display = String.valueOf(displayText.getText());
        text = Calculator.addDot(display);
        displayText.setText(text);
    }
    //点击右侧按钮
    public void handleClickRight(View view){
        tag = String.valueOf(view.getTag());
        display = String.valueOf(displayText.getText());
        //辅助连续按等号功能
        switch (tag){
            //除法
            case "1":
                Calculator.addSign(display,"/");
                break;
                //乘法
            case "2":
                Calculator.addSign(display,"*");
                break;
                //减法
            case "3":
                Calculator.addSign(display,"-");
                break;
                //加法
            case "4":
                Calculator.addSign(display,"+");
                break;
                //等于
            default:
                Calculator.addSign(display,"=");
                try{
                    if(Calculator.isValidExpression()){
                        displayText.setText(Calculator.processResult(Calculator.calculator())); 
                    }else{
                        Toast.makeText(MainActivity.this, "括号未闭合", Toast.LENGTH_SHORT).show();
                    }
                    
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "输入有误", Toast.LENGTH_SHORT).show();
                }
      
        }
    }
}