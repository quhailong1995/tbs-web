package cn.jeeweb.modules.tbs.entity;

public class UserLoanReport {
	
	private DmWhProduct dmWhProduct;
	private Integer loanCount;
	private double loanTotalPrice;
	private Integer loanExpire;
	private Integer loanAdvanceRepay;
	public DmWhProduct getDmWhProduct() {
		return dmWhProduct;
	}
	public void setDmWhProduct(DmWhProduct dmWhProduct) {
		this.dmWhProduct = dmWhProduct;
	}
	public Integer getLoanCount() {
		return loanCount;
	}
	public void setLoanCount(Integer loanCount) {
		this.loanCount = loanCount;
	}
	public double getLoanTotalPrice() {
		return loanTotalPrice;
	}
	public void setLoanTotalPrice(double loanTotalPrice) {
		this.loanTotalPrice = loanTotalPrice;
	}
	public Integer getLoanExpire() {
		return loanExpire;
	}
	public void setLoanExpire(Integer loanExpire) {
		this.loanExpire = loanExpire;
	}
	public Integer getLoanAdvanceRepay() {
		return loanAdvanceRepay;
	}
	public void setLoanAdvanceRepay(Integer loanAdvanceRepay) {
		this.loanAdvanceRepay = loanAdvanceRepay;
	}
	
	
	

}
