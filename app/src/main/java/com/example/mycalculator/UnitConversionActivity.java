package com.example.mycalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycalculator.Model.UnitConversion;

public class UnitConversionActivity extends AppCompatActivity {

    EditText inputSquare;
    EditText inputCubic;
    TextView squareResult;
    TextView cubicResult;

    RadioGroup radioSquare, radioSquareResult, radioCubic, radioCubicResult;


    Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_conversion);
        setting();
    }
    private void setting(){
        inputSquare = findViewById(R.id.inputSquare);
        inputCubic = findViewById(R.id.inputCubic);
        squareResult = findViewById(R.id.squareResult);
        cubicResult = findViewById(R.id.cubicResult);


        radioSquare = findViewById(R.id.unitRadioGroupSquare);
        radioSquareResult = findViewById(R.id.unitRadioGroupSquareResult);
        radioCubic = findViewById(R.id.unitRadioGroupCubic);
        radioCubicResult = findViewById(R.id.unitRadioGroupCubicResult);

        radioSquare.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                try{
                    String input = inputSquare.getText().toString();
                    UnitConversion.solveSquareInput(id,input);
                }catch (Exception e){
                    Toast.makeText(UnitConversionActivity.this, "请输入再点击", Toast.LENGTH_SHORT).show();
                }
            }
        });
        radioSquareResult.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                try{
                    String text = UnitConversion.solveSquareResult(id);
                    squareResult.setText("结果：" + text);
                }catch (Exception e){
                    Toast.makeText(UnitConversionActivity.this, "请输入后再点击", Toast.LENGTH_SHORT).show();
                }

            }
        });
        radioCubic.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                try{
                    String input = inputCubic.getText().toString();
                    UnitConversion.solveCubicInput(id, input);
                }catch (Exception e) {
                    Toast.makeText(UnitConversionActivity.this, "请输入再点击", Toast.LENGTH_SHORT).show();
                }
            }
        });
        radioCubicResult.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                try{
                    String text = UnitConversion.solveCubicResult(id);
                    cubicResult.setText("结果：" + text);
                }catch (Exception e){
                    Toast.makeText(UnitConversionActivity.this, "请输入后再点击", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.unit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.main_menu:
                intent = new Intent(UnitConversionActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.math_function:
                intent = new Intent(UnitConversionActivity.this,MathFunctionActivity.class);
                startActivity(intent);
                break;
            case R.id.conversion_of_number_systems:
                intent = new Intent(UnitConversionActivity.this,ConversionOfNumberSystemsActivity.class);
                startActivity(intent);
                break;

        }

        return true;
    }
}