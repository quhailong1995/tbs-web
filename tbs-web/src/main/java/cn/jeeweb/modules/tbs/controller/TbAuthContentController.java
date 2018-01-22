package cn.jeeweb.modules.tbs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.mapper.Wrapper;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.utils.CacheUtils;
import cn.jeeweb.modules.tbs.entity.DmWhChannel;
import cn.jeeweb.modules.tbs.entity.DmWhSerContent;
import cn.jeeweb.modules.tbs.entity.DmWhService;
import cn.jeeweb.modules.tbs.entity.TbAuthContent;
import cn.jeeweb.modules.tbs.entity.TbAuthService;
import cn.jeeweb.modules.tbs.service.IDmWhChannelService;
import cn.jeeweb.modules.tbs.service.IDmWhSerContentService;
import cn.jeeweb.modules.tbs.service.IDmWhServiceService;
import cn.jeeweb.modules.tbs.service.ITbAuthContentService;
import cn.jeeweb.modules.tbs.service.ITbAuthServiceService;

/**   
 * @Title: 授权内容管理
 * @Description: 授权内容管理
 * @author QuHaiLong
 * @date 2017-08-14 11:28:53
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/tbs/tbauthcontent")
@RequiresPathPermission("tbs:tbauthcontent")
public class TbAuthContentController extends BaseCRUDController<TbAuthContent, String> {
	
	@Autowired
	private ITbAuthContentService tbAuthContentService;
	@Autowired
	private IDmWhServiceService dmWhServiceService;
	@Autowired
	private IDmWhChannelService dmWhChannelService;
//	@Autowired
//	private ITbAuthContentExtService tbAuthContentExtService;
	@Autowired
	private IDmWhSerContentService dmWhSerContentService;
	@Autowired
	private ITbAuthServiceService tbAuthServiceService;
	
	
	
	@Override
	public TbAuthContent get(String id) {
		// TODO Auto-generated method stub
		id="";
		return super.get(id);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public String showUpdate(TbAuthContent entity, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//从请求中获取as_id
		String[] segUrl = request.getRequestURL().toString().split("/");
		String authServiceId = segUrl[segUrl.length-2];
		
		final TbAuthService tbAuthService = tbAuthServiceService.selectById(authServiceId);
		DmWhChannel dmWhChannel = dmWhChannelService.selectById(tbAuthService.getChannelId());
		DmWhService dmWhService = dmWhServiceService.selectById(tbAuthService.getServiceId());
//		TbAuthContentExt tbAuthContentExt = tbAuthContentExtService.selectById(tbAuthService.getId());
		
		List<TbAuthContent> tbAuthContentList = null;
		tbAuthContentList = (List<TbAuthContent>) CacheUtils.get("tbAuthContentCache", "asId_"+tbAuthService.getId());
		if(tbAuthContentList==null){
			tbAuthContentList = tbAuthContentService.selectList(new Wrapper<TbAuthContent>() {
				
				private static final long serialVersionUID = 1L;

				@Override
				public String getSqlSegment() {
					// TODO Auto-generated method stub
					return "ac where ac.as_id = '"+tbAuthService.getId()+"'";
				}
			});
		}
		
		StringBuffer scontentIdStr = new StringBuffer();
		//把所有TbAuthContent的scontentid取出来，用于前端校验是否已经选择
		for(TbAuthContent t:tbAuthContentList){
			scontentIdStr.append(","+t.getScontentId());
		}
		
		//System.out.println("**——————————测试专用标记————————————***"+scontentIdStr.toString());
		 model.addAttribute("dmWhChannel", dmWhChannel);
		 model.addAttribute("dmWhService", dmWhService);
//		 model.addAttribute("tbAuthContentExt", tbAuthContentExt);
		 model.addAttribute("scontentIdStr", scontentIdStr.toString().substring(1, scontentIdStr.toString().length()));//截取取点第一个‘，’
		 
		//System.out.println(")))))))))))"+segUrl[segUrl.length-2]);
		return super.showUpdate(entity, model, request, response);
	}
	
	@Override
	public AjaxJson update(Model model, final TbAuthContent entity,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		
		//System.out.println("**——————————测试专用标记————————————***"+entity);
		// TODO Auto-generated method stub
		//首先看看是不是给这个授权服务授权过内容
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("更新成功");
		
		final TbAuthService tbAuthService = tbAuthServiceService.selectOne(new Wrapper<TbAuthService>() {
			
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getSqlSelect() {
				// TODO Auto-generated method stub
				return "a.id,a.channel_id,a.service_id";
			}
			@Override
			public String getSqlSegment() {
				// TODO Auto-generated method stub
				return "a where a.channel_id='"+entity.getChannelId()+"' and a.service_id='"+entity.getServiceId()+"'";
			}
		});
		if(tbAuthService==null){
			ajaxJson.fail("该渠道还没有授权该服务！！！");
			return ajaxJson;
			
		}
		

		//先删除所有授权内容  ps：想不到其他更新方法了  因为这个表没有主键
		tbAuthContentService.delete(new Wrapper<TbAuthContent>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getSqlSegment() {
				// TODO Auto-generated method stub
				return "ac where ac.as_id = '"+tbAuthService.getId()+"'";
			}
		});
		
		//在保存
		//System.out.println("()()()()()()()"+entity.getScontentId());
		try{
			tbAuthContentService.saveOrUpdateTbAuthContent(entity, tbAuthService);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
		}

		
		return ajaxJson;
	}
	
	
	
	
	
	@Override
	public AjaxJson doSave(final TbAuthContent entity, HttpServletRequest request,
			HttpServletResponse response, BindingResult result) {
				//System.out.println(entity);
		// TODO Auto-generated method stub
				
				AjaxJson ajaxJson = new AjaxJson();
				ajaxJson.success("添加成功");	
				
				//首先通过channelid和serviceid 查找有没有给这个渠道授权该服务
				final TbAuthService tbAuthService = tbAuthServiceService.selectOne(new Wrapper<TbAuthService>() {
					
					
					private static final long serialVersionUID = 1L;

					@Override
					public String getSqlSelect() {
						// TODO Auto-generated method stub
						return "a.id,a.channel_id,a.service_id";
					}
					@Override
					public String getSqlSegment() {
						// TODO Auto-generated method stub
						return "a where a.channel_id='"+entity.getChannelId()+"' and a.service_id='"+entity.getServiceId()+"'";
					}
				});
				if(tbAuthService==null){
					ajaxJson.fail("该渠道还没有授权该服务！！！");
					return ajaxJson;
					
				}
				
				
				
				
				List<TbAuthContent> tbAuthContentList = tbAuthContentService.selectList(new Wrapper<TbAuthContent>() {
					
					private static final long serialVersionUID = 1L;

					@Override
					public String getSqlSegment() {
						// TODO Auto-generated method stub
						return "ac where as_id = '"+tbAuthService.getId()+"'";
					}
				});
				
				if(tbAuthContentList.size()!=0){
					ajaxJson.fail("已授权过服务内容，直接去修改好了！！！");
					return ajaxJson;
				}
				 
				try{
					tbAuthContentService.saveOrUpdateTbAuthContent(entity,tbAuthService);
				}catch(Exception e){
					e.printStackTrace();
					ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
				}
				

			return ajaxJson;

	}
	
	
	
	@Override
	public void preEdit(TbAuthContent entity, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		
		//获取所有的渠道名称及id
		List<DmWhChannel> dmWhChannelList = dmWhChannelService.selectList(new Wrapper<DmWhChannel>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getSqlSelect() {
				// TODO Auto-generated method stub
				return "dm_wh_channel.id,dm_wh_channel.channel_comment";
			}

			@Override
			public String getSqlSegment() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		//获取所有的服务名称及id
		final List<DmWhService> dmWhServiceList = dmWhServiceService.selectListBySType(new Wrapper<DmWhService>() {
			
			private static final long serialVersionUID = 1L;
			@Override
			public String getSqlSelect() {
				// TODO Auto-generated method stub
				return "s.id,s.service_comment";
			}
			@Override
			public String getSqlSegment() {
				// TODO Auto-generated method stub
				return "s where s.service_type='1'";//1为资源型  2为动作
			}
		});
		
/*		//第一个服务 的 所有服务内容
		List<DmWhSerContent> dmWhSerContentList = dmWhSerContentService.selectList(new Wrapper<DmWhSerContent>() {
			
			private static final long serialVersionUID = 1L;
			@Override
			public String getSqlSegment() {
				// TODO Auto-generated method stub
				return "sc where service_id = '"+dmWhServiceList.get(0).getId()+"'";
			}
		});
		*/
		
		
		
		model.addAttribute("dmWhChannelList", dmWhChannelList);
		model.addAttribute("dmWhServiceList", dmWhServiceList);
		model.addAttribute("firstServiceId", dmWhServiceList.get(0).getId());
		//model.addAttribute("dmWhSerContentList", dmWhSerContentList);
		
		//System.out.println("渠道："+dmWhChannelList+"服务："+dmWhServiceList+"第一个服务的服务内容:"+dmWhSerContentList);
	}
	
	
	
	
	
	

	
	
	
	
	/**
	 * 根据服务动态获取服务内容
	 * @description :  
	 * @author  	: QuHaiLong
	 * @date		: 2017年8月18日 上午11:13:04
	 * @param serviceId	
	 * @throws IOException 
	 *
	 */
	@RequestMapping(value="/changeAuthContentByService/{serviceId}",method=(RequestMethod.GET))
	public void changeAuthContentByService(@PathVariable("serviceId") final String serviceId , HttpServletResponse response) throws IOException{
		//System.out.println("**——————————测试专用标记————————————***"+serviceId);
		//List<DmWhSerContent> dmWhSerContentList =  dmWhSerContentService.selectList(new EntityWrapper<DmWhSerContent>(DmWhSerContent.class).eq("service_id", serviceId));
		List<DmWhSerContent> dmWhSerContentList = dmWhSerContentService.selectList(new Wrapper<DmWhSerContent>() {
			
			private static final long serialVersionUID = 1L;
			@Override
			public String getSqlSegment() {
				// TODO Auto-generated method stub
				return "sc where sc.service_id='"+serviceId+"' order by sc.sort ASC";
			}
		});
		
		
		//System.out.println("**——————————测试专用标记————————————***"+dmWhSerContentList.get(0).getColumnComment());
		
		String data = JSON.toJSONString(dmWhSerContentList);
		
		PrintWriter out = response.getWriter();
		out.print(data);
		out.flush();
		out.close();
		
	}
	
	
	
	/**
	 * 
	 * @description :   //查找所有服务类型为资源型的授权服务
	 * @author  	: QuHaiLong
	 * @date		: 2017年8月14日 下午3:00:08
	 * @param response	
	 * @throws IOException 
	 *
	 */
	@RequestMapping("/getAllAuthContent")
	public void authContentList(Queryable queryable,
			EntityWrapper<TbAuthService> entityWrapper,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 获得数据
		
				//System.out.println(queryable.getCondition().toString()+"**——————————测试专用标记————————————***");
			
		
		Page<TbAuthService> tbAuthServicePage =  tbAuthServiceService.myList(queryable);/*list(queryable,new EntityWrapper<TbAuthService>() {
			
			private static final long serialVersionUID = 1L;

			
			@Override
			public String getSqlSelect() {
				// TODO Auto-generated method stub
				return "tb_auth_service.id,tb_auth_service.channel_id,tb_auth_service.service_id,d.service_type";
			}


			@Override
			public String getSqlSegment() {
				// TODO Auto-generated method stub
				return ", dm_wh_service d where tb_auth_service.service_id = d.id and d.service_type = '1'  and exists(select ac.as_id from tb_auth_content ac where tb_auth_service.id = ac.as_id )  ";
			}
			
			
			

		});*/
		
		//System.out.println(tbAuthServiceList.size());
		
		//List<TbAuthServiceAndContent> TbAuthContentUntilList = new ArrayList<>();
				
		for(final TbAuthService t : tbAuthServicePage.getContent()){
			DmWhChannel dmWhChannel = dmWhChannelService.selectById(t.getChannelId());
			t.setDmWhChannel(dmWhChannel);
			DmWhService dmWhService = dmWhServiceService.selectById(t.getServiceId());
			t.setDmWhService(dmWhService);
//			TbAuthContentExt tbAuthContentExt = tbAuthContentExtService.selectById(t.getId());
//			t.setTbAuthContentExt(tbAuthContentExt);
	
			
			List<TbAuthContent> tbAuthContentList = tbAuthContentService
					.selectList(new Wrapper<TbAuthContent>() {

						private static final long serialVersionUID = 1L;

						@Override
						public String getSqlSegment() {
							// TODO Auto-generated method stub
							return "where as_id='" + t.getId() + "'";
						}

					});
			
			
			//System.out.println("************************************"+t.getId()+"##################"+tbAuthContentList);
			
			// 把该授权服务下所有的授权内容中的 字段解释 以 （姓名、证件类型）字符串连接
			StringBuffer serContentStr = new StringBuffer();
			for (TbAuthContent c : tbAuthContentList) {
				DmWhSerContent dmWhSerContent = dmWhSerContentService
						.selectById(c.getScontentId());
				// System.out.println("授权内容"+dmWhSerContent.getColumnComment());
				serContentStr.append(dmWhSerContent.getColumnComment() + "、");
			}
			String serContentStrToString = serContentStr.toString();
			//System.out.println("((((((((((((((((((((((((((((((((((("+ tbAuthContentList.size());
			t.setSerContentStr(serContentStrToString.substring(0,
					serContentStrToString.length() - 1));// 删除最后一个、号 并放入实体

			// System.out.println(serContentStr.toString());
		
			
		}
		
		
		PageJson<TbAuthService> pagejson = new PageJson<>(tbAuthServicePage);
		String jsonStr = JSON.toJSONString(pagejson,SerializerFeature.DisableCircularReferenceDetect);
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		
		
		
	}
	
	
	
	

}
