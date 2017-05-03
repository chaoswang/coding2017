package com.github.chaoswang.learning.java.stack.expr;

import org.junit.Assert;
import org.junit.Test;

/**
 * 从左至右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数， 用运算符对它们做相应的计算（次顶元素 op 栈顶元素），并将结果入栈；
 * 重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果。
 */
public class PostfixExprTest {
	@Test
	public void testEvaluate() {
		//下面的大括号仅仅只是起划分区域作用
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
