package cn.jeeweb.modules.tbs.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.tbs.entity.TbUser;
import cn.jeeweb.modules.tbs.service.IUniversalQueryUtilService;


/**   
 * @Title: TB_USER
 * @Description: TB_USER
 * @author quhailong
 * @date 2017-12-11 17:25:49
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/tbs/tbuser")
@RequiresPathPermission("tbs:tbuser")
public class TbUserController extends BaseCRUDController<TbUser,String> {
	@Autowired
	IUniversalQueryUtilService universalQueryUtilService ;
	
	@RequestMapping("hjlspage")
	public String hjlspage(HttpServletRequest req,Model model) throws IOException{
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		List<Map<String, Object>> l =  universalQueryUtilService.queryResults("db_sys", "select id,hjid,zjhm,xm from tb_user where id='"+id+"'");
//		String zjhm = req.getParameter("zjhm");
//		String xm = req.getParameter("xm");
		model.addAttribute("tbUser", l.get(0));
		//model.addAttribute("xm", xm);
		return display("hjlspage");
		
	}
	
	@RequestMapping("gettbuser")
	public void gettbuser(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String id = req.getParameter("id");
		List<Map<String, Object>> l =  universalQueryUtilService.queryResults("db_sys", "select * from tb_user where id='"+id+"'");
		formatObjectToString(l);
		StringUtils.printJson(resp, JSON.toJSONString(l));
		
	}
	
	@RequestMapping("gethjuser")
	public void gethjuser(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String hjid = req.getParameter("hjid");
		String sql  = "select * from hjxx_ls where hjid in("
				+ "select a.y_hjid "
				+ "from hjxx_bd a,hjxx b "
				+ "where a.y_hjid = b.hjid  "
				+ "and a.hjbdid = (select hjbdid from hjxx_bd where y_hjid = '"+hjid+"'))"
						+ " order by etl_stamp desc";
		List<Map<String, Object>> l =  universalQueryUtilService.queryResults("db_dm", sql);
		
		formatObjectToString(l);

		StringUtils.printJson(resp, JSON.toJSONString(l));
		
	}
	
	@RequestMapping("getbduser")
	public void getbduser(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String hjid = req.getParameter("hjid");
		String sql = "select a.hjbdid,a.y_hjid,b.zjlx_dm,"
				+"b.zjhm,b.xm,b.sjhm,a.bdsj,b.etl_stamp "
				+"from hjxx_bd a,hjxx b "
				+"where a.y_hjid=b.hjid "
				+"and a.hjbdid =(select hjbdid from hjxx_bd where y_hjid='"+hjid+"' ) order by a.bdsj desc";
		
		List<Map<String, Object>> l =  universalQueryUtilService.queryResults("db_dm", sql);
		formatObjectToString(l);
		StringUtils.printJson(resp, JSON.toJSONString(l));
		
	}
	
	
	public void formatObjectToString(List<Map<String, Object>> mapList){
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
							String timeString[] = entry.getValue().toString().split(" ");
							if(!timeString[1].equals("00:00:00.0")){
								entry.setValue( entry.getValue().toString().substring(0,entry.getValue().toString().length()-2 ));  
							}else{
								entry.setValue(timeString[0]);  
							}
								
						  }
						if(entry.getValue() instanceof oracle.sql.DATE){
							System.out.println("**********************************************************");  	
					  }
					}  
				}
		 }
	}
	

}
