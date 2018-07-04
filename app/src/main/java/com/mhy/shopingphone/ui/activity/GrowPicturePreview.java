package com.mhy.shopingphone.ui.activity;

import java.util.ArrayList;
import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
//
//import com.facebook.drawee.backends.pipeline.Fresco;
//import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
//import com.facebook.drawee.controller.BaseControllerListener;
//import com.facebook.imagepipeline.image.ImageInfo;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.view.webview.HackyViewPager;

//import me.relex.photodraweeview.PhotoDraweeView;

/**
 * 作者： "RedRainM" on 2018/4/23 0023.
 * 描述：
 */

public class GrowPicturePreview extends Activity implements OnClickListener  {
    private ArrayList<String> filePaths;
    private int position;
    private HackyViewPager mViewPager;
    private TextView tv_position;
    private TextView tv_title_name;
    private RelativeLayout al_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grow_picture_preview);
        initData();
        initView();
        initLisener();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    private void initLisener() {
        al_back.setOnClickListener(this);
    }

    private void initView() {
        tv_title_name.setText("图片预览");
        al_back=(RelativeLayout) findViewById(R.id.al_back);

    }

    private void initData() {

        tv_position = (TextView) findViewById(R.id.tv_position);

        filePaths = getIntent().getStringArrayListExtra("images");
        position = Integer.parseInt(getIntent().getStringExtra("position"));
        mViewPager = (HackyViewPager) findViewById(R.id.view_pager);

        SamplePagerAdapter samplePagerAdapter = new SamplePagerAdapter(filePaths);
        mViewPager.setAdapter(samplePagerAdapter);
        tv_position.setText("1/" + filePaths.size());
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                tv_position.setText((position + 1) + "/" + filePaths.size());
                GrowPicturePreview.this.position = position;

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        mViewPager.setCurrentItem(position);

    }

    class SamplePagerAdapter extends PagerAdapter {
        private ArrayList<String> lists;

        public SamplePagerAdapter(ArrayList<String> lists) {
            this.lists = lists;
        }

        @Override
        public int getCount() {
            return 1;
        }

      /*  @Override
        public View instantiateItem(ViewGroup container, int position) {
            final PhotoDraweeView photoDraweeView = new PhotoDraweeView(container.getContext());
            PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
            controller.setUri(Uri.parse(lists.get(position)));
            controller.setOldController(photoDraweeView.getController());
            controller.setControllerListener(new BaseControllerListener<ImageInfo>() {
                @Override
                public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                    super.onFinalImageSet(id, imageInfo, animatable);
                    if (imageInfo == null) {
                        return;
                    }
                    photoDraweeView.update(imageInfo.getWidth(), imageInfo.getHeight());
                }
            });
            photoDraweeView.setController(controller.build());

            try {
                container.addView(photoDraweeView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return photoDraweeView;
        }*/

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.al_back:
                this.finish();
                break;
            default:
                break;
        }
    }

}
