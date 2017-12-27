package com.jikexueyuan.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class JDBCTest {

	public static void main(String[] args) {
		String sql="SELECT * FROM tab_user";
		Connection conn=null;
		Statement st=null;//用于向数据库发送数据
		ResultSet rs=null;//封装了从数据库中查询到的数据
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//注册jdbc的驱动程序 class类jvm类的信息
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_db","root","root");
			//参数: 数据库的URL  用户名  密码
			st=conn.createStatement();
			rs=st.executeQuery(sql);//放送sql语句  ，返回ResultSet对象
			
			while(rs.next()) {//遍历ResultSet
				System.out.print(rs.getInt("id")+"  ");//获取数据库中为整型的字段值
				System.out.print(rs.getString("name")+" ");
				System.out.print(rs.getString("password")+" ");
				System.out.print(rs.getString("email")+" ");
				System.out.println();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("连接数据库失败");
		}finally {//执行资源的清理 按照由小到大的顺序执行
			try {
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("2处");
			}
			try {
				st.close();
			} catch (Exception e3) {
				// TODO: handle exception
				System.out.println("3处");
			}
			try {//关闭数据库连接
				conn.close();
			} catch (Exception e4) {
				// TODO: handle exception
				System.out.println("4处");
			}
		}
	}
}
