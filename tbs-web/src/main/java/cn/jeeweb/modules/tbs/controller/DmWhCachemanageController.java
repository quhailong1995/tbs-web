package cn.jeeweb.modules.tbs.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.PropertyPreFilterable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.utils.QueryableConvertUtils;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.utils.JeewebPropertiesUtil;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.tbs.entity.DmWhAppmanage;
import cn.jeeweb.modules.tbs.entity.DmWhCachemanage;
import cn.jeeweb.modules.tbs.entity.DmWhServermanage;
import cn.jeeweb.modules.tbs.entity.DmWhXtcs;
import cn.jeeweb.modules.tbs.helper.AccessOtherServerUntil;
import cn.jeeweb.modules.tbs.service.IDmWhAppmanageService;
import cn.jeeweb.modules.tbs.service.IDmWhCachemanageService;
import cn.jeeweb.modules.tbs.service.IDmWhServermanageService;
import cn.jeeweb.modules.tbs.service.IDmWhXtcsService;

/**   
 * @Title: 系统配置-缓存管理
 * @Description: 系统配置-缓存管理
 * @author quhailong
 * @date 2017-10-27 10:42:39
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/tbs/dmwhcachemanage")
@RequiresPathPermission("tbs:dmwhcachemanage")
public class DmWhCachemanageController extends BaseCRUDController<DmWhCachemanage, String> {
	@Autowired
	private IDmWhCachemanageService dmWhCachemanageService;
	@Autowired
	private IDmWhServermanageService dmWhServermanageService;
	@Autowired
	private IDmWhAppmanageService dmWhAppmanageService;
	
	
	private String cacheData;
	
	
	@Override
	public AjaxJson create(Model model, DmWhCachemanage entity,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
				DmWhCachemanage old = 	dmWhCachemanageService.selectOne(new EntityWrapper<DmWhCachemanage>().eq("cache_dm", entity.getCacheDm()));
				if(old!=null){
					AjaxJson ajaxJson = new AjaxJson();
					ajaxJson.fail("代码重复了");
					return ajaxJson;
				}
		return super.create(model, entity, result, request, response);
	}

	
	
	@RequestMapping(value="{cacheId}/showCacheOperationPage", method = RequestMethod.GET)
	public String showCacheOperationPage(@PathVariable("cacheId")String cacheId , Model model){
		DmWhCachemanage dmWhCachemanage = dmWhCachemanageService.selectById(cacheId);
	//	List<DmWhServermanage>  serverList = dmWhServermanageService.selectList(new EntityWrapper<DmWhServermanage>());
		model.addAttribute("dmWhCachemanage", dmWhCachemanage);
		//model.addAttribute("serverList", serverList);
		return display("operation");
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "{cacheId}/refreshCache", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson refreshCache(@PathVariable("cacheId")String cacheId,@RequestParam(value = "ids", required = false) String[] ids) {
		AjaxJson ajaxJson = new AjaxJson();
		
		String soapRequestData = null;
		String respData = "";
		
		DmWhCachemanage dmWhCachemanage = dmWhCachemanageService.selectById(cacheId);
		
		List<String> serverIdList = java.util.Arrays.asList(ids);
		try {
			for(String id:serverIdList){
				DmWhServermanage dmWhServermanage = dmWhServermanageService.selectById(id);
				
				String requestUrl = null;
				requestUrl = "http://"+dmWhServermanage.getServerIp()+":"+dmWhServermanage.getServerPort()+"/"+dmWhServermanage.getAppDm()+"/CacheWebService";
	
				
					soapRequestData = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:com='http://com.eqiao.tbs.sver.ws/'>"
							+"<soapenv:Header/><soapenv:Body><com:refreshCache><arg0>" + dmWhCachemanage.getCacheDm() + "</arg0></com:refreshCache></soapenv:Body></soapenv:Envelope>";
					
					
					String resultData = AccessOtherServerUntil.accressWebService(soapRequestData, requestUrl);
					Map<String, String> map = (Map<String, String>) (JSON.parse(resultData));
					
					if(map.get("code").equals("0")){
						resultData = map.get("msg");
						respData = respData +"服务器"+dmWhServermanage.getServerMc()+"响应：  "+resultData+"\n";
						
					}
					if(map.get("code").equals("-1")){
						resultData = map.get("msg");
						respData =respData + "服务器"+dmWhServermanage.getServerMc()+"响应：  "+resultData+"\n";
					}
				
				
			}
			
			ajaxJson.success(respData);
			return ajaxJson;
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("操作失败"+e.getMessage());
			return ajaxJson;
		}
		

		
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "{id}/viewCache", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void viewCache(@PathVariable("id")String id ,@RequestParam(value = "ids", required = false) String ids ,HttpServletResponse response) throws IOException{
		
		String resultData = "";
		String soapRequestData = null;
	//	DmWhXtcs dmWhXtcs = dmWhXtcsService.selectOne(new EntityWrapper<DmWhXtcs>().eq("xtcs_dm", "TBS.WS.Cache"));
		DmWhCachemanage dmWhCachemanage = dmWhCachemanageService.selectById(id);
		
		try {
			DmWhServermanage dmWhServermanage = dmWhServermanageService.selectById(ids);
			String requestUrl = null;
			requestUrl = "http://"+dmWhServermanage.getServerIp()+":"+dmWhServermanage.getServerPort()+"/"+dmWhServermanage.getAppDm()+"/CacheWebService";
			soapRequestData = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:com='http://com.eqiao.tbs.sver.ws/'>"
					+"<soapenv:Header/><soapenv:Body><com:viewCache><arg0>" + dmWhCachemanage.getCacheDm() + "</arg0></com:viewCache></soapenv:Body></soapenv:Envelope>";
			
			resultData = AccessOtherServerUntil.accressWebService(soapRequestData, requestUrl).replaceAll("，", ",");
			
			
			
			Map<String, String> map = (Map<String, String>) (JSON.parse(resultData));
			
			if(map.get("code").equals("0")){
				
				cacheData = JSON.toJSONString(map.get("data"));
				System.out.println(cacheData);
			}
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultData ="{code:\"-1\",msg:\""+e.getMessage()+"\"}";
			
		}
		
		response.getWriter().print(resultData);
	}
	
	
	@RequestMapping(value="showCacheContentPage", method ={ RequestMethod.GET,RequestMethod.POST})
	public String showCacheContentPage(){

		return display("showCacheContent");
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "showCacheContent", method = { RequestMethod.GET, RequestMethod.POST })
	public void showCacheContent(HttpServletResponse response,Model model) throws IOException {
		String head = "";
		List<Map<String, String>> mapList =(List<Map<String, String>>) JSON.parse(cacheData);
		for (String key : mapList.get(0).keySet()) {  
			  
			head = head+key+",";
		  
		}  

		
		Map<String, String> map = new HashMap<>();
		map.put("results",cacheData);
		map.put("total", Integer.toString(mapList.size()));
		map.put("head", head.substring(0, head.length()-1));
		
		StringUtils.printJson(response, JSON.toJSONString(map));
	}
	
	

}
