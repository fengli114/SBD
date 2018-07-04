package com.mhy.shopingphone.model.serverbean;

/**
 * 短信配置
 * 
 * @author Administrator
 *
 */
public class Smsconfigs {
	private String id;// id

	private String agentid;// 代理商id

	private String url; // 接口地址

	private Integer count;// 剩余条数

	private String layid;// 模板id

	private String signid;// 签名id

	private String tcid;// 套餐id

	private String mzid;// 面值id

	private Boolean datastatus;// 数据状态

	private Integer type; // 类型

	private String contenttype;// 内容类型

	public Smsconfigs() {
		super();
	}

	public Smsconfigs(String id, String agentid, String url, Integer count, String layid, String signid, String tcid,
			String mzid, Boolean datastatus, Integer type, String contenttype) {
		super();
		this.id = id;
		this.agentid = agentid;
		this.url = url;
		this.count = count;
		this.layid = layid;
		this.signid = signid;
		this.tcid = tcid;
		this.mzid = mzid;
		this.datastatus = datastatus;
		this.type = type;
		this.contenttype = contenttype;
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getLayid() {
		return layid;
	}

	public void setLayid(String layid) {
		this.layid = layid == null ? null : layid.trim();
	}

	public String getSignid() {
		return signid;
	}

	public void setSignid(String signid) {
		this.signid = signid == null ? null : signid.trim();
	}

	public String getTcid() {
		return tcid;
	}

	public void setTcid(String tcid) {
		this.tcid = tcid == null ? null : tcid.trim();
	}

	public String getMzid() {
		return mzid;
	}

	public void setMzid(String mzid) {
		this.mzid = mzid == null ? null : mzid.trim();
	}

	public Boolean getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Boolean datastatus) {
		this.datastatus = datastatus;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype == null ? null : contenttype.trim();
	}
}