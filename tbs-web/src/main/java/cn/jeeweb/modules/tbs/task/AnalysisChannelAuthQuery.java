package cn.jeeweb.modules.tbs.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.jeeweb.core.common.service.ICommonBaseDynamicDBService;
import cn.jeeweb.core.utils.SpringContextHolder;
import cn.jeeweb.modules.task.utils.MonitorTriggerListener;
import cn.jeeweb.modules.tbs.helper.StaticConfig;
import cn.jeeweb.modules.tbs.helper.XmlHelper;

public class AnalysisChannelAuthQuery {
	
	
	private ICommonBaseDynamicDBService<Map<String,Object>> commonBaseDynamicDBService  = SpringContextHolder.getBean("commonBaseDynamicDBService");
	
	public AnalysisChannelAuthQuery(){
		//System.out.println("********************************"+commonBaseDynamicDBService);
	}
	
	
	public List<Map<String,Object>> channelAuthQuery(){
		List<Map<String,Object>>  channelAuthQueryList = null;
		channelAuthQueryList =  commonBaseDynamicDBService.searchList(StaticConfig.DEFAULTDATASOURCE,"AnalysisChannelAuthQueryMapper.channelAuthQuery", null);
		
		return channelAuthQueryList;
	}

	public String queryMessageBlob(String send_seq){
		String messageBlob = null;
		messageBlob = (String) commonBaseDynamicDBService.searchList(StaticConfig.DEFAULTDATASOURCE,
				"AnalysisChannelAuthQueryMapper.queryMessageBlob", send_seq).get(0).get("RET_DATA");
		
		//System.out.println("********************************"+messageBlob);
		return messageBlob;
	}
	
	

	
	/**
	 * Description: 解析报文
	 * @author QuHaialong
	 * @date  2017年12月26日 下午2:50:35
	 */
	public Map<String,Object> analysisXMLMsg(String messageBlob ){
		Map<String,Object> returnMsg = new HashMap<String, Object>();
		
		
		Map<String,String> m = new HashMap<String, String>();//放不重复的数据
		
		Document doc = XmlHelper.parseXml(messageBlob);
		Element root = doc.getRootElement();
		Element nsmxlist = root.element("nsmxlist");
		//Element  = nsmxlist.element("");
		
		List<?> nsmxlistitems = nsmxlist.elements("nsmxlistitem");
		for (Iterator<?> it = nsmxlistitems.iterator(); it.hasNext();) {
		Element e = (Element) it.next();
		String key = XmlHelper.getChildNode(e, "tax_date").getText().trim();
		if(!m.containsKey(key)){
			m.put(key, XmlHelper.getChildNode(e, "pretax_income").getText().trim());
		}
		}
		
		
		Integer total_month = 0;
		Double max_income = 0.0;
		if(m!=null){
			total_month = m.size();

			Iterator<Map.Entry<String, String>> entries = m.entrySet().iterator();  
			  
			while (entries.hasNext()) { 
				
			    Entry<String, String> entry = entries.next();
			    if(max_income<Double.parseDouble(entry.getValue())){
			    	 max_income = Double.parseDouble(entry.getValue());
			    }
			}
		}
		
		
		List<String> dateStr = new ArrayList<String>();
		 dateStr.addAll(m.keySet());
		
		
		returnMsg.put("TOTAL_MONTH", total_month);
		returnMsg.put("MAX_SALARY", max_income);
		String is_zd = monthIs_ZD(dateStr)?"1":"0";
		returnMsg.put("IS_ZD", is_zd);
		returnMsg.put("IS_SX", is_zd.equals("0")&&total_month>=24?"1":"2");
		
		
		
		return returnMsg;
		
	}
	
	
	/**
	 * Description: 是否中断
	 * @author QuHaialong
	 * @date  2017年12月26日 下午4:49:16
	 */
	public boolean monthIs_ZD(List<String> dateList){

//		dateList = new ArrayList<String>();
//		dateList.add("201702");
//		dateList.add("201701");
//		dateList.add("201710");
//		dateList.add("201712");
//		dateList.add("201602");
		
		
		final SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		
		Collections.sort(dateList, new Comparator<String>() {  
            @Override  
            public int compare(String o1, String o2) {  
                  
                try {  
                    Date dt1 = format.parse(o1);  
                    Date dt2 = format.parse(o2);  
                    if (dt1.getTime() > dt2.getTime()) {  
                        return 1;  
                    } else if (dt1.getTime() < dt2.getTime()) {  
                        return -1;  
                    } else {  
                        return 0;  
                    }  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                return 0;  
            }  
        }); 
		
		if(dateList.size()>1){
			 Calendar calendar   =   new  GregorianCalendar();
			
		 try {
			 Date first = format.parse(dateList.get(0));
			 Date last = format.parse(dateList.get(dateList.size()-1));
			 
			 calendar.setTime(first);
			 
			 long s ;
			 for( s= first.getTime();s<=last.getTime(); s = calendar.getTime().getTime()){
				 calendar.add(Calendar.MONTH, 1);
				 String te = format.format(calendar.getTime());
				 if(!dateList.contains(te)){
						return true;
					}

			 }

			} catch (ParseException e) {
				e.printStackTrace();
			}
			 
		}
		
		
		
		
		
		
		return false;
		
	}
	
	
	
	
	
	/**
	 * Description: 保存数据库
	 * @author QuHaialong
	 * @date  2017年12月26日 下午5:28:03
	 */
	public Integer saveMessageRecord(Map<String,Object> map){
		
		return commonBaseDynamicDBService.insert(StaticConfig.DEFAULTDATASOURCE, "AnalysisChannelAuthQueryMapper.saveMessageRecord", map);
		
	}
	
	
	
	public void executeTask(){
		
		 String resultCode  = "2";
			String resultMessage = "执行成功！#########";
			
			Integer i = 0;
			
			Map<String,Object> saveMsg = new HashMap<String, Object>();
			try{
				List<Map<String,Object>> list = channelAuthQuery();
				for(Map<String,Object> l : list){
					String blobStr = queryMessageBlob((String)l.get("SEND_SEQ"));
					Map<String,Object> anaMsg = analysisXMLMsg(blobStr);
					saveMsg.putAll(anaMsg);
					saveMsg.put("XH", l.get("XH"));
					saveMsg.put("SEND_SEQ", l.get("SEND_SEQ"));
					saveMsg.put("USERID", l.get("USERID"));
					saveMsg.put("PRODUCT_ID", l.get("PRODUCT_ID"));
					saveMsg.put("CHANNEL_NAME", l.get("CHANNEL_NAME"));
					saveMsg.put("LRRQ", l.get("LRRQ"));
					
					i = i+ saveMessageRecord(saveMsg);
					
				}
				
				resultMessage = resultMessage + "成功加工" + i + "条数据";
//				String aa = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
//							+"<tbl>"
//							+"<nsmxlist>"
//							    +"<nsmxlistitem>"
//							      +"<tax_date>201705</tax_date>"
//							      +"<full_name>张坚芳</full_name>"
//							      +"<card_no>330203194611011513</card_no>"
//							      +"<pretax_income>9827</pretax_income>"
//							      +"<withholding_agent>张三</withholding_agent>"
//							      +"<mobile>1111111111111</mobile>"
//							      +"<withholding_agent_address></withholding_agent_address>"
//							      +"<card_type>201</card_type>"
//							      +"<actual_tax_amount>283.24</actual_tax_amount>"
//							      +"<social_security>1140.6</social_security>"
//							      +"<payable_tax_amount>283.24</payable_tax_amount>"
//							      +"<housing_fund>1304</housing_fund>"
//							    +"</nsmxlistitem>"
//							    +"<nsmxlistitem>"
//							      +"<tax_date>201705</tax_date>"
//							      +"<full_name>张坚芳</full_name>"
//							      +"<card_no>330203194611011513</card_no>"
//							      +"<pretax_income>9827</pretax_income>"
//							      +"<withholding_agent>张三</withholding_agent>"
//							      +"<mobile></mobile>"
//							      +"<withholding_agent_address></withholding_agent_address>"
//							      +"<card_type>201</card_type>"
//							      +"<actual_tax_amount>283.24</actual_tax_amount>"
//							      +"<social_security>1140.6</social_security>"
//							      +"<payable_tax_amount>283.24</payable_tax_amount>"
//							      +"<housing_fund>1304</housing_fund>"
//							    +"</nsmxlistitem>"
//							  +"</nsmxlist>"
//							+"</tbl>";
				//analysisXMLMsg(aa);
				
			}catch(Exception e){
				e.printStackTrace();
				resultCode = "3";
				resultMessage = "执行失败！#######"+e.getMessage();
			}
			
			
			MonitorTriggerListener.returnResultMessage(resultCode, resultMessage);
	}
	
}
