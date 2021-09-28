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

import com.example.mycalculator.Model.ConversionOfNumberSystems;

public class ConversionOfNumberSystemsActivity extends AppCompatActivity {

    EditText input;

    TextView result;

    RadioGroup select;
    Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_of_number_systems);
        setting();
    }
    private void setting(){
        input = findViewById(R.id.input);
        select = findViewById(R.id.selectConversion);
        result = findViewById(R.id.result);

        select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                try{
                    String str = input.getText().toString();
                    String display = "结果: 0";
                    switch (id){
                        case R.id.binary:
                            display = ConversionOfNumberSystems.returnBinary(str);
                            break;
                        case R.id.hex:
                            display = ConversionOfNumberSystems.returnHex(str);
                            break;
                        case R.id.octal:
                            display = ConversionOfNumberSystems.returnOctal(str);
                    }

                    result.setText("结果: " + display);
                }catch (Exception e){
                    Toast.makeText(ConversionOfNumberSystemsActivity.this, "请输" +
                            "入转换的十进制数", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.conversion_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.main_menu:
                intent = new Intent(ConversionOfNumberSystemsActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.math_function:
                intent = new Intent(ConversionOfNumberSystemsActivity.this,MathFunctionActivity.class);
                startActivity(intent);
                break;
            case R.id.unit_conversion:
                intent = new Intent(ConversionOfNumberSystemsActivity.this,UnitConversionActivity.class);
                startActivity(intent);
                break;

        }

        return true;
    }
}