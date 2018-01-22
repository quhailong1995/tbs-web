package cn.jeeweb.modules.tbs.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.tbs.entity.DmWhTaskLog;
import cn.jeeweb.modules.tbs.mapper.DmWhTaskLogMapper;
import cn.jeeweb.modules.tbs.service.IDmWhTaskLogService;

@Transactional
@Service("dmWhTaskLogService")
public class DmWhTaskLogServiceImpl extends  CommonServiceImpl<DmWhTaskLogMapper,DmWhTaskLog> implements IDmWhTaskLogService {
	
	@Override
	public Page<DmWhTaskLog> selectPage(Page<DmWhTaskLog> page,
			Wrapper<DmWhTaskLog> wrapper) {
		// TODO Auto-generated method stub
		page.setRecords(baseMapper.selectList(page, wrapper));
		return page;
	}

}
