package cn.jeeweb.modules.tbs.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.xml.sax.SAXException;

public class JSUtil  
{  
  
    // 如果要更换运行环境，请注意exePath最后的phantom.exe需要更改。因为这个只能在window版本上运行。前面的路径名  
    // 也需要和exePath里面的保持一致。否则无法调用      src\main\webapp\WEB-INF\static\vendors\phantomjs
    private final static String projectPath ="WEB-INF"
    		+ File.separator + "static"+ File.separator + "vendors"+ File.separator + "phantomjs";  
    private final static String jsPath = File.separator + "rasterize.js";  
    private final static String exePath =  File.separator+ "phantomjs-2.1.1-windows" + File.separator + "bin" + File.separator  + "phantomjs.exe";  
    public final static Logger log = Logger.getLogger(JSUtil.class);
  
    public static void main(String[] args) throws IOException, SAXException  
    {  
    	String fPath = "D:\\MyEclipse\\Workspaces\\tbs-web"+projectPath;
        // 测试调用。传入url即可  
//        String html = getParseredHtml2(fPath+exePath,fPath+jsPath,"http://localhost:7080/tbs-web/admin/tbs/query/report/exportpdfexample");  
//        System.out.println("html: " + html); 
    }  
    
    public static void execute(HttpServletRequest req,HttpServletResponse res,String dataStr) throws IOException, SAXException  
    {  
    	//dataStr = "";
        // 测试调用。传入url即可  
    	
    	String fPath = req.getServletContext().getRealPath("/")+projectPath;
    	
    	
    	String localAddress = "http://127.0.0.1:"+req.getServerPort()+req.getContextPath();
    	
        String html = getParseredHtml2(fPath+exePath,fPath+jsPath,localAddress,dataStr);  
        
      //  log.info(" html+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+" + html);
        String dd[] = html.split("imageDataBase64");
        if(dd.length>1){
        	res.getWriter().print(dd[1]);
        	
        }else{
        	res.getWriter().print("");
        }
        
    }
    
  
    // 调用phantomjs程序，并传入js文件，并通过流拿回需要的数据。  
    public static String getParseredHtml2(String exePath,String jsPath ,String localAddress, String exportparam) throws IOException  
    {  
    	String excuteContent = exePath + " " + jsPath + " " + localAddress+ " " + exportparam;
        Runtime rt = Runtime.getRuntime();  
        Process p = rt.exec(excuteContent);  
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));  
        StringBuffer sbf = new StringBuffer();  
        String tmp = "";  
        while ((tmp = br.readLine()) != null)  
        {  
            sbf.append(tmp);  
        }  
        String[] result = sbf.toString().split("companyServiceMod");  
        String result2 = "";  
        if(result.length >= 2)  
        {  
            result2 = result[1];  
            if(result2.length() > 200)  
            {  
                result2 = result2.substring(0, 200);
            }  
        }  

        return sbf.toString();
    }  
  
}   