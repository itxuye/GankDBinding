package me.itxuye.gankdbinding.presenter.Contract;

import java.util.List;
import me.itxuye.gankdbinding.base.BasePresenter;
import me.itxuye.gankdbinding.base.BaseView;
import me.itxuye.gankdbinding.model.entity.Meizi;

/**
 * @author Created by itxuye(http://itxuye.com)
 * @version 1.0.0
 */

public interface AllContract {
  interface View extends BaseView {
    void showProgress();
    void hideProgress();
    void showErrorView();
    void showNoMoreData();
    void showMeiziList(List<Meizi> meiziList);
  }

  interface Presenter extends BasePresenter<View> {

    void getTodayData(int page);
  }
}
