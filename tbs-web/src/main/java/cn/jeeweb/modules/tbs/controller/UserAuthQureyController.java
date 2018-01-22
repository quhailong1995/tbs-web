package cn.jeeweb.modules.tbs.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.common.service.ICommonBaseDynamicDBService;
import cn.jeeweb.core.common.service.impl.CommonBaseDynamicDBServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.PropertyPreFilterable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.utils.QueryableConvertUtils;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.core.utils.XmlUtils;
import cn.jeeweb.modules.tbs.entity.DmWhChannel;
import cn.jeeweb.modules.tbs.entity.DmWhProduct;
import cn.jeeweb.modules.tbs.entity.DmWhServermanage;
import cn.jeeweb.modules.tbs.entity.DmWhXtcs;
import cn.jeeweb.modules.tbs.entity.UserAuth;
import cn.jeeweb.modules.tbs.entity.ZmGrsdskjmx;
import cn.jeeweb.modules.tbs.helper.AccessOtherServerUntil;
import cn.jeeweb.modules.tbs.helper.StaticConfig;
import cn.jeeweb.modules.tbs.service.IDmWhChannelService;
import cn.jeeweb.modules.tbs.service.IDmWhProductService;
import cn.jeeweb.modules.tbs.service.IDmWhXtcsService;
import cn.jeeweb.modules.tbs.service.IUniversalQueryUtilService;
import cn.jeeweb.modules.tbs.service.IZmGrsdskjmxService;
import cn.jeeweb.modules.tbs.service.impl.DmWhXtcsServiceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: 用户授权查询
 * @Description: 用户授权查询
 * @author hongft
 * @date 2017-8-10
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/tbs/query/userAuth")
@RequiresPathPermission("tbs:query:userAuth")
public class UserAuthQureyController extends BaseCRUDController<UserAuth, String>{
	
	@Resource
	IDmWhProductService productService;
	@Resource
	IDmWhChannelService channelService;
	@Autowired
	IDmWhXtcsService dmWhXtcsService;
	@Autowired
	IZmGrsdskjmxService zmGrsdskjmxService;
	@Autowired
	IUniversalQueryUtilService 	universalQueryUtilService;
	@Autowired
	ICommonBaseDynamicDBService<Map<String,Object>>  commonBaseDynamicDBService;
	
	/**
	 * 请求模拟授权
	 * @param dataStr
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/analogAuth")
	public void analogAuth(String dataStr, HttpServletRequest request, HttpServletResponse response) throws Exception{
		//把前端传过来的参数序列化再拼装成xml字符串
		String str = dataStr.replaceAll("&quot;", "\"");
		JSONObject jsonObject = JSON.parseObject(str);
		//System.out.println("_______________________"+XmlUtils.getMessageXml(jsonObject));
		DmWhProduct product = new DmWhProduct();
		product.setProductName(jsonObject.getString("product_name"));
		product = productService.getProduct(product);
		//计算到期时间并加入json
		int term = product.getLoanBeforeYfs();//贷前月份数
		//int term = product.getLoanAfterYfs();//贷后月份数
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, term);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		jsonObject.put("expired_date", sdf.format(calendar.getTime()));
		//查询银行对应代码并加入json
		DmWhChannel channel = channelService.selectById(product.getChannelId());
		jsonObject.put("channel_name", channel.getChannelName());
		
		String xmlStr = XmlUtils.getMessageXml(jsonObject);
		//拼装请求字符串
		String data = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:com='http://com.eqiao.tbs.sver.ws/'>"
				+"<soapenv:Header/><soapenv:Body><com:doService><arg0>" + xmlStr + "</arg0></com:doService></soapenv:Body></soapenv:Envelope>";
		//System.out.println(data);
		
		URL url = new URL("http://127.0.0.1:1456/tbs/TBSWebService");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
		
		OutputStream os = connection.getOutputStream();
		os.write(data.getBytes("utf-8"));
		
		int responseCode = connection.getResponseCode();
		if(responseCode==200) {
			InputStream is = connection.getInputStream();
			//System.out.println("return "+is.available());
			response.setContentType("text/xml;charset=utf-8");
			ServletOutputStream outputStream = response.getOutputStream();
			
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len=is.read(buffer))>0) {
				outputStream.write(buffer, 0, len);
			}
			outputStream.flush();
		}
		//return "success";
	}
	
	
	@RequestMapping("/importAuthDataAgain")
	public void importAuthDataAgain(HttpServletRequest resquest,HttpServletResponse response) throws IOException{
		
		String respMessage = "";
		
		
		try{
			String rquestUrl = dmWhXtcsService.selectOne(new EntityWrapper<DmWhXtcs>().eq("xtcs_dm", "TBSInnerService")).getXtcsNr();
			String userId = resquest.getParameter("userId");
			String authCode = resquest.getParameter("authCode");
			String requestMessage= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
					+"<tbl>"
						+"<auth_code>"+authCode+"</auth_code>"
						+"<userid>"+userId+"</userid>"
					+"</tbl>";
			String soapRequestData = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:com='http://com.eqiao.tbs.sver.ws/'>"
					+"<soapenv:Header/><soapenv:Body><com:singleReMigrateGrsdsmx><arg0><![CDATA[" + requestMessage + "]]></arg0></com:singleReMigrateGrsdsmx></soapenv:Body></soapenv:Envelope>";
			String responseMessage  = AccessOtherServerUntil.accressWebService(soapRequestData, rquestUrl);
			response.getWriter().print(responseMessage);
		}catch(Exception e){
			e.printStackTrace();
			respMessage = "调用失败！"+e.getMessage();
			response.getWriter().print("{\"code\":\"0\",\"msg\":\""+respMessage+"\"}");
		}
		
	}
	
	
	
	@RequestMapping("/authDataDetailPage")
	public String authDataDetailPage(Model model,HttpServletRequest resquest,HttpServletResponse response) throws IOException{
		
		resquest.setCharacterEncoding("utf-8");
		String userId = resquest.getParameter("userId");
		String productId = resquest.getParameter("productId");
		String userFullName = resquest.getParameter("userFullName");
		String hjid = resquest.getParameter("hjid");
		String zjhm = resquest.getParameter("zjhm");
		model.addAttribute("userId", userId);
		model.addAttribute("productId", productId);
		model.addAttribute("userFullName", userFullName);
		model.addAttribute("hjid", hjid);
		model.addAttribute("zjhm", zjhm);
		
		
		return display("authDataDetail");
	}
	@RequestMapping("/authDataDetail")
	public void authDataDetail(Queryable queryable, PropertyPreFilterable propertyPreFilterable,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		
		
		
		String userId = request.getParameter("userId");
		String productId = request.getParameter("productId");
		String userFullName = request.getParameter("userFullName");
		
		
		
		EntityWrapper<ZmGrsdskjmx> entityWrapper = new EntityWrapper<ZmGrsdskjmx>(ZmGrsdskjmx.class);
		
		entityWrapper.addFilter("userid='"+userId+"' and product_id='"+productId+"'");
		// 预处理
		QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
		SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);

		cn.jeeweb.core.query.data.Page<ZmGrsdskjmx> p =  zmGrsdskjmxService.list(queryable,entityWrapper);
		
		if(p.getContent()!=null){
			DmWhProduct dp = productService.selectById(productId);
			for(ZmGrsdskjmx z:p.getContent()){
				z.setProductComment(dp.getProductComment());
				z.setUserName(userFullName);
			}
		}
		
		
		
		PageJson<ZmGrsdskjmx> pagejson = new PageJson<ZmGrsdskjmx>(p);
		String content = JSON.toJSONString(pagejson, filter);
		StringUtils.printJson(response, content);
		
	}
	

	
	@RequestMapping("/queryJishiData")
	public void queryJishiData(HttpServletRequest req,HttpServletResponse resp){
		StringBuffer sqlStatement = new StringBuffer();
		sqlStatement.append("select * from ( select  * from  lt_sb_grsdsbgb_2015 union  select  * from  lt_sb_grsdsbgb_2016 union select  * from  lt_sb_grsdsbgb_2017)");
		String hjid = req.getParameter("hjid");
		if(hjid!=null&&!hjid.equals("")){
			sqlStatement.append("where hjid = '"+hjid+"' order by SDQJQ desc");
		} 
		
		List<Map<String, Object>> mapList = universalQueryUtilService.queryResults("db_dm",sqlStatement.toString());
		
		
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
							entry.setValue(entry.getValue().toString().split(" ")[0]);  	
						  }
						if(entry.getValue() instanceof oracle.sql.DATE){
							System.out.println("**********************************************************");  	
					  }
					}  
				}
		 }
		
		
		String content = JSON.toJSONString(mapList);
		StringUtils.printJson(resp, content);
		
	}
	
	
	@RequestMapping("/queryGSSPT")
	@ResponseBody
	public List<Map<String, Object>> queryGSSPT(HttpServletRequest req){
		
		List<Map<String, Object>> list  = null;
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("FIDNUM", req.getParameter("zjhm").trim());
		parm.put("FNAME", req.getParameter("userFullName").trim());
		
		list = commonBaseDynamicDBService.searchList(StaticConfig.GSSDATASOURCE, "AuthDataTraceBack.queryGSSPT", parm);
		
		return list;
		
	}
	
	@RequestMapping("/queryJinSanCore")
	@ResponseBody
	public List<Map<String, Object>> queryJinSanCore(HttpServletRequest req){
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		String hjid = req.getParameter("hjid");
		
		Map<String, Object> y_ywzjMap = commonBaseDynamicDBService.searchObject(StaticConfig.DMDATASOURCE, "AuthDataTraceBack.queryY_ywzj", hjid);
		
		if(y_ywzjMap==null){
			return list;
		}else if(y_ywzjMap.get("Y_YWZJ").equals("")){
			return list;
		}
		
		String y_ywzj = (String) y_ywzjMap.get("Y_YWZJ");
		
		Map<String, Object> dj_zrrbdMap = commonBaseDynamicDBService.searchObject(StaticConfig.JSHXDATASOURCE, "AuthDataTraceBack.queryDahlb", y_ywzj);
		String dahlb = null;
		if(dj_zrrbdMap!=null){
			dahlb = (String) dj_zrrbdMap.get("DAHLB");
		}
	
		
		list = commonBaseDynamicDBService.searchList(StaticConfig.JSHXDATASOURCE, "AuthDataTraceBack.queryJinSanCore", dahlb);
		
		return list;
		
		
		
	}
	
	
}
