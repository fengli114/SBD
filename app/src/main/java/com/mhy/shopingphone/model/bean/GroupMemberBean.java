package com.mhy.shopingphone.model.bean;


import android.text.TextUtils;

import com.blankj.utilcode.util.EmptyUtils;
import com.mhy.shopingphone.model.bean.indexbar.BaseIndexPinyinBean;

import java.io.Serializable;

public class GroupMemberBean implements Serializable {

	private String name;   //显示的数据
	private String phone;
	private String contact_id;//联系人
	private String remark;
	private String account;
	private String nickName;
	private String phoneNumber;
	private String area;
	private String headerUrl;
	private String pinyin;
	private String phonebook_label;
	public String getPhonebook_label() {
		return phonebook_label;
	}

	public void setPhonebook_label(String phonebook_label) {
		this.phonebook_label = phonebook_label;
	}
	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccount() {
		return account;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getArea() {
		return area;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setHeaderUrl(String headerUrl) {
		this.headerUrl = headerUrl;
	}

	public String getHeaderUrl() {
		return headerUrl;
	}

	public String getFirstPinyin(){
		return !EmptyUtils.isEmpty(pinyin) ? pinyin.substring(0,1) : "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContact_id() {
		return contact_id;
	}

	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}

}
