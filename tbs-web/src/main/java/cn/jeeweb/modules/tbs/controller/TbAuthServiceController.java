package cn.jeeweb.modules.tbs.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.mapper.Wrapper;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import cn.jeeweb.modules.tbs.entity.DmWhChannel;
import cn.jeeweb.modules.tbs.entity.DmWhService;
import cn.jeeweb.modules.tbs.entity.TbAuthService;
import cn.jeeweb.modules.tbs.service.IDmWhChannelService;
import cn.jeeweb.modules.tbs.service.IDmWhServiceService;
import cn.jeeweb.modules.tbs.service.ITbAuthServiceService;

/**   
 * @Title: 系统服务授权
 * @Description: 系统服务授权
 * @author QuHaiLong
 * @date 2017-08-13 14:42:37
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/tbs/tbauthservice")
@RequiresPathPermission("tbs:tbauthservice")
public class TbAuthServiceController extends BaseCRUDController<TbAuthService, String> {
	@Autowired
	private IDmWhChannelService dmWhChannelService;
	@Autowired
	private ITbAuthServiceService tbAuthServiceService;
	@Autowired
	private IDmWhServiceService dmWhServiceService;
	

	 
	
	@Override
	public void preEdit(TbAuthService tbAuthService, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		List<DmWhChannel> dmWhChannelList = dmWhChannelService.selectList(new EntityWrapper<DmWhChannel>(DmWhChannel.class));
		List<DmWhService> dmWhServiceList = dmWhServiceService.selectListNoAuth(dmWhChannelList.get(0).getId());
		//System.out.println("&&&&&&&&&&&&&&&&&&&"+dmWhServiceList);
		request.setAttribute("dmWhServiceList", dmWhServiceList);
		request.setAttribute("dmWhChannelList", dmWhChannelList);
		
	}
	
	@RequestMapping("/getAllAuthService")
	 public void authServiceList(Queryable queryable,
				EntityWrapper<TbAuthService> entityWrapper,
				HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 获得数据
		//System.out.println("**——————————测试专用标记————————————***"+queryable);
		//.getValue("dmWhChannel.channelComment"))!=null||(queryable.getValue("dmWhService.serviceComment"))!=null
//		if(queryable.getCondition()!=null){
//			
//			queryable.addCondition("channel_id", queryable.getValue("dmWhChannel.channelComment"));
//			queryable.addCondition("service_id", queryable.getValue("dmWhService.serviceComment"));	
//			queryable.removeCondition("dmWhChannel.channelComment");
//
//			queryable.removeCondition("dmWhService.serviceComment");
//				
//			
//		}

		
		
		//System.out.println("**——————————测试专用标记————————————***"+queryable.getValue("channel_id")+"452345423"+queryable.getValue("service_id"));
			
			Page<TbAuthService> tbAuthServicePage = commonService.list(queryable,new EntityWrapper<TbAuthService>(){
				private static final long serialVersionUID = 1L;

				@Override
				public String getSqlSelect() {
					// TODO Auto-generated method stub
					return "tb_auth_service.channel_id,tb_auth_service.service_id,tb_auth_service.id"; //!!!!!!!!!!!!!!!!!!!居然丢了个字段  id，id必须，要更新用
					
				}
			});
			for(TbAuthService t:tbAuthServicePage.getContent()){
				DmWhChannel dmWhChannel = dmWhChannelService.selectById(t.getChannelId());
				DmWhService dmWhService = dmWhServiceService.selectById(t.getServiceId());
				dmWhService.setServiceComment(dmWhService.getServiceName()+"-"+dmWhService.getServiceComment());
				t.setDmWhChannel(dmWhChannel);
				t.setDmWhService(dmWhService);
			}
			
			PageJson<TbAuthService> pagejson = new PageJson<>(tbAuthServicePage);
			//System.out.println("////////////////////////////////////"+pagejson.getResults());
			String jsonStr = JSON.toJSONString(pagejson,SerializerFeature.DisableCircularReferenceDetect);//SerializerFeature.DisableCircularReferenceDetect禁止循环引用检测：
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
		 
	 }

	
	@Override
	public AjaxJson doSave(final TbAuthService entity, HttpServletRequest request,
			HttpServletResponse response, BindingResult result) {
		// TODO Auto-generated method stub
		AjaxJson ajaxJson = new AjaxJson();
	
		
	//之前前端CheckBox处理方法
		//System.out.println(entity.getServiceId());
		//String[] serviceIds = entity.getServiceId().split(",");
		//List<TbAuthService> list = new ArrayList<TbAuthService>();
//		for(int i =0;i<serviceIds.length;i++){
//			String id = UUID.randomUUID().toString().replace("-", "");
//			System.out.println("---=-=-=-=-=-=-=-=-=-=-="+id);
//			entity.setId(id);
//			entity.setServiceId(serviceIds[i]);
//			System.out.println(entity.getId()+"!!!!!!!!!!!!!!!!!!!"+entity.getServiceId());
//			
//			tbAuthServiceService.insertOrUpdate(entity);
//			//list.add(entity);
//		}

		//System.out.println(list);
		//tbAuthServiceService.insertOrUpdateBatch(list);
	
	
		
   //判断是否重复授权
		//1.检测
		TbAuthService tbAuthService = tbAuthServiceService.selectOne(new Wrapper<TbAuthService>() {
			
			private static final long serialVersionUID = 1L;
			@Override
			public String getSqlSelect() {
				// TODO Auto-generated method stub
				return "t.id";
			}
			@Override
			public String getSqlSegment() {
				// TODO Auto-generated method stub
				return "t where t.channel_id='"+entity.getChannelId()+"' and t.service_id='"+entity.getServiceId()+"'";
			}
		});
		//2.判断
		if(tbAuthService!=null){
			ajaxJson.fail("重复授权了！！！");
			return ajaxJson;
		}
		
		//下拉处理方法
		//System.out.println("**——————————测试专用标记————————————***"+entity);
		String id = UUID.randomUUID().toString().replace("-", "");  
		entity.setId(id);
		tbAuthServiceService.insert(entity);
		
		ajaxJson.success("保存成功");
		return ajaxJson;
	}
	
	
	

	
	
	
}
