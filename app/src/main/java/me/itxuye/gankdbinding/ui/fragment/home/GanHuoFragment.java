package me.itxuye.gankdbinding.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.adapter.GanhuoAdapter;
import me.itxuye.gankdbinding.base.BaseLazyMVPDataBindingFragment;
import me.itxuye.gankdbinding.databinding.FragmentGanhuoBinding;
import me.itxuye.gankdbinding.model.entity.Gank;
import me.itxuye.gankdbinding.presenter.Contract.GanhuoContract;
import me.itxuye.gankdbinding.presenter.GanhuoPresenter;
import me.itxuye.gankdbinding.utils.SnackBarUtil;
import me.itxuye.gankdbinding.widget.LMRecyclerView;

/**
 * @author Created by itxuye(http://itxuye.com)
 * @version 1.0.0
 */
public class GanHuoFragment
    extends BaseLazyMVPDataBindingFragment<FragmentGanhuoBinding, GanhuoPresenter>
    implements GanhuoContract.View, LMRecyclerView.LoadMoreListener,
    SwipeRefreshLayout.OnRefreshListener {

  private static final String TYPE = "type";
  private String type;
  private GanhuoAdapter adapter;
  private List<Gank> gankList;
  private int page = 1;
  private boolean isRefresh = true;
  private boolean canLoading = true;

  public static GanHuoFragment newInstance(String type) {
    GanHuoFragment fragment = new GanHuoFragment();
    Bundle args = new Bundle();
    args.putString(TYPE, type);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      type = getArguments().getString(TYPE);
    }
  }

  @Override protected void initInject() {
    getFragmentComponent().inject(this);
  }

  @Override protected void initViews() {

  }

  @Override protected int getContentViewId() {
    return R.layout.fragment_ganhuo;
  }

  @Override protected void initLazyData(@Nullable Bundle savedInstanceState) {
    gankList = new ArrayList<>();
    adapter = new GanhuoAdapter(_mActivity, gankList);
    b.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    b.recyclerView.setAdapter(adapter);
    b.recyclerView.setLoadMoreListener(this);
    b.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent,
        R.color.blue);
    b.swipeRefreshLayout.setOnRefreshListener(this);
    b.swipeRefreshLayout.post(new Runnable() {
      @Override public void run() {
        b.swipeRefreshLayout.setRefreshing(true);
        mPresenter.loadGank(type, page);
      }
    });
  }

  @Override public void showProgressBar() {
    if (!b.swipeRefreshLayout.isRefreshing()) {
      b.swipeRefreshLayout.setRefreshing(true);
    }
  }

  @Override public void hideProgressBar() {
    if (b.swipeRefreshLayout.isRefreshing()) {
      b.swipeRefreshLayout.setRefreshing(false);
    }
  }

  @Override public void showErrorView() {
    canLoading = true;
    SnackBarUtil.showTipWithAction(b.recyclerView, getString(R.string.load_failed),
        getString(R.string.retry), new View.OnClickListener() {
          @Override public void onClick(View v) {
            mPresenter.loadGank(type, page);
          }
        });
  }

  @Override public void showNoMoreData() {
    canLoading = false;
    SnackBarUtil.showSnackTip(b.recyclerView, getString(R.string.no_more_data));
  }

  @Override public void showListView(List<Gank> gankList) {
    canLoading = true;
    page++;
    if (isRefresh) {
      this.gankList.clear();
      this.gankList.addAll(gankList);
      adapter.notifyDataSetChanged();
      isRefresh = false;
    } else {
      this.gankList.addAll(gankList);
      adapter.notifyDataSetChanged();
    }
  }

  @Override public void showError(String msg) {

  }

  @Override public void loadMore() {
    if (canLoading) {
      mPresenter.loadGank(type, page);
      canLoading = false;
    }
  }

  @Override public void onRefresh() {
    isRefresh = true;
    page = 1;
    mPresenter.loadGank(type, page);
  }
}
