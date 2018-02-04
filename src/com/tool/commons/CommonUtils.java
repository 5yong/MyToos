package com.tool.commons;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

/**
 * 小工具：
 * 1.得到一个不重复的字串值
 * 2.把map封装成一个实体类对象
 * @author Administrator
 *
 *
 *
 */
public class CommonUtils {
/**
 * 返回一个不重复的字符串
 * @return
 */
	public static String getUuid(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	
	/**
	 * 把mpa转换成一个bean对象把Map转换成指定类型
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T> T toBean(@SuppressWarnings("rawtypes") Map map,Class<T> clazz){
		T bean = null;
		try {
			bean = clazz.newInstance();
			ConvertUtils.register(new DateConverter(),java.util.Date.class);
			BeanUtils.populate(bean, map);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}
}
