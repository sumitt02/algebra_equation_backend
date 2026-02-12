package com.example.equation.util;

import java.util.*;

public class InfixToPostfixConverter {

    private static final Map<String, Integer> PRECEDENCE = new HashMap<>();

    static {
        PRECEDENCE.put("+", 1);
        PRECEDENCE.put("-", 1);
        PRECEDENCE.put("*", 2);
        PRECEDENCE.put("/", 2);
        PRECEDENCE.put("^", 3);
    }

    public static List<String> convert(String expression) {
        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        expression = expression.replaceAll("\\s+", "");

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                output.add(String.valueOf(ch));
            } else if (ch == '(') {
                stack.push("(");
            } else if (ch == ')') {
                while (!stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                stack.pop();
            } else {
                String op = String.valueOf(ch);
                while (!stack.isEmpty()
                        && PRECEDENCE.containsKey(stack.peek())
                        && PRECEDENCE.get(stack.peek()) >= PRECEDENCE.get(op)) {
                    output.add(stack.pop());
                }
                stack.push(op);
            }
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }
}
