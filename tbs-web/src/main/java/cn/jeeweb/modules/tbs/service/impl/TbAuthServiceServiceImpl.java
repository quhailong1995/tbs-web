package cn.jeeweb.modules.tbs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.query.data.PageImpl;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.utils.CacheUtils;
import cn.jeeweb.modules.sys.security.shiro.realm.UserRealm.Principal;
import cn.jeeweb.modules.sys.utils.UserUtils;
import cn.jeeweb.modules.tbs.entity.TbAuthService;
import cn.jeeweb.modules.tbs.mapper.TbAuthContentMapper;
import cn.jeeweb.modules.tbs.mapper.TbAuthServiceMapper;
import cn.jeeweb.modules.tbs.service.ITbAuthServiceService;

import com.baomidou.mybatisplus.mapper.Wrapper;


/**   
 * @Title: 系统服务授权
 * @Description: 系统服务授权
 * @author QuHaiLong
 * @date 2017-08-13 14:42:37
 * @version V1.0   
 *
 */
@Transactional
@Service("tbAuthServiceService")
public class TbAuthServiceServiceImpl  extends CommonServiceImpl<TbAuthServiceMapper,TbAuthService> implements  ITbAuthServiceService {

	@Autowired
	private TbAuthContentMapper tbAuthContentMapper;
	@Autowired
	private TbAuthServiceMapper tbAuthServiceMapper;
	 
	@Override
	public boolean insert(TbAuthService tbAuthService) {
		// 保存主表
		Principal principal = UserUtils.getPrincipal();
		tbAuthService.setCreateName(principal.getUsername());
		tbAuthService.setYxbz("Y");
		tbAuthService.setXybz("Y");
		tbAuthService.setCreateDate(new Date());
		super.insert(tbAuthService);
		return true;
	}
	
	@Override
	public boolean insertOrUpdate(TbAuthService tbAuthService) {
		Principal principal = UserUtils.getPrincipal();
		try {
			// 更新主表
			tbAuthService.setUpdateName(principal.getUsername());
			tbAuthService.setUpdateDate(new Date());
			super.insertOrUpdate(tbAuthService);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		CacheUtils.remove("tbAuthServiceCache", "id_"+tbAuthService.getId());
		return true;
	}
	
	
	@Override
	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		
		//删除授权内容
		tbAuthContentMapper.deleteBatchIds(idList);

		//最后再调用父类方法删除全部授权
		
		for(Serializable id : idList){
			CacheUtils.remove("tbAuthServiceCache", "id_"+id);
			CacheUtils.remove("tbAuthContentCache", "asId_"+id);
		}
		return super.deleteBatchIds(idList);
	}

	@Override
	public cn.jeeweb.core.query.data.Page<TbAuthService> myList(
			Queryable queryable) {
		// TODO Auto-generated method stub
		String channelId =null;
		String serviceId = null;
		if(queryable.getCondition()!=null){
			if(queryable.getValue("channel_id")!=null){
				channelId = (String) queryable.getValue("channel_id");
			}
			if(queryable.getValue("service_id")!=null){
				serviceId = (String) queryable.getValue("service_id");
			}
		}
		int pagesize = queryable.getPageable().getPageSize();
		int startrow = ((queryable.getPageable().getPageNumber()-1)*pagesize);
		int endrow = startrow+pagesize;
		//System.out.println(pagesize+"**——————————测试专用标记————————————***"+endrow);
		List<TbAuthService> tbAuthServiceList = tbAuthServiceMapper.mySelectPage(startrow, endrow, channelId,serviceId);
		Integer totalCount = tbAuthServiceMapper.selectCount(new Wrapper<TbAuthService>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getSqlSelect() {
				// TODO Auto-generated method stub
				return "tac.id";
			}
			@Override
			public String getSqlSegment() {
				// TODO Auto-generated method stub
				return " tac, dm_wh_service d where tac.service_id = d.id  and d.service_type = '1'  and exists(select ac.as_id from tb_auth_content ac where tac.id = ac.as_id )";
			}
		});
		PageImpl<TbAuthService> page = new PageImpl<TbAuthService>(tbAuthServiceList,queryable.getPageable(),(long)totalCount);
		//System.out.println(page.toString()+"**——————————测试专用标记————————————***");
		
		return page;
		
	}

	@Override
	public TbAuthService selectById(Serializable id) {
		// TODO Auto-generated method stub
		TbAuthService tbAuthService = null;
		tbAuthService = (TbAuthService) CacheUtils.get("tbAuthServiceCache", "id_"+id);
		if(tbAuthService!=null){
			return tbAuthService;
		}
		tbAuthService = super.selectById(id);
		CacheUtils.put("tbAuthServiceCache", "id_"+id, tbAuthService);
		return tbAuthService;
	}
	
	
}
