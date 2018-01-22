package cn.jeeweb.modules.tbs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.modules.tbs.helper.DynamicDataSource;
import cn.jeeweb.modules.tbs.mapper.UniversalQueryUtilMapper;
import cn.jeeweb.modules.tbs.service.IUniversalQueryUtilService;


@Transactional(propagation = Propagation.SUPPORTS)
@Service("universalQueryUtilService")
public class UniversalQueryUtilServiceImpl  implements IUniversalQueryUtilService{

	@Autowired
	private UniversalQueryUtilMapper universalQueryUtilMapper;
	
	@Override
	public List<Map<String, Object>> queryResults(String datasource,String sqlStatement) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = null;
		try{
			DynamicDataSource.setDataSource(datasource);
			list = universalQueryUtilMapper.queryResults(sqlStatement);
		}catch(Exception e){
			throw e;
		}finally{
			DynamicDataSource.setDataSource("db_sys");
		}
		
		
		return list;
		
	}

	@Override
	public long addRecordBatch(String datasource,Map<String, Object> map) {
		// TODO Auto-generated method stub
		long i = 0;
		try{
			DynamicDataSource.setDataSource(datasource);
			i = universalQueryUtilMapper.addRecordBatch(map);
		}catch(Exception e){
			throw e;
		}finally{
			DynamicDataSource.setDataSource("db_sys");
		}
		return i;
	}
	
	
	
}
