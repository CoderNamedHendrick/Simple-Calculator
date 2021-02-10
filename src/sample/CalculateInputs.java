package sample;

import java.util.*;

public class CalculateInputs {
    private static Integer firstArgs;
    private static String input;
    private static Integer secondArgs;
    private static List<Integer> arguments = new ArrayList<>();
    private static Stack<Character> operands = new Stack<>();
    private List<String> operators = new ArrayList<>();
    private int result;

    public CalculateInputs(String input){
        this.input = new String(input);
        operators.addAll(Arrays.asList(new String[]{"+", "-", "*", "/"}));
        Random rand = new Random();
        String operator = operators.get(rand.nextInt(operators.size()));
        getArguments(operators, rand, operator);

        final int times = operands.size();
        calculate(times);
    }

    private int calculate(int times) {
        for (int i = 0; i < times; i++){
            if (operands.contains('/')){
                firstArgs = arguments.get(operands.indexOf('/'));
                secondArgs = arguments.get(operands.indexOf('/') + 1);
                result = firstArgs/secondArgs;
                arguments.set(operands.indexOf('/'), result); arguments.remove(operands.indexOf('/') + 1);
                operands.remove(operands.indexOf('/'));
            } else if (operands.contains('*')){
                firstArgs = arguments.get(operands.indexOf('*'));
                secondArgs = arguments.get(operands.indexOf('*') + 1);
                result = firstArgs*secondArgs;
                arguments.set(operands.indexOf('*'), result); arguments.remove(operands.indexOf('*') + 1);
                operands.remove(operands.indexOf('*'));
            } else if (operands.firstElement() == '+'){
                firstArgs = arguments.get(operands.indexOf('+'));
                secondArgs = arguments.get(operands.indexOf('+') + 1);
                result = firstArgs+secondArgs;
                arguments.set(operands.indexOf('+'), result); arguments.remove(operands.indexOf('+') + 1);
                operands.remove(operands.indexOf('+'));
            } else if (operands.firstElement() == '-'){
                firstArgs = arguments.get(operands.indexOf('-'));
                secondArgs = arguments.get(operands.indexOf('-') + 1);
                result = firstArgs-secondArgs;
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
        try {
            arguments.add(Integer.parseInt(input.substring(0, input.indexOf(operand))));
        }catch (NumberFormatException e){
            String worker = input.substring(0, input.indexOf(operand));
            if (worker.contains("-")){
                getArguments(worker, "-");
            }else if (worker.contains("+")){
                getArguments(worker, "+");
            } else if (worker.contains("*")){
                getArguments(worker, "*");
            } else if (worker.contains("/")){
                getArguments(worker,"/");
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
