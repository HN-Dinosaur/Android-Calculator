package com.example.mycalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ConversionOfNumberSystemsActivity extends AppCompatActivity {

    Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_of_number_systems);
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
//                Toast.makeText(this, "conversion_of_number_systems", Toast.LENGTH_SHORT).show();
                intent = new Intent(ConversionOfNumberSystemsActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.math_function:
//                Toast.makeText(this, "math_function", Toast.LENGTH_SHORT).show();
                intent = new Intent(ConversionOfNumberSystemsActivity.this,MathFunctionActivity.class);
                startActivity(intent);
                break;
            case R.id.unit_conversion:
//                Toast.makeText(this, "unit_conversion", Toast.LENGTH_SHORT).show();
                intent = new Intent(ConversionOfNumberSystemsActivity.this,UnitConversionActivity.class);
                startActivity(intent);
                break;

        }

        return true;
    }
}