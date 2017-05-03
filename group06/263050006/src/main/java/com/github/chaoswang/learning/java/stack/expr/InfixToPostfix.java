package com.github.chaoswang.learning.java.stack.expr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * ����׺���ʽת��Ϊ��׺���ʽ��
(1) ��ʼ������ջ�������ջS1�ʹ����м�����ջS2��
(2) ��������ɨ����׺���ʽ��
(3) ����������ʱ������ѹ��S2��
(4) ���������ʱ���Ƚ�����S1ջ������������ȼ���
(4-1) ���S1Ϊ�գ���ջ�������Ϊ�����š�(������ֱ�ӽ����������ջ��
(4-2) ���������ȼ���ջ��������ĸߣ�Ҳ�������ѹ��S1��ע��ת��Ϊǰ׺���ʽʱ�����ȼ��ϸ߻���ͬ���������򲻰�����ͬ���������
(4-3) ���򣬽�S1ջ���������������ѹ�뵽S2�У��ٴ�ת��(4-1)��S1���µ�ջ���������Ƚϣ�
(5) ��������ʱ��
(5-1) ����������š�(������ֱ��ѹ��S1��
(5-2) ����������š�)���������ε���S1ջ�������������ѹ��S2��ֱ������������Ϊֹ����ʱ����һ�����Ŷ�����
(6) �ظ�����(2)��(5)��ֱ�����ʽ�����ұߣ�
(7) ��S1��ʣ�����������ε�����ѹ��S2��
(8) ���ε���S2�е�Ԫ�ز���������������Ϊ��׺���ʽ��Ӧ�ĺ�׺���ʽ��ת��Ϊǰ׺���ʽʱ�������򣩡�
 *
 */
public class InfixToPostfix {
	public static List<Token> convert(String expr) {
		Stack<Token> s1 = new Stack<Token>();
		Stack<Token> s2 = new Stack<Token>();
		List<Token> tokens  = new TokenParser().parse(expr);
		System.out.println(tokens);
		for(Token token : tokens){
			if(token.isNumber()){
				s2.push(token);
			}else if(token.isOperator()){
				int size1 = s1.size();
				for(int i=0;i<size1;i++){
					Token op = s1.peek();
					if(token.hasHigherPriority(op)){
						s1.push(token);
						break;
					}else{
						s2.push(s1.pop());
					}
				}
				size1 = s1.size();
				if(size1 == 0){
					s1.push(token);
				}
			}
		}
		int size1 = s1.size();
		for(int i=0;i<size1;i++){
			s2.push(s1.pop());
		}
		tokens = new ArrayList<Token>();
		int size2 = s2.size();
		for(int i=0;i<size2;i++){
			tokens.add(s2.pop());
		}
		Collections.reverse(tokens);
		System.out.println(tokens);
		return tokens;
	}
	
	

}