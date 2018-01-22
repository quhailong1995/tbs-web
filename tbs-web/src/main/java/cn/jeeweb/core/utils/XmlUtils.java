/**
 * Project Name:pay-protocol
 * File Name:Xml.java
 * Package Name:cn.swiftpass.pay.protocol
 * Date:2014-8-10下午10:48:21
 *
*/

package cn.jeeweb.core.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * ClassName:Xml
 * Function: XML的工具方法
 * Date:     2017-9-26 09:37:21
 * @author    
 */
public class XmlUtils {   
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Date date = new Date();
	
	/**
	 * 获得整个message对应的xml字符串
	 * @param json
	 * @return
	 */
	public static String getMessageXml(JSONObject json){
		//确定本次授权的时间
		date = new Date();
		StringBuffer buffer = new StringBuffer();
		buffer.append("<![CDATA[<message>");
		buffer.append(getHeadXml());
		buffer.append(getBodyXml(json));
		buffer.append("</message>]]>");
		
		return buffer.toString();
	}
	
	/**
	 * 获得头信息的xml
	 * @return
	 */
	private static String getHeadXml(){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<head>");
		buffer.append("<service_id>ATS002</service_id>");
		buffer.append("<channel_id>NBDS.SYFWPT.DZSJAPP</channel_id>");
		buffer.append("<access_key>6dbaaffac587da4d163c3e99f1234512</access_key>");
		buffer.append("<send_seq>6dbaaffac592401e87da4d163c3e99f0</send_seq>");
		buffer.append("<send_date>").append("20170821").append("</send_date>");
		buffer.append("<send_time>102031110</send_time>");
		buffer.append("<is_encrypted>N</is_encrypted>");
		buffer.append("</head>");
		
		return buffer.toString();
	}
	
	/**
	 * 获得body信息xml
	 * @param json
	 * @return
	 */
	private static String getBodyXml(JSONObject json){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<body><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\"?><tbl>");
		buffer.append("<data>");
		buffer.append(getSbxxXml());
		buffer.append(getYwxxXml(json));
		buffer.append("<sjc><timestamp>").append(sdf.format(date)).append("</timestamp></sjc>");
		buffer.append("</data>");
		buffer.append("<sign>");
		buffer.append("<cert_data>6dbaaffac592401e87da4d163c3e99f03333231121111111111111…</cert_data>");
		buffer.append("<sign_data>6dbaaffac592401e87121212121111111111111…</sign_data>");
		buffer.append("</sign>");
		buffer.append("</tbl>]]]]>><![CDATA[</body>");
		
		return buffer.toString();
	}
	
	
	/**
	 * 获得设备信息xml
	 * @return
	 */
	private static String getSbxxXml(){
		Map<String, String> map = System.getenv();
		
		String ip = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		StringBuffer infoSb = new StringBuffer();
		
		infoSb.append("<sbxx>");
		infoSb.append("<sb_name>").append("联想R720").append("</sb_name>");
		infoSb.append("<czxt>").append(map.get("OS")).append("</czxt>");
		infoSb.append("<ip>").append(ip).append("</ip>");
		infoSb.append("<net>").append("wifi").append("</net>");
		infoSb.append("<sim_id>").append("cccccccccccccccc").append("</sim_id>");
		infoSb.append("</sbxx>");
		
		return infoSb.toString();
	}
	
	
	/**
	 * 获得用户信息xml
	 * @param params
	 * @return
	 */
    private static String getYwxxXml(JSONObject params){
        StringBuilder buf = new StringBuilder();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        buf.append("<ywxx>");
        for(String key : keys){
            buf.append("<").append(key).append(">");
            buf.append(params.get(key));
            buf.append("</").append(key).append(">\n");
        }
        buf.append("<auth_type>1</auth_type>");
        buf.append("<ywxt_userid>1111</ywxt_userid>");
        buf.append("<auth_date>").append(sdf.format(date)).append("</auth_date>");
        buf.append("</ywxx>");
        return buf.toString();
    }
}

