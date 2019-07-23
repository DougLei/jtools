package com.douglei.tools.utils.naming.converter.impl;

import com.douglei.tools.utils.StringUtil;
import com.douglei.tools.utils.naming.converter.Converter;

/**
 * 将列名转换为属性名的转换器
 * USER_NAME => userName
 * @author DougLei
 */
public class ColumnName2PropertyNameConverter implements Converter {
	
	@Override
	public String doConvert(String columnName) {
		StringBuilder sb = new StringBuilder(columnName.length());
		String[] columnInfo = StringUtil.trim_(columnName, '_');
		if(columnInfo[0] != null) {
			sb.append(columnInfo[0]);
		}
		
		columnName = columnInfo[1].toLowerCase();
		String[] words = columnName.split("_");
		sb.append(words[0]);
		
		String firstWord = null;
		int len = words.length;
		if(len > 1){
			for(int i = 1; i<len; i++){
				if(words[i].equals("")){// 证明是_
					sb.append("_");
				}else {
					firstWord = words[i].substring(0,1);
					sb.append(words[i].replaceFirst(firstWord, firstWord.toUpperCase()));
				}
			}
		}
		
		if(columnInfo[2] != null) {
			sb.append(columnInfo[2]);
		}
		return sb.toString();
	}
}
