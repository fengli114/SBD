package com.mhy.shopingphone.model.serverbean;

import java.util.Date;

/**
 * 广告
 * 
 * @author Administrator
 *
 */
public class Adversiments {
	private String id;//id

	private String parentid;//代理商id

	private Integer adtype;//广告类型  1开机图  2拨号等待图   3拨号键盘广告   4商城广告

	private String url;//广告路径

	private Integer showcount;//展示次数

	private Integer hitscount; //点击次数

	private Boolean datastatus;//数据状态

	private String path;//广告路径

	private Date createtime;//上传时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid == null ? null : parentid.trim();
	}

	public Integer getAdtype() {
		return adtype;
	}

	public void setAdtype(Integer adtype) {
		this.adtype = adtype;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Integer getShowcount() {
		return showcount;
	}

	public void setShowcount(Integer showcount) {
		this.showcount = showcount;
	}

	public Integer getHitscount() {
		return hitscount;
	}

	public void setHitscount(Integer hitscount) {
		this.hitscount = hitscount;
	}

	public Boolean getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Boolean datastatus) {
		this.datastatus = datastatus;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path == null ? null : path.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}