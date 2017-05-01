package org.service.referrer.dao;




import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.service.referrer.model.*;

public class ReferrerDAO {
	

	
	private SqlSessionFactory sqlSessionFactory; 
	
	public ReferrerDAO(){
		sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
	}
	

	/**
	 * Returns the list of all Contact instances from the database.
	 * @return the list of all Contact instances from the database.
	 */
	
	public ReferrerURL insertDataURL(ReferrerURL referrer){

		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			session.insert("ReferrerURL.insert",referrer) ;
			session.commit();
		} catch(PersistenceException ex){
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return referrer;
	}
	
	/**
	 * Returns the list of all Contact instances from the database.
	 * @return the list of all Contact instances from the database.
	 */
	
	public ReferrerURL populateUrl(ReferrerURL referrer){

		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			referrer = (ReferrerURL) session.selectOne("ReferrerURL.select",referrer);
		} catch(PersistenceException ex){
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return referrer;
	}

	
}
