package cn.jeeweb.modules.tbs.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.utils.CacheUtils;
import cn.jeeweb.modules.tbs.mapper.DmWhXtcsMapper;
import cn.jeeweb.modules.tbs.entity.DmWhXtcs;
import cn.jeeweb.modules.tbs.service.IDmWhXtcsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 系统参数维护
 * @Description: 系统参数维护
 * @author QuHaiLong
 * @date 2017-08-30 14:53:25
 * @version V1.0   
 *
 */
@Transactional
@Service("dmWhXtcsService")
public class DmWhXtcsServiceImpl  extends CommonServiceImpl<DmWhXtcsMapper,DmWhXtcs> implements  IDmWhXtcsService {

	@Override
	public boolean insert(DmWhXtcs entity) {
		// TODO Auto-generated method stub
		/*entity.setYxbz("Y");*/
		return super.insert(entity);
	}
	@Override
	public boolean insertOrUpdate(DmWhXtcs arg0) {
		// TODO Auto-generated method stub
		CacheUtils.remove("dmWhXtcsCache", "id_"+arg0.getId());
		return super.insertOrUpdate(arg0);
	}
	@Override
	public DmWhXtcs selectById(Serializable id) {
		// TODO Auto-generated method stub
		DmWhXtcs dmWhXtcs = null;
		dmWhXtcs = (DmWhXtcs) CacheUtils.get("dmWhXtcsCache", "id_"+id);
		if(dmWhXtcs!=null){return dmWhXtcs;}
		dmWhXtcs = super.selectById(id);
		CacheUtils.put("dmWhXtcsCache", "id_"+id, dmWhXtcs);
		return dmWhXtcs;
	}
	@Override
	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		for(Serializable id : idList){
			CacheUtils.remove("dmWhXtcsCache", "id_"+id);
		}
		return super.deleteBatchIds(idList);
	}
}
