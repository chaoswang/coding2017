package com.github.chaoswang.learning.java.stack.expr;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.github.chaoswang.learning.java.stack.operation.Operation;
import com.github.chaoswang.learning.java.stack.operation.OperationFactory;

/**
 * ��������ɨ����ʽ����������ʱ��������ѹ���ջ�����������ʱ������ջ������������
 * �����������������Ӧ�ļ��㣨ջ��Ԫ�� op �ζ�Ԫ�أ������������ջ��
 * �ظ���������ֱ�����ʽ����ˣ��������ó���ֵ��Ϊ���ʽ�Ľ����
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
