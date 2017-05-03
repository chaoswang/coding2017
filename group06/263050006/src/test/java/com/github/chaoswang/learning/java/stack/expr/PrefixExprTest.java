package com.github.chaoswang.learning.java.stack.expr;

import org.junit.Assert;
import org.junit.Test;

/**
 * 从右至左扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，
 * 用运算符对它们做相应的计算（栈顶元素 op 次顶元素），并将结果入栈；
 * 重复上述过程直到表达式最左端，最后运算得出的值即为表达式的结果。
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
