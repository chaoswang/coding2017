package com.github.chaoswang.learning.java.stack.operation;

import java.util.HashMap;
import java.util.Map;

public class OperationFactory {
	private static final Map<String, Operation> operations = new HashMap<>();
	static {
		operations.put("+", new AddImpl());
		operations.put("-", new MinusImpl());
		operations.put("*", new MultiplyImpl());
		operations.put("/", new DivideImpl());
	}
	
	public static Operation getOperation(String operator){
		return operations.get(operator);
	}
}
