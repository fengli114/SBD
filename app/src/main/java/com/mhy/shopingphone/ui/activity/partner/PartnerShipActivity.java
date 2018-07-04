package com.mhy.shopingphone.ui.activity.partner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.partner.PartnerContract;
import com.mhy.shopingphone.model.bean.partner.PartnerShipBean;
import com.mhy.shopingphone.presenter.partner.PartnerPresenter;
import com.mhy.shopingphone.ui.MainActivity;
import com.mhy.shopingphone.ui.activity.MyTeamActivity;
import com.mhy.shopingphone.ui.activity.tixian.BindActivity;
import com.mhy.shopingphone.ui.activity.tixian.MoneyRecordActivity;
import com.mhy.shopingphone.ui.activity.tixian.OrderDetailsActivity;
import com.mhy.shopingphone.ui.activity.tixian.TiXianActivity;
import com.youth.xframe.utils.log.XLog;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class PartnerShipActivity extends BaseMVPCompatActivity <PartnerContract
        .PartnerPresenter, PartnerContract.IPartnerModel> implements PartnerContract
        .IPartnerView {


    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.iv_partner_icon)
    CircleImageView ivPartnerIcon;
    @BindView(R.id.tv_partner_user)
    TextView tvPartnerUser;
    @BindView(R.id.tv_partner_account)
    TextView tvPartnerAccount;
    @BindView(R.id.lin_account)
    LinearLayout linAccount;
    @BindView(R.id.iv_partner_pic)
    ImageView ivPartnerPic;
    @BindView(R.id.tv_partner_income)
    TextView tvPartnerIncome;
    @BindView(R.id.tv_partner_ln)
    TextView tvPartnerLn;
    @BindView(R.id.al_partner_record)
    LinearLayout alPartnerRecord;
    @BindView(R.id.tv_money_sum)
    TextView tvMoneySum;
    @BindView(R.id.btn_go_tixian)
    Button btnGoTixian;
    @BindView(R.id.al_partner_detail)
    LinearLayout alPartnerDetail;
    @BindView(R.id.tv_user_sum_t)
    TextView tvUserSumT;
    @BindView(R.id.tv_user_click_sum_t)
    TextView tvUserClickSumT;
    @BindView(R.id.tv_user_sum_y)
    TextView tvUserSumY;
    @BindView(R.id.tv_user_click_sum_y)
    TextView tvUserClickSumY;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.al_back)
    RelativeLayout alBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;

    @BindView(R.id.fragment_rotate_header_with_text_view_frame)
    PtrClassicFrameLayout mPtrFrame;
    private Map<String, String> params;
    private String paramstr;

    @Override
    protected void onResume() {
        super.onResume();
        tvMoneySum.setText("¥" + Contant.profitMoney);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Util.setStatusBarHeigh(mContext, tou);
        params = new HashMap<>();
        tvTitle.setText("合伙人收益");
        tvRight.setText("我的团队");
        Glide.with(this).load(Contant.URL_IMAG_ICON).into(ivPartnerIcon);
        tvPartnerAccount.setText(Contant.Moblie);
        goHttp();

        //刷新
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                goHttp();
                mPtrFrame.refreshComplete();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }
    //请求
    private void goHttp() {
        params.put("ID", Contant.PARTNER_ID);//代理商id

        String paramsstring = StringUtil.mapToJson(params);
        paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
        //设置头部
        HttpUtils.headStr = Contant.PARTNER_SHIP_URLHead;
        mPresenter.loadContentList(paramstr);
        HttpUtils.LogHeadStr = "hehuo:";
        Contant.IsDebug = true;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_partner_mian;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return PartnerPresenter.newInstance();
    }


    @OnClick({R.id.tv_right, R.id.al_back, R.id.al_partner_record, R.id.btn_go_tixian, R.id.al_partner_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                startActivity(MyTeamActivity.class);
                break;
            case R.id.al_back:
                finish();
                break;
            case R.id.al_partner_record:
                startActivity(MoneyRecordActivity.class);
                break;
            case R.id.btn_go_tixian:
                startActivity(TiXianActivity.class);
                break;
            case R.id.al_partner_detail:
                startActivity(OrderDetailsActivity.class);
                break;
        }
    }


    @Override
    public void updateContentList(PartnerShipBean partnerShipEntity) {
        Glide.with(mContext).load(Api.GOODS + partnerShipEntity.getAd())
                .crossFade(300)
                .placeholder(R.drawable.partner_banner).into(ivPartnerPic);
        LogUtils.e("maomao" + partnerShipEntity.getAd());
        //
        float fMoney  = Float.valueOf(partnerShipEntity.getMoney());
        BigDecimal b   =   new   BigDecimal(fMoney);
        float   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
        tvPartnerIncome.setText("¥" + f1);
        tvPartnerLn.setText(partnerShipEntity.getCount() + "");

        Glide.with(this).load(Contant.URL_TEST + partnerShipEntity.getAd()).into(ivPartnerPic);
        //
        float fMoney1 = 0;
        if (partnerShipEntity.getMonth().size() == 1){
            if (partnerShipEntity.getMonth().get(0).getID().equals("00000000-0000-0000-0000-000000000000")){
                tvUserClickSumT.setText(partnerShipEntity.getMonth().size() - 1 + "");
            }else {
                tvUserClickSumY.setText(partnerShipEntity.getMonth().size()+"");
            }

        }else {
            for (int i = 0;i < partnerShipEntity.getMonth().size(); i++){
                fMoney1 = fMoney1 + Float.valueOf(partnerShipEntity.getMonth().get(i).getMoney());
            }
            tvUserClickSumT.setText(partnerShipEntity.getMonth().size() + "");
            BigDecimal b2   =   new   BigDecimal(fMoney1);
            float   f2  =   b2.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
            tvUserSumT.setText("¥" + f2);
        }

        //
        float fMoney2 = 0;
        if (partnerShipEntity.getUpMonth().size() == 1){
            if (partnerShipEntity.getUpMonth().get(0).getID().equals("00000000-0000-0000-0000-000000000000")){
                tvUserClickSumY.setText(partnerShipEntity.getUpMonth().size()-1+"");
            }else {
                tvUserClickSumY.setText(partnerShipEntity.getUpMonth().size()+"");
            }

        }else {
            for (int i = 0; i < partnerShipEntity.getUpMonth().size(); i++){
                fMoney2  += Float.valueOf(partnerShipEntity.getUpMonth().get(i).getMoney());
            }
            BigDecimal b3   =   new   BigDecimal(fMoney2);
            float   f3  =   b3.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
            tvUserSumY.setText("¥" + f3);
        }
    }

    @Override
    public void showNetworkError() {

    }
}
