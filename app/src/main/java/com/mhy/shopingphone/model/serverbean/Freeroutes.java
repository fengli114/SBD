package com.mhy.shopingphone.model.serverbean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 费率
 * 
 * @author Administrator
 *
 */
public class Freeroutes {
	private String id;// id

	private String name;// 费率名称

	private BigDecimal feelroute;// 费率

	private Boolean datastatus;// 数据状态

	private Integer ratelength;// 计费时长

	private Date createtime; // 添加时间

	public Freeroutes() {
		super();
	}

	public Freeroutes(String id, String name, BigDecimal feelroute, Boolean datastatus, Integer ratelength,
			Date createtime) {
		super();
		this.id = id;
		this.name = name;
		this.feelroute = feelroute;
		this.datastatus = datastatus;
		this.ratelength = ratelength;
		this.createtime = createtime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public BigDecimal getFeelroute() {
		return feelroute;
	}

	public void setFeelroute(BigDecimal feelroute) {
		this.feelroute = feelroute;
	}

	public Boolean getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Boolean datastatus) {
		this.datastatus = datastatus;
	}

	public Integer getRatelength() {
		return ratelength;
	}

	public void setRatelength(Integer ratelength) {
		this.ratelength = ratelength;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Freeroutes [id=" + id + ", name=" + name + ", feelroute=" + feelroute + ", datastatus=" + datastatus
				+ ", ratelength=" + ratelength + ", createtime=" + createtime + "]";
	}

}