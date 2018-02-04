package com.tool.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * 提供操作数据库的Jdbc工具类，注意使用本类的方法，必须提供c3p0-copnfig.xml文件
 * @author Administrator
 *
 */
public class JdbcUtils {
	
	private static DataSource dataSource = new ComboPooledDataSource();//饿汉式
	


	/**
	 * 它为null表示没有事务
	 * 它不为null表示有事务
	 * 当开启事务时，需要给它赋值
	 * 当结束事务时，需要给它赋值为null
	 * 并且在开启事务时，让dao的多个方法共享这个Connection
	 */
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	

	/**
	 * dao使用本方法来获取连接
	 * 
	* 如果有事务，返回当前事务的con
	* 如果没有事务，通过连接池返回新的con
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() {
		Connection con = local.get();//获得当前线程事务中连接
		if (con !=null) {
			return con;
		}
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("获取连接失败");}
	}
	

	/**
	 * 开启事务操作（建立连接和把连接connection设置为手动提交同时把该连接加入到该线程中去
	 * @throws SQLException
	*/
	public static void openTransaction(){
		Connection con = local.get();//获取当前线程的事务连接
		if (con !=null) {
			throw new RuntimeException("已经开启了事务,不能重复开启事务");
		}
		con = getConnection();
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException("设置手动提交事务失败!");
		}
		local.set(con);
	}
	
	
	/**
	 * 提交事务操作（提交事务和关闭连接同时把connection置为null，从当前threadlocal中移除当前线程的connection;
	 * @throws SQLException
	 */
	
	public static void commitTransaction() throws SQLException{
		Connection con = local.get();//获取当前线程的事务连接
		if (con == null) {
			throw new RuntimeException("没有可以提交的事务");
		}
		con.commit();
		con.close();
		con = null;
		local.remove();
	}
	
	/**
	 * 回滚事务操作（回滚事务和关闭连接同时把connection置为null，从当前threadlocal中移除当前线程的connection;
	 * @throws SQLException
	 */
	
	public static void rollbackTransaction() throws SQLException{
		Connection con = local.get();//获取当前线程的事务连接
		if (con == null) {
			throw new RuntimeException("没有可以回滚的事务");
		}
		con.rollback();
		con.close();
		con = null;
		local.remove();
	}
	
	/**
	 * 释放连接（根据是否存在事务来决定释放connection
	 * @param connection
	 * @throws SQLException
	 */
	public static void releaseConnection(Connection connection) throws SQLException{
		Connection con = local.get();//获取当前线程的事务连接
		if (con != connection) {//如果参数连接，与当前事务连接不同，说明这个连接不是当前事务，可以关闭！
			if (!connection.isClosed()&&connection != null) {
				 connection.close();
			}
		}
	
	}
}
