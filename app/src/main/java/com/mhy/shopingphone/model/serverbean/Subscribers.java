package com.mhy.shopingphone.model.serverbean;

import java.util.Date;

/**
 * 用户
 * 
 * @author Administrator
 *
 */
public class Subscribers {
	private String id;// id

	private String password;// 密码

	private Date createtime;// 注册时间

	private Date logintime;// 最后登录时间

	private String loginip;// 登陆ip

	private Integer role; // 权限0管理员1代理商2商家3开发者4用户5财务账号

	private Boolean datastatus;// 数据状态

	private String mobile;// 手机号

	private String parentid;// 代理商id

	private Date endtime;// 账户过期时间

	private String pic;// 用户头像 //代理商头像

	private Integer type;// 账户充值类型

	private String shopid;// 用户广告商id

	private String appstore;// appstore是否显示

	private Float integral; // 积分

	private String aliaccount;// 支付宝账户

	private Boolean alipay;// 是否绑定支付宝

	private Integer shopBalance;// 购物余额

	private String shopEnddate;

	private String birthday;// 生日

	private String gender;// 0未设置1男2女

	private String email;// 邮箱

	private String address;// 地址

	private String username;// 昵称

	private Boolean isyear;

	private Boolean ismonth;

	private Boolean isquarter;

	public Subscribers() {
		super();
	}

	public Subscribers(String id, String password, Date createtime, Date logintime, String loginip, Integer role,
			Boolean datastatus, String mobile, String parentid, Date endtime, String pic, Integer type, String shopid,
			String appstore, Float integral, String aliaccount, Boolean alipay, Integer shopBalance, String shopEnddate,
			String birthday, String gender, String email, String address, String username, Boolean isyear,
			Boolean ismonth, Boolean isquarter) {
		super();
		this.id = id;
		this.password = password;
		this.createtime = createtime;
		this.logintime = logintime;
		this.loginip = loginip;
		this.role = role;
		this.datastatus = datastatus;
		this.mobile = mobile;
		this.parentid = parentid;
		this.endtime = endtime;
		this.pic = pic;
		this.type = type;
		this.shopid = shopid;
		this.appstore = appstore;
		this.integral = integral;
		this.aliaccount = aliaccount;
		this.alipay = alipay;
		this.shopBalance = shopBalance;
		this.shopEnddate = shopEnddate;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.username = username;
		this.isyear = isyear;
		this.ismonth = ismonth;
		this.isquarter = isquarter;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip == null ? null : loginip.trim();
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
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

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid == null ? null : parentid.trim();
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid == null ? null : shopid.trim();
	}

	public String getAppstore() {
		return appstore;
	}

	public void setAppstore(String appstore) {
		this.appstore = appstore == null ? null : appstore.trim();
	}

	public Float getIntegral() {
		return integral;
	}

	public void setIntegral(Float integral) {
		this.integral = integral;
	}

	public String getAliaccount() {
		return aliaccount;
	}

	public void setAliaccount(String aliaccount) {
		this.aliaccount = aliaccount == null ? null : aliaccount.trim();
	}

	public Boolean getAlipay() {
		return alipay;
	}

	public void setAlipay(Boolean alipay) {
		this.alipay = alipay;
	}

	public Integer getShopBalance() {
		return shopBalance;
	}

	public void setShopBalance(Integer shopBalance) {
		this.shopBalance = shopBalance;
	}

	public String getShopEnddate() {
		return shopEnddate;
	}

	public void setShopEnddate(String shopEnddate) {
		this.shopEnddate = shopEnddate == null ? null : shopEnddate.trim();
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday == null ? null : birthday.trim();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender == null ? null : gender.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public Boolean getIsyear() {
		return isyear;
	}

	public void setIsyear(Boolean isyear) {
		this.isyear = isyear;
	}

	public Boolean getIsmonth() {
		return ismonth;
	}

	public void setIsmonth(Boolean ismonth) {
		this.ismonth = ismonth;
	}

	public Boolean getIsquarter() {
		return isquarter;
	}

	public void setIsquarter(Boolean isquarter) {
		this.isquarter = isquarter;
	}

	@Override
	public String toString() {
		return "Subscribers [id=" + id + ", password=" + password + ", createtime=" + createtime + ", logintime="
				+ logintime + ", loginip=" + loginip + ", role=" + role + ", datastatus=" + datastatus + ", mobile="
				+ mobile + ", parentid=" + parentid + ", endtime=" + endtime + ", pic=" + pic + ", type=" + type
				+ ", shopid=" + shopid + ", appstore=" + appstore + ", integral=" + integral + ", aliaccount="
				+ aliaccount + ", alipay=" + alipay + ", shopBalance=" + shopBalance + ", shopEnddate=" + shopEnddate
				+ ", birthday=" + birthday + ", gender=" + gender + ", email=" + email + ", address=" + address
				+ ", username=" + username + ", isyear=" + isyear + ", ismonth=" + ismonth + ", isquarter=" + isquarter
				+ "]";
	}

}