package cn.jeeweb.modules.tbs.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.modules.tbs.entity.DmWhChannel;
import cn.jeeweb.modules.tbs.entity.DmWhProduct;
import cn.jeeweb.modules.tbs.entity.ServiceCallReport;
import cn.jeeweb.modules.tbs.entity.UserAuthReport;
import cn.jeeweb.modules.tbs.entity.UserLoanQueryReport;
import cn.jeeweb.modules.tbs.entity.UserLoanReport;
import cn.jeeweb.modules.tbs.mapper.ReportStatisticsMapper;
import cn.jeeweb.modules.tbs.service.IDmWhChannelService;
import cn.jeeweb.modules.tbs.service.IDmWhProductService;
import cn.jeeweb.modules.tbs.service.IReportStatisticsService;


@Service("reportStatisticsService")
public class ReportStatisticsServiceImpl implements IReportStatisticsService {
	
	@Autowired
	private IDmWhProductService dmWhProductService;
	@Autowired
	private ReportStatisticsMapper reportStatisticsMapper;
	@Autowired
	private IDmWhChannelService dmWhChannelService;

	@Override
	public List<UserAuthReport> queryUserAuthReportList(String channelName,
			String dateStr) {
		// TODO Auto-generated method stub
		List<DmWhProduct> dmWhProductList=null;
		List<UserAuthReport>  userAuthReportList = new ArrayList<UserAuthReport>();
		
		if(channelName==null||channelName.equals("null")||channelName.equals("")){
			dmWhProductList = dmWhProductService.selectList(new EntityWrapper<DmWhProduct>());
		}else{
			dmWhProductList =  dmWhProductService.selectList(new EntityWrapper<DmWhProduct>().eq("CHANNEL_ID", channelName));
		}
		
		
		Integer totalAuthCount = 0;
		Integer totalCancelAuthCount = 0;
		Integer totalExpireAuthCount = 0;
		
		
		
		for(DmWhProduct d:dmWhProductList){
			UserAuthReport userAuthReport = new UserAuthReport();
			Integer authExpireC = reportStatisticsMapper.userAuthLsReportCount(d.getId(), dateStr);
			userAuthReport.setDmWhProduct(d);
			
			Integer authCount = reportStatisticsMapper.userAuthReportCount(d.getId(), dateStr)+authExpireC;
			userAuthReport.setAuthCount(authCount);
			totalAuthCount = totalAuthCount + authCount;
			
			
			Integer cancelAuthCount = reportStatisticsMapper.userRelieveAuthReportCount(d.getId(), dateStr);
			userAuthReport.setCancelAuthCount(cancelAuthCount);
			totalCancelAuthCount = totalCancelAuthCount + cancelAuthCount;
			
			
			userAuthReport.setExpireAuthCount(authExpireC);
			totalExpireAuthCount  = totalExpireAuthCount +authExpireC;
			
			
			
			Integer preMauthExpireC = reportStatisticsMapper.userAuthLsReportCount(d.getId(), getLastMonth(dateStr));
			Integer preMauthCount = reportStatisticsMapper.userAuthReportCount(d.getId(), getLastMonth(dateStr))+preMauthExpireC;
			if(preMauthCount==0){
				userAuthReport.setRingGrowth("");
			}else{
				try{
					userAuthReport.setRingGrowth(toPercentStr((authCount-preMauthCount)/(float)preMauthCount));
					
				}catch(ArithmeticException e){
					userAuthReport.setRingGrowth("");
				}
			}
			
			
			
			
			
			userAuthReportList.add(userAuthReport);
		}
		
		UserAuthReport lastUserAuthReport = new UserAuthReport();
		DmWhProduct heji = new DmWhProduct();
		heji.setProductComment("合计");
		lastUserAuthReport.setDmWhProduct(heji);
		lastUserAuthReport.setAuthCount(totalAuthCount);
		lastUserAuthReport.setCancelAuthCount(totalCancelAuthCount);
		lastUserAuthReport.setExpireAuthCount(totalExpireAuthCount);
		lastUserAuthReport.setRingGrowth("");
		userAuthReportList.add(lastUserAuthReport);
		
		return userAuthReportList;
	}

	@Override
	public List<UserLoanReport> queryUserLoanReportList(String channelName,
			String dateStr) {
		// TODO Auto-generated method stub
		List<DmWhProduct> dmWhProductList=null;
		List<UserLoanReport> userLoanReportList = new ArrayList<>();
		if(channelName==null||channelName.equals("null")||channelName.equals("")){
			dmWhProductList = dmWhProductService.selectList(new EntityWrapper<DmWhProduct>());
		}else{
			dmWhProductList =  dmWhProductService.selectList(new EntityWrapper<DmWhProduct>().eq("CHANNEL_ID", channelName));
		}
		
		
		Integer totalLoanExpire = 0;
		Integer totalLoanAdvanceRepay = 0;
		Integer totalLoanLoanCount = 0;
		Double totalLoanLoanTotalPrice = 0.0;
		
		for(DmWhProduct d:dmWhProductList){
			UserLoanReport userLoanReport = reportStatisticsMapper.userLoanReportCount(d.getId(), dateStr);
			//if(userLoanReport==null) continue;
			userLoanReport.setDmWhProduct(d);
			userLoanReport.setLoanExpire(reportStatisticsMapper.userLoanExpireReportCount(d.getId(), dateStr));
			userLoanReport.setLoanAdvanceRepay(reportStatisticsMapper.userLoanAdvanceRepayCount(d.getId(), dateStr));
			userLoanReportList.add(userLoanReport);
			
			totalLoanLoanCount = totalLoanLoanCount +userLoanReport.getLoanCount();
			totalLoanLoanTotalPrice = totalLoanLoanTotalPrice + userLoanReport.getLoanTotalPrice();
			totalLoanExpire = totalLoanExpire + userLoanReport.getLoanExpire();
			totalLoanAdvanceRepay = totalLoanAdvanceRepay + userLoanReport.getLoanAdvanceRepay();
		}
		
		UserLoanReport lastUserLoanReport = new UserLoanReport();
		DmWhProduct heji = new DmWhProduct();
		heji.setProductComment("合计");
		lastUserLoanReport.setDmWhProduct(heji);
		lastUserLoanReport.setLoanCount(totalLoanLoanCount);
		lastUserLoanReport.setLoanTotalPrice(totalLoanLoanTotalPrice);
		lastUserLoanReport.setLoanExpire(totalLoanExpire);
		lastUserLoanReport.setLoanAdvanceRepay(totalLoanAdvanceRepay);
		userLoanReportList.add(lastUserLoanReport);
		
		
		return userLoanReportList;
	}

	@Override
	public List<UserLoanQueryReport> queryUserLoanQueryReportList(
			String channelName, String dateStr) {
		// TODO Auto-generated method stub
		List<UserLoanQueryReport> userLoanQueryReportList = new ArrayList<UserLoanQueryReport>();
		
		
		
		Integer totalQueryCount_BTS003 = 0;
		Integer totalQueryCount_BTS004 = 0;
		
		if(channelName==null||channelName.equals("null")||channelName.equals("")){
			List<DmWhChannel> dmWhChannelList = dmWhChannelService.selectList(new EntityWrapper<DmWhChannel>());
			for(DmWhChannel d : dmWhChannelList){
				UserLoanQueryReport userLoanQueryReport = new UserLoanQueryReport();
				userLoanQueryReport.setDmWhChannel(d);
				userLoanQueryReport.setQueryCount_BTS003(reportStatisticsMapper.userLoanQueryReportCount(d.getChannelName(), "BTS003",dateStr));
				userLoanQueryReport.setQueryCount_BTS004(reportStatisticsMapper.userLoanQueryReportCount(d.getChannelName(), "BTS004",dateStr));
				
				
				Integer last003 = reportStatisticsMapper.userLoanQueryReportCount(d.getChannelName(), "BTS003",getLastMonth(dateStr));
				if(last003==0){
					userLoanQueryReport.setRingGrowth_BTS003("");
				}else{
					try{
						userLoanQueryReport.setRingGrowth_BTS003(toPercentStr((userLoanQueryReport.getQueryCount_BTS003()-last003)/(float)last003));
						
					}catch(ArithmeticException e){
						userLoanQueryReport.setRingGrowth_BTS003("");
					}
				}
				
				Integer last004 = reportStatisticsMapper.userLoanQueryReportCount(d.getChannelName(), "BTS004",getLastMonth(dateStr));
				if(last004==0){
					userLoanQueryReport.setRingGrowth_BTS004("");
				}else{
					try{
						userLoanQueryReport.setRingGrowth_BTS004(toPercentStr((userLoanQueryReport.getQueryCount_BTS004()-last004)/(float)last004));
						
					}catch(ArithmeticException e){
						userLoanQueryReport.setRingGrowth_BTS004("");
					}
				}
				
				
				
				
				totalQueryCount_BTS003 =totalQueryCount_BTS003 + userLoanQueryReport.getQueryCount_BTS003();
				totalQueryCount_BTS004 = totalQueryCount_BTS004 + userLoanQueryReport.getQueryCount_BTS004();
				
				userLoanQueryReportList.add(userLoanQueryReport);
			}
			
			UserLoanQueryReport lastUserLoanQueryReport = new UserLoanQueryReport();
			DmWhChannel heji = new DmWhChannel();
			heji.setChannelComment("合计");
			lastUserLoanQueryReport.setDmWhChannel(heji);
			lastUserLoanQueryReport.setQueryCount_BTS003(totalQueryCount_BTS003);
			lastUserLoanQueryReport.setQueryCount_BTS004(totalQueryCount_BTS004);
			lastUserLoanQueryReport.setRingGrowth_BTS003("");
			lastUserLoanQueryReport.setRingGrowth_BTS004("");
			userLoanQueryReportList.add(lastUserLoanQueryReport);
			
			
			
			
			return userLoanQueryReportList;
		}
		
		
		
		
		DmWhChannel dmWhChannel = dmWhChannelService.selectOne(new EntityWrapper<DmWhChannel>().eq("id", channelName));
		UserLoanQueryReport userLoanQueryReport = new UserLoanQueryReport();
		userLoanQueryReport.setDmWhChannel(dmWhChannel);
		userLoanQueryReport.setQueryCount_BTS003(reportStatisticsMapper.userLoanQueryReportCount(channelName, "BTS003",dateStr));
		userLoanQueryReport.setQueryCount_BTS004(reportStatisticsMapper.userLoanQueryReportCount(channelName, "BTS003",dateStr));
		
		Integer last003 = reportStatisticsMapper.userLoanQueryReportCount(channelName, "BTS003",getLastMonth(dateStr));
		if(last003==0){
			userLoanQueryReport.setRingGrowth_BTS003("");
		}else{
			try{
				userLoanQueryReport.setRingGrowth_BTS003(toPercentStr((userLoanQueryReport.getQueryCount_BTS003()-last003)/(float)last003));
				
			}catch(ArithmeticException e){
				userLoanQueryReport.setRingGrowth_BTS003("");
			}
		}
		
		Integer last004 = reportStatisticsMapper.userLoanQueryReportCount(channelName, "BTS004",getLastMonth(dateStr));
		if(last004==0){
			userLoanQueryReport.setRingGrowth_BTS004("");
		}else{
			try{
				userLoanQueryReport.setRingGrowth_BTS004(toPercentStr((userLoanQueryReport.getQueryCount_BTS004()-last004)/(float)last004));
				
			}catch(ArithmeticException e){
				userLoanQueryReport.setRingGrowth_BTS004("");
			}
		}
		
		
		userLoanQueryReportList.add(userLoanQueryReport);
		
		UserLoanQueryReport lastUserLoanQueryReport = new UserLoanQueryReport();
		DmWhChannel heji = new DmWhChannel();
		heji.setChannelComment("合计");
		lastUserLoanQueryReport.setDmWhChannel(heji);
		lastUserLoanQueryReport.setQueryCount_BTS003(userLoanQueryReport.getQueryCount_BTS003());
		lastUserLoanQueryReport.setQueryCount_BTS004(userLoanQueryReport.getQueryCount_BTS004());
		lastUserLoanQueryReport.setRingGrowth_BTS003("");
		lastUserLoanQueryReport.setRingGrowth_BTS004("");
		userLoanQueryReportList.add(lastUserLoanQueryReport);
		
		
		
		return userLoanQueryReportList;
	}

	@Override
	public Integer[] queryServiceCallReportList(
			String channelName, String dateStr) {
		// TODO Auto-generated method stub
		Integer[] countArray = {0,0,0,0,0,0,
		   		0,0,0,0,0,0,
		   		0,0,0,0,0,0,
		   		0,0,0,0,0,0,
		   		0,0,0,0,0,0,0};
		DmWhChannel dmWhChannel = dmWhChannelService.selectById(channelName);
		if(dmWhChannel!=null){
			channelName = dmWhChannel.getChannelName();
		}
		List<ServiceCallReport> serviceCallReportList = reportStatisticsMapper.serviceCallReportList(channelName, dateStr);
		for(ServiceCallReport s : serviceCallReportList ){
			countArray[Integer.parseInt(s.getsDate().split(" ")[0].split("-")[2])-1] = s.getCount();
		}
		
	
		return countArray;
	}

	public String getLastMonth(String dateStr){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		
		Date d = null;
		try {
			d = sf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		//过去一月
        c.setTime(d);
        c.add(Calendar.MONTH, -1);
        Date  last = c.getTime();
        
        return sf.format(last);
	}
	
	public String toPercentStr(float f){
		f = f*100;
		DecimalFormat   fnum  =   new  DecimalFormat("##0.00");    
		String   dd=fnum.format(f);   
		if(f>0){
			return "+"+dd+"%";
		}
		return dd+"%";
		
	}

}
