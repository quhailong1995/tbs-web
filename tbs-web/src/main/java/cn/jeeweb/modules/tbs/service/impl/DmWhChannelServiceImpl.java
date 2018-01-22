package cn.jeeweb.modules.tbs.service.impl;

import java.io.Serializable;
import java.util.List;
import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.utils.CacheUtils;
import cn.jeeweb.modules.tbs.mapper.DmWhChannelMapper;
import cn.jeeweb.modules.tbs.entity.DmWhChannel;
import cn.jeeweb.modules.tbs.service.IDmWhChannelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.mapper.Wrapper;


/**   
 * @Title: 银行接入管理
 * @Description: 银行接入管理
 * @author QuHaiLong
 * @date 2017-08-12 17:58:07
 * @version V1.0   
 *
 */
@Transactional
@Service("dmWhChannelService")
public class DmWhChannelServiceImpl  extends CommonServiceImpl<DmWhChannelMapper,DmWhChannel> implements  IDmWhChannelService {

	
	
	@Override
	public boolean insert(DmWhChannel dmWhChannel) {
		// 保存主表
		super.insert(dmWhChannel);
		CacheUtils.remove("dmWhChannelCache", "dmWhChannelList");
		return true;
	}
	
	//@CacheEvict(value ="dmWhChannelCache",key="id_"+"#dmWhChannel.id",beforeInvocation=true)
	@Override
	public boolean insertOrUpdate(DmWhChannel dmWhChannel) {
		try {
			// 更新主表
			super.insertOrUpdate(dmWhChannel);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		CacheUtils.remove("dmWhChannelCache", "id_"+dmWhChannel.getId());
		CacheUtils.remove("dmWhChannelCache", "dmWhChannelList");
		return true;
	}
	

	@Override
	public DmWhChannel selectById(Serializable id) {
		// TODO Auto-generated method stub
		DmWhChannel dmWhChannel =null;
		dmWhChannel = (DmWhChannel) CacheUtils.get("dmWhChannelCache", "id_"+id);
		if(dmWhChannel!=null){
			return dmWhChannel;
		}
		dmWhChannel = super.selectById(id);
		CacheUtils.put("dmWhChannelCache", "id_"+id, dmWhChannel);
		return dmWhChannel;
	}
	
	@Override
	public boolean deleteById(Serializable id) {
		// TODO Auto-generated method stub
		CacheUtils.remove("dmWhChannelCache", "id_"+id);
		CacheUtils.remove("dmWhChannelCache", "dmWhChannelList");
		return super.deleteById(id);
	}
	@Override
	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		for(Serializable id : idList){
			CacheUtils.remove("dmWhChannelCache", "id_"+id);
		}
		CacheUtils.remove("dmWhChannelCache", "dmWhChannelList");
		return super.deleteBatchIds(idList);
	}
	
	@SuppressWarnings("unchecked")
	//@CachePut(value = { "dmWhChannelCache" },key="dmWhChannelList")
	@Override
	public List<DmWhChannel> selectList(Wrapper<DmWhChannel> wrapper) {
		// TODO Auto-generated method stub
		List<DmWhChannel> dmWhChannelList = null;
		dmWhChannelList = (List<DmWhChannel>) CacheUtils.get("dmWhChannelCache", "dmWhChannelList");
		if(dmWhChannelList!=null){
			return dmWhChannelList;
		}
		dmWhChannelList = super.selectList(wrapper);
		CacheUtils.put("dmWhChannelCache", "dmWhChannelList", dmWhChannelList);
		return dmWhChannelList;
	}
	
}
