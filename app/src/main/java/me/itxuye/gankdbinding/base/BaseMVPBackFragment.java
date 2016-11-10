package me.itxuye.gankdbinding.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import javax.inject.Inject;
import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.app.GankApplication;
import me.itxuye.gankdbinding.di.component.DaggerFragmentComponent;
import me.itxuye.gankdbinding.di.component.FragmentComponent;
import me.itxuye.gankdbinding.di.module.FragmentModule;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

public abstract class BaseMVPBackFragment<T extends ViewDataBinding, P extends BasePresenter>
    extends SwipeBackFragment implements BaseView {

  protected T b;

  @Inject protected P mPresenter;

  protected FragmentComponent getFragmentComponent() {
    return DaggerFragmentComponent.builder()
        .applicationComponent(GankApplication.getAppComponent())
        .fragmentModule(getFragmentModule())
        .build();
  }

  protected FragmentModule getFragmentModule() {
    return new FragmentModule(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    if (getContentViewId() != 0) {
      b = DataBindingUtil.inflate(inflater, getContentViewId(), container, false);
    } else {
      throw new IllegalArgumentException("You must return a right contentView layout resource Id");
    }
    initInject();
    initViews();

    return attachToSwipeBack(b.getRoot());
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mPresenter.attachView(this);
    initData();
  }

  protected abstract void initData();

  protected abstract void initInject();

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

  @Override public void onDestroyView() {

    super.onDestroyView();
  }
}
