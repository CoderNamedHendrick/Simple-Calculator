package com.example.calculator;

import java.util.*;

public class CalculateInputs {
    private static String input;
    private static final List<Integer> arguments = new ArrayList<>();
    private static final Stack<Character> operands = new Stack<>();
    private int result;

    public CalculateInputs(String input){
        CalculateInputs.input = input;
        List<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));
        Random rand = new Random();
        String operator = operators.get(rand.nextInt(operators.size()));
        getArguments(operators, rand, operator);

        final int times = operands.size();
        calculate(times);
    }

    private void calculate(int times) {
        for (int i = 0; i < times; i++){
            Integer firstArgs;
            Integer secondArgs;
            if (operands.contains('/')){
                firstArgs = arguments.get(operands.indexOf('/'));
                secondArgs = arguments.get(operands.indexOf('/') + 1);
                result = firstArgs / secondArgs;
                arguments.set(operands.indexOf('/'), result); arguments.remove(operands.indexOf('/') + 1);
                operands.remove(operands.indexOf('/'));
            } else if (operands.contains('*')){
                firstArgs = arguments.get(operands.indexOf('*'));
                secondArgs = arguments.get(operands.indexOf('*') + 1);
                result = firstArgs * secondArgs;
                arguments.set(operands.indexOf('*'), result); arguments.remove(operands.indexOf('*') + 1);
                operands.remove(operands.indexOf('*'));
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
        }catch (NumberFormatException e){
            if (initialExpression.contains("-")){
                getArguments(initialExpression, "-");
            }else if (initialExpression.contains("+")){
                getArguments(initialExpression, "+");
            } else if (initialExpression.contains("*")){
                getArguments(initialExpression, "*");
            } else if (initialExpression.contains("/")){
                getArguments(initialExpression,"/");
            }
        }
        input = input.substring(input.indexOf(operand)+1);
        operands.push(operand.charAt(0));
        try{
            Integer.parseInt(input);
            arguments.add(Integer.parseInt(input));
        } catch (NumberFormatException e){
            if (input.contains("-")){
                getArguments(input, "-");
            }else if (input.contains("+")){
                getArguments(input, "+");
            } else if (input.contains("*")){
                getArguments(input, "*");
            } else if (input.contains("/")){
                getArguments(input,"/");
            }
        }
    }

    @Override
    public String toString() {
        return Integer.toString(result);
    }
}
