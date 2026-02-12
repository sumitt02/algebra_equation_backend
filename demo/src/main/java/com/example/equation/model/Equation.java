package com.example.equation.model;

public class Equation {

    private Long id;
    private String originalEquation;
    private ExpressionNode root;

    public Equation(Long id, String originalEquation, ExpressionNode root) {
        this.id = id;
        this.originalEquation = originalEquation;
        this.root = root;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalEquation() {
        return originalEquation;
    }

    public ExpressionNode getRoot() {
        return root;
    }
}
