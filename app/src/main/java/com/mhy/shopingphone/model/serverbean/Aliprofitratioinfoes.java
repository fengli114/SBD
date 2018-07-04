package com.mhy.shopingphone.model.serverbean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 代理商线上支付分润
 * 
 * @author Administrator
 *
 */
public class Aliprofitratioinfoes {
	private String id;//id

	private String agentid;//代理商id

	private String mobile;//手机号

	private Integer alimoney;//支付金额

	private BigDecimal profitmoney;//分润金额

	private Date createtime;//创建时间

	private BigDecimal profitratio;//分润比例

	private Boolean datastatus;//数据装填

	private String ordernum;//支付宝订单号

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public Integer getAlimoney() {
		return alimoney;
	}

	public void setAlimoney(Integer alimoney) {
		this.alimoney = alimoney;
	}

	public BigDecimal getProfitmoney() {
		return profitmoney;
	}

	public void setProfitmoney(BigDecimal profitmoney) {
		this.profitmoney = profitmoney;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public BigDecimal getProfitratio() {
		return profitratio;
	}

	public void setProfitratio(BigDecimal profitratio) {
		this.profitratio = profitratio;
	}

	public Boolean getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Boolean datastatus) {
		this.datastatus = datastatus;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum == null ? null : ordernum.trim();
	}
}