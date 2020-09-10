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

	private static OgnlHandler singleton = new OgnlHandler();
	public static final OgnlHandler getSingleton(){
		return singleton;
	}
	
	/**
	 * 创建新的实例
	 * @return
	 */
	public static OgnlHandler newInstance(){
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
	 * 获取boolean值
	 * @param expression
	 * @return
	 */
	public boolean getBooleanValue(String expression) {
		return getBooleanValue(expression, null);
	}
	
	/**
	 * 获取boolean值
	 * @param expression
	 * @param root
	 * @return
	 */
	public boolean getBooleanValue(String expression, Object root) {
		try {
			return (boolean) Ognl.getValue(expression, context, root);
		} catch (OgnlException e) {
			throw new OgnlHandlerException("getBooleanValue时出现异常: ", e);
		}
	}
	
	/**
	 * 获取Object值
	 * @param expression
	 * @return
	 */
	public Object getObjectValue(String expression) {
		return getObjectValue(expression, null);
	}
	
	/**
	 * 获取Object值
	 * @param expression
	 * @param root
	 * @return
	 */
	public Object getObjectValue(String expression, Object root) {
		try {
			return Ognl.getValue(expression, context, root);
		} catch (OgnlException e) {
			throw new OgnlHandlerException("getObjectValue时出现异常: ", e);
		}
	}
}