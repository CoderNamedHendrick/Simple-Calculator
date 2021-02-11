package com.example.calculator;

import java.util.*;

public class StringInputToFloatResultParser {
    private static List<Float> arguments;
    private static Stack<Character> operands;
    private final float mResult;
    private String mOperator;
    private final Random mRand;
    private final List<String> mOperators;

    public StringInputToFloatResultParser(String input){
        this.arguments = new ArrayList<Float>();
        this.operands = new Stack<>();

        mOperators = new ArrayList<>(Arrays.asList("+", "-", "x", "/"));
        mRand = new Random();
        mOperator = mOperators.get(mRand.nextInt(mOperators.size()));
        getArguments(input);

        final int times = operands.size();
        mResult = (float) calculate(times);
    }

    // Method to calculate the arguments which are placed the arguments ArrayList.
    private Float calculate(int times) {
        Float result = null;
        for (int i = 0; i < times; i++){
            Float firstArgs;
            Float secondArgs;
            if (operands.contains('/')){
                firstArgs = arguments.get(operands.indexOf('/'));
                secondArgs = arguments.get(operands.indexOf('/') + 1);
                result = firstArgs / secondArgs;
                arguments.set(operands.indexOf('/'), result); arguments.remove(operands.indexOf('/') + 1);
                operands.remove(operands.indexOf('/'));
            } else if (operands.contains('x')){
                firstArgs = arguments.get(operands.indexOf('x'));
                secondArgs = arguments.get(operands.indexOf('x') + 1);
                result = firstArgs * secondArgs;
                arguments.set(operands.indexOf('x'), result); arguments.remove(operands.indexOf('x') + 1);
                operands.remove(operands.indexOf('x'));
            } else if (operands.firstElement() == '+'){
                firstArgs = arguments.get(operands.indexOf('+'));
                secondArgs = arguments.get(operands.indexOf('+') + 1);
                result = firstArgs + secondArgs;
                arguments.set(operands.indexOf('+'), result); arguments.remove(operands.indexOf('+') + 1);
                operands.remove(operands.indexOf('+'));
            } else if (operands.firstElement() == '-'){
                firstArgs = arguments.get(operands.indexOf('-'));
                secondArgs = arguments.get(operands.indexOf('-') + 1);
                result = firstArgs - secondArgs;
                arguments.set(operands.indexOf('-'), result); arguments.remove(operands.indexOf('-') + 1);
                operands.remove(operands.indexOf('-'));
            }
        }

        return result;
    }

    private void getArguments(String input) {
        try{
            Float.parseFloat(input);
            getArguments(input, null);
            return;
        } catch (NumberFormatException e){
            if (input.contains(mOperator)) {
                getArguments(input, mOperator);
                return;
            }
            else {
                mOperators.remove(mOperator);
                mOperator = mOperators.get(mRand.nextInt(mOperators.size()));
                getArguments(input);
            }
        }
    }

    private void getArguments(String input, String operand){
        if (operand == null){
            arguments.add(Float.parseFloat(input));
            return;
        }
        String initialExpression = input.substring(0, input.indexOf(operand));
        try {
            arguments.add(Float.parseFloat(initialExpression));
        }
        catch (NumberFormatException e){
            if (initialExpression.contains("-")){
                getArguments(initialExpression, "-");
            }else if (initialExpression.contains("+")){
                getArguments(initialExpression, "+");
            } else if (initialExpression.contains("x")){
                getArguments(initialExpression, "x");
            } else if (initialExpression.contains("/")){
                getArguments(initialExpression,"/");
            }
        }
        input = input.substring(input.indexOf(operand)+1);
        operands.push(operand.charAt(0));
        getArguments(input);
    }

    @Override
    public String toString() {
        return Float.toString(mResult);
    }
}
