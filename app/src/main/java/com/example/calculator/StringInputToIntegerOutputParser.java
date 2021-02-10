package com.example.calculator;

import java.util.*;

public class StringInputToIntegerOutputParser {
    private static String input;
    private static List<Integer> arguments;
    private static Stack<Character> operands;
    private final int mResult;

    public StringInputToIntegerOutputParser(String input){
        this.input = input;
        this.arguments = new ArrayList<>();
        this.operands = new Stack<>();

        List<String> operators = new ArrayList<>(Arrays.asList("+", "-", "x", "/"));
        Random rand = new Random();
        String operator = operators.get(rand.nextInt(operators.size()));
        getArguments(operators, rand, operator);

        final int times = operands.size();
        mResult = calculate(times);
    }

    private Integer calculate(int times) {
        Integer result = null;
        for (int i = 0; i < times; i++){
            Integer firstArgs;
            Integer secondArgs;
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

    private void getArguments(List<String> operators, Random rand, String operator) {
        if (input.contains(operator)) {
        }
        else {
            operators.remove(operator);
            operator = operators.get(rand.nextInt(operators.size()));
            if (input.contains(operator)) {
            }
            else {
                operators.remove(operator);
                operator = operators.get(rand.nextInt(operators.size()));
                if (input.contains(operator)) {
                }
                else {
                    operators.remove(operator);
                    operator = operators.get(rand.nextInt(operators.size()));
                }
            }
        }
        getArguments(input, operator);
    }

    private void getArguments(String input, String operand){
        String initialExpression = input.substring(0, input.indexOf(operand));
        try {
            arguments.add(Integer.parseInt(initialExpression));
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
        try{
            Integer.parseInt(input);
            arguments.add(Integer.parseInt(input));
        }
        catch (NumberFormatException e){
            if (input.contains("-")){
                getArguments(input, "-");
            }else if (input.contains("+")){
                getArguments(input, "+");
            } else if (input.contains("x")){
                getArguments(input, "x");
            } else if (input.contains("/")){
                getArguments(input,"/");
            }
        }
    }

    @Override
    public String toString() {
        return Integer.toString(mResult);
    }
}
