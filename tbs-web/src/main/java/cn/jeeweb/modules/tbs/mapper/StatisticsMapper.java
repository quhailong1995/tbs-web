package cn.jeeweb.modules.tbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jeeweb.modules.tbs.entity.Statistics;

/**   
 * @Title: 统计
 * @Description: 统计
 * @author QuHaiLong
 * @date 2017-09-08 14:42:37
 * @version V1.0   
 *
 */
public interface StatisticsMapper {
	
	/**
	 * 
	 * @description :  统计服务交互数据
	 * @author  	: QuHaiLong
	 * @date		: 2017年9月11日 上午10:51:43
	 * @param serviceName 服务号码
	 * @param sType 统计方式 年月日
	 * @return	
	 *
	 */
	List<Statistics> statisticsTbTA(@Param(value="serviceName") String serviceName ,@Param(value="channelName") String channelName, @Param(value="sType") String sType );
	/**
	 * 
	 * @description :  用户授权统计
	 * @author  	: QuHaiLong
	 * @date		: 2017年9月13日 上午10:08:22
	 * @return	
	 *
	 */
	List<Statistics> userAuthStatistics(@Param("channelId")String channelId);
	/**
	 * 
	 * @description :  用户贷款统计
	 * @author  	: QuHaiLong
	 * @date		: 2017年9月13日 上午10:11:49
	 * @param channelId 银行id
	 * @return	
	 *
	 */
	List<Statistics> userLoanStatistics(@Param("channelId")String channelId);

}
