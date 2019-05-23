package com.douglei.utils.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * Ognl处理器
 * @author DougLei
 */
public class OgnlHandler {
	private static final OgnlContext context = new OgnlContext(null, null, new DefaultMemberAccess(true));
	
	/**
	 * 获取boolean值
	 * @param expression
	 * @param root
	 * @return
	 */
	public static boolean getBooleanValue(String expression, Object root) {
		try {
			return (boolean) Ognl.getValue(expression, context, root);
		} catch (OgnlException e) {
			e.printStackTrace();
		}
		return false;
	}
}