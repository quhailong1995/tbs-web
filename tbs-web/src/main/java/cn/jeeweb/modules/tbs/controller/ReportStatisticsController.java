package cn.jeeweb.modules.tbs.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;

import cn.jeeweb.core.common.controller.BaseController;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.tbs.entity.UserAuthReport;
import cn.jeeweb.modules.tbs.entity.UserLoanQueryReport;
import cn.jeeweb.modules.tbs.entity.UserLoanReport;
import cn.jeeweb.modules.tbs.helper.FindDates;
import cn.jeeweb.modules.tbs.helper.JSUtil;
import cn.jeeweb.modules.tbs.service.IDmWhChannelService;
import cn.jeeweb.modules.tbs.service.IReportStatisticsService;


@Controller
@RequestMapping("${admin.url.prefix}/tbs/query/report")
public class ReportStatisticsController extends BaseController{
	@Autowired
	private IReportStatisticsService reportStatisticsService;
	@Autowired
	private IDmWhChannelService dmWhChannelService;
	
	
	@RequestMapping
	public String showReportStatisticsPage(){
		return display("reportstatistics");
		
	}
	
	
	@RequestMapping(value = "queryUserAuthReport", method = { RequestMethod.GET, RequestMethod.POST })
	public void queryUserAuthReport(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String channelId = request.getParameter("channelId");
		String dateStr = request.getParameter("dateStr");
		if(dateStr==null||dateStr.equals("")){
			dateStr =getLastMonth();
		}
		
		List<UserAuthReport> userAuthReporList =  reportStatisticsService.queryUserAuthReportList(channelId, dateStr);
		String content = JSON.toJSONString(userAuthReporList);
		StringUtils.printJson(response, content);
	}
	
	@RequestMapping(value = "queryUserLoanReport", method = { RequestMethod.GET, RequestMethod.POST })
	public void queryUserLoanReport(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String channelId = request.getParameter("channelId");
		String dateStr = request.getParameter("dateStr");
		if(dateStr==null||dateStr.equals("")){
			dateStr =getLastMonth();
		}
		
		List<UserLoanReport> userLoanReportList =  reportStatisticsService.queryUserLoanReportList(channelId, dateStr);
		String content = JSON.toJSONString(userLoanReportList);
		StringUtils.printJson(response, content);
	}
	
	@RequestMapping(value = " queryUserLoanQueryReport", method = { RequestMethod.GET, RequestMethod.POST })
	public void  queryUserLoanQueryReport(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String channelId = request.getParameter("channelId");
		String dateStr = request.getParameter("dateStr");
		if(dateStr==null||dateStr.equals("")){
			dateStr =getLastMonth();
		}
		List<UserLoanQueryReport> userLoanQueryReportList =  reportStatisticsService.queryUserLoanQueryReportList(channelId, dateStr);
		String tempcontent = JSON.toJSONString(userLoanQueryReportList);
		//String content = tempcontent.substring(0, tempcontent.length()-2)+",\"total\":"+userLoanQueryReportList.size()+"}]";
		StringUtils.printJson(response, tempcontent);
	}
	
	@RequestMapping(value = "queryServiceCallReport/{channelName}/{dateStr}", method = { RequestMethod.GET, RequestMethod.POST })
	public void queryServiceCallReport(@PathVariable("channelName")String channelName, @PathVariable("dateStr")String dateStr ,HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		Integer[] countArray =  reportStatisticsService.queryServiceCallReportList(channelName, dateStr);
		String content = JSON.toJSONString(countArray);
		StringUtils.printJson(response, content);
	}
	
	@RequestMapping("getAllDays/{dateStr}")
	public void getAllDays(HttpServletResponse response,@PathVariable("dateStr")String dateStr) throws IOException{
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		Date  d= null;
		try {
			d  = sf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Calendar c = Calendar.getInstance();
		//过去一月
        c.setTime(d);
        c.add(Calendar.DAY_OF_MONTH, 0);
        Date begintM = c.getTime();//月第一天
        c.add(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH)-1);
        Date endM = c.getTime();//最后一天
        List<String> lastMonth = FindDates.findDates(begintM, endM);
       // lastMonth.remove(0);
        //System.out.println(lastMonth+"()()("+currentM);
        response.getWriter().print(JSON.toJSONString(lastMonth));
	}
	
	
	
	public String getLastMonth(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		Date d = new Date();
		Calendar c = Calendar.getInstance();
		//过去一月
        c.setTime(d);
        c.add(Calendar.MONTH, -1);
        Date  last = c.getTime();
        
        return sf.format(last);
	}
	
	
	
	@RequestMapping(value = "exportpdfexample", method = { RequestMethod.GET, RequestMethod.POST })
	public String exportpdfexample(Model model,HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		String channelId = request.getParameter("channelId");
		String dateStr = request.getParameter("dateStr");
		String channelComment = request.getParameter("channelComment");
		String statisticsDate = "";
		if(dateStr!=null){
			statisticsDate = dateStr.replace("-", "年");
		}
		String operateDate = request.getParameter("operateDate");
		
		model.addAttribute("channelId", channelId);
		model.addAttribute("dateStr", dateStr);
		model.addAttribute("channelComment", channelComment);
		model.addAttribute("statisticsDate", statisticsDate);
		model.addAttribute("operateDate", operateDate);
		
		
		return display("exportpdfexample");
		
	}
	
	@RequestMapping(value = "phantomjsExport", method = { RequestMethod.GET, RequestMethod.POST })
	public void phantomjsExport(HttpServletRequest request,HttpServletResponse res) throws IOException, SAXException{
		
		String channelId = request.getParameter("channelId");
		String dateStr = request.getParameter("dateStr");
		String channelComment = URLEncoder.encode(request.getParameter("channelComment"), "utf-8");
		String statisticsDate =URLEncoder.encode(dateStr.replace("-", "年"), "utf-8"); 
		String operateDate = URLEncoder.encode(  request.getParameter("operateDate"), "utf-8");
		
		String dataStr = "channelId="+channelId+"&"+"dateStr="+dateStr+"&"+"channelComment="+channelComment
				+"&"+"statisticsDate="+statisticsDate+"&"+"operateDate="+operateDate;
		
		
		JSUtil.execute(request,res,dataStr);
		//res.getWriter().print(content);
		
	}
	

}
