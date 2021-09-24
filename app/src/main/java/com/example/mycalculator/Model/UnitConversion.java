package com.example.mycalculator.Model;

//单位转换
public class UnitConversion {
    //一维
    public static String cmToM(String cm){
        Double cmNum = Calculator.convertToDouble(cm);
        return String.valueOf(cmNum / 100);
    }
    public static String cmToKm(String cm){
        Double cmNum = Calculator.convertToDouble(cm);
        return String.valueOf(cmNum / 100000);
    }
    public static String mToKm(String m){
        Double mNum = Calculator.convertToDouble(m);
        return String.valueOf(mNum / 1000);
    }
    public static String mToCm(String m){
        Double mNum = Calculator.convertToDouble(m);
        return String.valueOf(mNum * 100);
    }
    public static String kmToM(String km){
        Double kmNum = Calculator.convertToDouble(km);
        return String.valueOf(kmNum * 1000);
    }
    public static String kmToCm(String km){
        Double kmNum = Calculator.convertToDouble(km);
        return String.valueOf(kmNum * 100000);
    }
    //二维
    public static String squareCmToM(String squareCm){
        Double cmNum = Calculator.convertToDouble(squareCm);
        return String.valueOf(cmNum / 10000);
    }
    public static String squareCmToKm(String squareCm){
        Double cmNum = Calculator.convertToDouble(squareCm);
        return String.valueOf(cmNum / 1000000000);
    }
    public static String squareMToCm(String squareM){
        Double mNum = Calculator.convertToDouble(squareM);
        return String.valueOf(mNum * 1000);
    }
    public static String squareMToKm(String squareM){
        Double mNum = Calculator.convertToDouble(squareM);
        return String.valueOf(mNum / 10000);
    }
    public static String squareKmToM(String squareKm){
        Double kmNum = Calculator.convertToDouble(squareKm);
        return String.valueOf(kmNum * 10000);
    }
    public static String squareKmToCm(String squareKm){
        Double kmNum = Calculator.convertToDouble(squareKm);
        return String.valueOf(kmNum * 100000000);
    }
    //三维
    public static String cubicCmToMl(String cubicCm){
        return cubicCm;
    }
    public static String cubicCmToL(String cubicCm){
        Double cmNum = Calculator.convertToDouble(cubicCm);
        return String.valueOf(cmNum / 1000);
    }
    public static String cubicMToMl(String cubicM){
        Double mNum = Calculator.convertToDouble(cubicM);
        return String.valueOf(mNum * 1000000);
    }
    public static String cubicMToL(String cubicM){
        Double mNum = Calculator.convertToDouble(cubicM);
        return String.valueOf(mNum * 1000);
    }
}
