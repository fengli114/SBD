package com.mhy.shopingphone.model.serverbean;

/**
 * 线上支付
 * 
 * @author Administrator
 *
 */
public class Rechargeratios {
	private String id;// id

	private String agentid;// 代理商id

	private Integer money;// 到账话费

	private Integer alimoney;// 实付金额

	private Integer grade;// 优先级

	private String pic;// 图片地址

	private Boolean datastatus;// 数据状态

	private Integer shopmoney;// 购物金

	private String memo;// 备注信息

	private String probationperiod;// 有效期

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid == null ? null : agentid.trim();
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getAlimoney() {
		return alimoney;
	}

	public void setAlimoney(Integer alimoney) {
		this.alimoney = alimoney;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
	}

	public Boolean getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Boolean datastatus) {
		this.datastatus = datastatus;
	}

	public Integer getShopmoney() {
		return shopmoney;
	}

	public void setShopmoney(Integer shopmoney) {
		this.shopmoney = shopmoney;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	public String getProbationperiod() {
		return probationperiod;
	}

	public void setProbationperiod(String probationperiod) {
		this.probationperiod = probationperiod == null ? null : probationperiod.trim();
	}
}