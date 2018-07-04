package com.mhy.shopingphone.model.serverbean;

/**
 * 发现
 * 
 * @author Administrator
 *
 */
public class Shopinfoes {
	private String id;// id

	private String agentid;// 代理商id

	private String logo;// 图标

	private String text; // 文字

	private String url;// 跳转地址

	private Integer type;// 类型1八大模块2滚动文字

	private Boolean datastatus;// 数据状态

	private Integer grade;// 优先级

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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo == null ? null : logo.trim();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text == null ? null : text.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Boolean getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Boolean datastatus) {
		this.datastatus = datastatus;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Shopinfoes [id=" + id + ", agentid=" + agentid + ", logo=" + logo + ", text=" + text + ", url=" + url
				+ ", type=" + type + ", datastatus=" + datastatus + ", grade=" + grade + "]";
	}
}