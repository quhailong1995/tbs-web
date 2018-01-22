package cn.jeeweb.modules.tbs.entity;

import java.util.Date;

import oracle.sql.BLOB;
import cn.jeeweb.core.common.entity.DataEntity;
import cn.jeeweb.core.security.utils.Md5Utils;
import cn.jeeweb.modules.sys.entity.Dict;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("tb_user_auth")
@SuppressWarnings("serial")
public class UserAuth extends DataEntity<String>{
	
	private String id;			//编号
	private Integer userDjxh;	//用户序号
	private Dict cardType;		//证件类型
	private String cardNo;		//证件号码
	private String fullName;	//姓名
	private String channelId;	//授权渠道代码（银行）
	private String channelName;	//授权渠道名称（银行）
	@JSONField(format="yyyy-MM-dd HH:mm")
	private Date authDate;		//授权日期起
	@JSONField(format="yyyy-MM-dd HH:mm")
	private Date expiredDate;	//授权结束时间
	private String term;		//授权期限
	private String authType;	//授权方式（代码）
	private String authContent;	//授权内容
	private String authCode;	//授权码
	private Date beginDate1;	//授权日期起（查询用）
	private Date beginDate2;	//授权日期止（查询用）
	private String status;		//状态，1、 正常到期 2、取消授权(授权历史表里才有)
	private String signData;	//签名值(md5后的结果)
	
	private String history;		//是否是查询历史信息，1：是，0：否
	
	
	private String userId;
	
	private String productId;	
	
	private String hjid;
	
	
	
	
	
	
	
	public String getHjid() {
		return hjid;
	}
	public void setHjid(String hjid) {
		this.hjid = hjid;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getUserDjxh() {
		return userDjxh;
	}
	public void setUserDjxh(Integer userDjxh) {
		this.userDjxh = userDjxh;
	}
	public Dict getCardType() {
		return cardType;
	}
	public void setCardType(Dict cardType) {
		this.cardType = cardType;
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
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Date getAuthDate() {
		return authDate;
	}
	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	public String getAuthContent() {
		return authContent;
	}
	public void setAuthContent(String authContent) {
		this.authContent = authContent;
	}
	public Date getBeginDate1() {
		return beginDate1;
	}
	public void setBeginDate1(java.sql.Date beginDate1) {
		this.beginDate1 = beginDate1;
	}
	public Date getBeginDate2() {
		return beginDate2;
	}
	public void setBeginDate2(java.sql.Date beginDate2) {
		this.beginDate2 = beginDate2;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getSignData() {
		return signData;
	}
	public void setSignData(Object signData) {
		BLOB blob = (BLOB)signData;
		String blobStr = "";
		try {
			blobStr = new String(blob.getBytes((long)1, (int)blob.length()),"GBK");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		this.signData = Md5Utils.hash(blobStr);
	}
	
	
}
