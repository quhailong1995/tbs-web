package cn.jeeweb.modules.tbs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.tbs.entity.AuthLog;
import cn.jeeweb.modules.tbs.mapper.AuthLogMapper;
import cn.jeeweb.modules.tbs.service.IAuthLogService;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

@Transactional
@Service("authLogService")
public class AuthLogServiceImpl  extends CommonServiceImpl<AuthLogMapper,AuthLog> implements  IAuthLogService {
	
	
	@Override
	public Page<AuthLog> selectPage(Page<AuthLog> page, Wrapper<AuthLog> wrapper) {
		wrapper.orderBy("t.lrrq", false);
		page.setRecords(baseMapper.findList(page, wrapper));
		return page;
	}
	
	
	@Override
	public List<AuthLog> selectList(Wrapper<AuthLog> wrapper) {
		wrapper.orderBy("t.lrrq", false);
		return baseMapper.findList(wrapper);
	};
	
}
