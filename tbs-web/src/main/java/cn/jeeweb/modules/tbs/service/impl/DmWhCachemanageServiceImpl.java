package cn.jeeweb.modules.tbs.service.impl;

import java.util.Date;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.utils.UserUtils;
import cn.jeeweb.modules.tbs.mapper.DmWhCachemanageMapper;
import cn.jeeweb.modules.tbs.entity.DmWhCachemanage;
import cn.jeeweb.modules.tbs.service.IDmWhCachemanageService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 系统配置-缓存管理
 * @Description: 系统配置-缓存管理
 * @author quhailong
 * @date 2017-10-27 10:42:39
 * @version V1.0   
 *
 */
@Transactional
@Service("dmWhCachemanageService")
public class DmWhCachemanageServiceImpl  extends CommonServiceImpl<DmWhCachemanageMapper,DmWhCachemanage> implements  IDmWhCachemanageService {
	
	@Override
	public boolean insert(DmWhCachemanage entity) {
		// TODO Auto-generated method stub
		entity.setCreateName(UserUtils.getPrincipal().getUsername());
		entity.setCreateDate(new Date());
		return super.insert(entity);
	}
	
	@Override
	public boolean insertOrUpdate(DmWhCachemanage arg0) {
		// TODO Auto-generated method stub
		arg0.setUpdateDate(new Date());
		arg0.setUpdateName(UserUtils.getPrincipal().getUsername());
		return super.insertOrUpdate(arg0);
	}

}
