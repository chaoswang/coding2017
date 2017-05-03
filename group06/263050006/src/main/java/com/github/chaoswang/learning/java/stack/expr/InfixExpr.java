package com.github.chaoswang.learning.java.stack.expr;

import java.util.List;
import java.util.Stack;

import com.github.chaoswang.learning.java.stack.operation.Operation;
import com.github.chaoswang.learning.java.stack.operation.OperationFactory;

/**
 * ��׺����ʽ��һ��ͨ�õ��������߼���ʽ��ʾ����������������׺��ʽ���ڲ��������м䡣
 * ��׺����ʽ�����ǳ��õ�������ʾ��������Ȼ�˵Ĵ��Ժ����������������׺����ʽ��
 * ���Լ������˵��׺����ʽȴ�Ǻܸ��ӵģ���˼������ʽ��ֵʱ��ͨ����Ҫ�Ƚ���׺����ʽת��Ϊǰ׺���׺����ʽ��
 * Ȼ���ٽ�����ֵ���Լ������˵������ǰ׺���׺����ʽ��ֵ�ǳ��򵥡�
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