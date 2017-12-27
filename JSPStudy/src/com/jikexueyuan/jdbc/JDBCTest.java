package com.jikexueyuan.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class JDBCTest {

	public static void main(String[] args) {
		String sql="SELECT * FROM tab_user";
		Connection conn=null;
		Statement st=null;//���������ݿⷢ������
		ResultSet rs=null;//��װ�˴����ݿ��в�ѯ��������
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//ע��jdbc���������� class��jvm�����Ϣ
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_db","root","root");
			//����: ���ݿ��URL  �û���  ����
			st=conn.createStatement();
			rs=st.executeQuery(sql);//����sql���  ������ResultSet����
			
			while(rs.next()) {//����ResultSet
				System.out.print(rs.getInt("id")+"  ");//��ȡ���ݿ���Ϊ���͵��ֶ�ֵ
				System.out.print(rs.getString("name")+" ");
				System.out.print(rs.getString("password")+" ");
				System.out.print(rs.getString("email")+" ");
				System.out.println();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("�������ݿ�ʧ��");
		}finally {//ִ����Դ������ ������С�����˳��ִ��
			try {
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("2��");
			}
			try {
				st.close();
			} catch (Exception e3) {
				// TODO: handle exception
				System.out.println("3��");
			}
			try {//�ر����ݿ�����
				conn.close();
			} catch (Exception e4) {
				// TODO: handle exception
				System.out.println("4��");
			}
		}
	}
}
