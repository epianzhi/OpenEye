package com.qj.kaiyan.fragments;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.qj.kaiyan.R;
import com.qj.kaiyan.adapters.Myadapter;
import com.qj.kaiyan.base.BaseFragment;
import com.qj.kaiyan.base.MyApplication;
import com.qj.kaiyan.utils.CacheUtils;
import com.qj.kaiyan.utils.CatchUtils;
import com.qj.kaiyan.utils.SimpleRxGalleryFinal;
import com.qj.kaiyan.utils.glide.GlideCacheUtils;
import com.qj.kaiyan.views.RSearchActivity;
import com.qj.kaiyan.views.SearchResultActivity;
import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.BaseResultEvent;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {


    @BindView(R.id.mine_icon_bg)
    ImageView mineIconBg;
    @BindView(R.id.mine_icon_round)
    ImageView mineIconRound;
    @BindView(R.id.mine_tv_name)
    TextView mineTvName;
    @BindView(R.id.mine_top)
    RelativeLayout mineTop;
    @BindView(R.id.mine_history)
    TextView mineHistory;
    @BindView(R.id.mine_collect)
    TextView mineCollect;
    @BindView(R.id.mine_clearcache)
    TextView mineClearcache;
    @BindView(R.id.mine_version)
    TextView mineVersion;
    Unbinder unbinder;
    private View view;

    String[] items = new String[]{"拍照", "相册"};
    private ActionSheetDialog sheetDialog;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView() {

        Glide.with(this)
                .load(R.drawable.ic_launcher)
                .bitmapTransform(new BlurTransformation(getContext(), 25), new CenterCrop(getContext()))
                .into(mineIconBg);
        Glide.with(this)
                .load(R.drawable.ic_launcher)
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(mineIconRound);
    }

    @Override
    protected void initData() {

        try {
            String totalCacheSize =CacheUtils.getTotalCacheSize(MyApplication.getContext());
            mineClearcache.setText("缓存"+totalCacheSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.mine_icon_round, R.id.mine_history, R.id.mine_collect, R.id.mine_clearcache, R.id.mine_version})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_icon_round:
                if (!AndPermission.hasPermission(getContext(), Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AndPermission.with(getContext())
                            .requestCode(100)
                            .permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                            .rationale(rationaleListener)
                            .callback(permissionListener)
                            .start();

                } else {
                    showChooseWindow();
                }
                break;
            case R.id.mine_history:
                openActivity(RSearchActivity.class);
                break;
            case R.id.mine_collect:
                break;
            case R.id.mine_clearcache:
                CacheUtils.cleanExternalCache(MyApplication.getContext());
                CacheUtils.cleanFiles(MyApplication.getContext());
                String cacheSize = null;
                try {
                    cacheSize = CacheUtils.getTotalCacheSize(MyApplication.getContext());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mineClearcache.setText("缓存0B");
                break;
            case R.id.mine_version:
                break;
        }
    }

    private void showChooseWindow() {

        sheetDialog = new ActionSheetDialog(getContext(), items, null);
        sheetDialog.layoutAnimation(null);
        sheetDialog.isTitleShow(false).show();
        sheetDialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        createCamera();
                        break;
                    case 1:
                        createCapture();
                        break;
                }
                sheetDialog.dismiss();
            }
        });
    }

    private void createCapture() {
        RxGalleryFinal.with(getContext()).image().imageLoader(ImageLoaderType.GLIDE).subscribe(new RxBusResultDisposable<ImageMultipleResultEvent>() {
            @Override
            protected void onEvent(ImageMultipleResultEvent baseResultEvent) throws Exception {
                String originalPath = baseResultEvent.getResult().get(0).getOriginalPath();
                Glide.with(getContext())
                        .load(originalPath)
                        .bitmapTransform(new BlurTransformation(getContext(), 25), new CenterCrop(getContext()))
                        .into(mineIconBg);
                Glide.with(getContext())
                        .load(originalPath)
                        .bitmapTransform(new CropCircleTransformation(getContext()))
                        .into(mineIconRound);
            }
        }).openGallery();
    }

    private void createCamera() {
        SimpleRxGalleryFinal.get().init(new SimpleRxGalleryFinal.RxGalleryFinalCropListener() {
            @NonNull
            @Override
            public Activity getSimpleActivity() {
                return getActivity();
            }

            @Override
            public void onCropCancel() {

            }

            @Override
            public void onCropSuccess(@Nullable Uri uri) {
                Log.d("flag", "onCropSuccess: "+uri);
                Glide.with(getContext())
                        .load(uri)
                        .bitmapTransform(new BlurTransformation(getContext(), 25), new CenterCrop(getContext()))
                        .into(mineIconBg);
                Glide.with(getContext())
                        .load(uri)
                        .bitmapTransform(new CropCircleTransformation(getContext()))
                        .into(mineIconRound);

            }

            @Override
            public void onCropError(@NonNull String errorMessage) {
                Log.d("flag", "onCropError: "+errorMessage);

            }
        }).openCamera();
    }

    private RationaleListener rationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {

            AlertDialog.build(getContext())
                    .setTitle("友好提醒")
                    .setMessage("您已拒绝过相机权限，没有相机权限无法为您拍照，请把权限赐给我吧！")
                    .setPositiveButton("好，给你", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();
                            rationale.resume();
                        }
                    })
                    .setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();
                            rationale.cancel();
                        }
                    }).show();
        }
    };
    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            showChooseWindow();
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

            AndPermission.defaultSettingDialog(getContext()).show();

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
