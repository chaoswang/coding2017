package com.github.chaoswang.learning.java.stack.expr;

import java.util.List;
import java.util.Stack;

import com.github.chaoswang.learning.java.stack.operation.Operation;
import com.github.chaoswang.learning.java.stack.operation.OperationFactory;

/**
 * 中缀表达式是一种通用的算术或逻辑公式表示方法，操作符以中缀形式处于操作数的中间。
 * 中缀表达式是人们常用的算术表示方法。虽然人的大脑很容易理解与分析中缀表达式，
 * 但对计算机来说中缀表达式却是很复杂的，因此计算表达式的值时，通常需要先将中缀表达式转换为前缀或后缀表达式，
 * 然后再进行求值。对计算机来说，计算前缀或后缀表达式的值非常简单。
 *
 */
public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {		
		List<Token> tokens = InfixToPostfix.convert(expr);
		
		Stack<Float> stack = new Stack<Float>();
		for(Token token : tokens){
			if(token.isNumber()){
				stack.push(Float.parseFloat(token.value));
			}
			if(token.isOperator()){
				float num1 = stack.pop();
				float num2 = stack.pop();
				Operation op = OperationFactory.getOperation(token.value);
				stack.push(op.doOperation(num2, num1));
			}
		}
		return stack.pop();
	}
}
