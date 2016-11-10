package me.itxuye.gankdbinding.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.itxuye.gankdbinding.R;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseBackFragment<T extends ViewDataBinding> extends SwipeBackFragment {

  protected T b;
  private CompositeSubscription mCompositeSubscription;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    if (getContentViewId() != 0) {
      b = DataBindingUtil.inflate(inflater, getContentViewId(), container, false);
    } else {
      throw new IllegalArgumentException("You must return a right contentView layout resource Id");
    }
    initViews();
    return attachToSwipeBack(b.getRoot());
  }

  protected abstract void initViews();

  protected abstract int getContentViewId();

  protected void initToolbarNav(Toolbar toolbar) {
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        _mActivity.onBackPressed();
      }
    });
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
