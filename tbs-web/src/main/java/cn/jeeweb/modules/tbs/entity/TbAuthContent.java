package cn.jeeweb.modules.tbs.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import com.baomidou.mybatisplus.annotations.TableField;

/**   
 * @Title: 授权内容管理
 * @Description: 授权内容管理
 * @author QuHaiLong
 * @date 2017-08-14 14:38:37
 * @version V1.0   
 *
 */
@TableName("tb_auth_content")
@SuppressWarnings("serial")
public class TbAuthContent extends AbstractEntity<String> {

	@TableId(value = "as_id")
	private String id;			//授权服务id


    /**渠道ID*/
    @TableField(value = "channel_id")
	private String channelId;
    /**服务ID*/
    @TableField(value = "service_id")
	private String serviceId;
    /**scontent_id*/
    @TableField(value = "scontent_id")
	private String scontentId;
    
/*   @TableField(value = "channel_id")
    private DmWhChannel dmWhChannel;
    @TableField(value = "as_id")
    private Object tbAuthContentExt;
   
    @TableField(value = "service_id")
    private DmWhService dmWhService;
    @TableField(value = "scontent_id")
    private DmWhSerContent dmWhSerContent;
    
    
    
    
    
    
    
    
    
/*
	public DmWhSerContent getDmWhSerContent() {
		return dmWhSerContent;
	}

	public void setDmWhSerContent(DmWhSerContent dmWhSerContent) {
		this.dmWhSerContent = dmWhSerContent;
	}

	public DmWhChannel getDmWhChannel() {
		return dmWhChannel;
	}

	public void setDmWhChannel(DmWhChannel dmWhChannel) {
		this.dmWhChannel = dmWhChannel;
	}




	public Object getTbAuthContentExt() {
		return tbAuthContentExt;
	}

	public void setTbAuthContentExt(Object tbAuthContentExt) {
		this.tbAuthContentExt = tbAuthContentExt;
	}
	
	public DmWhService getDmWhService() {
		return dmWhService;
	}

	public void setDmWhService(DmWhService dmWhService) {
		this.dmWhService = dmWhService;
	}*/

	/**
	 * 获取  channelId
	 *@return: String  渠道ID
	 */
	public String getChannelId(){
		return this.channelId;
	}

	/**
	 * 设置  channelId
	 *@param: channelId  渠道ID
	 */
	public void setChannelId(String channelId){
		this.channelId = channelId;
	}
	/**
	 * 获取  serviceId
	 *@return: String  服务ID
	 */
	public String getServiceId(){
		return this.serviceId;
	}

	/**
	 * 设置  serviceId
	 *@param: serviceId  服务ID
	 */
	public void setServiceId(String serviceId){
		this.serviceId = serviceId;
	}
	/**
	 * 获取  scontentId
	 *@return: String  scontent_id
	 */
	public String getScontentId(){
		return this.scontentId;
	}

	/**
	 * 设置  scontentId
	 *@param: scontentId  scontent_id
	 */
	public void setScontentId(String scontentId){
		this.scontentId = scontentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	
}
