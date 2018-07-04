package com.mhy.shopingphone.model.bean.partner;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 作者： "RedRainM" on 2017/11/16 0016.
 * 描述：
 */
@Table(name="BindEntity")
public class BindEntity {

    /**
     * ID : 48e806cf-09a4-4cfd-8412-d80c1d5d06bd
     * UserID : 58162318-deca-4442-8e38-743b7729aa5b
     * Type : 3
     * AliAccount : 17704820771
     * BankName : 招商
     * BankNo : 6225751112130848
     * Name : 随便打你
     * DataStatus : true
     */
    @Column(
            name = "id",
            isId = true,
            autoGen = false
    )

    private String id = "1";

    @Column(name = "Type")
    private int Type;
    @Column(name = "AliAccount")
    private String AliAccount;
    @Column(name = "BankName")
    private String BankName;
    @Column(name = "BankNo")
    private String BankNo;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Mobile")
    private String Mobile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public String getAliAccount() {
        return AliAccount;
    }

    public void setAliAccount(String AliAccount) {
        this.AliAccount = AliAccount;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String BankName) {
        this.BankName = BankName;
    }

    public String getBankNo() {
        return BankNo;
    }

    public void setBankNo(String BankNo) {
        this.BankNo = BankNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    @Override
    public String toString() {
        return "BindEntity{" +
                "id='" + id + '\'' +
                ", Type=" + Type +
                ", AliAccount='" + AliAccount + '\'' +
                ", BankName='" + BankName + '\'' +
                ", BankNo='" + BankNo + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }
}
