package cn.jeeweb.modules.tbs.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

/**   
 * @Title: 支付产品管理
 * @Description: 支付产品管理
 * @author quhailong
 * @date 2017-11-13 11:37:50
 * @version V1.0   
 *
 */
@TableName("TB_PAY_PRODUCT")
@SuppressWarnings("serial")
public class TbPayProduct extends AbstractEntity<String> {

    
    @TableField(value = "PRODUCT_STATE")
	private String productState;
    
    @TableField(value = "WAY_DM")
	private String wayDm;
    
    @TableField(value = "PRODUCT_MC")
	private String productMc;
    
    @TableField(value = "PRODUCT_DM")
	private String productDm;
    
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
    
    @TableField(exist=false)
    private List<TbPayWay> tbPayWay;
    
 
	
	public List<TbPayWay> getTbPayWay() {
		return tbPayWay;
	}

	public void setTbPayWay(List<TbPayWay> tbPayWay) {
		this.tbPayWay = tbPayWay;
	}

	/**
	 * 获取  productState
	 *@return: String  
	 */
	public String getProductState(){
		return this.productState;
	}

	/**
	 * 设置  productState
	 *@param: productState 
	 */
	public void setProductState(String productState){
		this.productState = productState;
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
	 * 获取  productMc
	 *@return: String  
	 */
	public String getProductMc(){
		return this.productMc;
	}

	/**
	 * 设置  productMc
	 *@param: productMc 
	 */
	public void setProductMc(String productMc){
		this.productMc = productMc;
	}
	/**
	 * 获取  productDm
	 *@return: String  
	 */
	public String getProductDm(){
		return this.productDm;
	}

	/**
	 * 设置  productDm
	 *@param: productDm 
	 */
	public void setProductDm(String productDm){
		this.productDm = productDm;
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
	
}
