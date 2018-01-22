package cn.jeeweb.modules.tbs.entity;

import java.util.Date;

import cn.jeeweb.core.common.entity.AbstractEntity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;

/**   
 * @Title: 系统配置-缓存管理
 * @Description: 系统配置-缓存管理
 * @author quhailong
 * @date 2017-10-27 10:42:39
 * @version V1.0   
 *
 */
@TableName("dm_wh_cachemanage")
@SuppressWarnings("serial")
public class DmWhCachemanage extends AbstractEntity<String> {

    /**缓存管理主键id*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**缓存代码*/
    @TableField(value = "cache_dm")
	private String cacheDm;
    /**缓存名称*/
    @TableField(value = "cache_mc")
	private String cacheMc;
    /**命名空间*/
    @TableField(value = "cache_namespace")
	private String cacheNamespace;
    /**刷新方法*/
    @TableField(value = "refresh_method")
	private String refeshMethod;
    /**查看方法*/
    @TableField(value = "view_method")
	private String viewMethod;
    /**超时时间*/
    @TableField(value = "timeout")
	private String timeout;
    
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
	 *@return: String  缓存管理主键id
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  缓存管理主键id
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  cacheDm
	 *@return: String  缓存代码
	 */
	public String getCacheDm(){
		return this.cacheDm;
	}

	/**
	 * 设置  cacheDm
	 *@param: cacheDm  缓存代码
	 */
	public void setCacheDm(String cacheDm){
		this.cacheDm = cacheDm;
	}
	/**
	 * 获取  cacheMc
	 *@return: String  缓存名称
	 */
	public String getCacheMc(){
		return this.cacheMc;
	}

	/**
	 * 设置  cacheMc
	 *@param: cacheMc  缓存名称
	 */
	public void setCacheMc(String cacheMc){
		this.cacheMc = cacheMc;
	}
	/**
	 * 获取  cacheNamespace
	 *@return: String  命名空间
	 */
	public String getCacheNamespace(){
		return this.cacheNamespace;
	}

	/**
	 * 设置  cacheNamespace
	 *@param: cacheNamespace  命名空间
	 */
	public void setCacheNamespace(String cacheNamespace){
		this.cacheNamespace = cacheNamespace;
	}
	/**
	 * 获取  refeshMethod
	 *@return: String  刷新方法
	 */
	public String getRefeshMethod(){
		return this.refeshMethod;
	}

	/**
	 * 设置  refeshMethod
	 *@param: refeshMethod  刷新方法
	 */
	public void setRefeshMethod(String refeshMethod){
		this.refeshMethod = refeshMethod;
	}
	/**
	 * 获取  viewMethod
	 *@return: String  查看方法
	 */
	public String getViewMethod(){
		return this.viewMethod;
	}

	/**
	 * 设置  viewMethod
	 *@param: viewMethod  查看方法
	 */
	public void setViewMethod(String viewMethod){
		this.viewMethod = viewMethod;
	}
	/**
	 * 获取  timeout
	 *@return: String  超时时间
	 */
	public String getTimeout(){
		return this.timeout;
	}

	/**
	 * 设置  timeout
	 *@param: timeout  超时时间
	 */
	public void setTimeout(String timeout){
		this.timeout = timeout;
	}
	
}
