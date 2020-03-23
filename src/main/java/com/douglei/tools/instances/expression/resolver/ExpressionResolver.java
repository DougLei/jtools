package com.douglei.tools.instances.expression.resolver;

import java.util.Map;
import java.util.Stack;

import com.douglei.tools.instances.expression.resolver.operator.OperatorType;
import com.douglei.tools.instances.expression.resolver.operator.OperatorTypeHandler;
import com.douglei.tools.utils.StringUtil;

/**
 * 表达式解析器
 * @author DougLei
 */
public class ExpressionResolver {
	private String expression;// 要解析的表达式
	private Map<String, Object> map;
	private String postfixExpression;// 后缀表达式

	public ExpressionResolver() {
	}
	public ExpressionResolver(String expression) {
		this(expression, null);
	}
	public ExpressionResolver(String expression, Map<String, Object> map) {
		updateExpression(expression);
		updateMap(map);
	}
	
	public Object resolve() {
		Stack<OperatorType> operatorTypes = new Stack<OperatorType>();// 操作符类型栈
		Stack<Object> result = new Stack<Object>();// 操作结果值栈
		
		StringBuilder expressionToken = new StringBuilder(expression.length());
		StringBuilder postfixExpression = new StringBuilder(expression.length());
		String thisOp = null;
		String thisTmpOp = null;
		OperatorType operatorType = null;
		OperatorType tmpOperatorType = null;
		
		int k = 0;
		for(int i=0;i<expression.length();i++){
			thisOp = expression.substring(i, i+1);
			if((operatorType = OperatorTypeHandler.getOperatorType(thisOp)) == null){ // 判断[==]、[and]等这种多个字符的操作符
				k = 2;
				while(k<=OperatorTypeHandler.maxOperatorStrLength && (i+k) <= expression.length()){// 判断[==]、[and]等这种多个字符的操作符
					thisTmpOp = expression.substring(i, i+k);
					if((operatorType = OperatorTypeHandler.getOperatorType(thisTmpOp)) != null){
						i = i+k-1;
						break;
					}
					k++;
				}
			}else{
				if(i+2 <= expression.length()){
					thisTmpOp = expression.substring(i, i+2);
					if((tmpOperatorType = OperatorTypeHandler.getOperatorType(thisTmpOp)) != null){ // 判断[>=]、[<=]等这种和[>]、[<]字符的操作符
						thisOp = thisTmpOp;
						operatorType = tmpOperatorType;
						i = i+1;
					}
				}
			}
			
			if(operatorType == null){
				expressionToken.append(thisOp);
				continue;
			}
			
			if(!StringUtil.isEmpty(expressionToken)){
				result.push(getValue(expressionToken.toString()));
				expressionToken.setLength(0);
				postfixExpression.append(result.peek());
			}
			
			if(operatorTypes.isEmpty()){
				operatorTypes.push(operatorType);
			}else{
				if(operatorType == OperatorType.RIGHT_PARENTHESES){
					while(!operatorTypes.isEmpty()){
						if(operatorTypes.peek() == OperatorType.LEFT_PARENTHESES){
							operatorTypes.pop();
							break;
						}
						postfixExpression.append(operatorTypes.peek().getSymbol());
						result.push(operatorTypes.pop().calc(result.pop(), result.pop(), map));
					}
				}else{
					// 当前操作符的优先级，小于等于栈顶的元素，就要将栈顶元素弹出(循环判断操作)，再将新的操作符push到栈中
					if(operatorTypes.peek() != OperatorType.LEFT_PARENTHESES && operatorType.getPriority() <= operatorTypes.peek().getPriority()){
						do{
							postfixExpression.append(operatorTypes.peek().getSymbol());
							result.push(operatorTypes.pop().calc(result.pop(), result.pop(), map));
						}while(!operatorTypes.isEmpty() && operatorTypes.peek() != OperatorType.LEFT_PARENTHESES && operatorType.getPriority() <= operatorTypes.peek().getPriority());
					}
					operatorTypes.push(operatorType);
				}
			}
		}
		
		// 最后还要把最后一次的值，放到栈中
		if(!StringUtil.isEmpty(expressionToken)){
			result.push(getValue(expressionToken.toString()));
			expressionToken.setLength(0);
			postfixExpression.append(result.peek());
		}
		
		while(!operatorTypes.isEmpty()){
			if(operatorTypes.peek() == OperatorType.LEFT_PARENTHESES){
				throw new IllegalArgumentException("表达式异常，'()' 没有正确匹配，缺少 ')'");
			}
			postfixExpression.append(operatorTypes.peek().getSymbol());
			result.push(operatorTypes.pop().calc(result.pop(), result.pop(), map));
		}
		this.postfixExpression = postfixExpression.toString();
		return result.pop();
	}
	
	/**
	 * 获取value值
	 * @param valueString
	 * @return
	 */
	private Object getValue(String valueString) {
		valueString = valueString.trim();
		if(valueString.startsWith("\"") || valueString.startsWith("'")) {
			valueString = valueString.substring(1, valueString.length()-1);
		}
		return valueString;
	}
	
	public void updateExpression(String expression) {
		if(StringUtil.isEmpty(expression)){
			throw new NullPointerException("要解析的表达式不能为空");
		}
		this.expression = expression.trim();
	}
	public void updateMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public String getExpression() {
		return expression;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public String getPostfixExpression() {
		return postfixExpression;
	}
}
