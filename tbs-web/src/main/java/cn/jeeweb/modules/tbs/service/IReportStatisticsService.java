package cn.jeeweb.modules.tbs.service;

import java.util.List;

import cn.jeeweb.modules.tbs.entity.ServiceCallReport;
import cn.jeeweb.modules.tbs.entity.UserAuthReport;
import cn.jeeweb.modules.tbs.entity.UserLoanQueryReport;
import cn.jeeweb.modules.tbs.entity.UserLoanReport;

public interface IReportStatisticsService {
	
	List<UserAuthReport> queryUserAuthReportList(String channelName,String dateStr);
	List<UserLoanReport>  queryUserLoanReportList(String channelName,String dateStr);
	List<UserLoanQueryReport>  queryUserLoanQueryReportList(String channelName,String dateStr);
	Integer[]  queryServiceCallReportList(String channelName,String dateStr);

}
