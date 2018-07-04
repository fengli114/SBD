package com.mhy.shopingphone.model.serverbean;

import java.util.Date;

/**
 * api调用记录
 * 
 * @author Administrator
 *
 */
public class Apiinfoes {
	private String id;// id

	private String memo;// 接口描述

	private String jsondata;// 传入数据

	private String returndata;// 返回数据

	private Date usetime;// 调用时间

	private Boolean datastatus;// 数据状态

	private String useip;// 调用ip

	public Apiinfoes() {
		super();
	}

	public Apiinfoes(String id, String memo, String jsondata, String returndata, Date usetime, Boolean datastatus,
			String useip) {
		super();
		this.id = id;
		this.memo = memo;
		this.jsondata = jsondata;
		this.returndata = returndata;
		this.usetime = usetime;
		this.datastatus = datastatus;
		this.useip = useip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	public String getJsondata() {
		return jsondata;
	}

	public void setJsondata(String jsondata) {
		this.jsondata = jsondata == null ? null : jsondata.trim();
	}

	public String getReturndata() {
		return returndata;
	}

	public void setReturndata(String returndata) {
		this.returndata = returndata == null ? null : returndata.trim();
	}

	public Date getUsetime() {
		return usetime;
	}

	public void setUsetime(Date usetime) {
		this.usetime = usetime;
	}

	public Boolean getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(Boolean datastatus) {
		this.datastatus = datastatus;
	}

	public String getUseip() {
		return useip;
	}

	public void setUseip(String useip) {
		this.useip = useip == null ? null : useip.trim();
	}
}