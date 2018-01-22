package cn.jeeweb.modules.tbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.jeeweb.modules.tbs.entity.TbAuthService;
 
/**   
 * @Title: 系统服务授权数据库控制层接口
 * @Description: 系统服务授权数据库控制层接口
 * @author QuHaiLong
 * @date 2017-08-13 14:42:37
 * @version V1.0   
 *
 */
public interface TbAuthServiceMapper extends BaseMapper<TbAuthService> {

	
	/**
	 * 需求不同 自定义了一个查询
	 * @description :  
	 * @author  	: QuHaiLong
	 * @date		: 2017年8月22日 上午10:07:11
	 * @param startrow
	 * @param pagesize
	 * @param tbAuthService
	 * @return	
	 *
	 */
	List<TbAuthService> mySelectPage(@Param("startrow")int startrow,@Param("endrow")int endrow,@Param("channel_id")String channel_id,@Param("service_id")String service_id);
    
}