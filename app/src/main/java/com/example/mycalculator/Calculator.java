package com.example.mycalculator;

public class Calculator {
    public static Double calculatorNum1 = 0.0;
    public static Double calculatorNum2 = 0.0;
    public static boolean clickOnceEqual = false;
    public static boolean isOperator = false;
    public static boolean isComplicated = false;
    public static String operatorSign = null;
    //相反数
    public static String negation(String displayText){
        Double num = convertToDouble(displayText.replace(",",""));
        num = -num;
        return String.valueOf(num);
    }
    //百分数
    public static String percent(String displayText){
        Double num = convertToDouble(displayText);
        if(num == 0){
            return "0";
        }
        return String.valueOf(num / 100);
    }
    //按数字按钮
    public static String addNum(String displayText,String addNum){
        if(isComplicated || isOperator){
            isComplicated = false;
            isOperator = false;
            return addNum;
        }
        if(displayText.equals("0")){
            return addNum;
        }
        if(isLonger(displayText)){
            return displayText;
        }
        return insertCommaWhenAddNum(displayText + addNum);
    }
    //添加小数点
    public static String addDot(String displayText){
        String addDot = displayText + ".";
        //如果已经包含小数点了
        if(displayText.contains(".")){
            return displayText;
        }
        if(isComplicated){
            swap();
            isComplicated = false;
            return "0.";
        }
        return addDot;
    }
    //转换成DoubleNum
    public static Double convertToDouble(String displayText){
        return Double.parseDouble(displayText);
    }
    //交换两个数
    public static void swap(){
        double temp = calculatorNum1;
        calculatorNum1 = calculatorNum2;
        calculatorNum2 = temp;
    }
    //添加整数逗号
    public static String addIntComma(String display){
        String deleteDotStr = display.replace(".","");
        //先反转
        String reverse = reverseStr(deleteDotStr);
        //再间隔成数组
        String[] stringArray = reverse.split("");
        String str = "";
        int count = 0;
        //在字符串倒着的情况下增加","
        for(int i = 0; i < stringArray.length; i++) {
            str += stringArray[i];
            count += 1;
            if (count % 3 == 0 && count < stringArray.length) {
                str += ",";
            }
        }
        //反转
        return reverseStr(str);
    }
    //添加双精度浮点数的逗号
    public static String addDoubleComma(String display){
        String deleteDotStr = display.replace(",","");
        String[] splitStr = deleteDotStr.split("\\.");
        String intStr = addIntComma(splitStr[0]);
        String doubleStr = splitStr[1];
        return intStr + "." + doubleStr;
    }

    //判断是否是一个整数
    public static boolean isCanToInt(Double num){
        if(num == Math.floor(num)){
            return true;
        }
        return false;
    }
    //反转字符串
    public static String reverseStr(String str){
        return new StringBuffer(str).reverse().toString();
    }
    //set num1
    public static void setCalculatorNum1(String display){

        //去掉所有的,
        String temp = display.replace(",","");

        calculatorNum1 = Double.parseDouble(temp);
        isOperator = true;
    }
    //set num2
    public static void setCalculatorNum2(String display){
        String temp = display.replace(",","");
        calculatorNum2 = Double.parseDouble(temp);
    }
    //set Sign
    public static void setOperatorSign(String sign){
        operatorSign = sign;
    }
    //set isOperator
    public static void setIsOperator(boolean isOperatorParam){
        isOperator = isOperatorParam;
    }
    //set isComplicated
    public static void setIsComplicated(boolean isComplicatedParam){
        isComplicated = isComplicatedParam;
    }
    //得到sign
    public static String getOperatorSign(){
        return operatorSign;
    }
    //得到isOperator
    public static Boolean getIsOperator(){
        return isOperator;
    }
    //得到isComplicated
    public static Boolean getIsComplicated(){
        return isComplicated;
    }
    //得到num1
    public static Double getCalculatorNum1(){
        return calculatorNum1;
    }
    //得到num2
    public static Double getCalculatorNum2(){
        return calculatorNum2;
    }
    //除法
    public static Double divide(){
        if(clickOnceEqual){
            swap();
        }
        return calculatorNum1 / calculatorNum2;
    }
    //乘法
    public static Double multiply(){
        if(clickOnceEqual){
            swap();
        }

        return calculatorNum1 * calculatorNum2;
    }
    //减法
    public static Double sub(){
        if(clickOnceEqual){
            swap();
        }
        return calculatorNum1 - calculatorNum2;
    }
    //加法
    public static Double add(){
        if(clickOnceEqual){
            swap();
        }
        return calculatorNum1 + calculatorNum2;
    }
    //处理结果
    public static String processResult(Double result){

        if(isCanToInt(result)){
            return addIntComma(String.valueOf(result.intValue()));
        }else {
            return addDoubleComma(String.valueOf(result));
        }
    }
    public static String insertCommaWhenAddNum(String display){
        String deleteDotStr = display.replace(",","");
        if(isCanToInt(convertToDouble(deleteDotStr))){
            return addIntComma(deleteDotStr);
        }else{
            return addDoubleComma(deleteDotStr);
        }
    }
    public static boolean isLonger(String display){
        if(display.length() > 12){
            return true;
        }
        return false;
    }
}
