package com.example.mycalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MathFunctionActivity extends AppCompatActivity {

    Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_function);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.math_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.main_menu:
//                Toast.makeText(this, "conversion_of_number_systems", Toast.LENGTH_SHORT).show();
                intent = new Intent(MathFunctionActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.conversion_of_number_systems:
//                Toast.makeText(this, "math_function", Toast.LENGTH_SHORT).show();
                intent = new Intent(MathFunctionActivity.this,ConversionOfNumberSystemsActivity.class);
                startActivity(intent);
                break;
            case R.id.unit_conversion:
//                Toast.makeText(this, "unit_conversion", Toast.LENGTH_SHORT).show();
                intent = new Intent(MathFunctionActivity.this,UnitConversionActivity.class);
                startActivity(intent);
                break;

        }

        return true;
    }
}