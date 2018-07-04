package com.mhy.shopingphone.model.serverbean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息
 * 
 * @author Administrator
 *
 */
public class Commodities {
	private String id;// 商品id

	private String tmid;// 淘宝商品id

	private String name;// 商品名称

	private String pic;// 商品主图

	private String detailurl;// 详情地址

	private String category;// 分类名称

	private String tbkurl;// 淘宝客url

	private BigDecimal money;// 商品价格

	private Integer salesvolume;// 商品销量

	private BigDecimal commission;// 商品佣金

	private Date endtime;// 结束日期

	private String tgurl;// 推广url

	private Boolean datastatus;// 数据状态

	private Boolean discount;// 抵扣金额

	private Boolean isby;// 9.9商品

	private Boolean isjx;// 精选商品

	private Boolean isall;// 全部分类

	private Boolean ishot;// 热门搜索

	private Date createtime;// 添加时间

	private String yhstart;// 优惠卷面值

	private Integer remainCount;// 优惠卷剩余

	private String shopid;// 商户id

	private Boolean isnew;// 是否是2.0产品

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
	}

	public String getDetailurl() {
		return detailurl;
	}

	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl == null ? null : detailurl.trim();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category == null ? null : category.trim();
	}

	public String getTbkurl() {
		return tbkurl;
	}

	public void setTbkurl(String tbkurl) {
		this.tbkurl = tbkurl == null ? null : tbkurl.trim();
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getSalesvolume() {
		return salesvolume;
	}

	public void setSalesvolume(Integer salesvolume) {
		this.salesvolume = salesvolume;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getTgurl() {
		return tgurl;
	}

	public void setTgurl(String tgurl) {
		this.tgurl = tgurl == null ? null : tgurl.trim();
	}

	public Boolean getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Boolean datastatus) {
		this.datastatus = datastatus;
	}

	public Boolean getDiscount() {
		return discount;
	}

	public void setDiscount(Boolean discount) {
		this.discount = discount;
	}

	public Boolean getIsby() {
		return isby;
	}

	public void setIsby(Boolean isby) {
		this.isby = isby;
	}

	public Boolean getIsjx() {
		return isjx;
	}

	public void setIsjx(Boolean isjx) {
		this.isjx = isjx;
	}

	public Boolean getIsall() {
		return isall;
	}

	public void setIsall(Boolean isall) {
		this.isall = isall;
	}

	public Boolean getIshot() {
		return ishot;
	}

	public void setIshot(Boolean ishot) {
		this.ishot = ishot;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getYhstart() {
		return yhstart;
	}

	public void setYhstart(String yhstart) {
		this.yhstart = yhstart == null ? null : yhstart.trim();
	}

	public Integer getRemainCount() {
		return remainCount;
	}

	public void setRemainCount(Integer remainCount) {
		this.remainCount = remainCount;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid == null ? null : shopid.trim();
	}

	public Boolean getIsnew() {
		return isnew;
	}

	public void setIsnew(Boolean isnew) {
		this.isnew = isnew;
	}
}