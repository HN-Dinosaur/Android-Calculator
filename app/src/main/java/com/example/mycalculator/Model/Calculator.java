package com.example.mycalculator.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public static String expression = "";
    public static boolean isOperator = false;
    public static String operatorSign = "";
    public static String num1 = "";
    public static String num2 = "";
    public static boolean isComplicated = false;
    public static boolean isLeftBracket = false;
    public static boolean clickOnceEqual = false;


    //相反数
    public static String negation(String displayText){
        Double num = convertToDouble(replaceDot(displayText));
        num = -num;
        return processResult(num);
    }
    //百分数
    public static String percent(String displayText){
        Double num = convertToDouble(displayText);
        if(num == 0){
            return "0";
        }
        return processResult(num / 100);
    }
    //按数字按钮
    public static String addNumInScreen(String displayText,String addNum){

        if(displayText.equals("0")){
            return addNum;
        }else if(isComplicated || isOperator){
            isComplicated = false;
            isOperator = false;
            return addNum;
        }else if(isLonger(displayText)){
            return displayText;
        }
        return insertCommaWhenAddNum(displayText + addNum);
    }
    //添加数字在表达式里
    public static void addNumToExpression(String display){
        String str = replaceDot(display);
        //当前表达式为空
        if(expression.isEmpty()){
            expression += str;
            //当前表达式不为空
        }else{
            expression += " " + str;
        }
    }
    public static String replaceDot(String display){
        return display.replace(",","");
    }
    //添加小数点
    public static String addDot(String displayText){
        String addDot = displayText + ".";
        //如果已经包含小数点了
        if(displayText.contains(".")){
            return displayText;
        }
        if(isComplicated){
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
        String deleteDotStr = replaceDot(display);
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
        String deleteDotStr = replaceDot(display);
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


    public static void setNum1(String num){
        num1 = replaceDot(num);
    }
    public static void setNum2(String num){
        num2 = replaceDot(num);
    }

    //set isOperator
    public static void setIsOperator(boolean isOperatorParam){
        isOperator = isOperatorParam;
    }
    //set isComplicated
    public static void setIsComplicated(boolean isComplicatedParam){
        isComplicated = isComplicatedParam;
    }
    //得到isOperator
    public static Boolean getIsOperator(){
        return isOperator;
    }
    //得到isComplicated
    public static Boolean getIsComplicated(){
        return isComplicated;
    }

    public static double calculator(){
        isComplicated = true;
        if(!clickOnceEqual){
            clickOnceEqual = true;
            return getResult(infixToSuffix(expression));
        }else {
            return getResult(infixToSuffix(num1 + " " + operatorSign + " " + num2));
        }

    }


    //处理结果
    public static String processResult(Double result){
        if(isCanToInt(result)){
            return addIntComma(String.valueOf(result.intValue()));
        }else {
            return addDoubleComma(String.valueOf(result));
        }
    }
    //在添加数字时添加逗号
    public static String insertCommaWhenAddNum(String display){
        String deleteDotStr = replaceDot(display);
        if(isCanToInt(convertToDouble(deleteDotStr))){
            return addIntComma(deleteDotStr);
        }else{
            return addDoubleComma(deleteDotStr);
        }
    }
    //长度是否超过
    public static boolean isLonger(String display){
        if(display.length() > 12){
            return true;
        }
        return false;
    }
    //平方
    public static String square(String display){
        String str = replaceDot(display);
        Double num = convertToDouble(str);
        return processResult(num * num);

    }

    public static void addSign(String display, String sign){

        //当点击等于号时
        if(sign.equals("=")){
            setNum2(display);
            if(!isRightBracketAndOperator()){
                addNumToExpression(display);
            }
            //左括号 且没有左括号的时候
        }else if(sign.equals("(") && !isLeftBracket){
            if(expression.isEmpty()){
                expression += sign;
            }else{
                if(!isOperatorAndLeftBracket()){
                    addNumToExpression(display);
                }
                expression += " " + sign;
            }
            isLeftBracket = true;
            isOperator = true;
            //右括号 且有左括号的时候
        }else if(sign.equals(")") && isLeftBracket) {
            addNumToExpression(display);
            expression += " " + sign;
            isLeftBracket = false;
            isOperator = true;
            //四则运算
        } else if(isOperatorSign(sign)){
            //右括号 + 符号
            if(!isRightBracketAndOperator()){
                addNumToExpression(display);
            }
            String[] strArray = expression.split(" ");
            String last = strArray[strArray.length - 1];
            if(!isSameCategorySign(last, sign)){
                setNum1(display);
                operatorSign = sign;
                //如果不是相同类的符号
                expression += " " + sign;
                isOperator = true;
                clickOnceEqual = false;
            }
        }
    }
    public static boolean isRightBracketAndOperator(){
        String[] strArray = expression.split(" ");
        String str = strArray[strArray.length - 1];
        return str.equals(")");
    }
    public static boolean isOperatorAndLeftBracket(){
        String[] strArray = expression.split(" ");
        String str = strArray[strArray.length - 1];
        return isOperatorSign(str);
    }
    //
    public static String sqrt(String display){
        String str = replaceDot(display);
        Double num = convertToDouble(str);
        return processResult(Math.sqrt(num));
    }
    public static void clear(){
        expression = "";
        isOperator = false;
        isComplicated = false;
    }
    //判断表达式
    public static boolean isValidExpression(){
        //只有一个左括号的情况   自动添加右括号在末尾
        String[] strArray = expression.split(" ");
        boolean isCorrect= true;
        //遍历一遍  判断括号是否闭合
        for(int i = 0; i < strArray.length; i++){
            if(strArray[i].equals("(")){
                isCorrect = false;
            }else if(strArray[i].equals(")")){
                isCorrect = true;
            }
        }
        return isCorrect;
    }




    /**
     * 计算后缀表达式
     * @param suffix 9 3 1 - 3 * + 10 2 / + 以空格分隔
     */
    public static double getResult(String suffix){
        String[] strArray = suffix.split(" ");

        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < strArray.length; i++) {
            String str = strArray[i];
            if (isNumber(str))  //如果是数字，则入栈
                stack.push(Double.parseDouble(str));
            else {
                //遇到运算符，则从栈中弹出两个数字，进行运算
                double num1 = stack.pop();
                double num2 = stack.pop();
                double res = 0;
                switch (str){
                    case "+":res = num1 + num2;break;
                    case "-":res = num2 - num1;break;
                    case "*":res = num1 * num2;break;
                    case "/":res = num2 / num1;break;
                }
                stack.push(res);
            }
        }
        expression = "";
        return stack.pop();
    }




    /**
     * 将中缀表达式转化为后缀表达式
     * @param string 9 + ( 3 - 1 ) * 3 + 10 / 2
     * @return
     */
    public static String infixToSuffix(String string){
        Stack<String> stack = new Stack();
        String[] strArray = string.split(" ");
        String res = "";
        for (int i = 0; i < strArray.length; i++) {
            String str = strArray[i];

            //是数字直接加在字符串上
            if (isNumber(str)){
                if (res.length()==0)
                    res += str;
                else
                    res += " " + str;
            //是符号的话
            }else {
                if (str.equals("(")){
                    stack.push(str);
                }else {
                    if (str.equals(")")){
                        String sign = "";
                        String temp = "";
                        while (!(sign = stack.pop()).equals("(")){
                            temp += " " + sign;
                        }
                        res += temp;
                    }else {
                        int priority = getPriority(str);
                        String temp = "";
                        boolean flag = false;
                        while (!stack.empty()){
                            flag = false;
                            temp = stack.pop();
                            if (temp.equals("(")){
                                break;
                            }
                            if (getPriority(temp) >= priority){
                                res += " " + temp;
                                flag = true;
                            }
                        }
                        //如果temp为空即stack为空且1.没进while 2. 里边的优先级小于后边的 比如+ * 否则进入if
                        if (!temp.equals("") && !flag)
                            stack.push(temp);
                        stack.push(str);
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
    //判断是否出现相同的符号
    public static boolean isSameCategorySign(String sign1, String sign2){
        if(isOperatorSign(sign1) && isOperatorSign(sign2)){
            return true;
        }else if(isBracketSign(sign1) && isBracketSign(sign2)){
            return true;
        }
        return false;
    }

    //是否是运算符
    public static boolean isOperatorSign(String str){
        Pattern pattern = Pattern.compile("^[*|\\|+|-]$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    //是否是括号
    public static boolean isBracketSign(String str){
        Pattern pattern = Pattern.compile("^[(|)]$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
    //是否是小数或者整数
    public static boolean isNumber(String str){
        //匹配整数
        Pattern pattern1 = Pattern.compile("^(0|[1-9][0-9]*)$");
        //匹配小数
        Pattern pattern2 = Pattern.compile("^([0-9]{1,}[.][0-9]*)$");
        Matcher matcher1 = pattern1.matcher(str);
        Matcher matcher2 = pattern2.matcher(str);
        return matcher1.find() || matcher2.find();
    }
}
