package com.mhy.shopingphone.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.PickPhotoAdapter;
import com.mhy.shopingphone.utils.StatusBarUtils;
import com.mhy.shopingphone.view.webview.MultiplexImage;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者： "RedRainM" on 2018/4/23 0023.
 * 描述：
 */

public class PictureDisplayActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = PictureDisplayActivity.class.getName();

    private ViewPager viewPager;
    private TextView hint;
    private PickPhotoAdapter adapter;

    private List<MultiplexImage> images;
    int position;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_photos);

        initView();
    }

    private void initView(){
        viewPager = (ViewPager) this.findViewById(R.id.viewPager);
        hint = (TextView) this.findViewById(R.id.hint);

        getSupportActionBar().hide();
        StatusBarUtils.setStatusBar(this, Color.BLACK);

        images = getIntent().getParcelableArrayListExtra("images");
        position = getIntent().getIntExtra("position",0);

        if(adapter == null && images != null && images.size() != 0){
            adapter = new PickPhotoAdapter(this,images);
//            hiddenOriginalButton(position);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(position);
            viewPager.addOnPageChangeListener(this);
            hint.setText(position + 1 + "/" + images.size());
        }
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        hint.setText(position + 1 + "/" + images.size());

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static void startActivity(Context context, View view, List<MultiplexImage> imageList, int position) {
        Intent intent = new Intent(context, PictureDisplayActivity.class);
        intent.putParcelableArrayListExtra("images", (ArrayList<? extends Parcelable>) imageList);
        intent.putExtra("position",position);
        if (Build.VERSION.SDK_INT < 21) {
            context.startActivity(intent);
        } else {
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) context, view, "photoshow");
            context.startActivity(intent, options.toBundle());
        }
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
