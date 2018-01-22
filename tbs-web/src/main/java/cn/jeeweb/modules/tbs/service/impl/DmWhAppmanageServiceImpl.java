package cn.jeeweb.modules.tbs.service.impl;

import java.util.Date;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.utils.UserUtils;
import cn.jeeweb.modules.tbs.mapper.DmWhAppmanageMapper;
import cn.jeeweb.modules.tbs.entity.DmWhAppmanage;
import cn.jeeweb.modules.tbs.service.IDmWhAppmanageService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 系统配置-应用管理
 * @Description: 系统配置-应用管理
 * @author quhailong
 * @date 2017-10-27 10:27:29
 * @version V1.0   
 *
 */
@Transactional
@Service("dmWhAppmanageService")
public class DmWhAppmanageServiceImpl  extends CommonServiceImpl<DmWhAppmanageMapper,DmWhAppmanage> implements  IDmWhAppmanageService {
	
	@Override
	public boolean insert(DmWhAppmanage entity) {
		// TODO Auto-generated method stub
		entity.setCreateName(UserUtils.getPrincipal().getUsername());
		entity.setCreateDate(new Date());
		return super.insert(entity);
	}
	
	@Override
	public boolean insertOrUpdate(DmWhAppmanage arg0) {
		// TODO Auto-generated method stub
		arg0.setUpdateDate(new Date());
		arg0.setUpdateName(UserUtils.getPrincipal().getUsername());
		return super.insertOrUpdate(arg0);
	}

}
