package com.docker.calculator.serviceImpl;

import com.docker.calculator.DTO.OperandsDTORequest;
import com.docker.calculator.model.Calculator;
import com.docker.calculator.model.Operations;
import com.docker.calculator.repository.CalculatorRepository;
import com.docker.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Autowired
    private CalculatorRepository calculatorRepository;

    @Override
    public int additionOfNumbers(OperandsDTORequest operandsDTORequest) {
        int result=operandsDTORequest.getA()+operandsDTORequest.getB();
        Calculator calculator= new Calculator();
        calculator.setOperandA(operandsDTORequest.getA());
        calculator.setOperandB(operandsDTORequest.getB());
        calculator.setResult(result);
        calculator.setOperation(Operations.ADDITION);
        calculatorRepository.save(calculator);
        return result ;
    }

    @Override
    public int subtractionOfNumbers(OperandsDTORequest operandsDTORequest) {
        int result=Math.abs(operandsDTORequest.getA() - operandsDTORequest.getB());
        Calculator calculator= new Calculator();
            calculator.setOperandA(operandsDTORequest.getA());
            calculator.setOperandB(operandsDTORequest.getB());
            calculator.setResult(result);
            calculator.setOperation(Operations.ABSOLUTE_SUBTRACTION);
            calculatorRepository.save(calculator);
            return result;
        }



    @Override
    public int multiplicationOfNumbers(OperandsDTORequest operandsDTORequest) {
        int result=operandsDTORequest.getA() * operandsDTORequest.getB();
        Calculator calculator= new Calculator();
        calculator.setOperandA(operandsDTORequest.getA());
        calculator.setOperandB(operandsDTORequest.getB());
        calculator.setResult(result);
        calculator.setOperation(Operations.MULTIPLICATION);
        calculatorRepository.save(calculator);

        return result;
    }
}
