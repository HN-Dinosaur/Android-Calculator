package com.example.mycalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class UnitConversionActivity extends AppCompatActivity {

    Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_conversion);
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
//                Toast.makeText(this, "conversion_of_number_systems", Toast.LENGTH_SHORT).show();
                intent = new Intent(UnitConversionActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.math_function:
//                Toast.makeText(this, "math_function", Toast.LENGTH_SHORT).show();
                intent = new Intent(UnitConversionActivity.this,MathFunctionActivity.class);
                startActivity(intent);
                break;
            case R.id.conversion_of_number_systems:
//                Toast.makeText(this, "unit_conversion", Toast.LENGTH_SHORT).show();
                intent = new Intent(UnitConversionActivity.this,ConversionOfNumberSystemsActivity.class);
                startActivity(intent);
                break;

        }

        return true;
    }
}