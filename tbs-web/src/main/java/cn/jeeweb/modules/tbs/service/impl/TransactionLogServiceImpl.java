package cn.jeeweb.modules.tbs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.tbs.entity.TransactionLog;
import cn.jeeweb.modules.tbs.mapper.TransactionLogMapper;
import cn.jeeweb.modules.tbs.service.ITransactionLogService;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

@Transactional
@Service("transactionLogService")
public class TransactionLogServiceImpl  extends CommonServiceImpl<TransactionLogMapper,TransactionLog> implements  ITransactionLogService {
	
	
	@Override
	public Page<TransactionLog> selectPage(Page<TransactionLog> page, Wrapper<TransactionLog> wrapper) {
		wrapper.orderBy("t.lrrq", false);
		page.setRecords(baseMapper.findList(page, wrapper));
		return page;
	}
	
	
	@Override
	public List<TransactionLog> selectList(Wrapper<TransactionLog> wrapper) {
		wrapper.orderBy("t.lrrq", false);
		return baseMapper.findList(wrapper);
	}


	@Override
	public TransactionLog getByEntity(TransactionLog tl) {
		return baseMapper.findByEntity(tl);
	}


	@Override
	public TransactionLog getOneByEntity(TransactionLog tl) {
		// TODO Auto-generated method stub
		return baseMapper.findOneByEntity(tl);
	};
	
	
	
}
