package me.itxuye.gankdbinding.ui.fragment.gank;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.adapter.GankAdapter;
import me.itxuye.gankdbinding.base.BaseActivity;
import me.itxuye.gankdbinding.constant.GankApiConstant;
import me.itxuye.gankdbinding.model.entity.Gank;
import me.itxuye.gankdbinding.model.entity.Meizi;
import me.itxuye.gankdbinding.presenter.Contract.GankContract;
import me.itxuye.gankdbinding.presenter.GankPresenter;
import me.itxuye.gankdbinding.utils.DateUtil;
import me.itxuye.gankdbinding.utils.NetworkUtils;
import me.itxuye.gankdbinding.utils.SnackBarUtil;
import me.itxuye.gankdbinding.widget.ShareElement;

/**
 * @author Created by itxuye(http://itxuye.com)
 * @version 1.0.0
 */

public class GankActivity extends BaseActivity<GankPresenter> implements GankContract.View {

  private Meizi meizi;
  private List<Gank> list;
  private GankAdapter adapter;
  private Calendar calendar;
  CollapsingToolbarLayout toolbarLayout;
  FloatingActionButton fab;
  ImageView ivHead;
  RecyclerView rvGank;
  SmoothProgressBar progressbar;

  @Override protected void initViews() {
    toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
    fab = (FloatingActionButton) findViewById(R.id.fab);
    ivHead = (ImageView) findViewById(R.id.iv_head);
    rvGank = (RecyclerView) findViewById(R.id.rv_gank);
    progressbar = (SmoothProgressBar) findViewById(R.id.progressbar);

    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {

        if (!NetworkUtils.isWIFIConnected(GankActivity.this)) {
          SnackBarUtil.showTipWithAction(fab, "你使用的不是wifi网络，要继续吗？", "继续",
              new View.OnClickListener() {
                @Override public void onClick(View v) {
                  //if (list.size() > 0 && list.get(0).type.equals("休息视频")) {
                  //  Intent intent = new Intent(GankActivity.this, WebVideoActivity.class);
                  //  intent.putExtra(GankConfig.GANK, list.get(0));
                  //  startActivity(intent);
                  //} else {
                  //  TipUtil.showSnackTip(fab, getString(R.string.something_wrong));
                  //}
                }
              }, Snackbar.LENGTH_LONG);
        } else {
          //if (list.size() > 0 && list.get(0).type.equals("休息视频")) {
          //  Intent intent = new Intent(GankActivity.this, WebVideoActivity.class);
          //  intent.putExtra(GankConfig.GANK, list.get(0));
          //  startActivity(intent);
          //} else {
          //  TipUtil.showSnackTip(fab, getString(R.string.something_wrong));
          //}
        }
      }
    });
  }

  @Override protected void initInject() {
    getActivityComponent().inject(this);
  }

  @Override protected int getLayout() {
    return R.layout.fragment_gank;
  }

  @Override protected void initEventAndData() {
    getIntentData();
    initGankView();
  }

  private void initGankView() {
    list = new ArrayList<>();
    adapter = new GankAdapter(this, list);
    rvGank.setLayoutManager(new LinearLayoutManager(this));
    rvGank.setItemAnimator(new DefaultItemAnimator());
    rvGank.setAdapter(adapter);
    setTitle(DateUtil.toDateString(meizi.publishedAt));
    ivHead.setImageDrawable(ShareElement.shareDrawable);
    ViewCompat.setTransitionName(ivHead, GankApiConstant.TRANSLATE_GIRL_VIEW);
    fab.setClickable(false);
  }

  private void getIntentData() {
    meizi = (Meizi) getIntent().getSerializableExtra(GankApiConstant.MEIZI);
    calendar = Calendar.getInstance();
    calendar.setTime(meizi.publishedAt);
    mPresenter.fetchGankData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
        calendar.get(Calendar.DAY_OF_MONTH));
  }

  @Override public void showGankList(List<Gank> gankList) {
    list.addAll(gankList);
    adapter.notifyDataSetChanged();
    fab.setClickable(true);
  }

  @Override public void showProgressBar() {
    progressbar.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgressBar() {
    progressbar.setVisibility(View.GONE);
  }

  @Override public void showErrorView() {
    SnackBarUtil.showTipWithAction(fab, getString(R.string.load_failed), getString(R.string.retry),
        new View.OnClickListener() {
          @Override public void onClick(View v) {
            mPresenter.fetchGankData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));
          }
        });
  }

  @Override public void showError(String msg) {

  }
}
