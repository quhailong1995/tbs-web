package cn.jeeweb.modules.tbs.controller;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

import cn.jeeweb.core.common.controller.BaseController;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.modules.tbs.entity.DmWhChannel;
import cn.jeeweb.modules.tbs.helper.FindDates;
import cn.jeeweb.modules.tbs.service.IDmWhChannelService;
import cn.jeeweb.modules.tbs.service.IStatisticsService;


/**   
 * @Title: 
 * @Description: 图表统计控制层
 * @author QuHaiLong
 * @date 2017-08-31 15:53:25
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/tbs/statistics")
public class TbsStatisticsController extends BaseController{

	@Autowired
	private IStatisticsService statisticsService;
	@Autowired
	private IDmWhChannelService dmWhChannelService;
	

	
	@RequestMapping(value="getTradeByService/{serviceName}/{channelName}/{sType}")
	public void getTradeByService(@PathVariable(value="serviceName")String serviceName ,@PathVariable(value="channelName")String channelName, @PathVariable(value="sType")String sType , HttpServletResponse response) throws IOException{
		
		//System.out.println(serviceName+"*()("+sType+"()()()()"+sType);
		Integer[] countArray= statisticsService.statisticsByHourOrDay(serviceName,channelName, sType);
		
		response.getWriter().print(JSON.toJSONString(countArray));
	}
	
	@RequestMapping(value="getUserLoanStatistics/{channelId}")
	public void getUserLoanCount(@PathVariable("channelId")String channelId , HttpServletResponse response) throws IOException{
		Integer[] countArray= statisticsService.userLoanStatistics(channelId);
//		Integer[] countArray = {555,557,0,0,0,0,
//		   		787,878,0,0,0,666,
//		   		777,444,0,555,0,0,
//		   		0,0,0,0,0,0,
//		   		0,0,0,0,0,0,0};
		response.getWriter().print(JSON.toJSONString(countArray));
	}
	
	
	@RequestMapping(value="getOneUserLoanStatistics/{channelName}")
	public void getOneUserLoanCount(@PathVariable("channelName")String channelName , HttpServletResponse response) throws IOException{
		
		DmWhChannel dmWhChannel = dmWhChannelService.selectOne(new EntityWrapper<DmWhChannel>().eq("channel_name", channelName));
		String channelId;
		if(dmWhChannel!=null){
			channelId = dmWhChannel.getId();
		}else{
			channelId = "";
		}
		
		
		Integer[] countArray= statisticsService.userLoanStatistics(channelId);

		response.getWriter().print(JSON.toJSONString(countArray));
	}
	
	
	
	@RequestMapping(value="getAuthUserStatistics")
	public void getAuthUserCount(HttpServletResponse response,@RequestParam(value = "channelName", required = false)String channelName ) throws IOException{
		
		DmWhChannel dmWhChannel = dmWhChannelService.selectOne(new EntityWrapper<DmWhChannel>().eq("channel_name", channelName));
		String channelId;
		if(dmWhChannel!=null){
			channelId = dmWhChannel.getId();
		}else{
			channelId = "";
		}
		
		Integer[] countArray= statisticsService.userAuthStatistics(channelId);
		response.getWriter().print(JSON.toJSONString(countArray));
	}
	
	@RequestMapping("getPreMonth")
	public void getPreMonth(HttpServletResponse response) throws IOException{
		  Calendar c = Calendar.getInstance();
		//过去一月
	        c.setTime(new Date());
	        c.add(Calendar.DAY_OF_MONTH, -1);
	        Date endM = c.getTime();//前一天
	        c.add(Calendar.MONTH, -1);
	        Date begintM = c.getTime();//前一个月
	        List<String> lastMonth = FindDates.findDates(begintM, endM);
	        lastMonth.remove(0);
	        //System.out.println(lastMonth+"()()("+currentM);
	        response.getWriter().print(JSON.toJSONString(lastMonth));
	        
	}
	@RequestMapping("getPreHalfMonth")
	public void getPreHalfMonth(HttpServletResponse response) throws IOException{
		  Calendar c = Calendar.getInstance();
			//过去半月
		        c.setTime(new Date());
		        c.add(Calendar.DAY_OF_MONTH, -1);
		        Date endM = c.getTime();//前一天
		        c.add(Calendar.DAY_OF_MONTH, -14);
		        Date begintM = c.getTime();//前14天
		        List<String> lastMonth = FindDates.findDates(begintM, endM);
		        //System.out.println(lastMonth+"()()("+currentM);
		        response.getWriter().print(JSON.toJSONString(lastMonth));
	}
	@RequestMapping("getPreDayTime")
	public void getPreDayTime(HttpServletResponse response) throws IOException{
		  Calendar c = Calendar.getInstance();
			//昨天
		        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss"); 
		        c.setTime(new Date());
		        Date endM = c.getTime();//此时此刻
		        c.add(Calendar.DATE, -1);//
		        c.add(Calendar.HOUR_OF_DAY, 1);
		        Date begintM = c.getTime();//昨天
		        List<String> times = FindDates.findTimes(begintM, endM);
		        //times.remove(times.size()-1);
		        times.add(sdf.format(endM));
		        //System.out.println(lastMonth+"()()("+currentM);
		        response.getWriter().print(JSON.toJSONString(times));
	}

}
