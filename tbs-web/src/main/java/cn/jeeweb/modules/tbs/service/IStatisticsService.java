package cn.jeeweb.modules.tbs.service;

public interface IStatisticsService {

	/**
	 * 
	 * @description :  服务交易统计
	 * @author  	: QuHaiLong
	 * @date		: 2017年9月11日 上午11:54:00
	 * @param serviceName 服务代码
	 * @param sType 统计方式 天 小时
	 * @return	
	 *
	 */
	Integer[] statisticsByHourOrDay(String serviceName ,String channelName , String sType);
	/**
	 * 
	 * @description :  用户授权统计
	 * @author  	: QuHaiLong
	 * @date		: 2017年9月13日 上午10:13:02
	 * @return	
	 *
	 */
	Integer[] userAuthStatistics(String channelId);
	/**
	 * 
	 * @description :  用户贷款统计
	 * @author  	: QuHaiLong
	 * @date		: 2017年9月13日 上午10:13:46
	 * @param channelId 银行id
	 * @return	
	 *
	 */
	Integer[] userLoanStatistics(String channelId);

}
