package cn.jeeweb.modules.tbs.entity;

public class UserAuthReport {
	
	private DmWhProduct dmWhProduct;
	private Integer authCount;
	private String ringGrowth;
	private Integer cancelAuthCount;
	private Integer expireAuthCount;
	public DmWhProduct getDmWhProduct() {
		return dmWhProduct;
	}
	public void setDmWhProduct(DmWhProduct dmWhProduct) {
		this.dmWhProduct = dmWhProduct;
	}
	public Integer getAuthCount() {
		return authCount;
	}
	public void setAuthCount(Integer authCount) {
		this.authCount = authCount;
	}
	public String getRingGrowth() {
		return ringGrowth;
	}
	public void setRingGrowth(String ringGrowth) {
		this.ringGrowth = ringGrowth;
	}
	public Integer getCancelAuthCount() {
		return cancelAuthCount;
	}
	public void setCancelAuthCount(Integer cancelAuthCount) {
		this.cancelAuthCount = cancelAuthCount;
	}
	public Integer getExpireAuthCount() {
		return expireAuthCount;
	}
	public void setExpireAuthCount(Integer expireAuthCount) {
		this.expireAuthCount = expireAuthCount;
	}
	

}
