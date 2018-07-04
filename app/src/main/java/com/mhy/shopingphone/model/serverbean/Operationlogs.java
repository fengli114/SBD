package com.mhy.shopingphone.model.serverbean;

import java.util.Date;

/**
 * 操作日志
 * 
 * @author Administrator
 *
 */
public class Operationlogs {
	private String id;// ID

	private Integer type;// 类型

	private String content;// 操作内容

	private Date createtime;// 操作时间

	private String name;// 操作用户

	private Boolean datestatus;// 数据状态

	private String ipinfo;// ip信息

	public Operationlogs() {
		super();
	}

	public Operationlogs(String id, Integer type, String content, Date createtime, String name, Boolean datestatus,
			String ipinfo) {
		super();
		this.id = id;
		this.type = type;
		this.content = content;
		this.createtime = createtime;
		this.name = name;
		this.datestatus = datestatus;
		this.ipinfo = ipinfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Boolean getDatestatus() {
		return datestatus;
	}

	public void setDatestatus(Boolean datestatus) {
		this.datestatus = datestatus;
	}

	public String getIpinfo() {
		return ipinfo;
	}

	public void setIpinfo(String ipinfo) {
		this.ipinfo = ipinfo == null ? null : ipinfo.trim();
	}

	@Override
	public String toString() {
		return "Operationlogs [id=" + id + ", type=" + type + ", content=" + content + ", createtime=" + createtime
				+ ", name=" + name + ", datestatus=" + datestatus + ", ipinfo=" + ipinfo + "]";
	}

}