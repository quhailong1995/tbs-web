package cn.jeeweb.modules.tbs.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import java.util.Date;

/**   
 * @Title: 银行接入管理
 * @Description: 银行接入管理
 * @author QuHaiLong
 * @date 2017-08-12 17:58:07
 * @version V1.0   
 *
 */
@TableName("dm_wh_channel")
@SuppressWarnings("serial")
public class DmWhChannel extends AbstractEntity<String> {

    /**系统接入ID主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**系统接入代码（渠道代码）*/
    @TableField(value = "channel_name")
	private String channelName;
    /**系统接入名称*/
    @TableField(value = "channel_comment")
	private String channelComment;
    /**秘钥（可空）*/
    @TableField(value = "access_key")
	private String accessKey;
    /**限制ip列表*/
    @TableField(value = "limitip_list")
	private String limitipList;
    /**备注*/
    @TableField(value = "remarks")
	private String remarks;
    /**数据源名称（前置库）*/
    @TableField(value = "datasource_name")
	private String datasourceName;
    /**有效标志*/
    @TableField(value = "yxbz")
	private String yxbz;
    /**选用标志*/
    @TableField(value = "xybz")
	private String xybz;
    /**创建人*/
    @TableField(value = "create_name")
	private String createName;
    /**创建时间*/
    @TableField(value = "create_date")
	private Date createDate;
    /**更新人*/
    @TableField(value = "update_name")
	private String updateName;
    /**更新时间*/
    @TableField(value = "update_date")
	private Date updateDate;
	
	/**
	 * 获取  id
	 *@return: String  系统接入ID主键
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  系统接入ID主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  channelName
	 *@return: String  系统接入代码（渠道代码）
	 */
	public String getChannelName(){
		return this.channelName;
	}

	/**
	 * 设置  channelName
	 *@param: channelName  系统接入代码（渠道代码）
	 */
	public void setChannelName(String channelName){
		this.channelName = channelName;
	}
	/**
	 * 获取  channelComment
	 *@return: String  系统接入名称
	 */
	public String getChannelComment(){
		return this.channelComment;
	}

	/**
	 * 设置  channelComment
	 *@param: channelComment  系统接入名称
	 */
	public void setChannelComment(String channelComment){
		this.channelComment = channelComment;
	}
	/**
	 * 获取  encryptCode
	 *@return: String  秘钥（可空）
	 */
	public String getAccessKey(){
		return this.accessKey;
	}

	/**
	 * 设置  encryptCode
	 *@param: encryptCode  秘钥（可空）
	 */
	public void setAccessKey(String accessKey){
		this.accessKey = accessKey;
	}
	/**
	 * 获取  limitipList
	 *@return: String  限制ip列表
	 */
	public String getLimitipList(){
		return this.limitipList;
	}

	/**
	 * 设置  limitipList
	 *@param: limitipList  限制ip列表
	 */
	public void setLimitipList(String limitipList){
		this.limitipList = limitipList;
	}
	/**
	 * 获取  remarks
	 *@return: String  备注
	 */
	public String getRemarks(){
		return this.remarks;
	}

	/**
	 * 设置  remarks
	 *@param: remarks  备注
	 */
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
	/**
	 * 获取  datasourceName
	 *@return: String  数据源名称（前置库）
	 */
	public String getDatasourceName(){
		return this.datasourceName;
	}

	/**
	 * 设置  datasourceName
	 *@param: datasourceName  数据源名称（前置库）
	 */
	public void setDatasourceName(String datasourceName){
		this.datasourceName = datasourceName;
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
	 *@return: Date  创建时间
	 */
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 * 设置  createDate
	 *@param: createDate  创建时间
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 * 获取  updateName
	 *@return: String  更新人
	 */
	public String getUpdateName(){
		return this.updateName;
	}

	/**
	 * 设置  updateName
	 *@param: updateName  更新人
	 */
	public void setUpdateName(String updateName){
		this.updateName = updateName;
	}
	/**
	 * 获取  updateDate
	 *@return: Date  更新时间
	 */
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 * 设置  updateDate
	 *@param: updateDate  更新时间
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}


}