package cn.jeeweb.modules.tbs.entity;

import java.util.List;

public class ReportStatistics {
	
	private List<UserAuthReport> userAuthReportList;
	private List<UserLoanReport> userLoanReportList;
	private List<UserLoanQueryReport> userLoanQueryReportList;
	private List<ServiceCallReport> serviceCallReportList;
	public List<UserAuthReport> getUserAuthReportList() {
		return userAuthReportList;
	}
	public void setUserAuthReportList(List<UserAuthReport> userAuthReportList) {
		this.userAuthReportList = userAuthReportList;
	}
	public List<UserLoanReport> getUserLoanReportList() {
		return userLoanReportList;
	}
	public void setUserLoanReportList(List<UserLoanReport> userLoanReportList) {
		this.userLoanReportList = userLoanReportList;
	}
	public List<UserLoanQueryReport> getUserLoanQueryReportList() {
		return userLoanQueryReportList;
	}
	public void setUserLoanQueryReportList(
			List<UserLoanQueryReport> userLoanQueryReportList) {
		this.userLoanQueryReportList = userLoanQueryReportList;
	}
	public List<ServiceCallReport> getServiceCallReportList() {
		return serviceCallReportList;
	}
	public void setServiceCallReportList(
			List<ServiceCallReport> serviceCallReportList) {
		this.serviceCallReportList = serviceCallReportList;
	}
	
	
	
 
}
