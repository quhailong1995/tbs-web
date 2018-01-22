package cn.jeeweb.modules.tbs.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import java.util.Date;

/**   
 * @Title: 支付方式管理
 * @Description: 支付方式管理
 * @author quhailong
 * @date 2017-11-13 11:40:20
 * @version V1.0   
 *
 */
@TableName("TB_PAY_WAY")
@SuppressWarnings("serial")
public class TbPayWay extends AbstractEntity<String> {

    
    @TableField(value = "WAY_TYPE")
	private String wayType;
    
    @TableField(value = "WAY_MC")
	private String wayMc;
    
    @TableField(value = "WAY_DM")
	private String wayDm;
    
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    
    @TableField(value = "REMARKS")
	private String remarks;
    
    @TableField(value = "UPDATE_DATE",fill = FieldFill.UPDATE)
	private Date updateDate;
    
    @TableField(value = "UPDATE_NAME")
	private String updateName;
    
    @TableField(value = "CREATE_DATE",fill = FieldFill.INSERT)
	private Date createDate;
    
    @TableField(value = "CREATE_NAME")
	private String createName;
    
    @TableField(value = "XYBZ")
	private String xybz;
    
    @TableField(value = "PAY_SECRETKEY")
	private String paySecretkey;
    
    @TableField(value = "PAY_KEY")
	private String payKey;
	
	/**
	 * 获取  wayType
	 *@return: String  
	 */
	public String getWayType(){
		return this.wayType;
	}

	/**
	 * 设置  wayType
	 *@param: wayType 
	 */
	public void setWayType(String wayType){
		this.wayType = wayType;
	}
	/**
	 * 获取  wayMc
	 *@return: String  
	 */
	public String getWayMc(){
		return this.wayMc;
	}

	/**
	 * 设置  wayMc
	 *@param: wayMc 
	 */
	public void setWayMc(String wayMc){
		this.wayMc = wayMc;
	}
	/**
	 * 获取  wayDm
	 *@return: String  
	 */
	public String getWayDm(){
		return this.wayDm;
	}

	/**
	 * 设置  wayDm
	 *@param: wayDm 
	 */
	public void setWayDm(String wayDm){
		this.wayDm = wayDm;
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
	/**
	 * 获取  remarks
	 *@return: String  
	 */
	public String getRemarks(){
		return this.remarks;
	}

	/**
	 * 设置  remarks
	 *@param: remarks 
	 */
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
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
	 * 获取  updateName
	 *@return: String  
	 */
	public String getUpdateName(){
		return this.updateName;
	}

	/**
	 * 设置  updateName
	 *@param: updateName 
	 */
	public void setUpdateName(String updateName){
		this.updateName = updateName;
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
	 * 获取  createName
	 *@return: String  
	 */
	public String getCreateName(){
		return this.createName;
	}

	/**
	 * 设置  createName
	 *@param: createName 
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	/**
	 * 获取  xybz
	 *@return: String  
	 */
	public String getXybz(){
		return this.xybz;
	}

	/**
	 * 设置  xybz
	 *@param: xybz 
	 */
	public void setXybz(String xybz){
		this.xybz = xybz;
	}
	/**
	 * 获取  paySecretkey
	 *@return: String  
	 */
	public String getPaySecretkey(){
		return this.paySecretkey;
	}

	/**
	 * 设置  paySecretkey
	 *@param: paySecretkey 
	 */
	public void setPaySecretkey(String paySecretkey){
		this.paySecretkey = paySecretkey;
	}
	/**
	 * 获取  payKey
	 *@return: String  
	 */
	public String getPayKey(){
		return this.payKey;
	}

	/**
	 * 设置  payKey
	 *@param: payKey 
	 */
	public void setPayKey(String payKey){
		this.payKey = payKey;
	}
	
}
