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
	 * ��������ɨ����ʽ����������ʱ��������ѹ���ջ�����������ʱ������ջ������������ 
	 * �����������������Ӧ�ļ��㣨�ζ�Ԫ�� op ջ��Ԫ�أ������������ջ��
	 * �ظ���������ֱ�����ʽ���Ҷˣ��������ó���ֵ��Ϊ���ʽ�Ľ����
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