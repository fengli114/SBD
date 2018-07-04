package com.mhy.shopingphone.api;

import com.mhy.shopingphone.model.bean.AndroidBean;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.PayTypeBean;
import com.mhy.shopingphone.model.bean.RechargeBean;
import com.mhy.shopingphone.model.bean.UserBean;
import com.mhy.shopingphone.model.bean.banner.BannerBean;
import com.mhy.shopingphone.model.bean.detail.DynamicBean;
import com.mhy.shopingphone.model.bean.discover.NewsBean;
import com.mhy.shopingphone.model.bean.discover.ShoperBean;
import com.mhy.shopingphone.model.bean.goods.GoodsTabBean;
import com.mhy.shopingphone.model.bean.login.LoginBean;
import com.mhy.shopingphone.model.bean.partner.PartnerLoginBean;
import com.mhy.shopingphone.model.bean.partner.PartnerShipBean;
import com.mhy.shopingphone.model.bean.phone.MailistCallBean;
import com.mhy.shopingphone.model.bean.phone.UserInfoBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.MyOrderBean;
import com.mhy.shopingphone.model.bean.shop.ShopBannerBean;
import com.mhy.shopingphone.model.bean.shop.ShopDetailBean;
import com.mhy.shopingphone.model.bean.shop.ShopTypeBean;
import com.mhy.shopingphone.model.serverbean.Adversiments;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者： "RedRainM" on 2017/12/20 0020.
 * 描述： 淘商城Api
 */

public interface Api {
    public final static String GOODS = "http://admin.sbdznkj.com/";    //旧版本接口
    public final static String NEWGOODS = "http://smms.sbdznkj.com:2018/SbdVoip/";
    public static List<GoodsBean.JsonBean.CategoriesBean> rsList = null;
    //    public final static String NEWGOODS = "http://47.104.196.37:2018/SbdVoip/";//内网
    public final static String VIP = "admin/aboutApp";    //联系我们

    public static String TOKEN_URL = "sign/getToken";       //加密
    public static String INITIATE_URL = "app/homePage";    //启动图   已接通
    public static String CAROUSEL_URL = "app/homeBanner";     //轮播图   已接通
    public static String VERSIONING_URL = "app/updateVersion";   //版本更新   已接通
    public static String PLATE_URL = "find/getShop";           //八大板块   已接通
    public static String NES_URL = "find/getNews";                   //新闻
    public static String COMPLETE_URL = "shop/getCategory";       //全部分类  已接通
    public static String SHOPPING_URL = "shop/getShopIndex";   //商城      没有数据
    public static String SHOPPINGES_URL = "jdshop/getJDShop";   //京东商城
    public static String SHOPINDENT_URL = "shop/getOrder";     //商城订单     已接通
    public static String DEDUCTION_URL = "shop/shopByBlance";       //抵扣话费
    public static String WORDlISTQUERY_URL = "userInfo/getHD";  //话单查询     没有数据
    public static String PPLOADFILE_URL = "userInfo/updateUser";  //上传个人资料
    public static String USERINFO_URL = "userInfo/getBlance";        //个人信息
    public static String LINEITEM_URl = "shop/detailInfo";        //获取订单详情    接口异常
    public static String CALLBACK_URL = "call/getNum";              //获取回拨号码      已接通
    public static String CALLPHONE_URl = "call/callBack";           //拨打电话           无数据
    public static String USERINFOEXIST_URL = "login/checkUser";     //验证用户是否真实存在    已接通
    public static String VERIFICATION_URL = "login/smsInfo";        //获取验证码             已接通
    public static String LOGIN_URL = "login/register";                    //注册信息        待检测
    public static String ENTER_URL = "login/login";                  //登录                  已接通
    public static String PASSWORD_URL = "login/updatePwd";          //修改密码
    public static String DETAIL_URL = "recharge/getPayInfo";          //交易明细             已接通
    public static String CARDPAY_URL = "recharge/payinfo";          //购物卡充值 / 话费充值   账户不存在
    public static String LINEPAy_URL = "recharge/getPayOnline";          //获取在线充值信息         已接通
    public static String ALIPAY_URL = "recharge/aliPram";            //支付宝参数
    public static String NOPAY_URL = "recharge/cancelPay";          //支付失败时调用的接口
    public static String PHOTO_URL = "file/uploadFile";           //图像接口
    public static String GJDENGLU_URL = "wapadmin/dologin";           //企业管家登录
    public static String GJSHOUYE_URL = "app/conpartial";           //企业管家首页
    public static String CHONGZHI_URL = "pay/insertPayinfoByJson";           //充值
    public static String SHGUANLI_URL = "wapadmin/selectShop";           //商户管理
    public static String YHGUANLI_URL = "wapadmin/selectSubscriber";           //用户管理
    public static String ADDDATA_URL = "wapadmin/insertShop";           //商户添加
    public static String DELITEDATA_URL = "wapadmin/updateUsers";           //商户修改
    public static String ADDSHANGJIA_URL = "wapadmin/insertShopinfoes";           //添加八大模块
    public static String SEIGHTMODEL_URL = "wapadmin/eight";           //八大模块显示
    public static String PHOTOXIUGAI_URL = "wapadmin/updateShopinfoes";      //八大模块修改
    public static String PHOTOLUNBO_URL = "wapadmin/adversiments";      //轮播广告显示
    public static String ADDPHOTOLUNBO_URL = "wapadmin/insertAdversiments";      //轮播广告显示
    public static String XIUGAIMIMA_URL = "wapadmin/updateUsersPassword";      //修改密码
    public static String QITAGUANGGAO_URL = "app/adIndex";      //其他广告展示
    public static String ADDQITA_URL = "app/insertAd";      //添加其他广告
    public static String XIUGAIQITA_URL = "app/updateAd";      //其他广告模块修改
    public static String SHGLCZ_URL = "app/updateUserPay";           //商户管理充值
    public static String GUNDONG_URL = "app/turnIndex";           //滚动文字展示
    public static String GUNDONGFX_URL = "app/insertShopinfosText";           //发现页文字添加
    public static String GUNDONGSP_URL = "app/insertShopText";           //商城页文字添加
    /**
     * 个人信息
     *
     * @param map
     * @return 客户端参数错误
     */
    @FormUrlEncoded
    @POST("userInfo/getBlance")
    Observable<UserBean> getUserInfo(@Field("A") String map);

    /**
     * 商城
     *
     * @param map
     * @return 返回500
     */
    @FormUrlEncoded
    @POST("shop/getShopIndex")
    Observable<GoodsBean> getGoodsList(@FieldMap Map<String, String> map);


    /**
     * 商城订单
     *
     * @param map
     * @return 请输入完整参数
     */
    @FormUrlEncoded
    @POST("shop/getOrder")
    Observable<MyOrderBean> getMyOrder(@Field("A") String map);

    /**
     * 抵扣话费
     *
     * @param map
     * @return 未测
     */
    @FormUrlEncoded
    @POST("shop/shopByBlance")
    Observable<Ceshi> goNext(@Field("A") String map);
/*    @FormUrlEncoded   //商城
    @POST("Vm9pcEFwaQ//ODISHAFE")
    Observable<GoodsBean> getGoodsList(@Field("A") String map);*/

   /* @FormUrlEncoded
    @POST("Vm9pcEFwaQ/ISODFHkjl")
//判断用户是否存在
    Observable<UserBean> getUserInfo(@Field("A") String map);*/

    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/SDOBEWLebn")
    Observable<Ceshi> getFlash(@Field("A") String map);

    /**
     * 获取回拨号码
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/SDOFBGS")
    Observable<MailistCallBean> goAddPhone(@Field("A") String map);

    //旧轮播图接口
    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/dsaofiwhe")
    Observable<BannerBean> getBannerData(@Field("A") String map);

    @GET("Vm9pcEFwaQ/SODNGAOLN")
    Observable<NewsBean> getNewsList();

    //弃用的八大板块
   /* @FormUrlEncoded
    @POST("Vm9pcEFwaQ/OSDFpwer")
    Observable<ShoperBean> getShoperList(@Field("A") String map);*/

    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/OSDHAO/")
    Observable<ShopBannerBean> getShopBannerData(@Field("A") String map);

    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/SONDSOA")
    Observable<GoodsTabBean> getGoodsTabsData(@Field("A") String map);

    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/SIDOSER")
    Observable<Ceshi> goNowBuy(@Field("A") String map);

    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/OISDHBOS")
    Observable<ShopDetailBean> getShopDetails(@Field("A") String map);

  /*  @FormUrlEncoded
    @POST("Vm9pcEFwaQ/ODSIOSA")
    Observable<Ceshi> goNext(@Field("A") String map);*/

    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/OSdfoapsdf")
    Observable<LoginBean> goLogin(@Field("A") String map);

    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/ODSFNBSAa")
    Observable<Ceshi> getCode(@Field("A") String map);

    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/HUyewk")
    Observable<Ceshi> goRegister(@Field("A") String map);

    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/SODNGNsdfioSDi")
    Observable<Ceshi> forGetPwd(@Field("A") String map);

/*    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/OSDHOAFD")
    Observable<MyOrderBean> getMyOrder(@Field("A") String map);*/

    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/OSDFpwerSDUf")
    Observable<DynamicBean> getDynamicList(@Field("A") String map);


    //合伙人收益
    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/OSDHAOW/")
    Observable<PartnerShipBean> getPartner(@Field("A") String map);

    //合伙人Login
    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/SOIEHWNL")
    Observable<PartnerLoginBean> goPartnerLogin(@Field("A") String map);

    //充值
    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/SBDFbsdfeF")
    Observable<RechargeBean> goRecharge(@Field("A") String map);

    //回拨
    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/YSDlsdf")
    Observable<Ceshi> goDialBack(@Field("A") String map);

    //TODO  商品类型
    @FormUrlEncoded
    @POST("Vm9pcEFwaQ//SONDSOASDUIY")
    Observable<ShopTypeBean> getShopType(@Field("A") String map);

    //TODO  商家认证
    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/OSDIHSO/")
    Observable<Ceshi> goVer(@Field("A") String map);

    //TODO  支付类型
    @FormUrlEncoded
    @POST("Vm9pcEFwaQ/DIOSHA/")
    Observable<PayTypeBean> getPayType(@Field("A") String map);

//    //TODO  支付类型
//    @FormUrlEncoded
//    @POST("shop/getShopIndex")
//    Observable<PayTypeBean> getShopIndex(@FieldMap Map<String,String> map);

    /*
     *  回拨
    * */
    public static String DIALLBACK_URL = "Vm9pcEFwaQ/YSDlsdf";
    public static String DIALLBACK_URLHead = "Vm9pcEFwaQ/CallBack/";
    /*
    * 合伙人登陆接口
    * */
    public static String PARTNER_LOGIN = "Vm9pcEFwaQ/SOIEHWNL";
    public static String PARTNER_LOGIN_URLHead = "Vm9pcEFwaQ/AgentLogin/";
    /*
     * 合伙人主页
     * */
    public static String PARTNER_SHIP = "Vm9pcEFwaQ/OSDHAOW/";
    public static String PARTNER_SHIP_URLHead = "Vm9pcEFwaQ/GetIndexInfo/";
    /*
 * 订单
 * */
    public static String ORDER_URL = "Vm9pcEFwaQ/OSDHOA";
    public static String ORDER_URLHead = "Vm9pcEFwaQ/GetOrder/";
    /*
   * 修改密码
   * */
    public static String GET_NEW_PWD = "Vm9pcEFwaQ/SODNGNsdfioSDi ";
    public static String GET_NEW_PWD_URLHead = "Vm9pcEFwaQ/UpdatePwd/";
    /*
  * 验证码接口
  * */
    public static String VERIFICATIONCODE_URL = "Vm9pcEFwaQ/ODSFNBSAa";
    public static String CODE_URLHead = "Vm9pcEFwaQ/SmsInfo/";
    /*
    * 判断用户是否存在
    * */
    public static String IS_USER = "Vm9pcEFwaQ/ODSIOSA";
    public static String IS_USER_URLHead = "Vm9pcEFwaQ/Register/";
    /*
  * 抵换
  * */
    public static String TAKE_URL = "Vm9pcEFwaQ/SIDOS";
    public static String TAKE_URLHead = "Vm9pcEFwaQ/Pay/";
}
