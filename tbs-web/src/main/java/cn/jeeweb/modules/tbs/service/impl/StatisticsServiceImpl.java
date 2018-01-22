package cn.jeeweb.modules.tbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jeeweb.modules.tbs.entity.Statistics;
import cn.jeeweb.modules.tbs.mapper.StatisticsMapper;
import cn.jeeweb.modules.tbs.service.IStatisticsService;

@Service("statisticsService")
public class StatisticsServiceImpl implements IStatisticsService {
	@Autowired
	private StatisticsMapper statisticsMapper;

	@Override
	public Integer[] statisticsByHourOrDay(String serviceName,
			String channelName , String sType) {
		// TODO Auto-generated method stub
		List<Statistics> statisticsList = null;
		//System.out.println("()()("+sType+serviceName);
		if(sType.equals("hour")){
			Integer[] countArray = {0,0,0,0,0,0,
							   		0,0,0,0,0,0,
							   		0,0,0,0,0,0,
							   		0,0,0,0,0,0};
			statisticsList = statisticsMapper.statisticsTbTA(serviceName,channelName, sType);
			for(Statistics s : statisticsList ){
				if(s.getsHour()!=null){
					countArray[Integer.parseInt(s.getsHour())] = s.getCount();
				}
			
			}
			return countArray;
		}
		
		if(sType.equals("day")){
			 
			Integer[] countArray = {0,0,0,0,0,0,
							   		0,0,0,0,0,0,
							   		0,0,0,0,0,0,
							   		0,0,0,0,0,0,
							   		0,0,0,0,0,0,0};
			statisticsList = statisticsMapper.statisticsTbTA(serviceName, channelName, sType);
			for(Statistics s : statisticsList ){
			countArray[Integer.parseInt(s.getsDate().split(" ")[0].split("-")[2])-1] = s.getCount();
			//System.out.println(s.getsDate()+"()()()()"+Integer.parseInt(s.getsDate().split(" ")[0].split("-")[2])+"()()()()"+s.getCount());
			}
			return countArray;
		}
		return null;
	}

	@Override
	public Integer[] userAuthStatistics(String channelId) {
		// TODO Auto-generated method stub
		Integer[] countArray = {0,0,0,0,0,0,
						   		0,0,0,0,0,0,
						   		0,0,0,0,0,0,
						   		0,0,0,0,0,0,
						   		0,0,0,0,0,0,0};
		List<Statistics> statisticsList = statisticsMapper.userAuthStatistics(channelId);
		for(Statistics s : statisticsList ){
			countArray[Integer.parseInt(s.getsDate().split(" ")[0].split("-")[2])-1] = s.getCount();
			}
		return countArray;
	}

	@Override
	public Integer[] userLoanStatistics(String channelId) {
		// TODO Auto-generated method stub
		Integer[] countArray = {0,0,0,0,0,0,
						   		0,0,0,0,0,0,
						   		0,0,0,0,0,0,
						   		0,0,0,0,0,0,
						   		0,0,0,0,0,0,0};
		List<Statistics> statisticsList = statisticsMapper.userLoanStatistics(channelId);
		for(Statistics s : statisticsList ){
		countArray[Integer.parseInt(s.getsDate().split(" ")[0].split("-")[2])-1] = s.getCount();
		}
		return countArray;
	}



}
