package org.service.referrer.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


/** 
 * 
 * This class is MyBatis Connection Factory, which reads the configuration data from a XML file. 
 * @author Prasanna Kumar
 * @version 0.0.1
 */

public class MyBatisConnectionFactory {

	private static SqlSessionFactory sqlSessionFactory;

	static {

		try {

			String resource = "/config/SqlMapConfig.xml";
			

			if (sqlSessionFactory == null) {
					Reader reader = Resources.getResourceAsReader(resource);
					sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			}
		}

		catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}
		catch (IOException iOException) {
			iOException.printStackTrace();
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {

		return sqlSessionFactory;
	}

}
