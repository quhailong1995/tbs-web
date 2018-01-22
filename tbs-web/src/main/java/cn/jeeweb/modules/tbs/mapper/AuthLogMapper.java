package cn.jeeweb.modules.tbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jeeweb.modules.tbs.entity.AuthLog;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
 

/**
 * 用户贷款查询数据库控制层接口
 */
public interface AuthLogMapper extends BaseMapper<AuthLog> {
	
	List<AuthLog> findList(Page<AuthLog> page, @Param("ew") Wrapper<AuthLog> wrapper);
	
	List<AuthLog> findList(@Param("ew") Wrapper<AuthLog> wrapper);
	
}