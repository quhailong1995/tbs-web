package cn.jeeweb.modules.tbs.entity;

import java.util.Date;

import cn.jeeweb.core.common.entity.DataEntity;
import cn.jeeweb.modules.sys.entity.Dict;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("tb_user_loan")
@SuppressWarnings("serial")
public class UserLoan extends DataEntity<String>{
	
	private String id;			//编号
	private Integer userDjxh;	//用户序号
	private Dict cardType;		//证件类型
	private String cardNo;		//证件号码
	private String fullName;	//姓名
	private String channelId;	//贷款银行
	private String channelName;	//贷款银行中文名
	@JSONField(format="yyyy-MM-dd HH:mm")
	private Date loanBegin;		//放贷时间
	@JSONField(format="yyyy-MM-dd HH:mm")
	private Date loanEnd;		//贷款终止时间
	private String term;		//期限 月份数
	private Double loanMoney;	//贷款金额
	
	private Date loanEnd1;		//贷款终止时间起（查询用）
	private Date loanEnd2;		//贷款终止时间止（查询用）
	
	
	@Override
	public String getId() {
		return this.id;
	}
	@Override
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
	public Date getLoanBegin() {
		return loanBegin;
	}
	public void setLoanBegin(Date loanBegin) {
		this.loanBegin = loanBegin;
	}
	public Date getLoanEnd() {
		return loanEnd;
	}
	public void setLoanEnd(Date loanEnd) {
		this.loanEnd = loanEnd;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public Double getLoanMoney() {
		return loanMoney;
	}
	public void setLoanMoney(Double loanMoney) {
		this.loanMoney = loanMoney;
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
	public Date getLoanEnd1() {
		return loanEnd1;
	}
	public void setLoanEnd1(java.sql.Date loanEnd1) {
		this.loanEnd1 = loanEnd1;
	}
	public Date getLoanEnd2() {
		return loanEnd2;
	}
	public void setLoanEnd2(java.sql.Date loanEnd2) {
		this.loanEnd2 = loanEnd2;
	}
	
	
}
