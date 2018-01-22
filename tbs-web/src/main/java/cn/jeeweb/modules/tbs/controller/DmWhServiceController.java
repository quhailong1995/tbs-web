package cn.jeeweb.modules.tbs.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Wrapper;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.model.AjaxJson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import cn.jeeweb.modules.tbs.entity.DmWhService;
import cn.jeeweb.modules.tbs.entity.DmWhSerContent;
import cn.jeeweb.modules.tbs.entity.DmWhXtcs;
import cn.jeeweb.modules.tbs.entity.TbAuthService;
import cn.jeeweb.modules.tbs.helper.AccessOtherServerUntil;
import cn.jeeweb.modules.tbs.helper.XmlHelper;
import cn.jeeweb.modules.tbs.service.IDmWhSerContentService;
import cn.jeeweb.modules.tbs.service.IDmWhServiceService;
import cn.jeeweb.modules.tbs.service.IDmWhXtcsService;
import cn.jeeweb.modules.tbs.service.ITbAuthServiceService;

/**   
 * @Title: 系统服务管理
 * @Description: 系统服务管理
 * @author QuHaiLong
 * @date 2017-08-13 13:55:29
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/tbs/dmwhservice")
@RequiresPathPermission("tbs:dmwhservice")
public class DmWhServiceController extends BaseCRUDController<DmWhService, String> {
	@Autowired
	private IDmWhSerContentService dmWhSerContentService;
	@Autowired
	private ITbAuthServiceService tbAuthServiceService;
	@Autowired
	private IDmWhServiceService dmWhServiceService;
	@Autowired
	private IDmWhXtcsService dmWhXtcsService;


	@Override
	public void preAjaxList(Queryable queryable,
			EntityWrapper<DmWhService> entityWrapper,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		if(queryable.getPageable().getSort().toString().equals("id: ASC")){
			entityWrapper.orderBy("service_name", true);
		}
		super.preAjaxList(queryable, entityWrapper, request, response);
	}
	@Override
	public void preEdit(final DmWhService dmWhService, Model model, HttpServletRequest request, HttpServletResponse response) {
		// 获得数据
		// List<DmWhSerContent> dmWhSerContentList = dmWhSerContentService.selectList(new EntityWrapper<DmWhSerContent>(DmWhSerContent.class).eq("serviceId.id",dmWhService.getId()));
		if(dmWhService.getServiceType()=="2"){
			return;
		}
		List<DmWhSerContent> dmWhSerContentList = dmWhSerContentService.selectList(new Wrapper<DmWhSerContent>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getSqlSegment() {
				// TODO Auto-generated method stub
				return "sc where sc.service_id = '"+dmWhService.getId()+"' order by sc.sort ASC";
			}
		});
		model.addAttribute("dmWhSerContentList", dmWhSerContentList);
		
	}
	
	
	
	@Override
	public AjaxJson batchDelete(String[] ids) {
		// TODO Auto-generated method stub
		AjaxJson ajaxJson = new AjaxJson();
		
		for(final String s:ids){
			
			List<TbAuthService> tbAuthServiceList = tbAuthServiceService.selectList(new Wrapper<TbAuthService>() {
				
				private static final long serialVersionUID = 1L;

				@Override
				public String getSqlSegment() {
					// TODO Auto-generated method stub
					return "t where t.service_id='"+s+"'";
				}
			});
			if(tbAuthServiceList.size()!=0){
				ajaxJson.fail("有授权记录存在，删除失败！！！");
				return ajaxJson;
			}
		}
		
		return super.batchDelete(ids);
	}
	
	@RequestMapping("getAllService")
	public void getAllService(HttpServletResponse response) throws IOException{
		List<DmWhService> dmWhServiceList  = dmWhServiceService.selectList(new Wrapper<DmWhService>() {
			
			private static final long serialVersionUID = 1L;
			@Override
			public String getSqlSelect() {
				// TODO Auto-generated method stub
				return "d.id,d.service_name,d.service_comment";
			}
			@Override
			public String getSqlSegment() {
				// TODO Auto-generated method stub
				return "d";
			}
		});
		PrintWriter out = response.getWriter();
		String jsonStr = JSON.toJSONString(dmWhServiceList);
		out.print(jsonStr);
		out.flush();
		out.close();
	}
	
	@RequestMapping("getServiceByNoAuth/{channelId}")
	public void getServiceByNoAuth(@PathVariable("channelId")String channelId , HttpServletResponse response) throws IOException{
		List<DmWhService> dmWhServiceList = dmWhServiceService.selectListNoAuth(channelId);
		response.getWriter().print(JSON.toJSONString(dmWhServiceList));

	}
	
	
	//服务号码唯一性检测
	@RequestMapping("checkServiceName")
	public void checkChannelName(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String serviceName = request.getParameter("serviceName");
		DmWhService dmWhService = dmWhServiceService.selectOne(new EntityWrapper<DmWhService>(DmWhService.class).eq("service_name", serviceName));
		PrintWriter out = response.getWriter();
		if(dmWhService==null){
			out.print("1");
			return;
		}
		out.print("0");
	}
	
	@Override
	public AjaxJson doSave(DmWhService entity, HttpServletRequest request,
			HttpServletResponse response, BindingResult result) {
		// TODO Auto-generated method stub
		AjaxJson ajaxJson = new AjaxJson();
		DmWhService tem = dmWhServiceService.selectOne(new EntityWrapper<DmWhService>(DmWhService.class).eq("service_name", entity.getServiceName()).notIn("id", entity.getId()));
		if(tem!=null){
			ajaxJson.fail("服务ID重复");
			return ajaxJson;
		}
		return super.doSave(entity, request, response, result);
	}
	
	@RequestMapping(value="{serviceId}/showTestService", method = RequestMethod.GET)
	public String testServiceView(@PathVariable("serviceId") String id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		DmWhService entity = dmWhServiceService.selectById(id);
		String filePath = request.getServletContext().getRealPath("/")+"//WEB-INF//classes//xml//example//"+entity.getServiceName()+"_Request.xml";
		String requestMessage = null;
		try{
			Document dMessage = XmlHelper.readXml(filePath);
			requestMessage = XmlHelper.formatXml(dMessage);
		}catch(NullPointerException e){
			requestMessage =e.toString()+"            xml请求文件不存在";
		}
		String rquestUrl = dmWhXtcsService.selectOne(new EntityWrapper<DmWhXtcs>().eq("xtcs_dm", "TBS.WS.TEST")).getXtcsNr();
		model.addAttribute("data", entity);
		model.addAttribute("requestMessage", requestMessage);
		model.addAttribute("requestUrl", rquestUrl);
		return display("testService");
	}
	
	@RequestMapping(value="{serviceName}/testService", method = {RequestMethod.GET,RequestMethod.POST})
	public void testService(@PathVariable("serviceName") String serviceName, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String responseMessage = null;
		//String reqfilePath = request.getServletContext().getRealPath("/")+"//WEB-INF//classes//xml//example//"+serviceName+"_Request.xml";
		String requestMessage = request.getParameter("requestMessage").replace("]]>", "]]]]>><![CDATA[");
		String soapRequestData = null;
		DmWhXtcs dmWhXtcs = dmWhXtcsService.selectOne(new EntityWrapper<DmWhXtcs>().eq("xtcs_dm", "TBS.WS.TEST"));
		if(!dmWhXtcs.getXybz().equals("Y")){
			response.getWriter().print("系统参数没有开启");
			return;
		}
		//String rquestUrl = dmWhXtcs.getXtcsNr();
		String rquestUrl = dmWhXtcs.getXtcsNr();
		
		try{
//			Document d = XmlHelper.readXml(reqfilePath);
//			requestMessage = XmlHelper.formatXml(d,"utf-8");
			soapRequestData = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:com='http://com.eqiao.tbs.sver.ws/'>"
					+"<soapenv:Header/><soapenv:Body><com:doService><arg0><![CDATA[" + requestMessage + "]]></arg0></com:doService></soapenv:Body></soapenv:Envelope>";
			
			//System.out.println(soapRequestData);
		
		}catch(NullPointerException e){
			response.getWriter().print(e.toString()+"            xml请求文件不存在");
			return;
		}
		
		try{
			responseMessage = AccessOtherServerUntil.accressWebService(soapRequestData, rquestUrl);
		}catch(Exception e){
			e.printStackTrace();
			responseMessage = e.getMessage();
		}
		
		response.getWriter().print(responseMessage);

	}
	
	
}
