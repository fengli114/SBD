package com.mhy.shopingphone.model.serverbean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 话单
 * 
 * @author Administrator
 *
 */
public class Callbackinfoes {
	private String id;// id

	private String caller;// 主叫

	private String calle164;// 被叫

	private Date calltime;// 回拨时间

	private String status; // api接口状态

	private String parentid; // 代理商id

	private String sysnum;// 回拨号

	private Date fwdtime;// 接通时间

	private Date endtime;// 结束时间

	private BigDecimal money;// 通话费用

	private BigDecimal omoney;// 线路费用

	private Integer timecount;// 通话时长

	private String shopid;// 商户id

	public Callbackinfoes() {
		super();
	}

	public Callbackinfoes(String id, String caller, String calle164, Date calltime, String status, String parentid,
			String sysnum, Date fwdtime, Date endtime, BigDecimal money, BigDecimal omoney, Integer timecount,
			String shopid) {
		super();
		this.id = id;
		this.caller = caller;
		this.calle164 = calle164;
		this.calltime = calltime;
		this.status = status;
		this.parentid = parentid;
		this.sysnum = sysnum;
		this.fwdtime = fwdtime;
		this.endtime = endtime;
		this.money = money;
		this.omoney = omoney;
		this.timecount = timecount;
		this.shopid = shopid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller == null ? null : caller.trim();
	}

	public String getCalle164() {
		return calle164;
	}

	public void setCalle164(String calle164) {
		this.calle164 = calle164 == null ? null : calle164.trim();
	}

	public Date getCalltime() {
		return calltime;
	}

	public void setCalltime(Date calltime) {
		this.calltime = calltime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid == null ? null : parentid.trim();
	}

	public String getSysnum() {
		return sysnum;
	}

	public void setSysnum(String sysnum) {
		this.sysnum = sysnum == null ? null : sysnum.trim();
	}

	public Date getFwdtime() {
		return fwdtime;
	}

	public void setFwdtime(Date fwdtime) {
		this.fwdtime = fwdtime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getOmoney() {
		return omoney;
	}

	public void setOmoney(BigDecimal omoney) {
		this.omoney = omoney;
	}

	public Integer getTimecount() {
		return timecount;
	}

	public void setTimecount(Integer timecount) {
		this.timecount = timecount;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid == null ? null : shopid.trim();
	}

	@Override
	public String toString() {
		return "Callbackinfoes [id=" + id + ", caller=" + caller + ", calle164=" + calle164 + ", calltime=" + calltime
				+ ", status=" + status + ", parentid=" + parentid + ", sysnum=" + sysnum + ", fwdtime=" + fwdtime
				+ ", endtime=" + endtime + ", money=" + money + ", omoney=" + omoney + ", timecount=" + timecount
				+ ", shopid=" + shopid + "]";
	}

}