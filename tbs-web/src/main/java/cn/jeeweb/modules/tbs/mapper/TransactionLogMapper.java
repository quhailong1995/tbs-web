package cn.jeeweb.modules.tbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jeeweb.modules.tbs.entity.TransactionLog;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
 

/**
 * 税银服务平台交互日志数据访问接口
 */
public interface TransactionLogMapper extends BaseMapper<TransactionLog> {
	
	List<TransactionLog> findList(Page<TransactionLog> page, @Param("ew") Wrapper<TransactionLog> wrapper);
	
	List<TransactionLog> findList(@Param("ew") Wrapper<TransactionLog> wrapper);
	
	TransactionLog findByEntity(TransactionLog tl);
	TransactionLog findOneByEntity(TransactionLog tl);
	
}