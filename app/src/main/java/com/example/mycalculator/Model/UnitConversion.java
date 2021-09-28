package com.example.mycalculator.Model;

import com.example.mycalculator.R;

import java.math.BigDecimal;

//单位转换
public class UnitConversion {
    public static BigDecimal squareValue, cubicValue,squareResult,cubicResult;
    public static void solveSquareInput(int id, String text){
        BigDecimal input = new BigDecimal(text);
        switch (id){
            case R.id.radioSquareCm1:
                squareValue = input.multiply(new BigDecimal("0.0001"));
                break;
            case R.id.radioSquareM1:
                squareValue = input;
                break;
            case R.id.radioSquareKm1:
                squareValue = input.multiply(new BigDecimal("10000"));
                break;
        }
    }
    public static String solveSquareResult(int id){
        squareResult = new BigDecimal(0);
        switch (id){
            case R.id.radioSquareCm2:
                squareResult = squareValue.multiply(new BigDecimal("1000"));
                break;
            case R.id.radioSquareM2:
                squareResult = squareValue;
                break;
            case R.id.radioSquareKm2:
                squareResult = squareValue.multiply(new BigDecimal("0.0001"));
                break;
        }
        return squareResult.toString();
    }
    public static void solveCubicInput(int id, String text){

        BigDecimal input = new BigDecimal(text);

        if (id == R.id.radioCubicCm1 || id == R.id.radioMl1){
            cubicValue = input.multiply(new BigDecimal("0.001"));
        }else{
            cubicValue = input;
        }
    }
    public static String solveCubicResult(int id){
        cubicResult = new BigDecimal(0);
        if(id == R.id.radioMl2 || id == R.id.radioCubicCm2){
            cubicResult = cubicValue.multiply(new BigDecimal("1000"));
        }else{
            cubicResult = cubicValue;
        }
        return cubicResult.toString();
    }

}
