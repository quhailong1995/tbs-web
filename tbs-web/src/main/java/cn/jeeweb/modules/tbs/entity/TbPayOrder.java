package cn.jeeweb.modules.tbs.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import java.util.Date;

/**   
 * @Title: 支付订单管理
 * @Description: 支付订单管理
 * @author quhailong
 * @date 2017-11-13 11:41:36
 * @version V1.0   
 *
 */
@TableName("TB_PAY_ORDER")
@SuppressWarnings("serial")
public class TbPayOrder extends AbstractEntity<String> {

    
    @TableField(value = "ORDER_STATE")
	private String orderState;
    
    @TableField(value = "WAY_DM")
	private String wayDm;
    
    @TableField(value = "ORDER_DATE",fill = FieldFill.INSERT)
	private Date orderDate;
    
    @TableField(value = "ODER_AMOUNT")
	private Double oderAmount;
    
    @TableField(value = "PRODUCT_DM")
	private String productDm;
    
    @TableField(value = "ORDER_DM")
	private String orderDm;
    
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
	
	/**
	 * 获取  orderState
	 *@return: String  
	 */
	public String getOrderState(){
		return this.orderState;
	}

	/**
	 * 设置  orderState
	 *@param: orderState 
	 */
	public void setOrderState(String orderState){
		this.orderState = orderState;
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
	 * 获取  orderDate
	 *@return: Date  
	 */
	public Date getOrderDate(){
		return this.orderDate;
	}

	/**
	 * 设置  orderDate
	 *@param: orderDate 
	 */
	public void setOrderDate(Date orderDate){
		this.orderDate = orderDate;
	}
	/**
	 * 获取  oderAmount
	 *@return: Double  
	 */
	public Double getOderAmount(){
		return this.oderAmount;
	}

	/**
	 * 设置  oderAmount
	 *@param: oderAmount 
	 */
	public void setOderAmount(Double oderAmount){
		this.oderAmount = oderAmount;
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
	 * 获取  orderDm
	 *@return: String  
	 */
	public String getOrderDm(){
		return this.orderDm;
	}

	/**
	 * 设置  orderDm
	 *@param: orderDm 
	 */
	public void setOrderDm(String orderDm){
		this.orderDm = orderDm;
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
