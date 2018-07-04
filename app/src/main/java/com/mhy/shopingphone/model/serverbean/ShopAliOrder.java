package com.mhy.shopingphone.model.serverbean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 阿里订单
 * 
 * @author Administrator
 *
 */
public class ShopAliOrder {
	private String id;// id

	private Date createtime;// 创建时间

	private Date clicktime;// 点击时间

	private String title;// 标题

	private String cid;// 商品id

	private BigDecimal money;// 商品金额

	private BigDecimal outmoney; // 返现金额

	private BigDecimal jsmoney;// 结算金额

	private BigDecimal yjbl;// 佣金比例

	private BigDecimal yjmoney;// 佣金金额

	private String ordernum; // 订单号

	private String agentid;// 代理id

	private Boolean datastatus;// 数据类型

	private Integer status; // 0未申请返现1返现进行中2返现完成

	private Float reappearing;// 已经返现金额

	private Float notreappearing;// 未返金额

	private String userid;// 申请用户id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getClicktime() {
		return clicktime;
	}

	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid == null ? null : cid.trim();
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getOutmoney() {
		return outmoney;
	}

	public void setOutmoney(BigDecimal outmoney) {
		this.outmoney = outmoney;
	}

	public BigDecimal getJsmoney() {
		return jsmoney;
	}

	public void setJsmoney(BigDecimal jsmoney) {
		this.jsmoney = jsmoney;
	}

	public BigDecimal getYjbl() {
		return yjbl;
	}

	public void setYjbl(BigDecimal yjbl) {
		this.yjbl = yjbl;
	}

	public BigDecimal getYjmoney() {
		return yjmoney;
	}

	public void setYjmoney(BigDecimal yjmoney) {
		this.yjmoney = yjmoney;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum == null ? null : ordernum.trim();
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid == null ? null : agentid.trim();
	}

	public Boolean getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Boolean datastatus) {
		this.datastatus = datastatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Float getReappearing() {
		return reappearing;
	}

	public void setReappearing(Float reappearing) {
		this.reappearing = reappearing;
	}

	public Float getNotreappearing() {
		return notreappearing;
	}

	public void setNotreappearing(Float notreappearing) {
		this.notreappearing = notreappearing;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid == null ? null : userid.trim();
	}
}