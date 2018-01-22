package cn.jeeweb.modules.tbs.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import java.util.Date;

/**   
 * @Title: 税银产品管理
 * @Description: 税银产品管理
 * @author QuHaiLong
 * @date 2017-08-30 14:51:23
 * @version V1.0   
 *
 */
@TableName("dm_wh_product")
@SuppressWarnings("serial")
public class DmWhProduct extends AbstractEntity<String> {

    /**字段主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**渠道id*/
    @TableField(value = "channel_id")
	private String channelId;
    /**服务id*/
    @TableField(value = "service_id")
	private String serviceId;
    /**产品代码*/
    @TableField(value = "product_name")
	private String productName;
    /**产品名称*/
    @TableField(value = "product_comment")
	private String productComment;
    /**产品简介*/
    @TableField(value = "cpjj")
	private String cpjj ;
    /**贷前月份数*/
    @TableField(value = "loan_before_yfs")
	private Integer loanBeforeYfs;
    /**贷后月份数*/
    @TableField(value = "loan_after_yfs")
	private Integer loanAfterYfs;
    /**产品描述*/
    @TableField(value = "remark")
	private String remark;
    /**有效标志*/
    @TableField(value = "yxbz")
	private String yxbz ;
    /**选用标志*/
    @TableField(value = "xybz")
	private String xybz ;
    /**创建人*/
    @TableField(value = "create_name")
	private String createName;
    /**创建日期*/
    @TableField(value = "create_date")
	private Date createDate;
    /**修改人*/
    @TableField(value = "update_name")
	private String updateName;
    /**修改日期*/
    @TableField(value = "update_date")
	private Date updateDate;
    
    @TableField(value = "auth_buffer_term")
    private Integer authBufferTerm;
    @TableField(value = "auth_buffer_unit")
    private Integer authBufferUnit;
    
    @TableField(exist=false)
    private String authBuffer;
    
    
    
    @TableField(value = "channel_id")
    private DmWhChannel dmWhChannel;
    @TableField(value = "service_id")
    private DmWhService dmWhService;
    
    
    


	

	public Integer getAuthBufferTerm() {
		return authBufferTerm;
	}

	public void setAuthBufferTerm(Integer authBufferTerm) {
		this.authBufferTerm = authBufferTerm;
	}

	public Integer getAuthBufferUnit() {
		return authBufferUnit;
	}

	public void setAuthBufferUnit(Integer authBufferUnit) {
		this.authBufferUnit = authBufferUnit;
	}

	public String getAuthBuffer() {
			String t =null;
			if(authBufferUnit!=null){
				if(authBufferUnit==1){
					t = "/天";
				}
				if(authBufferUnit==2){
					t = "/月";
				}
				return authBufferTerm+t;
			}
		return authBuffer;
		
	}

	public void setAuthBuffer(String authBuffer) {
		this.authBuffer = authBuffer;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getLoanAfterYfs() {
		return loanAfterYfs;
	}

	public void setLoanAfterYfs(Integer loanAfterYfs) {
		this.loanAfterYfs = loanAfterYfs;
	}

	public DmWhService getDmWhService() {
		return dmWhService;
	}

	public void setDmWhService(DmWhService dmWhService) {
		this.dmWhService = dmWhService;
	}

	public DmWhChannel getDmWhChannel() {
		return dmWhChannel;
	}

	public void setDmWhChannel(DmWhChannel dmWhChannel) {
		this.dmWhChannel = dmWhChannel;
	}

	/**
	 * 获取  id
	 *@return: String  字段主键
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  字段主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  channelId
	 *@return: String  渠道id
	 */
	public String getChannelId(){
		return this.channelId;
	}

	/**
	 * 设置  channelId
	 *@param: channelId  渠道id
	 */
	public void setChannelId(String channelId){
		this.channelId = channelId;
	}
	/**
	 * 获取  productName
	 *@return: String  产品代码
	 */
	public String getProductName(){
		return this.productName;
	}

	/**
	 * 设置  productName
	 *@param: productName  产品代码
	 */
	public void setProductName(String productName){
		this.productName = productName;
	}
	/**
	 * 获取  productComment
	 *@return: String  产品名称
	 */
	public String getProductComment(){
		return this.productComment;
	}

	/**
	 * 设置  productComment
	 *@param: productComment  产品名称
	 */
	public void setProductComment(String productComment){
		this.productComment = productComment;
	}
	/**
	 * 获取  cpjj 
	 *@return: String  产品简介
	 */
	public String getCpjj (){
		return this.cpjj ;
	}

	/**
	 * 设置  cpjj 
	 *@param: cpjj   产品简介
	 */
	public void setCpjj (String cpjj ){
		this.cpjj  = cpjj ;
	}
	/**
	 * 获取  loanBeforeYfs
	 *@return: Integer  贷前月份数
	 */
	public Integer getLoanBeforeYfs(){
		return this.loanBeforeYfs;
	}

	/**
	 * 设置  loanBeforeYfs
	 *@param: loanBeforeYfs  贷前月份数
	 */
	public void setLoanBeforeYfs(Integer loanBeforeYfs){
		this.loanBeforeYfs = loanBeforeYfs;
	}
	/**
	 * 获取  remark
	 *@return: String  产品描述
	 */
	public String getRemark(){
		return this.remark;
	}

	/**
	 * 设置  remark
	 *@param: remark  产品描述
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	 * 获取  yxbz 
	 *@return: String  有效标志
	 */
	public String getYxbz (){
		return this.yxbz ;
	}

	/**
	 * 设置  yxbz 
	 *@param: yxbz   有效标志
	 */
	public void setYxbz (String yxbz ){
		this.yxbz  = yxbz ;
	}
	/**
	 * 获取  xybz 
	 *@return: String  选用标志
	 */
	public String getXybz (){
		return this.xybz ;
	}

	/**
	 * 设置  xybz 
	 *@param: xybz   选用标志
	 */
	public void setXybz (String xybz ){
		this.xybz  = xybz ;
	}
	/**
	 * 获取  createName
	 *@return: String  创建人
	 */
	public String getCreateName(){
		return this.createName;
	}

	/**
	 * 设置  createName
	 *@param: createName  创建人
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	/**
	 * 获取  createDate
	 *@return: Date  创建日期
	 */
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 * 设置  createDate
	 *@param: createDate  创建日期
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 * 获取  updateName
	 *@return: String  修改人
	 */
	public String getUpdateName(){
		return this.updateName;
	}

	/**
	 * 设置  updateName
	 *@param: updateName  修改人
	 */
	public void setUpdateName(String updateName){
		this.updateName = updateName;
	}
	/**
	 * 获取  updateDate
	 *@return: Date  修改日期
	 */
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 * 设置  updateDate
	 *@param: updateDate  修改日期
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	
}
