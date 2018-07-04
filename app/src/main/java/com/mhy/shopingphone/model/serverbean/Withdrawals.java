package com.mhy.shopingphone.model.serverbean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 提现申请
 * 
 * @author Administrator
 *
 */
public class Withdrawals {
	private String id;// id

	private String userid;// 用户id

	private Date createtime;// 添加时间

	private Integer withdrawalstype;// 提现类型

	private BigDecimal money;// 提现金额

	private Integer status;// 提现状态

	private String datastatus;// 数据状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid == null ? null : userid.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getWithdrawalstype() {
		return withdrawalstype;
	}

	public void setWithdrawalstype(Integer withdrawalstype) {
		this.withdrawalstype = withdrawalstype;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(String datastatus) {
		this.datastatus = datastatus == null ? null : datastatus.trim();
	}
}