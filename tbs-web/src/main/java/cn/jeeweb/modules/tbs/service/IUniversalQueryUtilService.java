package cn.jeeweb.modules.tbs.service;

import java.util.List;
import java.util.Map;


public interface IUniversalQueryUtilService{
	List<Map<String,Object>> queryResults(String datasource,String sqlStatement);
	long addRecordBatch(String datasource,Map<String,Object> map);
}
