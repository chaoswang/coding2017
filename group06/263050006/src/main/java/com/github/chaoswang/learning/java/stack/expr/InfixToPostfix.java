package com.github.chaoswang.learning.java.stack.expr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 将中缀表达式转换为后缀表达式：
(1) 初始化两个栈：运算符栈S1和储存中间结果的栈S2；
(2) 从左至右扫描中缀表达式；
(3) 遇到操作数时，将其压入S2；
(4) 遇到运算符时，比较其与S1栈顶运算符的优先级：
(4-1) 如果S1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
(4-2) 否则，若优先级比栈顶运算符的高，也将运算符压入S1（注意转换为前缀表达式时是优先级较高或相同，而这里则不包括相同的情况）；
(4-3) 否则，将S1栈顶的运算符弹出并压入到S2中，再次转到(4-1)与S1中新的栈顶运算符相比较；
(5) 遇到括号时：
(5-1) 如果是左括号“(”，则直接压入S1；
(5-2) 如果是右括号“)”，则依次弹出S1栈顶的运算符，并压入S2，直到遇到左括号为止，此时将这一对括号丢弃；
(6) 重复步骤(2)至(5)，直到表达式的最右边；
(7) 将S1中剩余的运算符依次弹出并压入S2；
(8) 依次弹出S2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式（转换为前缀表达式时不用逆序）。
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