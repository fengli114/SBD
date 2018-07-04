package com.mhy.shopingphone.model.serverbean;

/**
 * 号码归属地
 * 
 * @author Administrator
 *
 */
public class Attributions {
	private String id;// id

	private Integer number;// 号码

	private Integer prefix;// 号码前缀

	private String operator;// 运营商

	private Integer sectionno;// 手机号段

	private String province;// 归属省

	private String city;// 归属市

	private Integer areacode;// 地区编码

	private Integer postnum;// 邮编

	public Attributions() {
		super();
	}

	public Attributions(String id, Integer number, Integer prefix, String operator, Integer sectionno, String province,
			String city, Integer areacode, Integer postnum) {
		super();
		this.id = id;
		this.number = number;
		this.prefix = prefix;
		this.operator = operator;
		this.sectionno = sectionno;
		this.province = province;
		this.city = city;
		this.areacode = areacode;
		this.postnum = postnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getPrefix() {
		return prefix;
	}

	public void setPrefix(Integer prefix) {
		this.prefix = prefix;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator == null ? null : operator.trim();
	}

	public Integer getSectionno() {
		return sectionno;
	}

	public void setSectionno(Integer sectionno) {
		this.sectionno = sectionno;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public Integer getAreacode() {
		return areacode;
	}

	public void setAreacode(Integer areacode) {
		this.areacode = areacode;
	}

	public Integer getPostnum() {
		return postnum;
	}

	public void setPostnum(Integer postnum) {
		this.postnum = postnum;
	}
}