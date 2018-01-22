package cn.jeeweb.core.common.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.common.dao.CommonDao;



@Repository("commonDao")
public class CommonDaoImpl <E> extends SqlSessionDaoSupport  implements CommonDao <E>  {

	 @Resource  
	    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {  
	        super.setSqlSessionFactory(sqlSessionFactory);  
	    } 
	
	@Override
	public Integer insert(String statementName, Object paraterObject)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return getSqlSession().insert(statementName, paraterObject);
	}

	@Override
	public int delete(String statementName, Object parameterObject)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return  getSqlSession().delete(statementName, parameterObject);
	}

	@Override
	public int update(String statementName, Object parameterObject)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return getSqlSession().update(statementName, parameterObject);
	}

	@Override
	public Object searchObject(String statementName, Object parameterObject)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(statementName, parameterObject);
	}

	@Override
	public List<E> searchList(String statementName, Object parameterObject)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(statementName, parameterObject);
	}

	
	


}
