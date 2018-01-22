package cn.jeeweb.modules.tbs.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import java.util.Date;

/**   
 * @Title: 系统服务管理
 * @Description: 系统服务管理
 * @author QuHaiLong
 * @date 2017-08-13 13:55:29
 * @version V1.0   
 *
 */
@TableName("dm_wh_service")
@SuppressWarnings("serial")
public class DmWhService extends AbstractEntity<String> {

    /**系统服务（主键）*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**服务ID*/
    @TableField(value = "service_name")
	private String serviceName;
    /**服务名称*/
    @TableField(value = "service_comment")
	private String serviceComment;
    /**服务类型*/
    @TableField(value = "service_type")
	private String serviceType;
    /**回调地址*/
    @TableField(value = "method_path")
	private String methodPath;
    /**yxbz*/
    @TableField(value = "yxbz")
	private String yxbz;
    /**xybz*/
    @TableField(value = "xybz")
	private String xybz;
    /**创建人*/
    @TableField(value = "create_name")
	private String createName;
    /**create_date*/
    @TableField(value = "create_date")
	private Date createDate;
    /**创建日期*/
    @TableField(value = "update_name")
	private String updateName;
    /**修改日期*/
    @TableField(value = "update_date")
	private Date updateDate;
    /**服务类名*/
//    @TableField(value = "service_class_name")
//	private String serviceClassName;
    /**备注*/
    @TableField(value = "remarks")
	private String remarks;
	
	/**
	 * 获取  id
	 *@return: String  系统服务（主键）
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  系统服务（主键）
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  serviceName
	 *@return: String  服务ID
	 */
	public String getServiceName(){
		return this.serviceName;
	}

	/**
	 * 设置  serviceName
	 *@param: serviceName  服务ID
	 */
	public void setServiceName(String serviceName){
		this.serviceName = serviceName;
	}
	/**
	 * 获取  serviceComment
	 *@return: String  服务名称
	 */
	public String getServiceComment(){
		return this.serviceComment;
	}

	/**
	 * 设置  serviceComment
	 *@param: serviceComment  服务名称
	 */
	public void setServiceComment(String serviceComment){
		this.serviceComment = serviceComment;
	}
	/**
	 * 获取  serviceType
	 *@return: String  服务类型
	 */
	public String getServiceType(){
		return this.serviceType;
	}

	/**
	 * 设置  serviceType
	 *@param: serviceType  服务类型
	 */
	public void setServiceType(String serviceType){
		this.serviceType = serviceType;
	}
	/**
	 * 获取  methodPath
	 *@return: String  回调地址
	 */
	public String getMethodPath(){
		return this.methodPath;
	}

	/**
	 * 设置  methodPath
	 *@param: methodPath  回调地址
	 */
	public void setMethodPath(String methodPath){
		this.methodPath = methodPath;
	}
	/**
	 * 获取  yxbz
	 *@return: String  yxbz
	 */
	public String getYxbz(){
		return this.yxbz;
	}

	/**
	 * 设置  yxbz
	 *@param: yxbz  yxbz
	 */
	public void setYxbz(String yxbz){
		this.yxbz = yxbz;
	}
	/**
	 * 获取  xybz
	 *@return: String  xybz
	 */
	public String getXybz(){
		return this.xybz;
	}

	/**
	 * 设置  xybz
	 *@param: xybz  xybz
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
	 *@return: String  创建日期
	 */
	public String getUpdateName(){
		return this.updateName;
	}

	/**
	 * 设置  updateName
	 *@param: updateName  创建日期
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
	/**
	 * 获取  serviceClassName
	 *@return: String  服务类名
	 */
//	public String getServiceClassName(){
//		return this.serviceClassName;
//	}

	/**
	 * 设置  serviceClassName
	 *@param: serviceClassName  服务类名
	 */
//	public void setServiceClassName(String serviceClassName){
//		this.serviceClassName = serviceClassName;
//	}
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
	
}