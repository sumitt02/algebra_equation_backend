package com.example.equation.controller;

import com.example.equation.model.Equation;
import com.example.equation.service.EquationService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/equations")
public class EquationController {

    private final EquationService equationService;

    public EquationController(EquationService equationService) {
        this.equationService = equationService;
    }

    @PostMapping("/store")
    public Map<String, String> storeEquation(@RequestBody Map<String, String> request) {

        String equation = request.get("equation");
        Equation savedEquation = equationService.storeEquation(equation);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Equation stored successfully");
        response.put("equationId", savedEquation.getId().toString());

        return response;
    }

    
    @GetMapping
    public Map<String, Object> getAllEquations() {

        List<Equation> equations = equationService.getAllEquations();

        List<Map<String, String>> equationList = equations.stream().map(eq -> {
            Map<String, String> map = new HashMap<>();
            map.put("equationId", eq.getId().toString());
            map.put("equation", eq.getOriginalEquation());
            return map;
        }).toList();

        Map<String, Object> response = new HashMap<>();
        response.put("equations", equationList);

        return response;
    }

    
    @PostMapping("/{id}/evaluate")
    public Map<String, Object> evaluateEquation(
            @PathVariable Long id,
            @RequestBody Map<String, Map<String, Double>> request
    ) {

        Map<String, Double> variables = request.get("variables");
        double result = equationService.evaluateEquation(id, variables);
        Equation equation = equationService.getEquationById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("equationId", id.toString());
        response.put("equation", equation.getOriginalEquation());
        response.put("variables", variables);
        response.put("result", result);

        return response;
    }
}
