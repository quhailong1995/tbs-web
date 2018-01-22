package cn.jeeweb.modules.tbs.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.modules.sys.security.shiro.realm.UserRealm.Principal;
import cn.jeeweb.modules.sys.utils.UserUtils;
import cn.jeeweb.modules.tbs.mapper.DmWhServiceMapper;
import cn.jeeweb.modules.tbs.entity.DmWhService;
import cn.jeeweb.modules.tbs.entity.TbAuthService;
import cn.jeeweb.modules.tbs.service.IDmWhServiceService;
import cn.jeeweb.modules.tbs.service.ITbAuthServiceService;
import cn.jeeweb.modules.tbs.entity.DmWhSerContent;
import cn.jeeweb.modules.tbs.service.IDmWhSerContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.utils.CacheUtils;
import cn.jeeweb.core.utils.ServletUtils;
import cn.jeeweb.core.utils.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.commons.lang3.StringEscapeUtils;

/**   
 * @Title: 系统服务管理
 * @Description: 系统服务管理
 * @author QuHaiLong
 * @date 2017-08-13 13:55:29
 * @version V1.0   
 *
 */
@Transactional
@Service("dmWhServiceService")
public class DmWhServiceServiceImpl  extends CommonServiceImpl<DmWhServiceMapper,DmWhService> implements  IDmWhServiceService {
	@Autowired
	private IDmWhSerContentService dmWhSerContentService;
	@Autowired
	private ITbAuthServiceService tbAuthServiceService;
	
	//@CacheEvict(value="dmWhServiceCache",key="dmWhServiceList",beforeInvocation=true)
	@Override
	public boolean insert(DmWhService dmWhService) {
		
		// 保存主表
		Principal principal = UserUtils.getPrincipal();
		dmWhService.setCreateName(principal.getUsername());
		/*dmWhService.setXybz("Y");
		dmWhService.setYxbz("Y");*/
		dmWhService.setCreateDate(new Date());
		super.insert(dmWhService);
		// 保存
		String dmWhSerContentListJson = StringEscapeUtils
				.unescapeHtml4(ServletUtils.getRequest().getParameter("dmWhSerContentListJson"));
		List<DmWhSerContent> dmWhSerContentList = JSONObject.parseArray(dmWhSerContentListJson, DmWhSerContent.class);
		for (DmWhSerContent dmWhSerContent : dmWhSerContentList) {
			// 保存字段列表
			dmWhSerContent.setCreateName(principal.getUsername());
			if(!dmWhSerContent.getYxbz().equals("")&&dmWhSerContent.getYxbz()!=null){
				dmWhSerContent.setYxbz("Y");
			}
			dmWhSerContent.setXybz("Y");
			dmWhSerContent.setXybz("Y");
			dmWhSerContent.setCreateDate(new Date());
			dmWhSerContent.setServiceId(dmWhService);
			dmWhSerContentService.insert(dmWhSerContent);
		}
		CacheUtils.remove("dmWhServiceCache", "dmWhServiceList");
		return true;
	}
	
	//@CacheEvict(value="dmWhServiceCache",key="dmWhServiceList",beforeInvocation=true)
	@SuppressWarnings("unchecked")
	@Override
	public boolean insertOrUpdate(DmWhService dmWhService) {
		try {
			// 获得以前的数据
			List<DmWhSerContent> oldDmWhSerContentList = null;
			oldDmWhSerContentList = (List<DmWhSerContent>) CacheUtils.get("dmWhSerContentCache", "serviceId_"+dmWhService.getId());
			if(oldDmWhSerContentList==null){
				oldDmWhSerContentList = dmWhSerContentService.selectList(new EntityWrapper<DmWhSerContent>(DmWhSerContent.class).eq("serviceId.id",dmWhService.getId()));
			}
			
			// 字段
			String dmWhSerContentListJson = StringEscapeUtils
					.unescapeHtml4(ServletUtils.getRequest().getParameter("dmWhSerContentListJson"));
			List<DmWhSerContent> dmWhSerContentList = JSONObject.parseArray(dmWhSerContentListJson,
					DmWhSerContent.class);
			// 更新主表
			Principal principal = UserUtils.getPrincipal();
			dmWhService.setUpdateName(principal.getUsername());
			dmWhService.setUpdateDate(new Date());
			super.insertOrUpdate(dmWhService);
			dmWhSerContentList = JSONObject.parseArray(dmWhSerContentListJson, DmWhSerContent.class);
			List<String> newsDmWhSerContentIdList = new ArrayList<String>();
			// 保存或更新数据
			for (DmWhSerContent dmWhSerContent : dmWhSerContentList) {
				// 设置不变更的字段
				if (StringUtils.isEmpty(dmWhSerContent.getId())) {
					// 保存字段列表
					dmWhSerContent.setCreateName(principal.getUsername());
					dmWhSerContent.setCreateDate(new Date());
					dmWhSerContent.setServiceId(dmWhService);
					dmWhSerContentService.insert(dmWhSerContent);
				} else {
					dmWhSerContent.setUpdateName(principal.getUsername());
					dmWhSerContent.setUpdateDate(new Date());
					dmWhSerContentService.insertOrUpdate(dmWhSerContent);
				}
				newsDmWhSerContentIdList.add(dmWhSerContent.getId());
			}

			// 删除老数据
			for (DmWhSerContent dmWhSerContent : oldDmWhSerContentList) {
				String dmWhSerContentId = dmWhSerContent.getId();
				if (!newsDmWhSerContentIdList.contains(dmWhSerContentId)) {
					dmWhSerContentService.deleteById(dmWhSerContentId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		CacheUtils.remove("dmWhServiceCache", "id_"+dmWhService.getId());
		CacheUtils.remove("dmWhSerContentCache", "serviceId_"+dmWhService.getId());
		CacheUtils.remove("dmWhSerContentCache", "dmWhServiceList");
		
		
		return true;
	}
	

	
	@Override
	public DmWhService selectById(Serializable id) {
		// TODO Auto-generated method stub
		DmWhService dmWhService = null;
		dmWhService = (DmWhService) CacheUtils.get("dmWhServiceCache", "id_"+id);
		if(dmWhService!=null){
			return dmWhService;
		}
		dmWhService = super.selectById(id);
		CacheUtils.put("dmWhServiceCache", "id_"+id, dmWhService);
		return dmWhService;
	}
	
	//@CacheEvict(value="dmWhServiceCache",key="dmWhServiceList",beforeInvocation=true)
	@Override
	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		for(Serializable id : idList){
			CacheUtils.remove("dmWhServiceCache", "id_"+id);
			
		}
		CacheUtils.remove("dmWhServiceCache", "dmWhServiceList");
		return super.deleteBatchIds(idList);
	}
	
	//@CachePut(value = { "dmWhServiceCache" },key="dmWhServiceList")
	@SuppressWarnings("unchecked")
	@Override
	public List<DmWhService> selectList(Wrapper<DmWhService> wrapper) {
		// TODO Auto-generated method stub
		List<DmWhService> dmWhServiceList = null;
		dmWhServiceList = (List<DmWhService>) CacheUtils.get("dmWhServiceCache", "dmWhServiceList");
		if(dmWhServiceList!=null){
			return dmWhServiceList;
		}
		dmWhServiceList = super.selectList(wrapper);
		CacheUtils.put("dmWhServiceCache", "dmWhServiceList", dmWhServiceList);
		return dmWhServiceList;
	}

	@Override
	public List<DmWhService> selectListBySType(Wrapper<DmWhService> wrapper) {
		// TODO Auto-generated method stub
		return super.selectList(wrapper);
	}

	@Override
	public List<DmWhService> selectListNoAuth(String channelId) {
		// TODO Auto-generated method stub
		StringBuffer serviceIdbf = new StringBuffer();
		final String serviceIds ;
		List<TbAuthService> tAuthServiceList = tbAuthServiceService.selectList(new EntityWrapper<TbAuthService>(TbAuthService.class).eq("channel_id", channelId));
		//System.out.println("{}{}{}"+tAuthServiceList);
		if(tAuthServiceList.size()!=0){
			serviceIdbf.append("where d.id not in (");
			for(TbAuthService tbAuthService : tAuthServiceList){
				serviceIdbf.append("'"+tbAuthService.getServiceId()+"',");
			}
			serviceIds =  serviceIdbf.toString().substring(0,serviceIdbf.toString().length()-1)+")";

		}else{
			serviceIds = "";
		}
		
		List<DmWhService> dmWhServiceList  = super.selectList(new Wrapper<DmWhService>() {
					
					private static final long serialVersionUID = 1L;
					@Override
					public String getSqlSelect() {
						// TODO Auto-generated method stub
						return "d.id,d.service_comment";
					}
					@Override
					public String getSqlSegment() {
						// TODO Auto-generated method stub
						return "d "+serviceIds;
					}
				});
		return dmWhServiceList;
	}

}
