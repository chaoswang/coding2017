package com.github.chaoswang.learning.java.stack.operation;

public class DivideImpl implements Operation{

	@Override
	public float doOperation(float a, float b) {
		try {
			return a / b;
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
}
