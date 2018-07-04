package com.mhy.shopingphone.model.serverbean;

import java.util.Date;

/**
 * 帮助中心
 * 
 * @author Administrator
 *
 */
public class Helpers {
	private String id;// id

	private String agentid;// 代理商id

	private Date createtime;// 添加时间

	private String title; // 标题

	private String content;// 内容

	private String pic;// 图片

	private Boolean datastatus;// 数据状态

	public Helpers() {
		super();
	}

	public Helpers(String id, String agentid, Date createtime, String title, String content, String pic,
			Boolean datastatus) {
		super();
		this.id = id;
		this.agentid = agentid;
		this.createtime = createtime;
		this.title = title;
		this.content = content;
		this.pic = pic;
		this.datastatus = datastatus;
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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
	}

	public Boolean getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Boolean datastatus) {
		this.datastatus = datastatus;
	}

	@Override
	public String toString() {
		return "Helpers [id=" + id + ", agentid=" + agentid + ", createtime=" + createtime + ", title=" + title
				+ ", content=" + content + ", pic=" + pic + ", datastatus=" + datastatus + "]";
	}

}