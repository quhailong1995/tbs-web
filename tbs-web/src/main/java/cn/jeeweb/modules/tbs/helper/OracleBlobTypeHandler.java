/* 
 * $RCSfile: OracleBlobTypeHandler.java,v $ 
 * $Revision: 1.1  $ 
 * $Date: 2014-7-28  $ 
 * 
 * Copyright (C) 2014 eqiao, Inc. All rights reserved. 
 * 
 * This software is the proprietary information of eqiao, Inc. 
 * Use is subject to license terms. 
 */  
package cn.jeeweb.modules.tbs.helper;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BlobTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/** 
 * <p>Title: OracleBlobTypeHandler</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 2014</p>  
 * @author wangf 
 * @version 1.0 
 */

public class OracleBlobTypeHandler implements TypeHandler<Object> {
    public Object valueOf(String param) {  
        return null;  
    }  
  
    private static final TypeHandler<byte[]> TYPE_HANDLER = new BlobTypeHandler();
    
    
  /**
   * 获取返回值
   */
    public Object getResult(ResultSet arg0, String arg1) throws SQLException{      	
    	Blob blob = arg0.getBlob(arg1);
    	String r = null;
    	if ((blob == null || blob.length() == 0)) 
    		 return null;
		try {
			//将gb2312 更改成gbk，兼容生僻字
			r = new String(TYPE_HANDLER.getResult(arg0, arg1),"gbk");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return (blob == null || blob.length() == 0) ? null : r ;
    	
    }  
  
    
    public Object getResult(ResultSet arg0, int arg1) throws SQLException {  
        return null;  
    }  
  
    
    public Object getResult(CallableStatement arg0, int arg1) throws SQLException {  
        return null;  
    }  
  
  /**
   * 参数传入  
   */
	public void setParameter(PreparedStatement arg0, int arg1, Object arg2, JdbcType arg3) throws SQLException {
		if(arg2==null){
			arg2 = "空";
		}
		try {
			TYPE_HANDLER.setParameter(arg0, arg1, ((String)arg2).getBytes("GBK"), arg3);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
