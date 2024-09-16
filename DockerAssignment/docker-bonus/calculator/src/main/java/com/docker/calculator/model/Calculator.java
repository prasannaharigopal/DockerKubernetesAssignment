package com.docker.calculator.model;

import javax.persistence.*;

@Entity
public class Calculator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long operandA;
    private long operandB;
    @Enumerated(EnumType.STRING)
    private Operations operation;
    private long result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getOperandA() {
        return operandA;
    }

    public void setOperandA(long operandA) {
        this.operandA = operandA;
    }

    public long getOperandB() {
        return operandB;
    }

    public void setOperandB(long operandB) {
        this.operandB = operandB;
    }

    public Operations getOperation() {
        return operation;
    }

    public void setOperation(Operations operation) {
        this.operation = operation;
    }

    public long getResult() {
        return result;
    }

    public void setResult(long result) {
        this.result = result;
    }


}
