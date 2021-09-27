package com.example.mycalculator.Model;

public class MathFunction {
    public static int operatorNum = 0;
    private static double calculatorNum = 0.0;
    //按数字按钮
    public static String addNum(String displayText,String addNum){
        if(displayText.equals("0")){
            return "";
        }
        if(Calculator.isLonger(displayText)){
            return displayText;
        }
        return displayText + addNum;
    }
    public static double returnSin(Double num){
        return Math.sin(num);
    }
    public static double returnCos(Double num){
        return Math.cos(num);
    }
    public static double returnTan(Double num){
        return Math.tan(num);
    }
    public static double processResult(String display){
        //还原成纯数字
        processDisplayString(display);
        if(operatorNum == 1){
            return returnSin(calculatorNum);
        }else if(operatorNum == 2){
            return returnCos(calculatorNum);
        }else{
            return returnTan(calculatorNum);
        }
    }
    public static void setOperatorNum(int num){
        operatorNum = num;
    }
    //得到纯数字
    public static int getOperatorNum(){
        return operatorNum;
    }
    //处理当前显示字符串  只剩数字
    public static void processDisplayString(String display){
        String deleteStr = "";
        if(operatorNum == 1){
            deleteStr = display.replace("sin","");
        }else if(operatorNum == 2){
            deleteStr = display.replace("cos","");
        }else{
            deleteStr = display.replace("tan","");
        }
        calculatorNum = Double.parseDouble(deleteStr);
    }
}
