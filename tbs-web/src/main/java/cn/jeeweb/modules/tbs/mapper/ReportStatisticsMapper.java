package cn.jeeweb.modules.tbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jeeweb.modules.tbs.entity.ServiceCallReport;
import cn.jeeweb.modules.tbs.entity.UserLoanReport;



public interface ReportStatisticsMapper {
	
	Integer  userAuthReportCount(@Param("productId")String productId,@Param("dateStr")String dateStr);
	Integer userRelieveAuthReportCount(@Param("productId")String productId,@Param("dateStr")String dateStr);
	Integer userAuthLsReportCount(@Param("productId")String productId,@Param("dateStr")String dateStr);
	
	
	
	UserLoanReport userLoanReportCount(@Param("productId")String productId,@Param("dateStr")String dateStr);
	Integer userLoanExpireReportCount(@Param("productId")String productId,@Param("dateStr")String dateStr);
	Integer userLoanAdvanceRepayCount(@Param("productId")String productId,@Param("dateStr")String dateStr);
	
	
	
	Integer  userLoanQueryReportCount(@Param("channelName")String channelName,@Param("serviceName")String serviceName,@Param("dateStr")String dateStr);
	
	List<ServiceCallReport>  serviceCallReportList(@Param("channelName")String channelName,@Param("dateStr")String dateStr);

}
