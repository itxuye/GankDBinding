package me.itxuye.gankdbinding.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import javax.inject.Inject;
import me.itxuye.gankdbinding.app.GankApplication;
import me.itxuye.gankdbinding.di.component.ActivityComponent;
import me.itxuye.gankdbinding.di.component.DaggerActivityComponent;
import me.itxuye.gankdbinding.di.module.ActivityModule;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * MVP activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends SupportActivity implements BaseView{

    @Inject
    protected T mPresenter;
    protected Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initViews();
        mContext = this;
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);

        initEventAndData();
    }

    protected abstract void initViews();

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    protected ActivityComponent getActivityComponent(){
        return  DaggerActivityComponent.builder()
                .applicationComponent(GankApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }


    protected abstract void initInject();
    protected abstract int getLayout();
    protected abstract void initEventAndData();
}