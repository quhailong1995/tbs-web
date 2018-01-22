package cn.jeeweb.modules.tbs.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import java.util.Date;


/**   
 * @Title: 系统服务授权
 * @Description: 系统服务授权
 * @author QuHaiLong
 * @date 2017-08-13 14:42:37
 * @version V1.0   
 *
 */
@TableName("tb_auth_service")
@SuppressWarnings("serial")
public class TbAuthService extends AbstractEntity<String> {

    /**id*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**channel_id*/
    @TableField(value = "channel_id")
	private String channelId;
    /**service_id*/
    @TableField(value = "service_id")
	private String serviceId;
    /**是否强制加密*/
    @TableField(value = "is_encrypt")
	private String isEncrypt;
    /**有效标志*/
    @TableField(value = "yxbz")
	private String yxbz;
    /**选用标志*/
    @TableField(value = "xybz")
	private String xybz;
    /**create_name*/
    @TableField(value = "create_name")
	private String createName;
    /**create_date*/
    @TableField(value = "create_date")
	private Date createDate;
    /**update_name*/
    @TableField(value = "update_name")
	private String updateName;
    /**update_date*/
    @TableField(value = "update_date")
	private Date updateDate;
    
    
    
    @TableField(value = "channel_id")
    private DmWhChannel dmWhChannel;
    
    
    @TableField(value = "service_id")
    private DmWhService dmWhService;
    
   
    //private List<TbAuthContent> tbAuthContentList;
    

//    @TableField(value = "service_id")
//    private TbAuthContentExt tbAuthContentExt;
    
    @TableField(value = "service_id")
    private String serContentStr;
	
    
    
   


//	public List<TbAuthContent> getTbAuthContentList() {
//		return tbAuthContentList;
//	}
//
//	public void setTbAuthContentList(List<TbAuthContent> tbAuthContentList) {
//		this.tbAuthContentList = tbAuthContentList;
//	}

	public String getSerContentStr() {
		return serContentStr;
	}

	public void setSerContentStr(String serContentStr) {
		this.serContentStr = serContentStr;
	}

//	public TbAuthContentExt getTbAuthContentExt() {
//		return tbAuthContentExt;
//	}
//
//	public void setTbAuthContentExt(TbAuthContentExt tbAuthContentExt) {
//		this.tbAuthContentExt = tbAuthContentExt;
//	}

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
	 *@return: String  id
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  id
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  channelId
	 *@return: String  channel_id
	 */
	public String getChannelId(){
		return this.channelId;
	}

	/**
	 * 设置  channelId
	 *@param: channelId  channel_id
	 */
	public void setChannelId(String channelId){
		this.channelId = channelId;
	}
	/**
	 * 获取  serviceId
	 *@return: String  service_id
	 */
	public String getServiceId(){
		return this.serviceId;
	}

	/**
	 * 设置  serviceId
	 *@param: serviceId  service_id
	 */
	public void setServiceId(String serviceId){
		this.serviceId = serviceId;
	}
	/**
	 * 获取  isEncrypt
	 *@return: String  是否强制加密
	 */
	public String getIsEncrypt(){
		return this.isEncrypt;
	}

	/**
	 * 设置  isEncrypt
	 *@param: isEncrypt  是否强制加密
	 */
	public void setIsEncrypt(String isEncrypt){
		this.isEncrypt = isEncrypt;
	}
	/**
	 * 获取  yxbz
	 *@return: String  有效标志
	 */
	public String getYxbz(){
		return this.yxbz;
	}

	/**
	 * 设置  yxbz
	 *@param: yxbz  有效标志
	 */
	public void setYxbz(String yxbz){
		this.yxbz = yxbz;
	}
	/**
	 * 获取  xybz
	 *@return: String  选用标志
	 */
	public String getXybz(){
		return this.xybz;
	}

	/**
	 * 设置  xybz
	 *@param: xybz  选用标志
	 */
	public void setXybz(String xybz){
		this.xybz = xybz;
	}
	/**
	 * 获取  createName
	 *@return: String  create_name
	 */
	public String getCreateName(){
		return this.createName;
	}

	/**
	 * 设置  createName
	 *@param: createName  create_name
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	/**
	 * 获取  createDate
	 *@return: Date  create_date
	 */
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 * 设置  createDate
	 *@param: createDate  create_date
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 * 获取  updateName
	 *@return: String  update_name
	 */
	public String getUpdateName(){
		return this.updateName;
	}

	/**
	 * 设置  updateName
	 *@param: updateName  update_name
	 */
	public void setUpdateName(String updateName){
		this.updateName = updateName;
	}
	/**
	 * 获取  updateDate
	 *@return: Date  update_date
	 */
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 * 设置  updateDate
	 *@param: updateDate  update_date
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	
}