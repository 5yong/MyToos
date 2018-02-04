package com.tool.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.tool.commons.CommonUtils;

public class CommonUtilsTest {

	@Test
	public void testGetUuid() {
		System.out.println(CommonUtils.getUuid());
	}

	@Test
	public void testToBean() {
		Map map = new HashMap();
		map.put("name", "hu");
		map.put("age", "23");
		map.put("birthDate", "2016-06-05 11:11:11");
		System.out.println(CommonUtils.toBean(map, Person.class));
	}

}
