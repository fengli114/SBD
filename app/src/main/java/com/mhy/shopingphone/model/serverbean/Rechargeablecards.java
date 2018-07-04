package com.mhy.shopingphone.model.serverbean;

import java.util.Date;

/**
 * 充值卡
 * 
 * @author Administrator
 *
 */
public class Rechargeablecards {
	private String id;// id

	private String cardno;// 卡号

	private String pwd;// 卡密

	private Integer money;// 面值

	private String parentid;// 代理商id

	private Integer status;// 充值卡状态：0未充值，1已经充值**

	private Integer termofvalidity;// 有效期

	private Integer probationperiod;// 使用期

	private String feelroute;// 卡费率

	private Boolean datastatus; // 数据状态

	private String batch;// 生成批次

	private Date createtime;// 开卡时间

	private Date usetime;// 充值时间**

	private String mobile;// 充值账户**

	private String picpath;// 扫码充值图片地址

	private Integer type;// 充值卡类型

	private String number;// 编号

	public Rechargeablecards() {
		super();
	}

	public Rechargeablecards(String id, String cardno, String pwd, Integer money, String parentid, Integer status,
			Integer termofvalidity, Integer probationperiod, String feelroute, Boolean datastatus, String batch,
			Date createtime, Date usetime, String mobile, String picpath, Integer type, String number) {
		super();
		this.id = id;
		this.cardno = cardno;
		this.pwd = pwd;
		this.money = money;
		this.parentid = parentid;
		this.status = status;
		this.termofvalidity = termofvalidity;
		this.probationperiod = probationperiod;
		this.feelroute = feelroute;
		this.datastatus = datastatus;
		this.batch = batch;
		this.createtime = createtime;
		this.usetime = usetime;
		this.mobile = mobile;
		this.picpath = picpath;
		this.type = type;
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
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

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid == null ? null : parentid.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTermofvalidity() {
		return termofvalidity;
	}

	public void setTermofvalidity(Integer termofvalidity) {
		this.termofvalidity = termofvalidity;
	}

	public Integer getProbationperiod() {
		return probationperiod;
	}

	public void setProbationperiod(Integer probationperiod) {
		this.probationperiod = probationperiod;
	}

	public String getFeelroute() {
		return feelroute;
	}

	public void setFeelroute(String feelroute) {
		this.feelroute = feelroute == null ? null : feelroute.trim();
	}

	public Boolean getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Boolean datastatus) {
		this.datastatus = datastatus;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch == null ? null : batch.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUsetime() {
		return usetime;
	}

	public void setUsetime(Date usetime) {
		this.usetime = usetime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath == null ? null : picpath.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number == null ? null : number.trim();
	}

	@Override
	public String toString() {
		return "Rechargeablecards [id=" + id + ", cardno=" + cardno + ", pwd=" + pwd + ", money=" + money
				+ ", parentid=" + parentid + ", status=" + status + ", termofvalidity=" + termofvalidity
				+ ", probationperiod=" + probationperiod + ", feelroute=" + feelroute + ", datastatus=" + datastatus
				+ ", batch=" + batch + ", createtime=" + createtime + ", usetime=" + usetime + ", mobile=" + mobile
				+ ", picpath=" + picpath + ", type=" + type + ", number=" + number + "]";
	}
}