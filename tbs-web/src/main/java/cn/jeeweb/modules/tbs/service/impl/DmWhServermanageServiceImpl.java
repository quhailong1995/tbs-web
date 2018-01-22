package cn.jeeweb.modules.tbs.service.impl;

import java.util.Date;
import java.util.List;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.modules.sys.utils.UserUtils;
import cn.jeeweb.modules.tbs.mapper.DmWhServermanageMapper;
import cn.jeeweb.modules.tbs.entity.DmWhAppmanage;
import cn.jeeweb.modules.tbs.entity.DmWhServermanage;
import cn.jeeweb.modules.tbs.service.IDmWhAppmanageService;
import cn.jeeweb.modules.tbs.service.IDmWhServermanageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: 系统配置-服务器管理
 * @Description: 系统配置-服务器管理
 * @author quhailong
 * @date 2017-10-27 10:36:09
 * @version V1.0   
 *
 */
@Transactional
@Service("dmWhServermanageService")
public class DmWhServermanageServiceImpl  extends CommonServiceImpl<DmWhServermanageMapper,DmWhServermanage> implements  IDmWhServermanageService {
	
	@Autowired
	private IDmWhAppmanageService dmWhAppmanageService;
	
	@Override
	public boolean insert(DmWhServermanage entity) {
		// TODO Auto-generated method stub
		entity.setCreateName(UserUtils.getPrincipal().getUsername());
		entity.setCreateDate(new Date());
		return super.insert(entity);
	}
	
	@Override
	public boolean insertOrUpdate(DmWhServermanage arg0) {
		// TODO Auto-generated method stub
		arg0.setUpdateDate(new Date());
		arg0.setUpdateName(UserUtils.getPrincipal().getUsername());
		return super.insertOrUpdate(arg0);
	}
	
	@Override
	public Page<DmWhServermanage> selectPage(Page<DmWhServermanage> page,
			Wrapper<DmWhServermanage> wrapper) {
		// TODO Auto-generated method stub
		page = super.selectPage(page, wrapper);
		List<DmWhServermanage> dmWhServermanageList = page.getRecords();
	
		for(DmWhServermanage d : dmWhServermanageList){
			if(d.getXybz()!=null){
				d.setXybz(d.getXybz().equals("Y")?"开启":"停用");
			}
			if(d.getServerState()!=null){
				d.setServerState(d.getServerState().equals("1")?"可访问":"不可访问");
			}
			
			d.setApp(dmWhAppmanageService.selectOne(new EntityWrapper<DmWhAppmanage>().eq("app_dm", d.getAppDm())));
		}
		return page;
	}
	

}
