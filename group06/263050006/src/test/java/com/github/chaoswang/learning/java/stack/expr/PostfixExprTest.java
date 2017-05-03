package com.github.chaoswang.learning.java.stack.expr;

import org.junit.Assert;
import org.junit.Test;

/**
 * ��������ɨ����ʽ����������ʱ��������ѹ���ջ�����������ʱ������ջ������������ �����������������Ӧ�ļ��㣨�ζ�Ԫ�� op ջ��Ԫ�أ������������ջ��
 * �ظ���������ֱ�����ʽ���Ҷˣ��������ó���ֵ��Ϊ���ʽ�Ľ����
 */
public class PostfixExprTest {
	@Test
	public void testEvaluate() {
		//����Ĵ����Ž���ֻ���𻮷���������
		{   // 6*(5+(2+3)*8+3)
			PostfixExpr expr = new PostfixExpr("6 5 2 3 + 8 * + 3 + *");
			Assert.assertEquals(288, expr.evaluate(), 0.0f);
		}
		{
			// 9+(3-1)*3+10/2
			PostfixExpr expr = new PostfixExpr("9 3 1-3*+ 10 2/+");
			Assert.assertEquals(20, expr.evaluate(), 0.0f);
		}
		{
			// 10-2*3+50
			PostfixExpr expr = new PostfixExpr("10 2 3 * - 50 +");
			Assert.assertEquals(54, expr.evaluate(), 0.0f);
		}
	}

}
