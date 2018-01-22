package cn.jeeweb.modules.tbs.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.utils.CacheUtils;
import cn.jeeweb.modules.tbs.mapper.DmWhSerContentMapper;
import cn.jeeweb.modules.tbs.entity.DmWhSerContent;
import cn.jeeweb.modules.tbs.service.IDmWhSerContentService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: 
 * @Description: 
 * @author QuHaiLong
 * @date 2017-08-13 13:55:29
 * @version V1.0   
 *
 */
@Transactional
@Service("dmWhSerContentService")
public class DmWhSerContentServiceImpl  extends CommonServiceImpl<DmWhSerContentMapper,DmWhSerContent> implements  IDmWhSerContentService {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DmWhSerContent> selectList(Wrapper<DmWhSerContent> wrapper) {
		// TODO Auto-generated method stub
		List<DmWhSerContent> dmWhSerContentList = null;
		//String serviceId = (String) wrapper.getParamNameValuePairs().get("MPGENVAL1");
		String serviceId = wrapper.getSqlSegment().split("'")[1];
		dmWhSerContentList = (List<DmWhSerContent>) CacheUtils.get("dmWhSerContentCache", "serviceId_"+serviceId);
		if(dmWhSerContentList!=null){
			return dmWhSerContentList;
		}
		dmWhSerContentList = super.selectList(wrapper);
		CacheUtils.put("dmWhSerContentCache", "serviceId_"+serviceId, dmWhSerContentList);
		return dmWhSerContentList;
	}
	
	@Override
	public DmWhSerContent selectById(Serializable id) {
		// TODO Auto-generated method stub
		DmWhSerContent dmWhSerContent = null;
		dmWhSerContent = (DmWhSerContent) CacheUtils.get("dmWhSerContentCache", "id_"+id);
		if(dmWhSerContent!=null){
			return dmWhSerContent;
		}
		dmWhSerContent = super.selectById(id);
		CacheUtils.put("dmWhSerContentCache", "id_"+id, dmWhSerContent);
		return dmWhSerContent;
	}
	
	@Override
	public boolean deleteById(Serializable id) {
		// TODO Auto-generated method stub
		CacheUtils.remove("dmWhSerContentCache", "id_"+id);
		return super.deleteById(id);
	}
	@Override
	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		for(Serializable id : idList){
			CacheUtils.remove("dmWhSerContentCache", "id_"+id);
		}
		return super.deleteBatchIds(idList);
	}
	
	@Override
	public boolean insert(DmWhSerContent entity) {
		// TODO Auto-generated method stub
		CacheUtils.remove("dmWhSerContentCache","serviceId_"+entity.getServiceId());
		return super.insert(entity);
	}
	@Override
	public boolean insertOrUpdate(DmWhSerContent arg0) {
		// TODO Auto-generated method stub
		CacheUtils.remove("dmWhSerContentCache","serviceId_"+arg0.getServiceId());
		CacheUtils.remove("dmWhSerContentCache","id_"+arg0.getId());
		return super.insertOrUpdate(arg0);
	}
	

}
