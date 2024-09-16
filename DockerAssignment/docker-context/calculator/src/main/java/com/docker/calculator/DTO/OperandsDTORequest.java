package com.docker.calculator.DTO;

import lombok.Data;

@Data

public class OperandsDTORequest {
    private int a;
    private int b;
	public int getA() {

		return this.a;
	}
	public int getB() {

		return this.b;
	}

}
