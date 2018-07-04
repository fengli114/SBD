package com.mhy.shopingphone.ui.activity.detail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.blankj.utilcode.util.RegexUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.rxbus.RxBus;
import com.mhy.sdk.rxbus.Subscribe;
import com.mhy.sdk.utils.AppUtils;
import com.mhy.sdk.utils.DownloadUtil;
import com.mhy.sdk.utils.FileUtils;
import com.mhy.sdk.utils.GetJsonDataUtil;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.main.PersonalContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.UserBean;
import com.mhy.shopingphone.model.bean.UserInfoBean;
import com.mhy.shopingphone.model.bean.photoBean;
import com.mhy.shopingphone.model.bean.rxbus.RxEventHeadBean;
import com.mhy.shopingphone.model.detail.JsonBean;
import com.mhy.shopingphone.presenter.main.PersonalUpperPresenter;
import com.mhy.shopingphone.ui.VerificationActivity;
import com.mhy.shopingphone.ui.activity.main.HeadSettingActivity;
import com.mhy.shopingphone.ui.huadan.CustomDatePicker;
import com.mhy.shopingphone.widgets.PersonalPopupWindow;
import com.mhy.shopingphone.widgets.XCRoundRectImageView;
import com.youth.xframe.utils.log.XLog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_HEAD_IMAGE_URI;
import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_UP_LOGO;

public class PersonalDataActivity extends BaseMVPCompatActivity<PersonalContract
        .PersonalUpperPresenter, PersonalContract.IPersonalUpperModel> implements PersonalContract.IPersonalUpperView {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.al_back)
    RelativeLayout alBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.title)
    LinearLayout title;
    @BindView(R.id.civ_head)    //头像
            XCRoundRectImageView civHead;
    @BindView(R.id.civ_heads)      //背景图
            XCRoundRectImageView civHeads;
    @BindView(R.id.ll_head_icon)
    LinearLayout llHeadIcon;
    @BindView(R.id.ll_head_background)
    LinearLayout llBackground;
    @BindView(R.id.ll_my_day)
    LinearLayout llDay;
    @BindView(R.id.tv_my_day)
    TextView tvDay;
    @BindView(R.id.tv_my_phone)
    TextView tvMyPhone;
    @BindView(R.id.ll_my_phone)
    LinearLayout llMyPhone;
    @BindView(R.id.edit_my_birth)
    EditText editMyBirth;
    @BindView(R.id.ll_my_birth)
    LinearLayout llMyBirth;
    @BindView(R.id.tv_my_gender)
    TextView tvMyGender;
    @BindView(R.id.ll_my_gender)
    LinearLayout llMyGender;
    @BindView(R.id.edit_my_email)
    EditText editMyEmail;
    @BindView(R.id.ll_my_email)
    LinearLayout llMyEmail;
    @BindView(R.id.tv_my_country)
    TextView tvMyCountry;
    @BindView(R.id.ll_my_country)
    LinearLayout llMyCountry;
    @BindView(R.id.tv_my_shoper)
    TextView tvMyShoper;
    @BindView(R.id.ll_my_shoper)
    LinearLayout llMyShoper;
    @BindView(R.id.tou)
    ImageView tou;
    private String flages;
    private final static int Verification_CODE = 4;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private CustomDatePicker customDatePicker;
    private String now_time;
    private String now;
    private String pathimg;
    private boolean isLoaded = false;
    private PersonalPopupWindow popupWindow;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        LogUtils.e("Begin Parse Data");
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    LogUtils.e("Parse Succeed");
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    LogUtils.e("Parse Failed");
                    break;

            }
        }
    };
    private File fileLOGO;
    private UserInfoBean userBean;
    private int sex;

    @Override
    protected void initView(Bundle savedInstanceState) {
        RxBus.get().register(this);
        initPopupView();
        Util.setStatusBarHeigh(mContext, tou);
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        tvTitle.setText("个人资料");
        tvRight.setText("保存");
        if (getIntent() != null) {
            userBean = (UserInfoBean) getIntent().getSerializableExtra("info");
            tvMyPhone.setText(userBean.getJson().getInfo().getMobile());
            if (!TextUtils.isEmpty(userBean.getJson().getInfo().getAddress())) {
                tvMyCountry.setText(userBean.getJson().getInfo().getAddress());
                tvMyCountry.setTextColor(getResources().getColor(R.color.txt_28));
            }
            if (!TextUtils.isEmpty(userBean.getJson().getInfo().getUsername())) {
                editMyBirth.setText(userBean.getJson().getInfo().getUsername());
                editMyBirth.setSelection(editMyBirth.getText().toString().length());
            }
            if (!TextUtils.isEmpty(userBean.getJson().getInfo().getEmail())) {
                editMyEmail.setText(userBean.getJson().getInfo().getEmail());
                editMyEmail.setSelection(editMyEmail.getText().toString().length());
            }
            if (userBean.getJson().getInfo().getGender().equals("1")) {
                tvMyGender.setText("男");
                tvMyGender.setTextColor(getResources().getColor(R.color.txt_28));
                sex = 1;
            } else if (userBean.getJson().getInfo().getGender().equals("2")) {
                tvMyGender.setText("女");
                tvMyGender.setTextColor(getResources().getColor(R.color.txt_28));
                sex = 2;
            } else {
                sex = 0;
            }
            if (userBean.getJson().isIsAuth()) {
                tvMyShoper.setText("已认证");
            }

        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());
        now_time = now.split(" ")[0];
        initDatePicker();
        Glide.with(mContext).load(Contant.URL_IMAG_ICON).crossFade(300).placeholder(R
                .drawable.touxiangpic).into(civHead);
        Glide.with(mContext).load(Contant.URL_IMAG_ICON).crossFade(300).placeholder(R
                .drawable.touxiangpic).into(civHeads);
    }

    private void initDatePicker() {
        customDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvDay.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker.showSpecificTime(false); // 不显示时和分
        customDatePicker.setIsLoop(false); // 不允许循环滚动

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_data;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return PersonalUpperPresenter.newInstance();
    }


    @OnClick({R.id.al_back, R.id.tv_right, R.id.ll_head_icon, R.id.ll_head_background, R.id.ll_my_day, R.id.ll_my_phone, R.id.ll_my_birth, R.id.ll_my_gender, R.id.ll_my_email, R.id.ll_my_country, R.id.ll_my_shoper})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.al_back:
                finish();
                break;
            case R.id.tv_right:
                if (isEmail()) {
                    getIformation();
                }
                break;
            case R.id.ll_head_icon:
                flages = "1";
                mPresenter.btnHeadClicked();
                break;
            case R.id.ll_head_background:
                flages = "2";
                mPresenter.btnHeadClicked();
                break;
            case R.id.ll_my_day:    //出生年月
                customDatePicker.show(now_time);
                break;
            case R.id.ll_my_phone:
                break;
            case R.id.ll_my_birth:
                break;
            case R.id.ll_my_gender:
                change_sex();
                break;
            case R.id.ll_my_email:
                break;
            case R.id.ll_my_country:
                if (isLoaded) {
                    ShowPickerView();
                } else {
                    ToastUtils.showToast("Please waiting until the data is parsed");
                }
                break;
            case R.id.ll_my_shoper:
                if (!userBean.getJson().isIsAuth()) {
                    startActivity(VerificationActivity.class);
                } else {
                    showDialogUpdate();
                }
                break;
        }
    }

    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置提示框的标题
        builder.setMessage("该商家已认证，如有变更，请联系客服").
                // 设置确定按钮
                        setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        // 设置取消按钮,null是什么都不做，并关闭对话框
//                        setNegativeButton("取消", null);

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        // 显示对话框
        alertDialog.show();

    }

    private boolean isEmail() {
        String name = editMyBirth.getText().toString();
        int nameL = editMyBirth.getText().toString().length();
        if (!TextUtils.isEmpty(name) && !RegexUtils.isZh(name) && nameL > 1) {
            ToastUtils.showToast("您的输入有误，请至少2位汉字");
            return false;
        }
      /*  if (nameL == 1) {
            ToastUtils.showToast("您的输入有误，请输入2-4位姓名");
            return false;
        }*/
       /* if (!TextUtils.isEmpty(email) && !RegexUtils.isEmail(email)) {
            ToastUtils.showToast("请确定邮箱号是否正确~");
            return false;
        }*/

        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //        Logger.e("RxBus.get().unRegister(this)");
        RxBus.get().unRegister(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void change_sex() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); //定义一个AlertDialog
        String[] strarr = {"男", "女", "神秘"};
        builder.setItems(strarr, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                // 自动生成的方法存根
                if (arg1 == 0) {//男
                    sex = 1;
                    tvMyGender.setText("男");
                } else if (arg1 == 1) {//女
                    tvMyGender.setText("女");
                    sex = 2;
                } else if (arg1 == 2) {//女
                    tvMyGender.setText("神秘");
                    sex = 3;
                } else {//女
                    sex = 0;
                }

            }
        });
        builder.show();
    }


    private void ShowPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);

                ToastUtils.showToast(tx);
                tvMyCountry.setText(tx);
                tvMyCountry.setVisibility(View.VISIBLE);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    @Override
    public void initPopupView() {
        popupWindow = new PersonalPopupWindow(this);
        popupWindow.setOnItemClickListener(new PersonalPopupWindow.OnItemClickListener() {
            @Override
            public void onCaremaClicked() {
                mPresenter.btnCameraClicked();
            }

            @Override
            public void onPhotoClicked() {
                mPresenter.btnPhotoClicked();
            }

            @Override
            public void onCancelClicked() {
                mPresenter.btnCancelClicked();
            }
        });

    }

    @Override
    public void showHead(Bitmap bitmap, Uri uri) {
        try {
            fileLOGO = new File(new URI(uri.toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            LogUtils.e("上传头像：" + e.toString());
        }
    }

    @Override
    public void showPopupView() {
        View parent = LayoutInflater.from(this).inflate(R.layout.fragment_personal, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
    }

    @Override
    public void dismissPopupView() {
        popupWindow.dismiss();
    }

    @Override
    public boolean popupIsShowing() {
        return popupWindow.isShowing();
    }

    @Override
    public void gotoHeadSettingActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(mContext, HeadSettingActivity.class);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    public void gotoSystemPhoto(int requestCode) {
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media
                .EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), requestCode);
    }

    @Override
    public void gotoSystemCamera(File tempFile, int requestCode) {
//跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            //            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            //            Uri contentUri = FileProvider.getUriForFile(mActivity, BuildConfig.APPLICATION_ID + "" +
            //                    ".fileProvider", tempFile);
            //            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    /**
     * RxBus接收图片Uri
     *
     * @param bean RxEventHeadBean
     */
    @Subscribe(code = RX_BUS_CODE_HEAD_IMAGE_URI)
    public void rxBusEvent(RxEventHeadBean bean) {
        Uri uri = bean.getUri();
        if (uri == null) {
            return;
        }
        String cropImagePath = FileUtils.getRealFilePathFromUri(AppUtils.getContext(), uri);
        try {
            fileLOGO = new File(new URI(uri.toString()));
            if (flages.equals("1")) {
                getfileIformation();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            LogUtils.e("上传头像：" + e.toString());
        }
        Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
        if (bitMap != null)
            if (flages.equals("1")) {
                civHead.setImageBitmap(bitMap);
            } else {
                civHeads.setImageBitmap(bitMap);
            }

    }

    /*
  * 上传文件接口
  * */
    public void getfileIformation() {
        //请求参数
      /*  Map<String, String> params = new HashMap<String, String>();
        params.put("mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));
        params.put("userid", String.valueOf(SharedPreferencesHelper.getInstance().getData("UserId", "")));
        params.put("address", tvMyCountry.getText().toString());
        params.put("birthday", tvRight.getText().toString().trim());
        params.put("username", editMyBirth.getText().toString());
        params.put("gender", sex + "");
        params.put("email", editMyEmail.getText().toString());*/
        OkHttpUtils
                .post()
                .url(Api.NEWGOODS + Api.PHOTO_URL)
//                .params(params)
                .addFile("file", fileLOGO.getName(), fileLOGO)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("fengli====" + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.e("fengli===" + response);
                            photoBean baseBean = new Gson().fromJson(response, photoBean.class);
                            if (baseBean.getErrorCode() == 2000) {
                                if (baseBean.getJson().indexOf("http") != -1) {
                                    Contant.URL_IMAG_ICON = baseBean.getJson();
                                } else {
                                    Contant.URL_IMAG_ICON = Api.NEWGOODS + baseBean.getJson();
                                }

                                pathimg = baseBean.getJson();
                                Glide.with(PersonalDataActivity.this).load(Contant.URL_IMAG_ICON).into(civHead);
                                RxBus.get().send(RX_BUS_CODE_UP_LOGO);
                            } else {
                                ToastUtils.showToast("上传失败");
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /*
  * 上传文件接口
  * */
    public void getIformation() {
        //请求参数
        Map<String, String> params = new HashMap<String, String>();
        if (pathimg != null && pathimg.length() > 0) {
            params.put("headImage", pathimg);
        } else {
            params.put("headImage", "");
        }
        params.put("mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));
        params.put("userid", String.valueOf(SharedPreferencesHelper.getInstance().getData("UserId", "")));
        params.put("address", tvMyCountry.getText().toString());
        params.put("birthday", tvRight.getText().toString().trim());
        params.put("username", editMyBirth.getText().toString());
        params.put("gender", sex + "");
        params.put("email", editMyEmail.getText().toString());

        OkHttpUtils
                .post()
                .url(Api.NEWGOODS + Api.PPLOADFILE_URL)
                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showToast("保存异常");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        UserInfoBean baseBean = new Gson().fromJson(response, UserInfoBean.class);
                        if (baseBean.getErrorCode() == 2000) {
                            ToastUtils.showToast("保存成功");
                            finish();
                        } else if (baseBean.getErrorCode() == 1005) {
                            Map<String, String> parames = new HashMap<>();
                            parames.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));
                            parames.put("ParentId", String.valueOf(Contant.PARENTID));
                            OkHttpUtils.post()
                                    .url(Api.NEWGOODS + Api.TOKEN_URL)
                                    .params(parames)
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            Ceshi ceshi = new Gson().fromJson(response, Ceshi.class);
                                            if (ceshi.getErrorCode() == 2000) {
                                                SharedPreferencesHelper.getInstance().saveData("Tokens", ceshi.getData());//代理Id
                                                getIformation();
                                            } else {
                                                ToastUtils.showToast("网络异常");
                                            }
                                        }
                                    });
                        } else {
                            ToastUtils.showToast("保存失败");
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Verification_CODE:
                if (resultCode == RESULT_FIRST_USER) {
                    tvMyShoper.setText("已认证");
                }
                break;

        }
    }
}
