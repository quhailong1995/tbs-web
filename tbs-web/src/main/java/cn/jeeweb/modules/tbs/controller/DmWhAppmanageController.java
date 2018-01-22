package cn.jeeweb.modules.tbs.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.tbs.entity.DmWhAppmanage;
import cn.jeeweb.modules.tbs.service.IDmWhAppmanageService;

/**   
 * @Title: 系统配置-应用管理
 * @Description: 系统配置-应用管理
 * @author quhailong
 * @date 2017-10-27 10:27:29
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/tbs/dmwhappmanage")
@RequiresPathPermission("tbs:dmwhappmanage")
public class DmWhAppmanageController extends BaseCRUDController<DmWhAppmanage, String> {
	
	@Autowired
	private IDmWhAppmanageService dmWhAppmanageService;
	
	
	@Override
	public AjaxJson create(Model model, DmWhAppmanage entity,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		DmWhAppmanage old = dmWhAppmanageService.selectOne(new EntityWrapper<DmWhAppmanage>().eq("app_dm", entity.getAppDm()));
		if(old!=null){
			AjaxJson ajaxJson = new AjaxJson();
			 ajaxJson.fail("应用代码重复");
			 return ajaxJson;
		}
		
		return super.create(model, entity, result, request, response);
	}
	
	
	
	
	

	@RequestMapping("getAllApp")
	public void getAllChannel(HttpServletResponse response) throws IOException{
		response.getWriter().print(JSON.toJSONString(dmWhAppmanageService.selectList(new EntityWrapper<DmWhAppmanage>())));
	}
	

}
