package com.mhy.shopingphone.model.serverbean;

/**
 * 商城文字
 * 
 * @author Administrator
 *
 */
public class ShopText {
	private String id;// id

	private String text;// 文字

	private String pic;// 图片

	private Boolean datastatus;// 数据状态

	private Integer type;// 类型

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text == null ? null : text.trim();
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}