package com.mhy.shopingphone.model.serverbean;

import java.util.Date;

/**
 * 充值记录
 * 
 * @author Administrator
 *
 */
public class Payinfoes {
	private String id; // id

	private String mobile;// 充值手机号

	private String cardno;// 卡号

	private String pwd;// 卡密

	private String parentid;// 代理商id

	private Boolean status;// 2提交3到账

	private Integer money;// 充值金额

	private Boolean datastatus;// 数据状态

	private Date paytime;// 充值时间

	private Integer type;// 充值类型 1话费卡/购物卡 2空中充值 3在线充值

	private String alistatus;// 支付宝状态

	private String ordernum; // 支付宝订单

	private String alimoney;// 支付金额

	private Integer cardtype;// 充值卡类型1话费卡2购物卡

	public Payinfoes() {
		super();
	}

	public Payinfoes(String id, String mobile, String cardno, String pwd, String parentid, Boolean status,
			Integer money, Boolean datastatus, Date paytime, Integer type, String alistatus, String ordernum,
			String alimoney, Integer cardtype) {
		super();
		this.id = id;
		this.mobile = mobile;
		this.cardno = cardno;
		this.pwd = pwd;
		this.parentid = parentid;
		this.status = status;
		this.money = money;
		this.datastatus = datastatus;
		this.paytime = paytime;
		this.type = type;
		this.alistatus = alistatus;
		this.ordernum = ordernum;
		this.alimoney = alimoney;
		this.cardtype = cardtype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno == null ? null : cardno.trim();
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd == null ? null : pwd.trim();
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid == null ? null : parentid.trim();
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Boolean getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Boolean datastatus) {
		this.datastatus = datastatus;
	}

	public Date getPaytime() {
		return paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAlistatus() {
		return alistatus;
	}

	public void setAlistatus(String alistatus) {
		this.alistatus = alistatus == null ? null : alistatus.trim();
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum == null ? null : ordernum.trim();
	}

	public String getAlimoney() {
		return alimoney;
	}

	public void setAlimoney(String alimoney) {
		this.alimoney = alimoney == null ? null : alimoney.trim();
	}

	public Integer getCardtype() {
		return cardtype;
	}

	public void setCardtype(Integer cardtype) {
		this.cardtype = cardtype;
	}

	@Override
	public String toString() {
		return "Payinfoes [id=" + id + ", mobile=" + mobile + ", cardno=" + cardno + ", pwd=" + pwd + ", parentid="
				+ parentid + ", status=" + status + ", money=" + money + ", datastatus=" + datastatus + ", paytime="
				+ paytime + ", type=" + type + ", alistatus=" + alistatus + ", ordernum=" + ordernum + ", alimoney="
				+ alimoney + ", cardtype=" + cardtype + "]";
	}
}