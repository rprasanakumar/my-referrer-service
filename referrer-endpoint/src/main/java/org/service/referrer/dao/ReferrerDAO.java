package org.service.referrer.dao;




import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.service.referrer.model.*;

/**
 * 
 * @author Prasanna Kumar
 * @version 0.0.1
 * @since 04/19/2017
 * This class is a Data access object layer, which interacts with the Data Base and manages different 
 * actions 
 *
 */


public class ReferrerDAO {
	

	
	private SqlSessionFactory sqlSessionFactory; 
	
	public ReferrerDAO(){
		sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
	}
	

	/**
	 * @inserts domainurl, id with hitcount.
	 * @return ReferrerURL object.
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
