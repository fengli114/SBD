package com.mhy.shopingphone.model.serverbean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 淘宝客信息
 * 
 * @author Administrator
 *
 */
public class TbkInfo {
	private String id;// id

	private String tmid;// 商品id

	private String tbkurl;// 领卷链接

	private BigDecimal discount;// 抵扣金额

	private Integer remainCount; // 优惠卷剩余

	private BigDecimal commission;// 商品佣金

	private Date updatetime;// 更新时间

	private String shopid;// 商户id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getTmid() {
		return tmid;
	}

	public void setTmid(String tmid) {
		this.tmid = tmid == null ? null : tmid.trim();
	}

	public String getTbkurl() {
		return tbkurl;
	}

	public void setTbkurl(String tbkurl) {
		this.tbkurl = tbkurl == null ? null : tbkurl.trim();
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Integer getRemainCount() {
		return remainCount;
	}

	public void setRemainCount(Integer remainCount) {
		this.remainCount = remainCount;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid == null ? null : shopid.trim();
	}
}