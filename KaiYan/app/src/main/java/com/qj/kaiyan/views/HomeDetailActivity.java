package com.qj.kaiyan.views;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.qj.kaiyan.R;
import com.qj.kaiyan.base.BaseActitvity;
import com.qj.kaiyan.entitys.HomeResult;
import com.qj.kaiyan.entitys.HomeResultItem;
import com.qj.kaiyan.entitys.ItemListBean;
import com.qj.kaiyan.utils.ToastUtils;
import com.qj.kaiyan.utils.UMShareUtils;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.listener.StandardVideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeDetailActivity extends BaseActitvity {

    @BindView(R.id.gsy_player)
    StandardGSYVideoPlayer gsyPlayer;
    @BindView(R.id.iv_homedetail_bottom)
    ImageView ivHomedetailBottom;
    @BindView(R.id.tv_homedetail_title)
    TextView tvHomedetailTitle;
    @BindView(R.id.tv_homedetail_time)
    TextView tvHomedetailTime;
    @BindView(R.id.tv_homedetail_detail)
    TextView tvHomedetailDetail;
    @BindView(R.id.tv_homedetail_favnum)
    TextView tvHomedetailFavnum;
    @BindView(R.id.tv_homedetail_sharenum)
    TextView tvHomedetailSharenum;
    @BindView(R.id.tv_homedetail_replynum)
    TextView tvHomedetailReplynum;
    @BindView(R.id.tv_homedetail_downnum)
    TextView tvHomedetailDownnum;

    private HomeResultItem resultItem;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("flag", "handleMessage: ");
            gsyPlayer.setThumbImageView(imageView);

        }
    };
    private ImageView imageView;
    private boolean isPlay = false;
    private OrientationUtils orientationUtils;
    private boolean isPause = false;
    private ActionBar actionBar;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_home_detail);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        Bundle data = getIntent().getExtras();
       ItemListBean itemListBean = (ItemListBean) data.getSerializable("data");
        actionBar = getActionBar();
        actionBar.hide();
        resultItem = itemListBean.getData();
        String title = resultItem.getTitle();

        tvHomedetailTitle.setText(title);
        tvHomedetailTime.setText(resultItem.getCategory() + "/" + (resultItem.getDuration() / 60 + ":" + resultItem.getDuration() % 60));
        tvHomedetailDetail.setText(resultItem.getDescription());

        HomeResultItem.ConsumptionBean consumption = resultItem.getConsumption();

        tvHomedetailFavnum.setText("" + consumption.getCollectionCount());
        tvHomedetailReplynum.setText("" + consumption.getReplyCount());
        tvHomedetailSharenum.setText("" + consumption.getShareCount());

        Glide.with(this).load(resultItem.getCover().getBlurred()).asBitmap().centerCrop().into(ivHomedetailBottom);
        imageView = new ImageView(HomeDetailActivity.this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        new MyTask().execute(resultItem.getCover().getFeed());

        gsyPlayer.setUp(resultItem.getPlayUrl(), false, null);
        gsyPlayer.getTitleTextView().setVisibility(View.GONE);
        gsyPlayer.getBackButton().setVisibility(View.VISIBLE);
        orientationUtils = new OrientationUtils(this, gsyPlayer);
        gsyPlayer.setIsTouchWiget(true);

        gsyPlayer.setRotateViewAuto(false);
        gsyPlayer.setLockLand(false);
        gsyPlayer.setShowFullAnimation(false);
        gsyPlayer.setNeedLockFull(true);
        gsyPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orientationUtils.resolveByClick();
                gsyPlayer.startWindowFullscreen(HomeDetailActivity.this, false, true);

                actionBar.hide();
            }
        });
        gsyPlayer.setStandardVideoAllCallBack(new StandardVideoAllCallBack() {
            @Override
            public void onClickStartThumb(String url, Object... objects) {

            }

            @Override
            public void onClickBlank(String url, Object... objects) {

            }

            @Override
            public void onClickBlankFullscreen(String url, Object... objects) {

            }

            @Override
            public void onPrepared(String url, Object... objects) {

                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onClickStartIcon(String url, Object... objects) {

            }

            @Override
            public void onClickStartError(String url, Object... objects) {

            }

            @Override
            public void onClickStop(String url, Object... objects) {

            }

            @Override
            public void onClickStopFullscreen(String url, Object... objects) {

            }

            @Override
            public void onClickResume(String url, Object... objects) {

            }

            @Override
            public void onClickResumeFullscreen(String url, Object... objects) {

            }

            @Override
            public void onClickSeekbar(String url, Object... objects) {

            }

            @Override
            public void onClickSeekbarFullscreen(String url, Object... objects) {

            }

            @Override
            public void onAutoComplete(String url, Object... objects) {

            }

            @Override
            public void onEnterFullscreen(String url, Object... objects) {

            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {

                if (orientationUtils != null) {

                    orientationUtils.backToProtVideo();
                }
            }

            @Override
            public void onQuitSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onEnterSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekVolume(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekPosition(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekLight(String url, Object... objects) {

            }

            @Override
            public void onPlayError(String url, Object... objects) {

            }
        });

        gsyPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                orientationUtils.setEnable(!lock);
            }
        });


    }

    @Override
    protected void initListener() {

        gsyPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    @OnClick({R.id.tv_homedetail_sharenum, R.id.tv_homedetail_downnum})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_homedetail_sharenum:
                UMWeb web=new UMWeb("https://mm.nongye91.com/index/share");
                web.setTitle("分享给你");
                web.setDescription("download url");
                web.setThumb(new UMImage(this,R.drawable.ic_launcher));
                new ShareAction(this)
//                        .withMedia(new UMImage(this,R.mipmap.ic_launcher))
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                        .withMedia(web)
                        .setCallback(UMShareUtils.shareListener)
                        .open();
                break;
            case R.id.tv_homedetail_downnum:
                break;
        }
    }


    class MyTask extends AsyncTask<String, Void, String> {

        private String absolutePath;
        private FileInputStream fis;

        @Override
        protected String doInBackground(String... strings) {
            FutureTarget<File> futureTarget = Glide.with(HomeDetailActivity.this).load(strings[0]).downloadOnly(100, 100);


            try {
                File file = futureTarget.get();
                absolutePath = file.getAbsolutePath();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return absolutePath;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                fis = new FileInputStream(s);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            AndPermission.with(HomeDetailActivity.this).permission(Permission.STORAGE).callback(this).start();
            Bitmap bitmap = BitmapFactory.decodeStream(fis);

            imageView.setImageBitmap(bitmap);

            handler.sendEmptyMessage(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause = true;
    }

    @Override
    public void onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (isPlay && !isPause) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!gsyPlayer.isIfCurrentIsFullscreen()) {
                    gsyPlayer.startWindowFullscreen(HomeDetailActivity.this, true, true);
                }
            } else {
                if (gsyPlayer.isIfCurrentIsFullscreen()) {
                    StandardGSYVideoPlayer.backFromWindowFull(this);
                }
                if (orientationUtils != null) {
                    orientationUtils.setEnable(true);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }


}
