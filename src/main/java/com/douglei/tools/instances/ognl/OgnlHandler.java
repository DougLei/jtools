package com.douglei.tools.instances.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * Ognl处理器
 * @author DougLei
 */
public class OgnlHandler {
	private OgnlContext context;
	
	private OgnlHandler(){
		context = new OgnlContext(null, null, new DefaultMemberAccess(true));
	}

	/**
	 * 获取单例实例
	 * @return
	 */
	public static final OgnlHandler singleInstance(){
		if(singleInstance == null){
			singleInstance = new OgnlHandler();
		}
		return singleInstance;
	}
	private static OgnlHandler singleInstance;
	
	/**
	 * 创建实例
	 * @return
	 */
	public static final OgnlHandler newInstance(){
		return new OgnlHandler();
	}
	
	/**
	 * 获取OgnlContext
	 * @return ognl.OgnlContext
	 */
	public OgnlContext getOgnlContext() {
		return context;
	}
	
	/**
	 * 判断root参数是否为空
	 * @param expression
	 * @param root
	 */
	private void rootIsNull(String expression, Object root) {
		if(root == null) {
			throw new NullPointerException("获取表达式["+expression+"]对应的值时, 传入的对象为null");
		}
	}
	
	/**
	 * 获取boolean值
	 * @param expression
	 * @param root
	 * @return
	 */
	public boolean getBooleanValue(String expression, Object root) {
		rootIsNull(expression, root);
		try {
			return (boolean) Ognl.getValue(expression, context, root);
		} catch (OgnlException e) {
			throw new RuntimeException("getBooleanValue时出现异常: ", e);
		}
	}
	
	/**
	 * 获取Object值
	 * @param expression
	 * @param root
	 * @return
	 */
	public Object getObjectValue(String expression, Object root) {
		rootIsNull(expression, root);
		try {
			return Ognl.getValue(expression, context, root);
		} catch (OgnlException e) {
			throw new RuntimeException("getObjectValue时出现异常: ", e);
		}
	}
}