package com.douglei.tools.clz;

/**
 * 国际化的结果实体
 * @author DougLei
 */
public abstract class I18NResult {
	
	/**
	 * 返回code, 后续可以集成国际化
	 * @return
	 */
	public abstract String getCode();
	
	/**
	 * 匹配国际化 {@link I18NResult#getCode()} 对应的message中, 声明的占位符的值数组
	 * @return
	 */
	public Object[] getParams() {
		return null;
	}
	
	/**
	 * 获取原生的消息
	 * @return
	 */
	public abstract String getOriginMessage();
}
