package cn.jeeweb.modules.tbs.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CronExpressionUtil {
	
	/**
	 * 验证cron表达式是不是具体时间
	 * @param cronExpression
	 * @return
	 */
	public static boolean isJTSJ(String cronExpression){
		
		String[] segCronExpression = cronExpression.split("\\s+");
		
		for(String c :segCronExpression){
			if(c.equals("*")){
				return false;
			}
		}
		if(!segCronExpression[segCronExpression.length-2].equals("?")){
			return false;
		}
		return true;
		
	}
	
	
	/**
	 * 判断是否过期
	 * @return
	 */
	public static boolean isExpire(String cronExpression){
		if(isJTSJ(cronExpression)){
			String[] segCronExpression = cronExpression.split("\\s+");
			String dateStr = "";
			for(int i=segCronExpression.length-1 ;i>=0;i--){
				if(!segCronExpression[i].equals("?")){
					dateStr = dateStr+segCronExpression[i]+":";
				}
			}
			
			Date taskDate = null;
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");  
			dateStr = dateStr.substring(0, dateStr.length()-1);
			try {
				taskDate = sdf.parse(dateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(now.after(taskDate)){
				System.out.println("======");
				return true;
			}
			
		}
		return false;
		
	}
	
	
	public static String parseToCroExpression(String date){
		if(date==null){return null;}
		
		String[] dates = date.split("\\s+")[0].split("-");
		String[] times = date.split("\\s+")[1].split(":");
		
		String croExpression = "";
		for(int i=times.length-1;i>=0;i--){
			croExpression = croExpression+times[i]+" ";
		}
		for(int i=dates.length-1;i>=0;i--){
			if(i==1){
				croExpression = croExpression+dates[i]+" "+"?"+" ";
				continue;
			}
			croExpression = croExpression+dates[i]+" ";
		}
		
		return croExpression;
		
	}
	

	public static String parseToDate(String cronExpression){
		if(cronExpression==null){return null;}
		String[] segCronExpression = cronExpression.split("\\s+");
		String dateStr = "";
		for(int i=segCronExpression.length-1 ;i>=0;i--){
			if(!segCronExpression[i].equals("?")){
				dateStr = dateStr+segCronExpression[i]+":";
			}
			if(i==3){
				dateStr = dateStr+" ";
			}
		}
		
		String[] datetime = dateStr.substring(0, dateStr.length()-1).split("\\s+");
		
		return datetime[0].replaceAll(":", "-").substring(0, datetime[0].length()-1)+" "+datetime[1];
	}
	

}
