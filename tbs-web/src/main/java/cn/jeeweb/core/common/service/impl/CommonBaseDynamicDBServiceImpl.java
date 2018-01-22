package cn.jeeweb.core.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.common.dao.CommonDao;
import cn.jeeweb.core.common.service.ICommonBaseDynamicDBService;
import cn.jeeweb.modules.tbs.helper.DynamicDataSource;
import cn.jeeweb.modules.tbs.helper.StaticConfig;

/**
 * Title: CommonBaseDynamicDBServiceImpl
 * Description: 
 * @author QuHaialong
 * @param <E>
 * @date  2017年12月25日 下午3:21:44
 */

@Transactional(propagation = Propagation.SUPPORTS)
@Service("commonBaseDynamicDBService")
public class CommonBaseDynamicDBServiceImpl<E> implements ICommonBaseDynamicDBService<E> {

	@SuppressWarnings("rawtypes")
	@Resource
	private CommonDao commonDao;

	@Override
	public Integer insert(String dsname, String statementName,
			Object paraterObject) throws DataAccessException {
		// TODO Auto-generated method stub
		int i = 0;
		try{
			DynamicDataSource.setDataSource(dsname);
			i = commonDao.insert(statementName, paraterObject);
		}catch(Exception e){
			throw e;
		}finally{
			DynamicDataSource.setDataSource(StaticConfig.DEFAULTDATASOURCE);
		}
		
		return i;
	}

	@Override
	public int delete(String dsname, String statementName,
			Object parameterObject) throws DataAccessException {
		// TODO Auto-generated method stub
		int i = 0;
		try{
			DynamicDataSource.setDataSource(dsname);
			i = commonDao.delete(statementName, parameterObject);
		}catch(Exception e){
			throw e;
		}finally{
			DynamicDataSource.setDataSource(StaticConfig.DEFAULTDATASOURCE);
		}
		return i;
	}

	@Override
	public int update(String dsname, String statementName,
			Object parameterObject) throws DataAccessException {
		// TODO Auto-generated method stub
		int i = 0;
		try{
			DynamicDataSource.setDataSource(dsname);
			i = commonDao.update(statementName, parameterObject);
		}catch(Exception e){
			throw e;
		}finally{
			DynamicDataSource.setDataSource(StaticConfig.DEFAULTDATASOURCE);
		}
		return i;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E searchObject(String dsname, String statementName,
			Object parameterObject) throws DataAccessException {
		// TODO Auto-generated method stub
		E object = null ;
		try{
			DynamicDataSource.setDataSource(dsname);
			object = (E) commonDao.searchObject(statementName, parameterObject);
		}catch(Exception e){
			throw e;
		}finally{
			DynamicDataSource.setDataSource(StaticConfig.DEFAULTDATASOURCE);
		}
		return object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> searchList(String dsname, String statementName,
			Object parameterObject) throws DataAccessException {
		// TODO Auto-generated method stub
		List<E> olist = null ;
		try{
			DynamicDataSource.setDataSource(dsname);
			olist = commonDao.searchList(statementName, parameterObject);
		}catch(Exception e){
			throw e;
		}finally{
			DynamicDataSource.setDataSource(StaticConfig.DEFAULTDATASOURCE);
		}
		return olist;
	}

	
	
	
}
