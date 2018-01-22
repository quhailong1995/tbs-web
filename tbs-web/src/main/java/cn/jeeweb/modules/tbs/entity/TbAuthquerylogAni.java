package cn.jeeweb.modules.tbs.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import java.util.Date;

/**   
 * @Title: TB_AUTHQUERYLOG_ANI
 * @Description: TB_AUTHQUERYLOG_ANI
 * @author quhl
 * @date 2018-01-02 10:08:00
 * @version V1.0   
 *
 */
@TableName("TB_AUTHQUERYLOG_ANI")
@SuppressWarnings("serial")
public class TbAuthquerylogAni extends AbstractEntity<String> {

    
    @TableField(value = "LRSJ")
	private Date lrsj;
    
    @TableField(value = "LRRQ")
	private Date lrrq;
    
    @TableField(value = "CHANNEL_NAME")
	private String channelName;
    
    @TableField(value = "IS_SX")
	private String isSx;
    
    @TableField(value = "IS_ZD")
	private String isZd;
    
    @TableField(value = "MAX_SALARY")
	private Double maxSalary;
    
    @TableField(value = "TOTAL_MONTH")
	private Double totalMonth;
    
    @TableField(value = "PRODUCT_ID")
	private String productId;
    
    @TableField(value = "USERID")
	private String userid;
    
    @TableField(value = "SEND_SEQ")
	private String sendSeq;
    
    @TableId(value = "XH")
	private String id;
    
 
    private String fullName;
    
 
    private String zjhm;
    
  
    private String productComment;
	
   
    private String hjid;
    
    
    
	public String getHjid() {
		return hjid;
	}

	public void setHjid(String hjid) {
		this.hjid = hjid;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getProductComment() {
		return productComment;
	}

	public void setProductComment(String productComment) {
		this.productComment = productComment;
	}

	/**
	 * 获取  lrsj
	 *@return: Date  
	 */
	public Date getLrsj(){
		return this.lrsj;
	}

	/**
	 * 设置  lrsj
	 *@param: lrsj 
	 */
	public void setLrsj(Date lrsj){
		this.lrsj = lrsj;
	}
	/**
	 * 获取  lrrq
	 *@return: Date  
	 */
	public Date getLrrq(){
		return this.lrrq;
	}

	/**
	 * 设置  lrrq
	 *@param: lrrq 
	 */
	public void setLrrq(Date lrrq){
		this.lrrq = lrrq;
	}
	/**
	 * 获取  channelName
	 *@return: String  
	 */
	public String getChannelName(){
		return this.channelName;
	}

	/**
	 * 设置  channelName
	 *@param: channelName 
	 */
	public void setChannelName(String channelName){
		this.channelName = channelName;
	}
	/**
	 * 获取  isSx
	 *@return: String  
	 */
	public String getIsSx(){
		return this.isSx;
	}

	/**
	 * 设置  isSx
	 *@param: isSx 
	 */
	public void setIsSx(String isSx){
		this.isSx = isSx;
	}
	/**
	 * 获取  isZd
	 *@return: String  
	 */
	public String getIsZd(){
		return this.isZd;
	}

	/**
	 * 设置  isZd
	 *@param: isZd 
	 */
	public void setIsZd(String isZd){
		this.isZd = isZd;
	}
	/**
	 * 获取  maxSalary
	 *@return: Double  
	 */
	public Double getMaxSalary(){
		return this.maxSalary;
	}

	/**
	 * 设置  maxSalary
	 *@param: maxSalary 
	 */
	public void setMaxSalary(Double maxSalary){
		this.maxSalary = maxSalary;
	}
	/**
	 * 获取  totalMonth
	 *@return: Double  
	 */
	public Double getTotalMonth(){
		return this.totalMonth;
	}

	/**
	 * 设置  totalMonth
	 *@param: totalMonth 
	 */
	public void setTotalMonth(Double totalMonth){
		this.totalMonth = totalMonth;
	}
	/**
	 * 获取  productId
	 *@return: String  
	 */
	public String getProductId(){
		return this.productId;
	}

	/**
	 * 设置  productId
	 *@param: productId 
	 */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/**
	 * 获取  userid
	 *@return: String  
	 */
	public String getUserid(){
		return this.userid;
	}

	/**
	 * 设置  userid
	 *@param: userid 
	 */
	public void setUserid(String userid){
		this.userid = userid;
	}
	/**
	 * 获取  sendSeq
	 *@return: String  
	 */
	public String getSendSeq(){
		return this.sendSeq;
	}

	/**
	 * 设置  sendSeq
	 *@param: sendSeq 
	 */
	public void setSendSeq(String sendSeq){
		this.sendSeq = sendSeq;
	}
	/**
	 * 获取  xh
	 *@return: Double  
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  xh
	 *@param: xh 
	 */
	public void setId(String xh){
		this.id = xh;
	}
	
}
