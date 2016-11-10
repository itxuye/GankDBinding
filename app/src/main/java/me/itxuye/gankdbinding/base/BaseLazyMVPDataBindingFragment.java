package me.itxuye.gankdbinding.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import javax.inject.Inject;
import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.app.GankApplication;
import me.itxuye.gankdbinding.di.component.DaggerFragmentComponent;
import me.itxuye.gankdbinding.di.component.FragmentComponent;
import me.itxuye.gankdbinding.di.module.FragmentModule;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseLazyMVPDataBindingFragment<T extends ViewDataBinding, P extends BasePresenter>
    extends SupportFragment implements BaseView {
  // 再点一次退出程序时间设置
  private static final long WAIT_TIME = 2000L;
  private long TOUCH_TIME = 0;
  protected boolean isInited = false;
  protected T b;
  @Inject protected P mPresenter;
  private Bundle mSavedInstanceState;

  protected FragmentComponent getFragmentComponent() {
    return DaggerFragmentComponent.builder()
        .applicationComponent(GankApplication.getAppComponent())
        .fragmentModule(getFragmentModule())
        .build();
  }

  protected FragmentModule getFragmentModule() {
    return new FragmentModule(this);
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mSavedInstanceState = savedInstanceState;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    if (getContentViewId() != 0) {
      b = DataBindingUtil.inflate(inflater, getContentViewId(), container, false);
    } else {
      throw new IllegalArgumentException("You must return a right contentView layout resource Id");
    }
    initViews();
    initInject();
    return b.getRoot();
  }

  protected abstract void initInject();

  protected abstract void initViews();

  protected abstract int getContentViewId();

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    if (savedInstanceState == null) {
      if (!isHidden()) {
        isInited = true;
        initLazyData(null);
      }
    } else {
      // isSupportHidden()仅在saveIns tanceState!=null时有意义,是库帮助记录Fragment状态的方法
      if (!isSupportHidden()) {
        isInited = true;
        initLazyData(savedInstanceState);
      }
    }
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mPresenter.attachView(this);
    //if (savedInstanceState == null) {
    //  if (!isHidden()) {
    //    isInited = true;
    //    initLazyData();
    //  }
    //} else {
    //  if (!isSupportHidden()) {
    //    isInited = true;
    //    initLazyData();
    //  }
    //}
  }

  @Override public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    if (!isInited && !hidden) {
      isInited = true;
      initLazyData(mSavedInstanceState);
    }
  }

  /**
   * 懒加载
   */
  protected abstract void initLazyData(@Nullable Bundle savedInstanceState);

  /**
   * 处理回退事件
   */
  @Override public boolean onBackPressedSupport() {
    if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
      _mActivity.finish();
    } else {
      TOUCH_TIME = System.currentTimeMillis();
      Toast.makeText(_mActivity, R.string.press_again_exit, Toast.LENGTH_SHORT).show();
    }
    return true;
  }

  /**
   * show toast with Snackbar
   */
  protected void showToast(String msg) {
    if (!TextUtils.isEmpty(msg)) {
      Snackbar.make(_mActivity.getWindow().getDecorView(), msg, Snackbar.LENGTH_SHORT).show();
    }
  }
}
