package cn.jeeweb.modules.tbs.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import cn.jeeweb.core.common.controller.BaseController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.tbs.service.IUniversalQueryUtilService;


@Controller
@RequestMapping("${admin.url.prefix}/tbs/universalqueryutil")
@RequiresPathPermission("tbs:universalqueryutil")
public class UniversalQueryUtilController extends BaseController{

	@Autowired
	private IUniversalQueryUtilService universalQueryUtilService;
	
	@RequestMapping("")
	public String showPage(){
		return display("operationpage");
		
	}
	
	@RequestMapping("excuteQuery")
	public void excuteQuery(HttpServletRequest request,HttpServletResponse response){
		String head = "";
		String code = "0";
		String message = "";
		String total = "0";
		List<Map<String, Object>> mapList = null;
		String results = null;
		try{
			String sqlStatement = request.getParameter("sqlStatement");
			String dataSource = request.getParameter("dataSource");
			if(dataSource==null||dataSource.equals("null")){
				dataSource = "db_sys";
			}
			 mapList = universalQueryUtilService.queryResults(dataSource,sqlStatement);
			 
			 if(mapList!=null){
				 for(Map<String, Object> map : mapList){
						Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();  
						  
						while (entries.hasNext()) {  
						  
						    Entry<String, Object> entry = entries.next(); 
						    if(entry.getValue() instanceof oracle.sql.BLOB && entry.getValue()!=null){
						    	entry.setValue(entry.getValue().toString());
						    	//entry.setValue("<BLOB>");
						    }
							if(entry.getValue() instanceof java.util.Date && entry.getValue()!=null){
								//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
								//Date d=new Date(Long.parseLong( ));
								entry.setValue(entry.getValue().toString());  	
							  }
							if(entry.getValue() instanceof oracle.sql.DATE){
								System.out.println("**********************************************************");  	
						  }
						}  
					}
			 }
			
			 
			 
			results = JSON.toJSONString(mapList);
			if(mapList!=null){
				total = Integer.toString(mapList.size());
				for (String key : mapList.get(0).keySet()) {  
					if("ROWID".equals(key)){
						continue;
					}
					head = head+key+",";
				} 
				head = head.substring(0, head.length()-1);
			} 
		}catch(Exception e){
			e.printStackTrace();
			code = "-1";
			message = "查询失败！！！###"+e.getMessage();
		}
		

		
		Map<String, String> map = new HashMap<>();
		map.put("code",code);
		map.put("message",message);
		map.put("results",results);
		map.put("total", total);
		map.put("head", head);
		
		StringUtils.printJson(response, JSON.toJSONString(map));
	}
	
	
}
