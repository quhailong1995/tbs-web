package cn.jeeweb.modules.tbs.entity;

import java.beans.Transient;
import java.util.Date;

import cn.jeeweb.core.common.entity.AbstractEntity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;

/**   
 * @Title: 系统配置-服务器管理
 * @Description: 系统配置-服务器管理
 * @author quhailong
 * @date 2017-10-27 10:36:09
 * @version V1.0   
 *
 */
@TableName("dm_wh_servermanage")
@SuppressWarnings("serial")
public class DmWhServermanage extends AbstractEntity<String> {

    /**服务器主键id*/
	@TableId(value = "id", type = IdType.UUID)
	private String id;
    /**服务器名称*/
    @TableField(value = "server_mc")
	private String serverMc;
    /**ip*/
    @TableField(value = "server_ip")
	private String serverIp;
    /**端口*/
    @TableField(value = "server_port")
	private String serverPort;
    /**应用代码*/
    @TableField(value = "app_dm")
	private String appDm;
    /**应用名称*/
    @TableField(value = "app_mc")
	private String appMc;
    /**环境*/
    @TableField(value = "server_environment")
	private String serverEnvironment;
    /**是否启用*/
    @TableField(value = "xybz")
	private String xybz;
    /**服务器状态*/
    @TableField(value = "server_state")
	private String serverState;
    
    
    
    
    
    
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
    
    
    
    @TableField(exist = false)
    private DmWhAppmanage app;
	public DmWhAppmanage getApp() {
		return app;
	}

	public void setApp(DmWhAppmanage app) {
		this.app = app;
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
	 *@return: String  服务器主键id
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  服务器主键id
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  serverMc
	 *@return: String  服务器名称
	 */
	public String getServerMc(){
		return this.serverMc;
	}

	/**
	 * 设置  serverMc
	 *@param: serverMc  服务器名称
	 */
	public void setServerMc(String serverMc){
		this.serverMc = serverMc;
	}
	/**
	 * 获取  serverIp
	 *@return: String  ip
	 */
	public String getServerIp(){
		return this.serverIp;
	}

	/**
	 * 设置  serverIp
	 *@param: serverIp  ip
	 */
	public void setServerIp(String serverIp){
		this.serverIp = serverIp;
	}
	/**
	 * 获取  serverPort
	 *@return: String  端口
	 */
	public String getServerPort(){
		return this.serverPort;
	}

	/**
	 * 设置  serverPort
	 *@param: serverPort  端口
	 */
	public void setServerPort(String serverPort){
		this.serverPort = serverPort;
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
	 * 获取  serverEnvironment
	 *@return: String  环境
	 */
	public String getServerEnvironment(){
		return this.serverEnvironment;
	}

	/**
	 * 设置  serverEnvironment
	 *@param: serverEnvironment  环境
	 */
	public void setServerEnvironment(String serverEnvironment){
		this.serverEnvironment = serverEnvironment;
	}
	/**
	 * 获取  xybz
	 *@return: String  是否启用
	 */
	public String getXybz(){
		return this.xybz;
	}

	/**
	 * 设置  xybz
	 *@param: xybz  是否启用
	 */
	public void setXybz(String xybz){
		this.xybz = xybz;
	}
	/**
	 * 获取  serverState
	 *@return: String  服务器状态
	 */
	public String getServerState(){
		return this.serverState;
	}

	/**
	 * 设置  serverState
	 *@param: serverState  服务器状态
	 */
	public void setServerState(String serverState){
		this.serverState = serverState;
	}
	
}
