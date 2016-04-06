package com.paly.test;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * SqlSessionFactory 单例
 */
public class MyBatisDAOUtil {

	private static SqlSessionFactory factory;

	private MyBatisDAOUtil() {
	}

	static {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		factory = new SqlSessionFactoryBuilder().build(reader);
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return factory;
	}
}
