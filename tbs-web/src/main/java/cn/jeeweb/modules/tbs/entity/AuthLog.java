package cn.jeeweb.modules.tbs.entity;

import java.util.Date;

import cn.jeeweb.core.common.entity.DataEntity;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 授权日志查询实体类
 * @author hft
 *
 */
@TableName("tb_auth_querylog")
@SuppressWarnings("serial")
public class AuthLog extends DataEntity<String>{
	
	private String id;				//编号
	private String userid;			//用户ID
	private String cardNo;			//证件号码
	private String fullName; 		//姓名
	private String channelName;		//渠道代码
	private String channelComment;	//渠道名称
	private String serviceName;		//服务代码
	private String serviceComment;	//服务名称
	private String accessToken;		//访问码
	private String sendSeq;			//发送键
	private Date lrrq;				//操作时间
	
	
	public AuthLog() {
		super();
	}
	
	public AuthLog(Long id, String cardNo, String fullName, String authChannel,
			String serviceName, String qureyTime, String accesseCode) {
		super();
//		this.id = id;
//		this.cardNo = cardNo;
//		this.fullName = fullName;
//		this.authChannel = authChannel;
//		this.serviceName = serviceName;
//		this.qureyTime = qureyTime;
//		this.accesseCode = accesseCode;
	}
	
	
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
		
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getChannelName() {
		return channelName;
	}


	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}


	public String getChannelComment() {
		return channelComment;
	}


	public void setChannelComment(String channelComment) {
		this.channelComment = channelComment;
	}


	public String getServiceName() {
		return serviceName;
	}


	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	public String getServiceComment() {
		return serviceComment;
	}


	public void setServiceComment(String serviceComment) {
		this.serviceComment = serviceComment;
	}


	public String getAccessToken() {
		return accessToken;
	}


	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


	public String getSendSeq() {
		return sendSeq;
	}


	public void setSendSeq(String sendSeq) {
		this.sendSeq = sendSeq;
	}


	public Date getLrrq() {
		return lrrq;
	}


	public void setLrrq(Date lrrq) {
		this.lrrq = lrrq;
	}
	
	
	
	
}
