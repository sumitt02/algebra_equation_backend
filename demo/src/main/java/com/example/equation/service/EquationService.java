package com.example.equation.service;

import com.example.equation.model.Equation;
import com.example.equation.model.ExpressionNode;
import com.example.equation.repository.EquationRepository;
import com.example.equation.util.ExpressionTreeBuilder;
import com.example.equation.util.InfixToPostfixConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EquationService {

    private final EquationRepository repository;

    public EquationService(EquationRepository repository) {
        this.repository = repository;
    }

    public Equation storeEquation(String equation) {

        List<String> postfix = InfixToPostfixConverter.convert(equation);
        ExpressionNode root = ExpressionTreeBuilder.buildTree(postfix);

        return repository.save(equation, root);
    }

    
    public List<Equation> getAllEquations() {
        return repository.findAll();
    }

   
    public Equation getEquationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equation not found with id: " + id));
    }

    
    public double evaluateEquation(Long id, Map<String, Double> variables) {

        Equation equation = getEquationById(id);
        return evaluateNode(equation.getRoot(), variables);
    }

   
    private double evaluateNode(ExpressionNode node, Map<String, Double> variables) {

        String value = node.getValue();

        
        if (isOperator(value)) {
            double left = evaluateNode(node.getLeft(), variables);
            double right = evaluateNode(node.getRight(), variables);
            return applyOperator(value, left, right);
        }

       
        if (isNumber(value)) {
            return Double.parseDouble(value);
        }

        
        if (!variables.containsKey(value)) {
            throw new RuntimeException("Missing value for variable: " + value);
        }

        return variables.get(value);
    }

    
    private boolean isOperator(String value) {
        return "+-*/^".contains(value);
    }

    private boolean isNumber(String value) {
        return value.matches("\\d+(\\.\\d+)?");
    }

    private double applyOperator(String operator, double left, double right) {

        switch (operator) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                if (right == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return left / right;
            case "^":
                return Math.pow(left, right);
            default:
                throw new RuntimeException("Unsupported operator: " + operator);
        }
    }
}
