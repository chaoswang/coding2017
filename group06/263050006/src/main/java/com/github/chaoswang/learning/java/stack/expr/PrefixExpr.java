package com.github.chaoswang.learning.java.stack.expr;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.github.chaoswang.learning.java.stack.operation.Operation;
import com.github.chaoswang.learning.java.stack.operation.OperationFactory;

/**
 * 从右至左扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，
 * 用运算符对它们做相应的计算（栈顶元素 op 次顶元素），并将结果入栈；
 * 重复上述过程直到表达式最左端，最后运算得出的值即为表达式的结果。
 *
 */
public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		TokenParser parser = new TokenParser();
		List<Token> tokens  = parser.parse(expr);
		Collections.reverse(tokens);
		Stack<Float> stack = new Stack<Float>();
		for(Token token : tokens){
			if(token.isNumber()){
				stack.push(Float.parseFloat(token.value));
			}
			if(token.isOperator()){
				float num1 = stack.pop();
				float num2 = stack.pop();
				Operation op = OperationFactory.getOperation(token.value);
				stack.push(op.doOperation(num1, num2));
			}
		}
		return stack.pop();
	}
}
