package com.mhy.shopingphone.model.serverbean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户订单
 * 
 * @author Administrator
 *
 */
public class ShopOrder {
	private String id;// id

	private String cid;// 商品id

	private String ordernum;// 订单编号

	private Date createtime;// 创建时间

	private String pic; // 商品图片

	private String discount;// 抵扣金额

	private String blance;// 抵扣后余额

	private String money;// 商品价格

	private String detailurl; // 详情url

	private Boolean datastatus; // 数据状态

	private String userid;// 用户id

	private String parentid; // 代理商id

	private BigDecimal commission;// 佣金

	private Integer status;//订单状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid == null ? null : cid.trim();
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum == null ? null : ordernum.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount == null ? null : discount.trim();
	}

	public String getBlance() {
		return blance;
	}

	public void setBlance(String blance) {
		this.blance = blance == null ? null : blance.trim();
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money == null ? null : money.trim();
	}

	public String getDetailurl() {
		return detailurl;
	}

	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl == null ? null : detailurl.trim();
	}

	public Boolean getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Boolean datastatus) {
		this.datastatus = datastatus;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid == null ? null : userid.trim();
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid == null ? null : parentid.trim();
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}