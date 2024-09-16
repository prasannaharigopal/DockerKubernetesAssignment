package com.docker.calculator.service;

import com.docker.calculator.DTO.OperandsDTORequest;

public interface CalculatorService {
    int additionOfNumbers(OperandsDTORequest operandsDTORequest);

    int subtractionOfNumbers(OperandsDTORequest operandsDTORequest);

    int multiplicationOfNumbers(OperandsDTORequest operandsDTORequest);
}
