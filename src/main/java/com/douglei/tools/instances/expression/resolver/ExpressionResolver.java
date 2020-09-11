package com.douglei.tools.instances.expression.resolver;

import java.util.LinkedList;
import java.util.Map;

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
		LinkedList<OperatorType> operatorTypes = new LinkedList<OperatorType>();// 操作符类型集合
		LinkedList<Object> result = new LinkedList<Object>();// 操作结果值集合
		
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
				result.add(getValue(expressionToken.toString()));
				expressionToken.setLength(0);
				postfixExpression.append(result.getLast());
			}
			
			if(operatorTypes.isEmpty()){
				operatorTypes.add(operatorType);
			}else{
				if(operatorType == OperatorType.RIGHT_PARENTHESES){
					while(!operatorTypes.isEmpty()){
						if(operatorTypes.getLast() == OperatorType.LEFT_PARENTHESES){
							operatorTypes.removeLast();
							break;
						}
						postfixExpression.append(operatorTypes.getLast().getSymbol());
						result.add(operatorTypes.removeLast().calc(result.removeLast(), result.removeLast(), map));
					}
				}else{
					// 当前操作符的优先级，小于等于集合中最后一个元素，就要将最后一个元素取出(循环判断操作)，再将新的操作符add到集合
					if(operatorTypes.getLast() != OperatorType.LEFT_PARENTHESES && operatorType.getPriority() <= operatorTypes.getLast().getPriority()){
						do{
							postfixExpression.append(operatorTypes.getLast().getSymbol());
							result.add(operatorTypes.removeLast().calc(result.removeLast(), result.removeLast(), map));
						}while(!operatorTypes.isEmpty() && operatorTypes.getLast() != OperatorType.LEFT_PARENTHESES && operatorType.getPriority() <= operatorTypes.getLast().getPriority());
					}
					operatorTypes.add(operatorType);
				}
			}
		}
		
		// 最后还要把最后一次的值，放到集合中
		if(!StringUtil.isEmpty(expressionToken)){
			result.add(getValue(expressionToken.toString()));
			expressionToken.setLength(0);
			postfixExpression.append(result.getLast());
		}
		
		while(!operatorTypes.isEmpty()){
			if(operatorTypes.getLast() == OperatorType.LEFT_PARENTHESES){
				throw new IllegalArgumentException("表达式异常，'()' 没有正确匹配，缺少 ')'");
			}
			postfixExpression.append(operatorTypes.getLast().getSymbol());
			result.add(operatorTypes.removeLast().calc(result.removeLast(), result.removeLast(), map));
		}
		this.postfixExpression = postfixExpression.toString();
		return result.removeLast();
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
