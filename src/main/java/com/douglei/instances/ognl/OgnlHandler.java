package com.douglei.instances.ognl;

import ognl.MemberAccess;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * Ognl处理器
 * @author DougLei
 */
public class OgnlHandler {
	private MemberAccess memberAccess;
	private OgnlContext context;
	
	private OgnlHandler(){
		memberAccess = new DefaultMemberAccess(true);
		context = new OgnlContext(null, null, memberAccess);
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
	 * 获取boolean值
	 * @param expression
	 * @param root
	 * @return
	 */
	public boolean getBooleanValue(String expression, Object root) {
		try {
			return (boolean) Ognl.getValue(expression, context, root);
		} catch (OgnlException e) {
			e.printStackTrace();
		}
		return false;
	}
}