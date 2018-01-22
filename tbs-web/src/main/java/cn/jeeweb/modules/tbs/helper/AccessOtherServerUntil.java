package cn.jeeweb.modules.tbs.helper;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;


/**
 * 
 *
 * @author QuHaiLong
 * @date   2017年9月27日 下午2:16:01
 *
 */
public class AccessOtherServerUntil {
	
	public static String accressWebService(String soapRequestData,String rquestUrl) throws Exception{
		
/*		System.out.println("-------------------------------------------------------------------------");
		System.out.println(soapRequestData);
		System.out.println("-------------------------------------------------------------------------");*/
		
		System.out.println("-------------------------------------------------------------------------"+rquestUrl);

		URL url = new URL(rquestUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setConnectTimeout(2000);
		connection.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
		
		OutputStream os = connection.getOutputStream();
		os.write(soapRequestData.getBytes("UTF-8"));
	
		int responseCode = connection.getResponseCode();
		if(responseCode==200) {
			ByteArrayOutputStream infoStream = new ByteArrayOutputStream();  
			InputStream is = connection.getInputStream();//String xml
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len=is.read(buffer))>0) {
				infoStream.write(buffer, 0, len);
			}

			Pattern p = Pattern.compile("<return>(.*)</return>");//正则表达式，取=和|之间的字符串，不包括=和|  
			Matcher m = p.matcher(XmlHelper.formatXML_cdata(infoStream.toString("UTF-8")).replaceAll("&quot;", "\"").replaceAll("&gt;", ">").replaceAll("&lt;", "<"));  
			 while(m.find()) {  
			     //   System.out.println(m.group(1));//m.group(1)不包括这两个字符  
			        return m.group(1);
			  
			   }  
		}
//		if(200 == responseCode){//表示服务端响应成功  
//            InputStream is = connection.getInputStream();  
//            InputStreamReader isr = new InputStreamReader(is);  
//            BufferedReader br = new BufferedReader(isr);  
//              
//            StringBuilder sb = new StringBuilder();  
//            String temp = null;  
//            while(null != (temp = br.readLine())){  
//                sb.append(temp);  
//            }  
//            System.out.println(sb.toString());  
//              
//            is.close();  
//            isr.close();  
//            br.close(); 
//          //return  XmlHelper.formatXML_cdata(sb.toString().replaceAll("&quot;", "\"").replaceAll("&gt;", ">").replaceAll("&lt;", "<"));
//            return sb.toString();
//        }  
  
        os.close();  
		return "{\"code\":\"-1\",\"msg\":\"响应失败："+String.valueOf(connection.getResponseCode())+"\"}";
	}


}
