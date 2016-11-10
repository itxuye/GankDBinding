package me.itxuye.gankdbinding.presenter.Contract;

import java.util.List;
import me.itxuye.gankdbinding.base.BasePresenter;
import me.itxuye.gankdbinding.base.BaseView;
import me.itxuye.gankdbinding.model.entity.Gank;

/**
 * @author Created by itxuye(http://itxuye.com)
 * @version 1.0.0
 */

public interface GanhuoContract {
  interface View extends BaseView{
    void showProgressBar();
    void hideProgressBar();
    void showErrorView();
    void showNoMoreData();
    void showListView(List<Gank> gankList);
  }

  interface Presenter extends BasePresenter<GanhuoContract.View> {

    void loadGank(String type,int page);
  }
}
