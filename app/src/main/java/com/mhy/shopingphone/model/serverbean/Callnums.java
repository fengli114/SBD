package com.mhy.shopingphone.model.serverbean;

/**
 * 回拨号码
 * 
 * @author Administrator
 *
 */
public class Callnums {
	private String id;// id

	private String agentid;// 代理商id

	private String nums;// 回拨号码

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid == null ? null : agentid.trim();
	}

	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums == null ? null : nums.trim();
	}
}