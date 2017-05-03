package com.github.chaoswang.learning.java.stack.expr;

import java.util.List;
import java.util.Stack;

import com.github.chaoswang.learning.java.stack.operation.Operation;
import com.github.chaoswang.learning.java.stack.operation.OperationFactory;

public class PostfixExpr {

	String expr = null;
	
	public PostfixExpr(String expr) {
		this.expr = expr;
	}

	/**
	 * http://blog.csdn.net/antineutrino/article/details/6763722/
	 * 从左至右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数， 
	 * 用运算符对它们做相应的计算（次顶元素 op 栈顶元素），并将结果入栈；
	 * 重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果。
	 * // 6*(5+(2+3)*8+3)
	 * ("6 5 2 3 + 8 * + 3 + *");
	 */
	public float evaluate() {
		TokenParser parser = new TokenParser();
		List<Token> tokens  = parser.parse(expr);
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