package com.docker.calculator.controller;

import com.docker.calculator.DTO.OperandsDTORequest;
import com.docker.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/math/")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @PostMapping("/addition")
    public ResponseEntity<String> addition(@RequestBody OperandsDTORequest operandsDTORequest){
            return  ResponseEntity.ok("Addition of  A and B is "+calculatorService.additionOfNumbers(operandsDTORequest));

    }

    @PostMapping("/subtraction")
    public ResponseEntity<String> subtraction(@RequestBody OperandsDTORequest operandsDTORequest){
        return ResponseEntity.ok("Here the subtraction can be done by(Large Number with Small Number ) ,i.e  If A is smaller than B the the Subtraction result is (B - A),The Subtraction of given two numbers is "+calculatorService.subtractionOfNumbers(operandsDTORequest));
    }

    @PostMapping("/multiplication")
    public ResponseEntity<String> multiplication(@RequestBody OperandsDTORequest operandsDTORequest){
        return ResponseEntity.ok("Multiplication of A and B is "+ calculatorService.multiplicationOfNumbers(operandsDTORequest));
    }


}
