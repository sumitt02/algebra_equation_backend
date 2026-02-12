package com.example.equation.repository;

import com.example.equation.model.Equation;
import com.example.equation.model.ExpressionNode;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EquationRepository {

    private final Map<Long, Equation> storage = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Equation save(String originalEquation, ExpressionNode root) {
        Long id = idGenerator.getAndIncrement();
        Equation equation = new Equation(id, originalEquation, root);
        storage.put(id, equation);
        return equation;
    }

    public Optional<Equation> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<Equation> findAll() {
        return new ArrayList<>(storage.values());
    }
}
