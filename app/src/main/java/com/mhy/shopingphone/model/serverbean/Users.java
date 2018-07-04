package com.mhy.shopingphone.model.serverbean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 代理商
 * 
 * @author Administrator
 *
 */
public class Users {
	private String id;// id

	private String username; // 昵称

	private String password; // 密码

	private String email;// 邮箱

	private Date createtime;// 注册时间

	private Date logintime;// 最后登录时间

	private String loginip;// 登陆ip

	private Integer role;// 权限0管理员1代理商2商家3开发者4用户5财务账号

	private Boolean datastatus;// 数据状态

	private String mobile;// 手机号

	private BigDecimal blance;// 当前余额

	private String freeroute;// 费率

	private String parentid;// 代理商id

	private String agentlogo;// 代理商logo

	private String prefix;// 代理商前缀

	private String theme;// 后台皮肤

	private Boolean addcard;// 是否允许开卡

	private Boolean nextagent;// 是否允许开设下级代理

	private String androidversion;// 安卓版本号

	private String androidpath;// 安卓下载地址

	private String iosversion;// 苹果版本号

	private String iospath;// 苹果更新地址

	private Integer regimoney;// 注册赠送金额

	private Date endtime;// 账户过期时间

	private String pic;// 用户头像 //代理商头像

	private BigDecimal dkbl;// 商城抵扣比例

	private Boolean isshop; // 商户是否开通商城

	private Integer type;// 账户充值类型

	private String shopid;// 用户广告商id

	private String adid;// 代理商阿里妈妈广告id

	private BigDecimal profitratio;// 代理商分润比例

	private String appkey;// 阿里妈妈appkey

	private String appsecret;// 阿里妈妈appsecret

	private BigDecimal linebalance;// 线路余额

	private String pid;// 阿里妈妈推广位id

	private String dappkey;// 大淘客appkey

	private String sqappkey;// 转链key

	private Integer overdraft;// 透支金额

	private String appstore;// appstore是否显示

	private String appid;// 阿里支付appid

	private String keypath;// 私钥地址

	private String agentprefix;// 代理商前缀

	private String oneagent;// 顶级代理

	private Boolean sxxh;// 双透

	private Boolean isauth;// 认证状态

	private Boolean isalipay;// 线上支付

	private Boolean isprofit;// 淘宝返利

	private Boolean isaliprofit;// 线上支付返利

	private BigDecimal aliprofitratio;// 代理商分润比例

	private BigDecimal aliprofitmoney;// 代理商分润比例

	private BigDecimal profitmoney;// 代理商分润金额

	private Integer shopblance;// 空冲购物余额

	private Integer calltype;// 线路商1久话2魅逸

	public Users() {
		super();
	}

	public Users(String id, String username, String password, String email, Date createtime, Date logintime,
			String loginip, Integer role, Boolean datastatus, String mobile, BigDecimal blance, String freeroute,
			String parentid, String agentlogo, String prefix, String theme, Boolean addcard, Boolean nextagent,
			String androidversion, String androidpath, String iosversion, String iospath, Integer regimoney,
			Date endtime, String pic, BigDecimal dkbl, Boolean isshop, Integer type, String shopid, String adid,
			BigDecimal profitratio, String appkey, String appsecret, BigDecimal linebalance, String pid, String dappkey,
			String sqappkey, Integer overdraft, String appstore, String appid, String keypath, String agentprefix,
			String oneagent, Boolean sxxh, Boolean isauth, Boolean isalipay, Boolean isprofit, Boolean isaliprofit,
			BigDecimal aliprofitratio, BigDecimal aliprofitmoney, BigDecimal profitmoney, Integer shopblance,
			Integer calltype) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.createtime = createtime;
		this.logintime = logintime;
		this.loginip = loginip;
		this.role = role;
		this.datastatus = datastatus;
		this.mobile = mobile;
		this.blance = blance;
		this.freeroute = freeroute;
		this.parentid = parentid;
		this.agentlogo = agentlogo;
		this.prefix = prefix;
		this.theme = theme;
		this.addcard = addcard;
		this.nextagent = nextagent;
		this.androidversion = androidversion;
		this.androidpath = androidpath;
		this.iosversion = iosversion;
		this.iospath = iospath;
		this.regimoney = regimoney;
		this.endtime = endtime;
		this.pic = pic;
		this.dkbl = dkbl;
		this.isshop = isshop;
		this.type = type;
		this.shopid = shopid;
		this.adid = adid;
		this.profitratio = profitratio;
		this.appkey = appkey;
		this.appsecret = appsecret;
		this.linebalance = linebalance;
		this.pid = pid;
		this.dappkey = dappkey;
		this.sqappkey = sqappkey;
		this.overdraft = overdraft;
		this.appstore = appstore;
		this.appid = appid;
		this.keypath = keypath;
		this.agentprefix = agentprefix;
		this.oneagent = oneagent;
		this.sxxh = sxxh;
		this.isauth = isauth;
		this.isalipay = isalipay;
		this.isprofit = isprofit;
		this.isaliprofit = isaliprofit;
		this.aliprofitratio = aliprofitratio;
		this.aliprofitmoney = aliprofitmoney;
		this.profitmoney = profitmoney;
		this.shopblance = shopblance;
		this.calltype = calltype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
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

	public BigDecimal getBlance() {
		return blance;
	}

	public void setBlance(BigDecimal blance) {
		this.blance = blance;
	}

	public String getFreeroute() {
		return freeroute;
	}

	public void setFreeroute(String freeroute) {
		this.freeroute = freeroute == null ? null : freeroute.trim();
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid == null ? null : parentid.trim();
	}

	public String getAgentlogo() {
		return agentlogo;
	}

	public void setAgentlogo(String agentlogo) {
		this.agentlogo = agentlogo == null ? null : agentlogo.trim();
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix == null ? null : prefix.trim();
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme == null ? null : theme.trim();
	}

	public Boolean getAddcard() {
		return addcard;
	}

	public void setAddcard(Boolean addcard) {
		this.addcard = addcard;
	}

	public Boolean getNextagent() {
		return nextagent;
	}

	public void setNextagent(Boolean nextagent) {
		this.nextagent = nextagent;
	}

	public String getAndroidversion() {
		return androidversion;
	}

	public void setAndroidversion(String androidversion) {
		this.androidversion = androidversion == null ? null : androidversion.trim();
	}

	public String getAndroidpath() {
		return androidpath;
	}

	public void setAndroidpath(String androidpath) {
		this.androidpath = androidpath == null ? null : androidpath.trim();
	}

	public String getIosversion() {
		return iosversion;
	}

	public void setIosversion(String iosversion) {
		this.iosversion = iosversion == null ? null : iosversion.trim();
	}

	public String getIospath() {
		return iospath;
	}

	public void setIospath(String iospath) {
		this.iospath = iospath == null ? null : iospath.trim();
	}

	public Integer getRegimoney() {
		return regimoney;
	}

	public void setRegimoney(Integer regimoney) {
		this.regimoney = regimoney;
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

	public BigDecimal getDkbl() {
		return dkbl;
	}

	public void setDkbl(BigDecimal dkbl) {
		this.dkbl = dkbl;
	}

	public Boolean getIsshop() {
		return isshop;
	}

	public void setIsshop(Boolean isshop) {
		this.isshop = isshop;
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

	public String getAdid() {
		return adid;
	}

	public void setAdid(String adid) {
		this.adid = adid == null ? null : adid.trim();
	}

	public BigDecimal getProfitratio() {
		return profitratio;
	}

	public void setProfitratio(BigDecimal profitratio) {
		this.profitratio = profitratio;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey == null ? null : appkey.trim();
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret == null ? null : appsecret.trim();
	}

	public BigDecimal getLinebalance() {
		return linebalance;
	}

	public void setLinebalance(BigDecimal linebalance) {
		this.linebalance = linebalance;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getDappkey() {
		return dappkey;
	}

	public void setDappkey(String dappkey) {
		this.dappkey = dappkey == null ? null : dappkey.trim();
	}

	public String getSqappkey() {
		return sqappkey;
	}

	public void setSqappkey(String sqappkey) {
		this.sqappkey = sqappkey == null ? null : sqappkey.trim();
	}

	public Integer getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(Integer overdraft) {
		this.overdraft = overdraft;
	}

	public String getAppstore() {
		return appstore;
	}

	public void setAppstore(String appstore) {
		this.appstore = appstore == null ? null : appstore.trim();
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid == null ? null : appid.trim();
	}

	public String getKeypath() {
		return keypath;
	}

	public void setKeypath(String keypath) {
		this.keypath = keypath == null ? null : keypath.trim();
	}

	public String getAgentprefix() {
		return agentprefix;
	}

	public void setAgentprefix(String agentprefix) {
		this.agentprefix = agentprefix == null ? null : agentprefix.trim();
	}

	public String getOneagent() {
		return oneagent;
	}

	public void setOneagent(String oneagent) {
		this.oneagent = oneagent == null ? null : oneagent.trim();
	}

	public Boolean getSxxh() {
		return sxxh;
	}

	public void setSxxh(Boolean sxxh) {
		this.sxxh = sxxh;
	}

	public Boolean getIsauth() {
		return isauth;
	}

	public void setIsauth(Boolean isauth) {
		this.isauth = isauth;
	}

	public Boolean getIsalipay() {
		return isalipay;
	}

	public void setIsalipay(Boolean isalipay) {
		this.isalipay = isalipay;
	}

	public Boolean getIsprofit() {
		return isprofit;
	}

	public void setIsprofit(Boolean isprofit) {
		this.isprofit = isprofit;
	}

	public Boolean getIsaliprofit() {
		return isaliprofit;
	}

	public void setIsaliprofit(Boolean isaliprofit) {
		this.isaliprofit = isaliprofit;
	}

	public BigDecimal getAliprofitratio() {
		return aliprofitratio;
	}

	public void setAliprofitratio(BigDecimal aliprofitratio) {
		this.aliprofitratio = aliprofitratio;
	}

	public BigDecimal getAliprofitmoney() {
		return aliprofitmoney;
	}

	public void setAliprofitmoney(BigDecimal aliprofitmoney) {
		this.aliprofitmoney = aliprofitmoney;
	}

	public BigDecimal getProfitmoney() {
		return profitmoney;
	}

	public void setProfitmoney(BigDecimal profitmoney) {
		this.profitmoney = profitmoney;
	}

	public Integer getShopblance() {
		return shopblance;
	}

	public void setShopblance(Integer shopblance) {
		this.shopblance = shopblance;
	}

	public Integer getCalltype() {
		return calltype;
	}

	public void setCalltype(Integer calltype) {
		this.calltype = calltype;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", createtime=" + createtime + ", logintime=" + logintime + ", loginip=" + loginip + ", role=" + role
				+ ", datastatus=" + datastatus + ", mobile=" + mobile + ", blance=" + blance + ", freeroute="
				+ freeroute + ", parentid=" + parentid + ", agentlogo=" + agentlogo + ", prefix=" + prefix + ", theme="
				+ theme + ", addcard=" + addcard + ", nextagent=" + nextagent + ", androidversion=" + androidversion
				+ ", androidpath=" + androidpath + ", iosversion=" + iosversion + ", iospath=" + iospath
				+ ", regimoney=" + regimoney + ", endtime=" + endtime + ", pic=" + pic + ", dkbl=" + dkbl + ", isshop="
				+ isshop + ", type=" + type + ", shopid=" + shopid + ", adid=" + adid + ", profitratio=" + profitratio
				+ ", appkey=" + appkey + ", appsecret=" + appsecret + ", linebalance=" + linebalance + ", pid=" + pid
				+ ", dappkey=" + dappkey + ", sqappkey=" + sqappkey + ", overdraft=" + overdraft + ", appstore="
				+ appstore + ", appid=" + appid + ", keypath=" + keypath + ", agentprefix=" + agentprefix
				+ ", oneagent=" + oneagent + ", sxxh=" + sxxh + ", isauth=" + isauth + ", isalipay=" + isalipay
				+ ", isprofit=" + isprofit + ", isaliprofit=" + isaliprofit + ", aliprofitratio=" + aliprofitratio
				+ ", aliprofitmoney=" + aliprofitmoney + ", profitmoney=" + profitmoney + ", shopblance=" + shopblance
				+ ", calltype=" + calltype + "]";
	}

}