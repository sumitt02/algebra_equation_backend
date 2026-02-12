package com.example.equation.util;

import com.example.equation.model.ExpressionNode;

import java.util.List;
import java.util.Stack;

public class ExpressionTreeBuilder {

   
    public static ExpressionNode buildTree(List<String> postfixTokens) {

        Stack<ExpressionNode> stack = new Stack<>();

        for (String token : postfixTokens) {

            
            if (isOperand(token)) {
                stack.push(new ExpressionNode(token));
            }
            
            else {
                ExpressionNode right = stack.pop();
                ExpressionNode left = stack.pop();

                ExpressionNode operatorNode =
                        new ExpressionNode(token, left, right);

                stack.push(operatorNode);
            }
        }

        return stack.pop();
    }

    private static boolean isOperand(String token) {
        return token.matches("[a-zA-Z]+") || token.matches("\\d+(\\.\\d+)?");
    }
}
