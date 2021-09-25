package com.example.mycalculator.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public static String expression = "";
    public static Double calculatorNum1 = 0.0;
    public static Double calculatorNum2 = 0.0;
    public static Double result = 0.0;
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
        if(expression.isEmpty()){
            expression = String.valueOf(calculatorNum1);
        }else{
            expression += " " + calculatorNum1;
        }
    }
    //set num2
    public static void setCalculatorNum2(String display){
        String temp = display.replace(",","");
        calculatorNum2 = Double.parseDouble(temp);
        expression += " " + calculatorNum2;
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

    public static double calculator(){
        result = getResult(infixToSuffix(expression));
        return result;
    }

    //交换两个数
    public static void swap(){
        double temp = calculatorNum1;
        calculatorNum1 = calculatorNum2;
        calculatorNum2 = temp;
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
    public static String square(String display){
        Double num = convertToDouble(display);
        return String.valueOf(num * num);

    }
    public static void addSign(String sign){
        if(expression.isEmpty()){
            expression += sign;
        }else{
            String[] strArray = expression.split(" ");
            String last = strArray[strArray.length - 1];
            if(!isSign(last)){
                expression += " " + sign;
            }
        }
    }
    public static String sqrt(String display){
        Double num = convertToDouble(display);
        return String.valueOf(Math.sqrt(num));
    }



    /**
     * 计算后缀表达式
     * @param inputString 9 3 1 - 3 * + 10 2 / + 以空格分隔
     */
    public static double getResult(String inputString){
        String[] input = inputString.split(" ");

        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < input.length; i++) {
            String s = input[i];
            if (isNumber(s))  //如果是数字，则入栈
                stack.push(Double.parseDouble(s));
            else {
                //遇到运算符，则从栈中弹出两个数字，进行运算
                double n1 = stack.pop();
                double n2 = stack.pop();
                double res = 0;
                switch (s){
                    case "+":res = n1 + n2;break;
                    case "-":res = n2 - n1;break;
                    case "*":res = n1 * n2;break;
                    case "/":res = n2 / n1;break;
                }
                stack.push(res);
            }
        }

        return stack.pop();
    }




    /**
     * 将中缀表达式转化为后缀表达式
     * @param string 9 + ( 3 - 1 ) * 3 + 10 / 2
     * @return
     */
    public static String infixToSuffix(String string){
        Stack<String> stack = new Stack();
        String[] chars = string.split(" ");
        String res = "";
        for (int i = 0; i < chars.length; i++) {
            String s = String.valueOf(chars[i]);
            if (isNumber(s)){
                if (res.length()==0)
                    res += s;
                else
                    res += " "+s;
            }else {
                if (s.equals("(")){
                    stack.push(s);
                }else {
                    if (s.equals(")")){
                        String t = "";
                        String s1 = "";
                        while (!(t = stack.pop()).equals("(")){
                            s1 += " "+t;
                        }
                        res += s1;
                    }else {
                        int priority = getPriority(s);
                        String s1 = "";
                        boolean flag = false;
                        while (!stack.empty()){
                            flag = false;
                            s1 = stack.pop();
                            if (s1.equals("(")){
//                                stack.push("(");
                                break;
                            }

                            if (getPriority(s1) >= priority){
                                res += " " + s1;
                                flag = true;
                            }
                        }
                        if (!s1.equals("") && !flag)
                            stack.push(s1);
                        stack.push(s);
                    }
                }


            }

        }

        while (!stack.empty()){
            res += " " + stack.pop();
        }
        return res;
    }


    //获取运算符的优先级
    public static int getPriority(String s){
        Map<String,Integer> map = new HashMap<>();
        map.put("+",0);
        map.put("-",0);
        map.put("*",1);
        map.put("/",1);
        map.put("(",2);
        map.put(")",2);


        return map.get(s);
    }

    public static boolean isSign(String c){
        Pattern pattern = Pattern.compile("^[*|\\|+|-|(|)]$");
        Matcher matcher = pattern.matcher(c);
        return matcher.find();
    }

    public static boolean isNumber(String c){
        //匹配整数
        Pattern pattern1 = Pattern.compile("^(0|[1-9][0-9]*)$");
        //匹配小数
        Pattern pattern2 = Pattern.compile("^([0-9]{1,}[.][0-9]*)$");
        Matcher matcher1 = pattern1.matcher(c);
        Matcher matcher2 = pattern2.matcher(c);
        return matcher1.find() || matcher2.find();
    }


}
