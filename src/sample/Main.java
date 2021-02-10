package sample;

import java.util.*;

public class Main {

    private static int result;
    private static Integer firstArgs;
    private static String input;
    private static Integer secondArgs;
    private static List<Integer> arguments;
    private static Stack<Character> operands;

    public static void main(String[] args) {
        input = new String("32*2+55+77");

        CalculateInputs result = new CalculateInputs(input);
        System.out.println(result.toString());
    }

}