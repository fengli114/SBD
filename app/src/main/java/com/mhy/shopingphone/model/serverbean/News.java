package com.mhy.shopingphone.model.serverbean;

import java.util.Date;

/**
 * 新闻
 * 
 * @author Administrator
 *
 */
public class News {
	private String id;// id

	private Date createtime; // 创建时间

	private String title;// 标题

	private String pic;// 图片

	private String url;// 跳转地址

	private String from;// 来源

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
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

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from == null ? null : from.trim();
	}
}