package com.douglei.instances.expression.resolver;

import java.util.Map;

/**
 * 表达式解析器的处理者
 * @author DougLei
 */
public class ExpressionResolverHandler {
	private ExpressionResolverHandler(){}
	private final ExpressionResolver expressionResolver = new ExpressionResolver();

	/**
	 * 获取单例实例
	 * @return
	 */
	public static final ExpressionResolverHandler singleInstance(){
		if(singleInstance == null){
			singleInstance = new ExpressionResolverHandler();
		}
		return singleInstance;
	}
	private static ExpressionResolverHandler singleInstance;
	
	/**
	 * 创建实例
	 * @return
	 */
	public static final ExpressionResolverHandler newInstance(){
		return new ExpressionResolverHandler();
	}
	
	// -----------------------------------------------------------------------------------------------------------
	/**
	 * 获取解析的expression
	 * @return
	 */
	public String getExpression() {
		return expressionResolver.getExpression();
	}
	
	/**
	 * 获取传入的map
	 * @return
	 */
	public Map<String, Object> getMap() {
		return expressionResolver.getMap();
	}
	
	/**
	 * 获取解析后的 后缀表达式
	 * @return
	 */
	public String getPostfixExpression() {
		return expressionResolver.getPostfixExpression();
	}
	
	// -----------------------------------------------------------------------------------------------------------
	/**
	 * 解析表达式
	 * <p>返回结果为object类型，需要调用者根据表达式手动转换为相应的数据类型</p>
	 * @param expression
	 * @return
	 */
	public Object resolve(String expression){
		return resolve(expression, null);
	}
	
	/**
	 * 根据map，解析表达式
	 * <p>返回结果为object类型，需要调用者根据表达式手动转换为相应的数据类型</p>
	 * @param expression
	 * @param map
	 * @return
	 */
	public Object resolve(String expression, Map<String, Object> map){
		return expressionResolver.resolve(expression, map);
	}
	
	// -----------------------------------------------------------------------------------------------------------
	/**
	 * 解析表达式的结果，为boolean类型
	 * @param expression
	 * @return
	 */
	public boolean resolveToBoolean(String expression){
		Object result = resolve(expression);
		if(!(result instanceof Boolean)){
			throw new IllegalArgumentException("["+expression+"]表达式解析结果为["+result+"]，非boolean类型，请检查表达式");
		}
		return (boolean) result;
	}
	
	/**
	 * 解析表达式的结果，为boolean类型
	 * @param expression
	 * @param map
	 * @return
	 */
	public boolean resolveToBoolean(String expression, Map<String, Object> map){
		Object result = resolve(expression, map);
		if(!(result instanceof Boolean)){
			throw new IllegalArgumentException("["+expression+"]表达式解析结果为["+result+"]，非boolean类型，请检查表达式");
		}
		return (boolean) result;
	}
	
	// -----------------------------------------------------------------------------------------------------------
	/**
	 * 解析表达式的结果，为integer类型
	 * @param expression
	 * @return
	 */
	public int resolveToInteger(String expression){
		Object result = resolve(expression);
		if(!(result instanceof Integer)){
			throw new IllegalArgumentException("["+expression+"]表达式解析结果为["+result+"]，非integer类型，请检查表达式");
		}
		return (int) result;
	}
	
	/**
	 * 解析表达式的结果，为integer类型
	 * @param expression
	 * @param map
	 * @return
	 */
	public int resolveToInteger(String expression, Map<String, Object> map){
		Object result = resolve(expression, map);
		if(!(result instanceof Integer)){
			throw new IllegalArgumentException("["+expression+"]表达式解析结果为["+result+"]，非integer类型，请检查表达式");
		}
		return (int) result;
	}
		
	// -----------------------------------------------------------------------------------------------------------
	/**
	 * 解析表达式的结果，为double类型
	 * @param expression
	 * @return
	 */
	public double resolveToDouble(String expression){
		Object result = resolve(expression);
		if(!(result instanceof Double)){
			throw new IllegalArgumentException("["+expression+"]表达式解析结果为["+result+"]，非double类型，请检查表达式");
		}
		return (double) result;
	}
	
	/**
	 * 解析表达式的结果，为double类型
	 * @param expression
	 * @param map
	 * @return
	 */
	public double resolveToDouble(String expression, Map<String, Object> map){
		Object result = resolve(expression, map);
		if(!(result instanceof Double)){
			throw new IllegalArgumentException("["+expression+"]表达式解析结果为["+result+"]，非double类型，请检查表达式");
		}
		return (double) result;
	}
}
