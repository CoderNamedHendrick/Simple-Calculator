package sample;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Main{

    private static int result;
    private static Integer firstArgs;
    private static String restOfArgs;
    private static char operand;
    private static Integer secondArgs;
    private static List<Integer> arguments;
    private static Stack<Character> operands;

    public static void main(String[] args) {
        restOfArgs = new String("75-33*23");
        int length = restOfArgs.split("[-+*/]").length;
        arguments = new ArrayList<>();
        operands = new Stack<>();
        String[] operators = {"+", "-", "*", "/"};
        Random rand = new Random();


        getArguments(restOfArgs, operators[rand.nextInt(operators.length)]);


        System.out.println(arguments.toString());
        System.out.println(operands.toString());

//        final int times = operands.size();
//        for (int i=0; i < times; i++){
//            if (operands.firstElement() == '+'){
//                firstArgs = arguments.get(0);
//                secondArgs = arguments.get(1);
//                result = firstArgs+secondArgs;
//                operands.remove(operands.firstElement());
//                arguments.set(0, result); arguments.remove(1);
//            } else if (operands.firstElement() == '-'){
//                firstArgs = arguments.get(0);
//                secondArgs = arguments.get(1);
//                result = firstArgs - secondArgs;
//                operands.remove(operands.firstElement());
//                arguments.set(0, result); arguments.remove(1);
//            } else if (operands.firstElement() == '*'){
//                firstArgs = arguments.get(0);
//                secondArgs = arguments.get(1);
//                result = firstArgs * secondArgs;
//                operands.remove(operands.firstElement());
//                arguments.set(0, result); arguments.remove(1);
//            } else if (operands.firstElement() == '/'){
//                firstArgs = arguments.get(0);
//                secondArgs = arguments.get(1);
//                result = firstArgs / secondArgs;
//                operands.remove(operands.firstElement());
//                arguments.set(0, result); arguments.remove(1);
//            }
//        }
//        System.out.println(arguments.toString());
//        System.out.println(operands.toString());
//        for (int adds = 1; adds<ch.length; adds++){
//            result += Integer.parseInt(ch[adds]);
//        }
//        for (int i=0; i<length; i++){
//            s = spliter(s);
//        }


    }

    public static void getArguments(String input, String operand){
        try {
            arguments.add(Integer.parseInt(input.substring(0, input.indexOf(operand))));
        }catch (NumberFormatException e){
            String worker = input.substring(0, input.indexOf(operand));
            if (worker.contains("-")){
                getArguments(worker, "-");
//                arguments.add(0, Integer.parseInt(worker.substring(0, worker.indexOf("-"))));
//                arguments.add(1, Integer.parseInt(worker.substring(worker.indexOf("-")+1)));
//                operands.add(0, '-');
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
