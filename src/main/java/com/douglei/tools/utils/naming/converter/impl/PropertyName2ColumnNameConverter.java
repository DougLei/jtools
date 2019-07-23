package com.douglei.tools.utils.naming.converter.impl;

import com.douglei.tools.utils.StringUtil;
import com.douglei.tools.utils.naming.converter.Converter;

/**
 * 将属性名转换为列名的转换器
 * userName => user_name
 * @author DougLei
 */
public class PropertyName2ColumnNameConverter implements Converter {

	@Override
	public String doConvert(String propertyName) {
		StringBuilder cn = new StringBuilder(propertyName.length() + 5);
		String[] pns = StringUtil.trim_(propertyName, '_');
		if(pns[0] != null) {
			cn.append(pns[0]);
		}
		
		propertyName = pns[1];
		cn.append(propertyName.charAt(0));
		char tmp;
		for(byte i=1;i<propertyName.length();i++) {
			tmp = propertyName.charAt(i);
			if(tmp > 64 && tmp < 91) {
				cn.append("_");
			}
			cn.append(tmp);
		}
		
		if(pns[2] != null) {
			cn.append(pns[2]);
		}
		return cn.toString().toLowerCase();
	}
}
