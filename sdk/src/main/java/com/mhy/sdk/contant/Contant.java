package com.mhy.sdk.contant;

import java.util.List;

/**
 * 作者： "RedRainM" on 2017/12/20 0020.
 * 描述：
 */


public class Contant {
    public final static String URL_TEST = "http://admin.sbdznkj.com/";
    //    public final static String URL_TEST = "http://192.168.13.105/";//内网
    public static boolean IsDebug = false;

    /**
     * 支付
     */
    public static final int PAY_ZFB = 0x7;
    public static final int PAY_CANCEL = 0x8;
    public static final int PAY_FAIL = 0x9;

    public static String URLKey = "Q3VzdG9tQmFzZTY0";//固定key 参数时候传

    public static String BGMURL = "";//登陆界面url

    public static String Moblie = "";//登陆界面url

    public static String profitMoney = "";//合伙人的money
    public static String gwjMoney = "";//购物卡余额
    public static String phoneMoney = "";//购物卡余额
    public static String zfbAcount = "";//
    public static String JF_STRING = "";//
    public static String PARTNER_ID = "";//合伙人

    public static String SCAN_QR_CODE = "";//扫描的二维码

    public static String URL_IMAGE = "http://admin.sbdznkj.com";//取图片
    public static String URL_IMAG_ICON = "";//头像

    public static String PARENTID = "58162318-deca-4442-8e38-743b7729aa5b";//代理商id默认
    /*
   * 商品
   * */
    public static String GOODS_URL = "Vm9pcEFwaQ/OSNAODSI";
    public static String GOODS_URLHead = "Vm9pcEFwaQ/GetShopIndex/";

    /**
     * 版本控制
     */
    public static String ANDROID_ID = "Vm9pcEFwaQ/GetVersion/";
    /*
     * 订单
      * */
    public static String ORDER_URL = "Vm9pcEFwaQ/OSDHOAFD";
    public static String ORDER_URLHead = "Vm9pcEFwaQ/GetOrder/";

    /*
 * 企业动态
 * */
    public static String DYNAMIC_URL = "Vm9pcEFwaQ/OSDFpwerSDUf";
    public static String DYNAMIC_URLHead = "Vm9pcEFwaQ/GetNotice/";


    //新闻
    public static String NEW_URL = "Vm9pcEFwaQ/SODNGAOLN";
    /*
  * 上传头像文件
  * */
    public static String PPLOADFILE_URL = "Vm9pcEFwaQ/IUODSfb";

    /*
  * 所有广告
  * */
    public static String ALLADV_URL = "Vm9pcEFwaQ/dsaofiwhe";
    public static String ALLADV_URLHead = "Vm9pcEFwaQ/GetAd/";
    /*
   * 商户界面
   * */
    public static String SHOPPING_URL = "Vm9pcEFwaQ/OSDFpwer";
    public static String SHOPPING_URLHead = "Vm9pcEFwaQ/GetShop/";
    /*
     * SHOPbanner
     * */
    public static String SHOPPING_BANNER = "Vm9pcEFwaQ/OSDHAO/";
    public static String SHOPPING_BANNERHead = "Vm9pcEFwaQ/GetShopInfo/";

    /**
     * 获取分类
     */
    public static String GET_TAB_URL = "Vm9pcEFwaQ/SONDSOA";
    public static String GET_TAB_URLHead = "Vm9pcEFwaQ/GetCategory/";

    //联系我们
    public static String URL_LIANXI = "Vm9pcEFwaQ/ABOUT?id=";

    //帮助中心
    public static String URL_HELP = "Vm9pcEFwaQ/hindex?agentid=";

    //企业管家
    public static String URL_HOUSEKEEPER = "http://admin.sbdznkj.com/";

    /*
     * 判断用户是否存在
     * */
    public static String IS_USER = "Vm9pcEFwaQ/ODSIOSA";
    public static String IS_USER_URLHead = "Vm9pcEFwaQ/Register/";

    /*
  * 登录头
  * */
    public static String LOGIN_URL = "Vm9pcEFwaQ/OSdfoapsdf";//登录接口地址
    public static String LOGIN_URLHead = "Vm9pcEFwaQ/Login/";
    /*
       * 合伙人主页
       * */
    public static String PARTNER_SHIP = "Vm9pcEFwaQ/OSDHAOW/";
    public static String PARTNER_SHIP_URLHead = "Vm9pcEFwaQ/GetIndexInfo/";
    /*
    * 查询余额
    * */
    public static String PRSONAlINFORMATION_URL = "Vm9pcEFwaQ/ISODFHkjl";
    public static String PRSONAlINFORMATION_URLHead = "Vm9pcEFwaQ/GetBlance/";
    /*
       * 获得启动图
       * */
    public static String BOOTDIAGRAM_URL = "Vm9pcEFwaQ/SDOBEWLebn";
    public static String BOOTDIAGRAM_URLHead = "Vm9pcEFwaQ/UpdatePwd/";

    /*
  * 通讯录接口
  * */
    public static String MAIlLIST_URL = "Vm9pcEFwaQ/SDOFBGS";
    public static String MAIlLIST_URLHead = "Vm9pcEFwaQ/GetNum/";

    /*
   * 修改密码
   * */
    public static String GET_NEW_PWD = "Vm9pcEFwaQ/SODNGNsdfioSDi";
    public static String GET_NEW_PWD_URLHead = "Vm9pcEFwaQ/UpdatePwd/";

    /*
   * 验证码接口
   * */
    public static String VERIFICATIONCODE_URL = "Vm9pcEFwaQ/ODSFNBSAa";
    public static String CODE_URLHead = "Vm9pcEFwaQ/SmsInfo/";

    /*
     * 注册
    * */
    public static String REGISTER_URL = "Vm9pcEFwaQ/HUyewk";
    public static String REGISTER_URLHead = "Vm9pcEFwaQ/Register/";

    /*
     * 合伙人登陆接口
     * */
    public static String PARTNER_LOGIN = "Vm9pcEFwaQ/SOIEHWNL";
    public static String PARTNER_LOGIN_URLHead = "Vm9pcEFwaQ/AgentLogin/";

    /*
    * 充值
    * */
    public static String RECHARGE_URL = "Vm9pcEFwaQ/SBDFbsdfeF";
    public static String RECHARGE_URLHead = "Vm9pcEFwaQ/Payinfo/";

    /*
   * 回拨
   * */
    public static String DIALLBACK_URL = "Vm9pcEFwaQ/YSDlsdf";
    public static String DIALLBACK_URLHead = "Vm9pcEFwaQ/CallBack/";
    /*
* 抵换
* */
    public static String TAKE_URL = "Vm9pcEFwaQ/SIDOSER";
    public static String TAKE_URLHead = "Vm9pcEFwaQ/ShopByBlance/";
    /*
    * 商品类型
    * */
    public static String SHOP_TYPE_URL = "Vm9pcEFwaQ/SONDSOASDUIY";
    public static String SHOP_TYPE_URLHead = "Vm9pcEFwaQ/GetCategory/";

    /*
       * 团队成员和收益
       * */
    public static String MY_TEAM = "Vm9pcEFwaQ/OSDUIASOE/";
    public static String MY_TEAM_URLHead = "Vm9pcEFwaQ/GetUserInfo/";

    /*
 * 申请提现
 * */
    public static String TIXIAN_MONEY = "Vm9pcEFwaQ/OSDUIHASO/";
    public static String TIXIAN_MONEY_URLHead = "Vm9pcEFwaQ/AddPayInfo/";

    /*
   * 绑定提现账户
   * */
    public static String BIND_USER = "Vm9pcEFwaQ/SDIOjsdfo/";
    public static String BIND_USER_URLHead = "Vm9pcEFwaQ/GetIndexInfo/";

    /*
 * 获取提现记录
 * */
    public static String TIXIAN_RECORD = "Vm9pcEFwaQ/ShOSUISD/";
    public static String TIXIAN_RECORD_URLHead = "Vm9pcEFwaQ/GetPayInfo/";

    /*
       * 收益订单
       * */
    public static String TX_ORDER = "Vm9pcEFwaQ/OSDHOAs/";
    public static String TX_ORDER_URLHead = "Vm9pcEFwaQ/GetOrders/";

    /*
  * 获取阿里支付参数
  * */
    public static String ZFB_RECORD = "/Vm9pcEFwaQ/OISDOSweDUISo";
    public static String ZFB_RECORD_URLHead = "Vm9pcEFwaQ/AliPram/";

    /*
   * 获取充值记录
   * */
    public static String RECHARGE_RECORD = "Vm9pcEFwaQ/OSHDBFBEW";
    public static String RECHARGE_RECORD_URLHead = "Vm9pcEFwaQ/GetPayInfo/";

    /*
* 话单查询
* */
    public static String WORDlISTQUERY_URL = "Vm9pcEFwaQ/SODIFNasd";
    public static String WORDlISTQUERY_URLHead = "Vm9pcEFwaQ/GetHD/";
}
