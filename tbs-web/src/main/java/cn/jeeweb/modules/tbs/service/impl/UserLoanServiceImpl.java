package cn.jeeweb.modules.tbs.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.tbs.entity.UserLoan;
import cn.jeeweb.modules.tbs.mapper.UserLoanMapper;
import cn.jeeweb.modules.tbs.service.IUserLoanService;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

@Transactional
@Service("userLoanService")
public class UserLoanServiceImpl  extends CommonServiceImpl<UserLoanMapper,UserLoan> implements  IUserLoanService {
	
	
	@Override
	public Page<UserLoan> selectPage(Page<UserLoan> page, Wrapper<UserLoan> wrapper) {
		wrapper.orderBy("l.loan_begin", false);
		page.setRecords(baseMapper.selectList(page, wrapper));
		return page;
	}
	
}
