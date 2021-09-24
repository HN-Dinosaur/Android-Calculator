package com.example.mycalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MathFunctionActivity extends AppCompatActivity {

    String text = null;
    Intent intent = null;
    TextView displayText = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_function);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.math_menu, menu);
        displayText = findViewById(R.id.displayText);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.main_menu:
                intent = new Intent(MathFunctionActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.conversion_of_number_systems:
                intent = new Intent(MathFunctionActivity.this,ConversionOfNumberSystemsActivity.class);
                startActivity(intent);
                break;
            case R.id.unit_conversion:
                intent = new Intent(MathFunctionActivity.this,UnitConversionActivity.class);
                startActivity(intent);
                break;

        }

        return true;
    }
    public void handleClickRight(View view){
        String tag = String.valueOf(view.getTag());
        String display = String.valueOf(displayText.getText());
        //AC
        if(tag.equals("1")){
            text = "0";
            //等于
        }else{
            text = String.valueOf(MathFunction.processResult(display));
        }
        displayText.setText(text);
    }
    public void handleClickTop(View view){
        String tag = String.valueOf(view.getTag());
        //sin
        if(tag.equals("1")){
            MathFunction.setOperatorNum(1);
            text = "sin";
            //cos
        }else if(tag.equals("2")){
            MathFunction.setOperatorNum(2);
            text = "cos";
            //tan
        }else{
            MathFunction.setOperatorNum(3);
            text = "tan";
        }
        displayText.setText(text);
    }
    public void handleClickNum(View view){
        //点击数字按钮
        String display = String.valueOf(displayText.getText());
        String tag = String.valueOf(view.getTag());
        text = MathFunction.addNum(display,tag);
        displayText.setText(text);
    }
}