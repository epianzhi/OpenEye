package com.qj.kaiyan.views;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.qj.kaiyan.R;
import com.qj.kaiyan.base.BaseActitvity;
import com.qj.kaiyan.fragments.FindFragment;
import com.qj.kaiyan.fragments.HomeFragment;
import com.qj.kaiyan.fragments.HotFragment;
import com.qj.kaiyan.fragments.MineFragment;
import com.qj.kaiyan.utils.SimpleRxGalleryFinal;
import com.qj.kaiyan.utils.ToastUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActitvity {

    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;
    @BindView(R.id.iv_main_search)
    ImageView ivMainSearch;
    @BindView(R.id.bottom_navigate)
    BottomNavigationView bottomNavigate;
    @BindView(R.id.frame_container)
    FrameLayout frameContainer;

    private HomeFragment homeFragment;
    private FindFragment findFragment;
    private HotFragment hotFragment;
    private MineFragment mineFragment;

    private int currentIndex=0,index=-1;
    Fragment[] fragments;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ImmersionBar.with(this).transparentBar().barAlpha(0.5f).fitsSystemWindows(true).init();
        tvMainTitle.setText(getDay());
        tvMainTitle.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Lobster-1.4.otf"));


        initFragments();
    }

    private void initFragments() {
        homeFragment=new HomeFragment();
        findFragment=new FindFragment();
        hotFragment=new HotFragment();
        mineFragment=new MineFragment();
        fragments=new Fragment[]{homeFragment,findFragment,hotFragment,mineFragment};
    }

    @Override
    protected void initData() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction().add(R.id.frame_container,fragments[0]).commit();

    }

    @Override
    protected void initListener() {

        bottomNavigate.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_home:

                        index=0;
                        refresh();
                        return true;
                    case R.id.item_find:

                        tvMainTitle.setText("Discover");
                        index=1;
                        refresh();
                        return true;
                    case R.id.item_hot:

                        tvMainTitle.setText("Ranking");
                        index=2;
                        refresh();
                        return true;
                    case R.id.item_mine:

                        tvMainTitle.setText("Mine");
                        index=3;
                        refresh();
                        return true;
                }
                return false;
            }
        });

    }

    public void refresh(){
        if (index!=currentIndex){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (fragments[currentIndex].isAdded()){
                fragmentTransaction.hide(fragments[currentIndex]);
            }
            if (!fragments[index].isAdded()){
                fragmentTransaction.add(R.id.frame_container,fragments[index]);

            }
            fragmentTransaction.show(fragments[index]).commit();

            currentIndex=index;
        }

        if (index==3){
            ivMainSearch.setImageResource(R.drawable.icon_setting);
        }else {
            ivMainSearch.setImageResource(R.drawable.icon_search);
        }
    }
   public String getDay(){
       String[] dayArray = getResources().getStringArray(R.array.weekday);
       Calendar instance = Calendar.getInstance();
       int i = instance.get(Calendar.DAY_OF_WEEK) - 1;
       return dayArray[i];

   }

    @OnClick(R.id.iv_main_search)
    public void onViewClicked() {
        if (index!=3){
            openActivity(SearchActivity.class);
        }else
          openActivity(ChartActivity.class);

    }
    private long lastTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK==keyCode){
            if (System.currentTimeMillis()-lastTime>2000){
                showShort("再按一次退出程序");
                lastTime=System.currentTimeMillis();
            }else {
                finishAll();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SimpleRxGalleryFinal.get().onActivityResult(requestCode,resultCode,data);
    }
}
