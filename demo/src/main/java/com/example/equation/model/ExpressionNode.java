package com.example.equation.model;

public class ExpressionNode {

    private String value;
    private ExpressionNode left;
    private ExpressionNode right;

    public ExpressionNode(String value) {
        this.value = value;
    }

    public ExpressionNode(String value, ExpressionNode left, ExpressionNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public String getValue() {
        return value;
    }

    public ExpressionNode getLeft() {
        return left;
    }

    public ExpressionNode getRight() {
        return right;
    }
}
