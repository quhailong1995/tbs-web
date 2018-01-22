package cn.jeeweb.modules.tbs.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.jeeweb.core.utils.SpringContextHolder;
import cn.jeeweb.modules.task.utils.MonitorTriggerListener;
import cn.jeeweb.modules.tbs.service.IUniversalQueryUtilService;

public class AnalysisData {
	
	public static void main(String[] args) {
		
	}
	
	
	
	private IUniversalQueryUtilService universalQueryUtilService = SpringContextHolder.getBean("universalQueryUtilService");
	
	
	public List<Map<String,Object>> personaltaxchangelist(){
		
		 long begin_id = Long.parseLong(universalQueryUtilService.queryResults("db_dm", "select nvl(max(id),0) id from LT_DJ_GSS_GX").get(0).get("ID").toString());
		
		
		String sqlStatement = "select  "
				+ "to_char(CHANGETIME,'yyyy-mm-dd hh24:mi:ss') CHANGETIME,   "
				+ "ID,  "
				+ "USERID,  "
				+ "REMARK  "
				+ "from personaltaxchangelist "
				+ "where 1=1 and id >"+begin_id
				+ "   and CHANGETIME>=date '2017-08-01' "
				+ "order by id asc";
		return universalQueryUtilService.queryResults("db_gss", sqlStatement);
	}
	
	public long analysisToLT_DJ_GSS_GX(List<Map<String,Object>> dataList){
		if(dataList==null||dataList.size()<1){
			return 0;
		}
		
		
		Map<String,Object> sqlMap = new HashMap<String, Object>();
		
		String baseSql = "INSERT INTO  LT_DJ_GSS_GX   "
				+ "(ID, ZJLX_DM, XM, ZJHM, ZJLX_DM_GX, XM_GX, ZJHM_GX, BGSJ, ETL_STAMP) ";
		sqlMap.put("baseSql", baseSql);
		
		List<String> dataSqlList = new ArrayList<String>();
		
		
		
		List<Map<String,Object>> newdataList = removeRepeatData(dataList);
		long tempCount = newdataList.size();
		
		
		for(Map<String,Object> map : newdataList){
			
			//Map<String,Object> remarkmap =  getSegStr((String) map.get("REMARK"));
			@SuppressWarnings("unchecked")
			Map<String,Object> remarkmap =  (Map<String, Object>) map.get("REMARK");
			
			if(getDataFromLT_DJ_GSS_GX(remarkmap)>0) {tempCount--;continue;}
			
			String sqlStatement =  map.get("ID")+","
									+"'"+remarkmap.get("ZJLX_DM")+"',"
									+"'"+remarkmap.get("XM")+"',"
									+"'"+remarkmap.get("ZJHM")+"',"
									+"'"+remarkmap.get("ZJLX_DM_GX")+"',"
									+"'"+remarkmap.get("XM_GX")+"',"
									+"'"+remarkmap.get("ZJHM_GX")+"',"
									+"sysdate"+","
									+null;
			dataSqlList.add(sqlStatement);
		}
		
		sqlMap.put("dataSqlList", dataSqlList);
		
		
		if(tempCount<1){
			return 0;
		}
		
		return universalQueryUtilService.addRecordBatch("db_dm", sqlMap);

	}
	
	//排重
	public long getDataFromLT_DJ_GSS_GX(Map<String,Object> remarkmap){
		
		
		StringBuffer sqlStatement = new StringBuffer("select count(*) COUNT from lt_dj_gss_gx t where  1=1 ");
		Set<String> keySet = remarkmap.keySet();  
		for(String key : keySet) {  
        //for(Iterator<String> it = keySet.iterator();it.hasNext();) {  
           // String key = it.next();  
            //通过便利本Set集合的过程中就可以获取map集合中key的value  
            if(remarkmap.get(key).equals("")){
            	key = "and "+key + " is null   ";
		    	
		    }else{
		    	key = "and "+key + "='"+remarkmap.get(key)+"'    ";
		    }
            sqlStatement.append(key);
        }  
/*
		Iterator<Map.Entry<String, Object>> entries = remarkmap.entrySet().iterator();  
		  
		while (entries.hasNext()) {  
		  
		    Entry<String, Object> entry = entries.next(); 
		    if(entry.getValue().equals("")){
		    	entry.setValue("is null");
		    	
		    }else{
		    	entry.setValue("='"+entry.getValue()+"'");
		    }
		}*/
		/*String sqlStatement = "select count(*) COUNT from lt_dj_gss_gx t where  1=1 "
				+ "t.ZJLX_DM "+remarkmap.get("ZJLX_DM")+" and "
				+ "t.XM "+remarkmap.get("XM")+" and "
				+ "t.ZJHM "+remarkmap.get("ZJHM")+" and "
				+ "t.ZJLX_DM_GX "+remarkmap.get("ZJLX_DM_GX")+" and "
				+ "t.XM_GX "+remarkmap.get("XM_GX")+" and "
				+ "t.ZJHM_GX "+remarkmap.get("ZJHM_GX");*/
        
     
        
        
		return Long.parseLong(universalQueryUtilService.queryResults("db_dm", sqlStatement.toString()).get(0).get("COUNT").toString());
		
	}
	
	public List<Map<String,Object>> removeRepeatData(List<Map<String,Object>> olddataList){
		List<Map<String,Object>> newdataList = new ArrayList<Map<String,Object>>();
		
		for(Map<String,Object> map : olddataList){
			Map<String,Object> remarkmap =  getSegStr((String) map.get("REMARK"));
			Map<String,Object> newmap = new HashMap<String, Object>();
			
			if(newdataList.size()<1){
				newmap.put("ID", map.get("ID"));
				newmap.put("REMARK", remarkmap);
				newdataList.add(newmap);
				continue;
			}
			int flag = 0;
			for(Map<String,Object> map2 : newdataList){
				if(map2.containsValue(remarkmap)){
					flag = 1;
				}
			}
			
			if(flag==0) {
				newmap.put("ID", map.get("ID"));
				newmap.put("REMARK", remarkmap);
				newdataList.add(newmap);
			}
			
			
			
			
		}
		
		
		
		return newdataList;
	}
	
	
	
	
	
	
	
	//PERSONALTAXLISTID: 100000003427675944证照类型: '其他' -> '身份证'证照号码: '350203197810300024' -> '350203197810300024'姓名: '' -> '黄巧彬'
	
	
	public Map<String,Object> getSegStr(String remark){
		Map<String,Object> map = new HashMap<String, Object>();
		
		int indexZzlx = remark.indexOf("证照类型：", 0);
		int indexZjhm = remark.indexOf("证照号码：", 0);
		int indexXm = remark.indexOf("姓名：", 0);
		
		String zzlxStr = remark.substring(indexZzlx!=-1?indexZzlx:remark.indexOf("证照类型:", 0)+5
				,indexZjhm!=-1?indexZjhm:remark.indexOf("证照号码:", 0));
		String [] zzlxarray = zzlxStr.split("->");
		if(zzlxarray.length>1){
			zzlxarray =strHandle(zzlxarray);
			for(int i=0;i<=1;i++){
				if(zzlxarray[i].contains("身份证")){
					zzlxarray[i] = "1";
				}else if(zzlxarray[i].contains("护照")){
					zzlxarray[i] = "2";
				}else if(zzlxarray[i].contains("军官证")){
					zzlxarray[i] = "3";
				}else if(zzlxarray[i].contains("回乡证")){
					zzlxarray[i] = "3";
				}else{
					zzlxarray[i] = "0";
				}
			}
			map.put("ZJLX_DM", zzlxarray[0]);
			map.put("ZJLX_DM_GX", zzlxarray[1]);
		}


		String zjhmStr = remark.substring(indexZjhm!=-1?indexZjhm:remark.indexOf("证照号码:", 0)+5
				,indexXm!=-1?indexXm:remark.indexOf("姓名:", 0));
		String[] zjhmarray = zjhmStr.split("->");
		if(zjhmarray.length>1){
			zjhmarray =strHandle(zjhmarray);
			map.put("ZJHM", zjhmarray[0]);
			map.put("ZJHM_GX", zjhmarray[1]);
		}
		
		
		String xmStr = remark.substring(indexXm!=-1?indexXm:remark.indexOf("姓名:", 0)+5,remark.length()-1);
		String[] xmarray = xmStr.split("->");
		if(xmarray.length>1){
			xmarray =strHandle(xmarray);
			map.put("XM", xmarray[0]);
			map.put("XM_GX", xmarray[1]);
		}

		return map;
		
	}
	
	public String[] strHandle(String[] ss){
		ss[0] =ss[0].replaceAll("'", "").replaceAll(" ", "");
		if(ss[0]==null){
			ss[0] = "";
		}
		ss[1] = ss[1].replaceAll("'", "").replaceAll(" ", "");
		if(ss[1]==null){
			ss[1] = "";
		}
		
		return new String[]{ss[0],ss[1]};
	}
	
	
	
	
	
	
	
	
	
	
	public void executeTask(){
		
		String resultCode  = "2";
		String resultMessage = "执行成功！#########";
		
		try{
			AnalysisData analysisData = new AnalysisData();
			
			List<Map<String,Object>> personaltaxchangelist = analysisData.personaltaxchangelist();
			
			long count = analysisData.analysisToLT_DJ_GSS_GX(personaltaxchangelist);
			
			resultMessage = resultMessage + "插入" + count + "条数据";
			
		}catch(Exception e){
			e.printStackTrace();
			resultCode = "3";
			resultMessage = "执行失败！#######"+e.getMessage();
		}
		
		
		MonitorTriggerListener.returnResultMessage(resultCode, resultMessage);
	}
	

	
	

}
