package me.itxuye.gankdbinding.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.adapter.AllAdapter;
import me.itxuye.gankdbinding.base.BaseLazyMVPDataBindingFragment;
import me.itxuye.gankdbinding.constant.GankApiConstant;
import me.itxuye.gankdbinding.databinding.FragmentAllBinding;
import me.itxuye.gankdbinding.databinding.ItemMeiziBinding;
import me.itxuye.gankdbinding.model.entity.Meizi;
import me.itxuye.gankdbinding.presenter.AllPrenster;
import me.itxuye.gankdbinding.presenter.Contract.AllContract;
import me.itxuye.gankdbinding.ui.fragment.gank.GankActivity;
import me.itxuye.gankdbinding.utils.SPDataUtil;
import me.itxuye.gankdbinding.utils.SnackBarUtil;
import me.itxuye.gankdbinding.widget.LMRecyclerView;
import me.itxuye.gankdbinding.widget.ShareElement;

/**
 * @author Created by itxuye(http://itxuye.com)
 * @version 1.0.0
 */

public class AllFragment extends BaseLazyMVPDataBindingFragment<FragmentAllBinding, AllPrenster>
    implements AllContract.View, LMRecyclerView.LoadMoreListener,
    SwipeRefreshLayout.OnRefreshListener {

  private List<Meizi> meizis;
  private AllAdapter adapter;

  private int page = 1;
  private boolean isRefresh = true;
  private boolean canLoading = true;

  public static AllFragment newInstance(String s) {
    return new AllFragment();
  }

  @Override protected void initInject() {
    getFragmentComponent().inject(this);
  }

  @Override protected void initViews() {

  }

  @Override protected int getContentViewId() {
    return R.layout.fragment_all;
  }

  @Override protected void initLazyData(@Nullable Bundle savedInstanceState) {
    meizis = SPDataUtil.getFirstPageGirls(_mActivity);
    if (meizis == null) meizis = new ArrayList<>();
    adapter = new AllAdapter(_mActivity, meizis);
    b.recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
    b.recyclerView.setAdapter(adapter);

    b.recyclerView.setLoadMoreListener(this);
    b.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent,
        R.color.blue);
    b.swipeRefreshLayout.setOnRefreshListener(this);
    b.swipeRefreshLayout.post(new Runnable() {
      @Override public void run() {
        b.swipeRefreshLayout.setRefreshing(true);
        mPresenter.getTodayData(page);
      }
    });

    adapter.setmOnClickContentListener(new AllAdapter.OnClickContentListener() {
      @Override
      public void onClick(int position, RecyclerView.ViewHolder holder, ItemMeiziBinding binding) {
        ShareElement.shareDrawable = binding.ivMeizi.getDrawable();
        Intent intent = new Intent(_mActivity, GankActivity.class);
        intent.putExtra(GankApiConstant.MEIZI, (Serializable) binding.getRoot().getTag());
        ActivityOptionsCompat optionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(_mActivity, binding.ivMeizi,
                getResources().getString(R.string.image_transition));
        ActivityCompat.startActivity(_mActivity, intent, optionsCompat.toBundle());
      }
    });
  }

  @Override public void showProgress() {
    if (!b.swipeRefreshLayout.isRefreshing()) b.swipeRefreshLayout.setRefreshing(true);
  }

  @Override public void hideProgress() {
    if (b.swipeRefreshLayout.isRefreshing()) b.swipeRefreshLayout.setRefreshing(false);
  }

  @Override public void showErrorView() {
    canLoading = true;
    SnackBarUtil.showTipWithAction(b.recyclerView, getString(R.string.load_failed),
        getString(R.string.retry), new View.OnClickListener() {
          @Override public void onClick(View v) {
            mPresenter.getTodayData(page);
          }
        });
  }

  @Override public void showNoMoreData() {
    canLoading = false;
    SnackBarUtil.showSnackTip(b.recyclerView, getString(R.string.no_more_data));
  }

  @Override public void showMeiziList(List<Meizi> meiziList) {
    canLoading = true;
    page++;
    if (isRefresh) {
      SPDataUtil.saveFirstPageGirls(_mActivity, meiziList);
      meizis.clear();
      meizis.addAll(meiziList);
      adapter.notifyDataSetChanged();
      isRefresh = false;
    } else {
      meizis.addAll(meiziList);
      adapter.notifyDataSetChanged();
    }
  }

  @Override public void showError(String msg) {

  }

  @Override public void loadMore() {
    if (canLoading) {
      mPresenter.getTodayData(page);
      canLoading = false;
    }
  }

  @Override public void onRefresh() {
    isRefresh = true;
    page = 1;
    mPresenter.getTodayData(page);
  }
}
