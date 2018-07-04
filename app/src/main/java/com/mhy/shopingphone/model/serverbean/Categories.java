package com.mhy.shopingphone.model.serverbean;

/**
 * 分类
 * 
 * @author Administrator
 *
 */
public class Categories {
	private String id;// id

	private String alicate;// 阿里分类

	private String cate;// 分类名称

	private String grade;// 优先级

	private String pic;// 分类图

	private Boolean datastatus;// 数据状态

	private String indexpic;// 首页图

	private String dpic;// 首页大图

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getAlicate() {
		return alicate;
	}

	public void setAlicate(String alicate) {
		this.alicate = alicate == null ? null : alicate.trim();
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate == null ? null : cate.trim();
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade == null ? null : grade.trim();
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

	public String getIndexpic() {
		return indexpic;
	}

	public void setIndexpic(String indexpic) {
		this.indexpic = indexpic == null ? null : indexpic.trim();
	}

	public String getDpic() {
		return dpic;
	}

	public void setDpic(String dpic) {
		this.dpic = dpic == null ? null : dpic.trim();
	}
}