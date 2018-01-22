package cn.jeeweb.modules.tbs.entity;

import java.util.Date;

import oracle.sql.BLOB;
import cn.jeeweb.core.common.entity.DataEntity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 税银服务平台交互日志实体类
 * @author hft
 *
 */
@TableName("tb_transactionlog")
@SuppressWarnings("serial")
public class TransactionLog extends DataEntity<String>{
	
	private String id;				//编号
	private String channelName;		//银行代码
	private String channelComment;	//银行名称
	private String serviceName;		//服务代码
	private String serviceComment;	//服务名称
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date lrrq;				//发生时间
	private String code;			//状态码
	private String state;			//状态名称
	private String massage;			//返回消息内容
	private String userid;			//用户id
	private String fullName;		//用户姓名
	private String cardNo;			//用户身份证号码
	private String sendSeq;			//消息键
	private String recData;			//接收消息
	private String retData;			//返回消息

	private Date transactionDate1;	//交易日期起（查询用）
	private Date transactionDate2;	//交易日期止（查询用）
	
	
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
		
	}
	
	private String blobToString(BLOB blob){
		String blobStr = "";
		try {
			blobStr = new String(blob.getBytes((long)1, (int)blob.length()),"gbk");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blobStr;
	}
	
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getChannelComment() {
		return channelComment;
	}
	public void setChannelComment(String channelComment) {
		this.channelComment = channelComment;
	}
	public String getServiceComment() {
		return serviceComment;
	}
	public void setServiceComment(String serviceComment) {
		this.serviceComment = serviceComment;
	}
	public Date getLrrq() {
		return lrrq;
	}
	public void setLrrq(Date lrrq) {
		this.lrrq = lrrq;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getSendSeq() {
		return sendSeq;
	}
	public void setSendSeq(String sendSeq) {
		this.sendSeq = sendSeq;
	}
	public String getRecData() {
		return recData;
	}
	public void setRecData(Object recData) {
		BLOB blob = (BLOB)recData;
		this.recData = blobToString(blob);
	}
	public String getRetData() {
		return retData;
	}
	public void setRetData(Object retData) {
		BLOB blob = (BLOB)retData;
		this.retData = blobToString(blob);
	}
	public Date getTransactionDate1() {
		return transactionDate1;
	}
	public void setTransactionDate1(java.sql.Date transactionDate1) {
		this.transactionDate1 = transactionDate1;
	}
	public Date getTransactionDate2() {
		return transactionDate2;
	}
	public void setTransactionDate2(java.sql.Date transactionDate2) {
		this.transactionDate2 = transactionDate2;
	}
	
	
	
}
