package cn.jeeweb.modules.tbs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Wrapper;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.sys.security.shiro.realm.UserRealm.Principal;
import cn.jeeweb.modules.sys.utils.UserUtils;
import cn.jeeweb.modules.tbs.entity.DmWhChannel;
import cn.jeeweb.modules.tbs.entity.TbAuthService;
import cn.jeeweb.modules.tbs.service.IDmWhChannelService;
import cn.jeeweb.modules.tbs.service.ITbAuthServiceService;

/**   
 * @Title: 银行接入管理
 * @Description: 银行接入管理
 * @author QuHaiLong
 * @date 2017-08-12 17:58:07
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/tbs/dmwhchannel")
@RequiresPathPermission("tbs:dmwhchannel")
public class DmWhChannelController extends BaseCRUDController<DmWhChannel, String> {
	@Autowired
	private ITbAuthServiceService tbAuthServiceService ;
	@Autowired
	private IDmWhChannelService dmWhChannelService;


	@Override
	public AjaxJson create(Model model, DmWhChannel entity,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Principal principal = UserUtils.getPrincipal();
		entity.setCreateName(principal.getUsername());
/*		entity.setYxbz("Y");
		entity.setXybz("Y");*/
		entity.setCreateDate(new Date());
		return super.create(model, entity, result, request, response);
	}
	

	
	
	@Override
	public AjaxJson batchDelete(String[] ids) {
		// TODO Auto-generated method stub
		AjaxJson ajaxJson = new AjaxJson();
		
		for(final String s :ids){
			List<TbAuthService> tbAuthServiceList = tbAuthServiceService.selectList(new Wrapper<TbAuthService>() {
				
				private static final long serialVersionUID = 1L;

				@Override
				public String getSqlSegment() {
					// TODO Auto-generated method stub
					return "t where t.channel_id='"+s+"'";
				}
			});
			if(tbAuthServiceList.size()!=0){
				ajaxJson.fail("有授权记录存在，删除失败！！！");
				return ajaxJson;
			}
		}
		return super.batchDelete(ids);
	}
	
	/*@Override
	public void preAjaxList(Queryable queryable,
			EntityWrapper<DmWhChannel> entityWrapper,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		System.out.println("**——————————测试专用标记————————————***"+queryable.getValue("channelComment"));
		System.out.println("**——————————测试专用标记————————————***"+request.getMethod()+"-=-=-"+request.getCharacterEncoding()+"0909090"+request.getRequestURL());
		super.preAjaxList(queryable, entityWrapper, request, response);
	}
	
*/
	@RequestMapping("getAllChannel")
	public void getAllChannel(HttpServletResponse response) throws IOException{
		List<DmWhChannel> dmWhChannelList = dmWhChannelService.selectList(new Wrapper<DmWhChannel>() {
			
			private static final long serialVersionUID = 1L;
			@Override
			public String getSqlSelect() {
				// TODO Auto-generated method stub
				return "d.id,d.channel_name,d.channel_comment";
			}
			@Override
			public String getSqlSegment() {
				// TODO Auto-generated method stub
				return "d";
			}
		});
		
		String jsonStr = JSON.toJSONString(dmWhChannelList);
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		
	}
	
	
	@Override
	public AjaxJson update(Model model, DmWhChannel entity,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Principal principal = UserUtils.getPrincipal();
		entity.setUpdateName(principal.getUsername());
		entity.setUpdateDate(new Date());
		return super.update(model, entity, result, request, response);
	}
	
	//渠道号码唯一性检测
	@RequestMapping(value="checkChannelName")
	public void checkChannelName(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String channelName = request.getParameter("channelName");
		DmWhChannel dmWhChannel = dmWhChannelService.selectOne(new EntityWrapper<DmWhChannel>(DmWhChannel.class).eq("channelName",channelName));
		System.out.println(channelName);
		PrintWriter out = response.getWriter();
		if(dmWhChannel==null){
			out.print("1");
			return;
		}
		out.print("0");
	}
	
	@Override
	public AjaxJson doSave(DmWhChannel entity, HttpServletRequest request,
			HttpServletResponse response, BindingResult result) {
		// TODO Auto-generated method stub
		AjaxJson ajaxJson = new AjaxJson();
		DmWhChannel dmWhChannel = dmWhChannelService.selectOne(new EntityWrapper<DmWhChannel>(DmWhChannel.class).eq("channelName",entity.getChannelName()).notIn("id", entity.getId()));
		if(dmWhChannel!=null){
			ajaxJson.fail("渠道ID重复!");
			return ajaxJson;
		}
		return super.doSave(entity, request, response, result);
	}
	
}
