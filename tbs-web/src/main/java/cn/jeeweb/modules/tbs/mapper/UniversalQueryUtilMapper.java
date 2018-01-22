package cn.jeeweb.modules.tbs.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UniversalQueryUtilMapper{
	
	List<Map<String,Object>> queryResults(@Param("sqlStatement")String sqlStatement);
	long addRecordBatch(Map<String,Object> map);

}
