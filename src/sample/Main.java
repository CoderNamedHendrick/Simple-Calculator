package sample;

import java.lang.reflect.Type;
import java.util.*;

public class Main{

    private static int result;
    private static Integer firstArgs;
    private static String restOfArgs;
    private static char operand;
    private static Integer secondArgs;
    private static List<Integer> arguments;
    private static Stack<Character> operands;

    public static void main(String[] args) {
        restOfArgs = new String("75-5+16");
        arguments = new ArrayList<>();
        operands = new Stack<>();
        String[] operandss = {"+", "-", "*", "/"};
        List<String> operators = new ArrayList<>();
        operators.addAll(Arrays.asList(operandss));
        Random rand = new Random();


        String operator = operators.get(rand.nextInt(operators.size()));
        System.out.println(operator);

        getArguments(operators, rand, operator);


        System.out.println(arguments.toString());
        System.out.println(operands.toString());

        final int times = operands.size();
        for (int i=0; i < times; i++){
            if (operands.contains('/')){
                firstArgs = arguments.get(operands.indexOf('/'));
                secondArgs = arguments.get(operands.indexOf('/') + 1);
                result = firstArgs+secondArgs;
                arguments.set(operands.indexOf('/'), result); arguments.remove(operands.indexOf('/') + 1);
                operands.remove(operands.indexOf('/'));
            } else if (operands.firstElement() == '*'){
                firstArgs = arguments.get(operands.indexOf('*'));
                secondArgs = arguments.get(operands.indexOf('*') + 1);
                result = firstArgs+secondArgs;
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
                result = firstArgs+secondArgs;
                arguments.set(operands.indexOf('-'), result); arguments.remove(operands.indexOf('-') + 1);
                operands.remove(operands.indexOf('-'));
            }
       }
        System.out.println(arguments.toString());
        System.out.println(operands.toString());
//        for (int adds = 1; adds<ch.length; adds++){
//            result += Integer.parseInt(ch[adds]);
//        }
//        for (int i=0; i<length; i++){
//            s = spliter(s);
//        }


    }

    private static void getArguments(List<String> operators, Random rand, String operator) {
        if (restOfArgs.contains(operator)) {
        }
        else {
            operators.remove(operator);
            operator = operators.get(rand.nextInt(operators.size()));
            if (restOfArgs.contains(operator)) {
            }
            else {
                operators.remove(operator);
                operator = operators.get(rand.nextInt(operators.size()));
                if (restOfArgs.contains(operator)) {
                }
                else {
                    operators.remove(operator);
                    operator = operators.get(rand.nextInt(operators.size()));
                }
            }
        }
        getArguments(restOfArgs, operator);
    }

    private static void getArguments(String input, String operand){
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
}
