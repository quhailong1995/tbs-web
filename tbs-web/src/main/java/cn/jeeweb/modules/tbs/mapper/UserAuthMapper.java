package cn.jeeweb.modules.tbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jeeweb.modules.tbs.entity.UserAuth;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
 

/**
 * 用户授权查询数据库控制层接口
 */
public interface UserAuthMapper extends BaseMapper<UserAuth> {
	
	/**
	 * 分页查询用户授权记录
	 * @param page
	 * @param wrapper
	 * @return
	 */
	List<UserAuth> findList(Page<UserAuth> page, @Param("ew") Wrapper<UserAuth> wrapper);
	
	
	/**
	 * 分页查询用户授权历史信息
	 * @param page
	 * @param wrapper
	 * @return
	 */
	List<UserAuth> findHistoryList(Page<UserAuth> page, @Param("ew") Wrapper<UserAuth> wrapper);
	
	/**
	 * 查询用户授权记录
	 * @param wrapper
	 * @return
	 */
	List<UserAuth> findList(@Param("ew") Wrapper<UserAuth> wrapper);
	
	/**
	 * 查询用户授权历史信息
	 * @param wrapper
	 * @return
	 */
	List<UserAuth> findHistoryList(@Param("ew") Wrapper<UserAuth> wrapper);
    
}