package com.github.chaoswang.learning.java.stack.expr;

import org.junit.Assert;
import org.junit.Test;

/**
 * ��������ɨ�����ʽ����������ʱ��������ѹ���ջ�����������ʱ������ջ������������
 * �����������������Ӧ�ļ��㣨ջ��Ԫ�� op �ζ�Ԫ�أ������������ջ��
 * �ظ���������ֱ������ʽ����ˣ��������ó���ֵ��Ϊ����ʽ�Ľ����
 *
 */
public class PrefixExprTest {
	@Test
	public void testEvaluate() {
		{
			// 2*3+4*5 
			PrefixExpr expr = new PrefixExpr("+ * 2 3* 4 5");
			Assert.assertEquals(26, expr.evaluate(),0.001f);
		}
		{
			// 4*2 + 6+9*2/3 -8
			PrefixExpr expr = new PrefixExpr("-++6/*2 9 3 * 4 2 8");
			Assert.assertEquals(12, expr.evaluate(),0.001f);
		}
		{
			//(3+4)*5-6
			PrefixExpr expr = new PrefixExpr("- * + 3 4 5 6");
			Assert.assertEquals(29, expr.evaluate(),0.001f);
		}
		{
			//1+((2+3)*4)-5
			PrefixExpr expr = new PrefixExpr("- + 1 * + 2 3 4 5");
			Assert.assertEquals(16, expr.evaluate(),0.001f);
		}
	}
}