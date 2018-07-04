package com.mhy.shopingphone.model.serverbean;

import java.util.Date;

/**
 * 注册记录
 * 
 * @author Administrator
 *
 */
public class Registerinfoes {
	private String id;// id

	private String mobile;// 手机号

	private Date registertime;// 注册时间

	private String registerip;// 注册ip

	private String address;// 注册归属地

	private String parentid;// 归属代理商

	private String mobileaddress;// 号码归属地

	private Boolean datastatus;// 数据状态

	public Registerinfoes() {
		super();
	}

	public Registerinfoes(String id, String mobile, Date registertime, String registerip, String address,
			String parentid, String mobileaddress, Boolean datastatus) {
		super();
		this.id = id;
		this.mobile = mobile;
		this.registertime = registertime;
		this.registerip = registerip;
		this.address = address;
		this.parentid = parentid;
		this.mobileaddress = mobileaddress;
		this.datastatus = datastatus;
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

	public Date getRegistertime() {
		return registertime;
	}

	public void setRegistertime(Date registertime) {
		this.registertime = registertime;
	}

	public String getRegisterip() {
		return registerip;
	}

	public void setRegisterip(String registerip) {
		this.registerip = registerip == null ? null : registerip.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid == null ? null : parentid.trim();
	}

	public String getMobileaddress() {
		return mobileaddress;
	}

	public void setMobileaddress(String mobileaddress) {
		this.mobileaddress = mobileaddress == null ? null : mobileaddress.trim();
	}

	public Boolean getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Boolean datastatus) {
		this.datastatus = datastatus;
	}

	@Override
	public String toString() {
		return "Registerinfoes [id=" + id + ", mobile=" + mobile + ", registertime=" + registertime + ", registerip="
				+ registerip + ", address=" + address + ", parentid=" + parentid + ", mobileaddress=" + mobileaddress
				+ ", datastatus=" + datastatus + "]";
	}
}