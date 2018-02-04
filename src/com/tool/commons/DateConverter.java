package com.tool.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;

/**
 * 数据类型转换
 * @author Administrator
 *
 */
public class DateConverter implements Converter {

 /**
  * 把字串串string类型转换为指定的数据date数据类型值
  */
	public Object convert(@SuppressWarnings("rawtypes") Class clazz, Object value) {
		if (value == null) {
			return null;
		}
		if (!(value instanceof String)) {
			return value;
		}
		
		String val = value.toString();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
		return	format.parse(val);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
}
