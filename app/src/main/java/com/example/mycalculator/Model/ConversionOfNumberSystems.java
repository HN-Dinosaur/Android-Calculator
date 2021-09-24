package com.example.mycalculator.Model;

//进制转换
public class ConversionOfNumberSystems {
    //十六进制
    public static String returnHex(String display){
        int num = Integer.parseInt(display);
        return Integer.toHexString(num);
    }
    //八进制
    public static String returnOctal(String display){
        int num = Integer.parseInt(display);
        return Integer.toOctalString(num);
    }
    public static String returnBinary(String display){
        int num = Integer.parseInt(display);
        return Integer.toBinaryString(num);
    }
}
