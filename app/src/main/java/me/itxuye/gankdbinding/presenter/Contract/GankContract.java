package me.itxuye.gankdbinding.presenter.Contract;

import java.util.List;
import me.itxuye.gankdbinding.base.BasePresenter;
import me.itxuye.gankdbinding.base.BaseView;
import me.itxuye.gankdbinding.model.entity.Gank;

/**
 * @author Created by itxuye(http://itxuye.com)
 * @version 1.0.0
 */

public interface GankContract {
  interface View extends BaseView {
    void showGankList(List<Gank> gankList);
    void showProgressBar();
    void hideProgressBar();
    void showErrorView();
  }

  interface Presenter extends BasePresenter<GankContract.View> {

    void fetchGankData(int year, int month, int day);
  }
}
