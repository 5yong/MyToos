package com.tool.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tool.jdbc.JdbcUtils;

public class JdbcUtilsTest {

	@Test
	public void testGetDataSource() {
		System.out.println(JdbcUtils.getDataSource());
	}

	@Test
	public void testGetConnection() {
		System.out.println(JdbcUtils.getConnection());
	}

	@Test
	public void testOpenTransaction() {
		fail("Not yet implemented");
	}

	@Test
	public void testCommitTransaction() {
		fail("Not yet implemented");
	}

	@Test
	public void testRollbackTransaction() {
		fail("Not yet implemented");
	}

	@Test
	public void testReleaseConnection() {
		fail("Not yet implemented");
	}

}
