package com.mhy.shopingphone.model.serverbean;

import java.util.Date;

/**
 * 短信内容
 * 
 * @author Administrator
 *
 */
public class Smsinfoes {
	private String id;// id

	private String mobile;// 手机号

	private Integer num;// 验证码

	private Date createtime; // 发送时间

	private Integer status;// 发送状态

	private String city; // 号码归属地

	private String agentid;// 代理商id

	private String apistatus;// api状态

	public Smsinfoes() {
		super();
	}

	public Smsinfoes(String id, String mobile, Integer num, Date createtime, Integer status, String city,
			String agentid, String apistatus) {
		super();
		this.id = id;
		this.mobile = mobile;
		this.num = num;
		this.createtime = createtime;
		this.status = status;
		this.city = city;
		this.agentid = agentid;
		this.apistatus = apistatus;
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid == null ? null : agentid.trim();
	}

	public String getApistatus() {
		return apistatus;
	}

	public void setApistatus(String apistatus) {
		this.apistatus = apistatus == null ? null : apistatus.trim();
	}

	@Override
	public String toString() {
		return "Smsinfoes [id=" + id + ", mobile=" + mobile + ", num=" + num + ", createtime=" + createtime
				+ ", status=" + status + ", city=" + city + ", agentid=" + agentid + ", apistatus=" + apistatus + "]";
	}
}