package cn.jeeweb.core.common.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
/**
 * Title: ICommonBaseDynamicDBService
 * Description: 
 * @author QuHaialong
 * @date  2017年12月25日 下午4:42:15
 */
public interface ICommonBaseDynamicDBService<E> {


	
	 /**
	  * 
	 * @Title: insert   
	 * @Description: (插入动作)
	 * @param @param statementName
	 * @param @param paraterObject
	 * @param @return    设定文件   
	 * @return Integer    返回类型   
	 * @throws
	  */
	public Integer insert(String dsname,String statementName,Object paraterObject)  throws DataAccessException;
	
	
	
	
	
	/**
	 * 	
	* @Title: delete   
	* @Description: (删除动作)
	* @param @param statementName
	* @param @param parameterObject
	* @param @return    设定文件   
	* @return int    返回类型   
	* @throws
	 */
	public int delete(String dsname,String statementName,Object parameterObject)  throws DataAccessException;
	
	
	
	/**
	 * 
	* @Title: update   
	* @Description: (更新动作)
	* @param @param statementName
	* @param @param parameterObject
	* @param @return    设定文件   
	* @return int    返回类型   
	* @throws
	 */
	public int update(String dsname, String statementName,Object parameterObject)  throws DataAccessException;

	


	 /**
	  * 
	 * @Title: searchObject   
	 * @Description: (查询动作)
	 * @param @param statementName
	 * @param @param parameterObject
	 * @param @return    设定文件   
	 * @return Object    返回类型   
	 * @throws
	  */
	public E searchObject(String dsname,String statementName,Object parameterObject)  throws DataAccessException;	

	 


	/**
	 * 
	* @Title: searchList   
	* @Description: (查询数据集动作)
	* @param @param statementName
	* @param @param parameterObject
	* @param @return    设定文件   
	* @return List<E>    返回类型   
	* @throws
	 */
	public List<E> searchList(String dsname,String statementName,Object parameterObject)  throws DataAccessException;




}
