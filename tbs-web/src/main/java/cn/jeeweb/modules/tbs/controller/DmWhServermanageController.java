package cn.jeeweb.modules.tbs.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.telnet.TelnetClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.annotation.PageableDefaults;
import cn.jeeweb.core.query.data.PropertyPreFilterable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.utils.QueryableConvertUtils;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.utils.JeewebPropertiesUtil;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.tbs.entity.DmWhServermanage;
import cn.jeeweb.modules.tbs.service.IDmWhServermanageService;

/**   
 * @Title: 系统配置-服务器管理
 * @Description: 系统配置-服务器管理
 * @author quhailong
 * @date 2017-10-27 10:36:09
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/tbs/dmwhservermanage")
@RequiresPathPermission("tbs:dmwhservermanage")
public class DmWhServermanageController extends BaseCRUDController<DmWhServermanage, String> {

	@Autowired
	private IDmWhServermanageService dmWhServermanageService;
	
	@RequestMapping(value = "testAppState", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson testAppState(@RequestParam(value = "ids", required = false) String[] ids) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("检测完毕");
		
		
			List<String> idList = java.util.Arrays.asList(ids);
			for(String id : idList){
				DmWhServermanage dmWhServermanage = dmWhServermanageService.selectById(id);
				
				TelnetClient telnetClient = new TelnetClient();  
				telnetClient.setConnectTimeout(1000);
	            //telnetClient.setDefaultTimeout(5000); //socket延迟时间：5000ms
	            try {
					telnetClient.connect(dmWhServermanage.getServerIp(),Integer.parseInt(dmWhServermanage.getServerPort()));
					dmWhServermanage.setServerState("1");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					dmWhServermanage.setServerState("0");
					e.printStackTrace();
				}
				//System.out.println("------------------"+dmWhServermanage.getServerState());
				dmWhServermanageService.insertOrUpdate(dmWhServermanage);
			}
			
		
		
		return ajaxJson;
	}
	
	public static void main(String[] args) {
        try {
            //TelnetClient telnetClient = new TelnetClient("vt200");  //指明Telnet终端类型，否则会返回来的数据中文会乱码
            TelnetClient telnetClient = new TelnetClient();
            telnetClient.setDefaultTimeout(5000); //socket延迟时间：5000ms
            telnetClient.connect("192.168.1.15",8081);  //建立一个连接,默认端口是23
            
            System.out.println("++++++++"+telnetClient.isAvailable());
            
            
            InputStream inputStream = telnetClient.getInputStream(); //读取命令的流
            PrintStream pStream = new PrintStream(telnetClient.getOutputStream());  //写命令的流
            byte[] b = new byte[1024];
            int size;
            StringBuffer sBuffer = new StringBuffer(300);
            while(true) {     //读取Server返回来的数据，直到读到登陆标识，这个时候认为可以输入用户名
                size = inputStream.read(b);
                if(-1 != size) {
                    sBuffer.append(new String(b,0,size));
                    if(sBuffer.toString().trim().endsWith("login:")) {
                        break;
                    }
                }
            }
            System.out.println(sBuffer.toString());
            pStream.println("exit"); //写命令
            pStream.flush(); //将命令发送到telnet Server
            if(null != pStream) {
                pStream.close();
            }
            telnetClient.disconnect();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	

	
	@RequestMapping(value = "availableServer", method = { RequestMethod.GET, RequestMethod.POST })
	public void availableServer(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String aa = request.getParameter("page");
		System.out.println(aa);
		EntityWrapper<DmWhServermanage> entityWrapper = new EntityWrapper<DmWhServermanage>(entityClass);
		
		entityWrapper.addFilter("xybz='Y' and server_state='1'");
		// 预处理
		QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
		SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
		PageJson<DmWhServermanage> pagejson = new PageJson<DmWhServermanage>(commonService.list(queryable,entityWrapper));
		String content = JSON.toJSONString(pagejson, filter);
		StringUtils.printJson(response, content);
	}
	
	
	
}
