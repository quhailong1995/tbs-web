package cn.jeeweb.modules.tbs.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import java.util.Date;

/**   
 * @Title: TB_USER
 * @Description: TB_USER
 * @author quhailong
 * @date 2017-12-11 17:25:49
 * @version V1.0   
 *
 */
@TableName("TB_USER")
@SuppressWarnings("serial")
public class TbUser extends AbstractEntity<String> {

    
    @TableField(value = "UPDATE_DATE",fill = FieldFill.UPDATE)
	private Date updateDate;
    
    @TableField(value = "CREATE_DATE",fill = FieldFill.INSERT)
	private Date createDate;
    
    @TableField(value = "SJHM")
	private String sjhm;
    
    @TableField(value = "ZJLX_DM")
	private String zjlxDm;
    
    @TableField(value = "ZJHM")
	private String zjhm;
    
    @TableField(value = "XM")
	private String xm;
    
    @TableField(value = "HJID")
	private Double hjid;
    
    @TableId(value = "id", type = IdType.UUID)
	private String id;
	
	/**
	 * 获取  updateDate
	 *@return: Date  
	 */
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 * 设置  updateDate
	 *@param: updateDate 
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 * 获取  createDate
	 *@return: Date  
	 */
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 * 设置  createDate
	 *@param: createDate 
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 * 获取  sjhm
	 *@return: String  
	 */
	public String getSjhm(){
		return this.sjhm;
	}

	/**
	 * 设置  sjhm
	 *@param: sjhm 
	 */
	public void setSjhm(String sjhm){
		this.sjhm = sjhm;
	}
	/**
	 * 获取  zjlxDm
	 *@return: String  
	 */
	public String getZjlxDm(){
		return this.zjlxDm;
	}

	/**
	 * 设置  zjlxDm
	 *@param: zjlxDm 
	 */
	public void setZjlxDm(String zjlxDm){
		this.zjlxDm = zjlxDm;
	}
	/**
	 * 获取  zjhm
	 *@return: String  
	 */
	public String getZjhm(){
		return this.zjhm;
	}

	/**
	 * 设置  zjhm
	 *@param: zjhm 
	 */
	public void setZjhm(String zjhm){
		this.zjhm = zjhm;
	}
	/**
	 * 获取  xm
	 *@return: String  
	 */
	public String getXm(){
		return this.xm;
	}

	/**
	 * 设置  xm
	 *@param: xm 
	 */
	public void setXm(String xm){
		this.xm = xm;
	}
	/**
	 * 获取  hjid
	 *@return: Double  
	 */
	public Double getHjid(){
		return this.hjid;
	}

	/**
	 * 设置  hjid
	 *@param: hjid 
	 */
	public void setHjid(Double hjid){
		this.hjid = hjid;
	}
	/**
	 * 获取  id
	 *@return: String  
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id 
	 */
	public void setId(String id){
		this.id = id;
	}
	
}
