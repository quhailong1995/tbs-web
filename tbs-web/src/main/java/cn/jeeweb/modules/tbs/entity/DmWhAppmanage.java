package cn.jeeweb.modules.tbs.entity;

import java.beans.Transient;
import java.util.Date;

import cn.jeeweb.core.common.entity.AbstractEntity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;

/**   
 * @Title: 系统配置-应用管理
 * @Description: 系统配置-应用管理
 * @author quhailong
 * @date 2017-10-27 10:27:29
 * @version V1.0   
 *
 */
@TableName("dm_wh_appmanage")
@SuppressWarnings("serial")
public class DmWhAppmanage extends AbstractEntity<String> {

    /**主键id*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**应用代码*/
    @TableField(value = "app_dm")
	private String appDm;
    /**应用名称*/
    @TableField(value = "app_mc")
	private String appMc;
    /**服务器类型*/
    @TableField(value = "server_type")
	private String serverType;
    /**应用状态*/
    @TableField(value = "app_state")
	private String appState;
    
    
    
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
    @TableField(value = "bz")
    private String bz;
    
    
   

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
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

    
    
    
    
    
    
	
	/**
	 * 获取  id
	 *@return: String  主键id
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  主键id
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  appDm
	 *@return: String  应用代码
	 */
	public String getAppDm(){
		return this.appDm;
	}

	/**
	 * 设置  appDm
	 *@param: appDm  应用代码
	 */
	public void setAppDm(String appDm){
		this.appDm = appDm;
	}
	/**
	 * 获取  appMc
	 *@return: String  应用名称
	 */
	public String getAppMc(){
		return this.appMc;
	}

	/**
	 * 设置  appMc
	 *@param: appMc  应用名称
	 */
	public void setAppMc(String appMc){
		this.appMc = appMc;
	}
	/**
	 * 获取  serverType
	 *@return: String  服务器类型
	 */
	public String getServerType(){
		return this.serverType;
	}

	/**
	 * 设置  serverType
	 *@param: serverType  服务器类型
	 */
	public void setServerType(String serverType){
		this.serverType = serverType;
	}
	/**
	 * 获取  appState
	 *@return: String  应用状态
	 */
	public String getAppState(){
		return this.appState;
	}

	/**
	 * 设置  appState
	 *@param: appState  应用状态
	 */
	public void setAppState(String appState){
		this.appState = appState;
	}
	
}
