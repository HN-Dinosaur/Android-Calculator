package com.example.mycalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mycalculator.Model.UnitConversion;

import java.math.BigDecimal;

public class UnitConversionActivity extends AppCompatActivity {

    EditText inputSquare;
    EditText inputCubic;
    TextView squareResult;
    TextView cubicResult;

    RadioGroup radioSquare, radioSquareResult, radioCubic, radioCubicResult;
    BigDecimal squareTempResult, cubicTempResult;

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
        radioCubicResult = findViewById(R.id.unitRadioGroupSquareResult);

        radioSquare.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                BigDecimal input = new BigDecimal(inputSquare.getText().toString());
                switch (id){
                    case R.id.radioSquareCm1:
                        squareTempResult = input.multiply(new BigDecimal("0.001"));
                        break;
                    case R.id.radioSquareM1:
                        squareTempResult = input;
                        break;
                    case R.id.radioSquareKm1:
                        squareTempResult = input.multiply(new BigDecimal("10000"));
                        break;
                }
            }
        });
        radioSquareResult.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                BigDecimal squareNumResult = null;
                switch (id){
                    case R.id.radioSquareCm2:
                        squareNumResult = squareTempResult.multiply(new BigDecimal("1000"));
                        break;
                    case R.id.radioSquareM2:
                        squareNumResult = squareTempResult;
                        return;
                    case R.id.radioSquareKm2:
                        squareNumResult = squareTempResult.multiply(new BigDecimal("0.0001"));
                }
                squareResult.setText(squareNumResult.toString());
            }
        });
        radioCubic.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                BigDecimal input = new BigDecimal(inputCubic.getText().toString());
                if (id == R.id.radioCubicCm1 || id == R.id.radioMl1){
                    cubicTempResult = input.multiply(new BigDecimal("0.001"));
                }else{
                    cubicTempResult = input;
                }
            }
        });
        radioCubicResult.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                BigDecimal cubicNumResult = null;
                if(id == R.id.radioMl2 || id == R.id.radioCubicCm2){
                    cubicNumResult = cubicTempResult.multiply(new BigDecimal("1000"));
                }else{
                    cubicNumResult = cubicTempResult;
                }
                cubicResult.setText(cubicNumResult.toString());
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