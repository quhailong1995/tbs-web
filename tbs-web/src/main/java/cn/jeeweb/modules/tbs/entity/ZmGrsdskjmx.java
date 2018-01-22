package cn.jeeweb.modules.tbs.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import cn.jeeweb.core.common.entity.AbstractEntity;


@TableName("zm_grsdskjmx")
@SuppressWarnings("serial")
public class ZmGrsdskjmx extends AbstractEntity<String>{
	
	private String id;
	@TableField(value = "userid")
	private String userId;
	@TableField(exist = false)
	private String userName;
	private String productId;
	@TableField(exist = false)
	private String productComment;
	@JSONField(format="yyyy-MM-dd")
	private Date skssqq;//税款所属期起
	@JSONField(format="yyyy-MM-dd")
	private Date skssqz;//税款所属期止
	private String grsdssdxmDm;//个人所得税所得项目代码
	private Double sre;//收入额
	private Double sbfjcs;//社保费缴存数
	private Double gjjjcs;//公积金缴存数
	private Double ynse;//应纳税额
	private Double snse;//实纳税额
	private Double ynssde;//应纳税所得额
	private String kjywrmc;//扣缴义务人名称
	
	private String kjywrdz;//扣缴义务人地址
	@JSONField(format="yyyy-MM-dd")
	private Date etlDate;//加工时间
	
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProductComment() {
		return productComment;
	}
	public void setProductComment(String productComment) {
		this.productComment = productComment;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Date getSkssqq() {
		return skssqq;
	}
	public void setSkssqq(Date skssqq) {
		this.skssqq = skssqq;
	}
	public Date getSkssqz() {
		return skssqz;
	}
	public void setSkssqz(Date skssqz) {
		this.skssqz = skssqz;
	}
	public String getGrsdssdxmDm() {
		return grsdssdxmDm;
	}
	public void setGrsdssdxmDm(String grsdssdxmDm) {
		this.grsdssdxmDm = grsdssdxmDm;
	}
	public Double getSre() {
		return sre;
	}
	public void setSre(Double sre) {
		this.sre = sre;
	}
	public Double getSbfjcs() {
		return sbfjcs;
	}
	public void setSbfjcs(Double sbfjcs) {
		this.sbfjcs = sbfjcs;
	}
	public Double getGjjjcs() {
		return gjjjcs;
	}
	public void setGjjjcs(Double gjjjcs) {
		this.gjjjcs = gjjjcs;
	}
	public Double getYnse() {
		return ynse;
	}
	public void setYnse(Double ynse) {
		this.ynse = ynse;
	}
	public Double getSnse() {
		return snse;
	}
	public void setSnse(Double snse) {
		this.snse = snse;
	}
	public Double getYnssde() {
		return ynssde;
	}
	public void setYnssde(Double ynssde) {
		this.ynssde = ynssde;
	}
	public String getKjywrmc() {
		return kjywrmc;
	}
	public void setKjywrmc(String kjywrmc) {
		this.kjywrmc = kjywrmc;
	}
	public String getKjywrdz() {
		return kjywrdz;
	}
	public void setKjywrdz(String kjywrdz) {
		this.kjywrdz = kjywrdz;
	}
	public Date getEtlDate() {
		return etlDate;
	}
	public void setEtlDate(Date etlDate) {
		this.etlDate = etlDate;
	}
	
	
	
	
	

}
