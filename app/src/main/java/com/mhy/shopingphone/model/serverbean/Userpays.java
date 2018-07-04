package com.mhy.shopingphone.model.serverbean;

/**
 * 提现信息
 * 
 * @author Administrator
 *
 */
public class Userpays {
	private String id;// id

	private String userid;// 用户id

	private Integer type;// 类型

	private String aliaccount;// 支付宝

	private String bankname;// 银行名称

	private String bankno;// 银行卡号

	private String name;// 姓名

	private Boolean datastatus;// 数据状态

	private String mobile;// 提现通知手机号

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAliaccount() {
		return aliaccount;
	}

	public void setAliaccount(String aliaccount) {
		this.aliaccount = aliaccount == null ? null : aliaccount.trim();
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname == null ? null : bankname.trim();
	}

	public String getBankno() {
		return bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno == null ? null : bankno.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Boolean getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Boolean datastatus) {
		this.datastatus = datastatus;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}
}