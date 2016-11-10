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
import me.itxuye.gankdbinding.R;
import me.yokeyword.fragmentation.SupportFragment;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseLazyDataBindingFragment<T extends ViewDataBinding>
    extends SupportFragment {
  // 再点一次退出程序时间设置
  private static final long WAIT_TIME = 2000L;
  private long TOUCH_TIME = 0;

  private boolean mInited = false;
  private Bundle mSavedInstanceState;
  protected T b;
  private CompositeSubscription mCompositeSubscription;

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
    return b.getRoot();
  }

  protected abstract void initViews();

  protected abstract int getContentViewId();

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    if (savedInstanceState == null) {
      if (!isHidden()) {
        mInited = true;
        initLazyView(null);
      }
    } else {
      // isSupportHidden()仅在saveIns tanceState!=null时有意义,是库帮助记录Fragment状态的方法
      if (!isSupportHidden()) {
        mInited = true;
        initLazyView(savedInstanceState);
      }
    }
  }

  @Override public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    if (!mInited && !hidden) {
      mInited = true;
      initLazyView(mSavedInstanceState);
    }
  }

  /**
   * 懒加载
   */
  protected abstract void initLazyView(@Nullable Bundle savedInstanceState);

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

  public void addSubscription(Subscription s) {
    if (this.mCompositeSubscription == null) {
      this.mCompositeSubscription = new CompositeSubscription();
    }
    this.mCompositeSubscription.add(s);
  }

  @Override public void onDestroyView() {
    try {
      if (this.mCompositeSubscription != null) {
        this.mCompositeSubscription.unsubscribe();
        this.mCompositeSubscription = null;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    super.onDestroyView();
  }
}
