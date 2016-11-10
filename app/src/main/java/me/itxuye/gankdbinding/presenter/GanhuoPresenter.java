package me.itxuye.gankdbinding.presenter;

import javax.inject.Inject;
import me.itxuye.gankdbinding.base.RxPresenter;
import me.itxuye.gankdbinding.model.GanHuoData;
import me.itxuye.gankdbinding.network.ApiService;
import me.itxuye.gankdbinding.presenter.Contract.GanhuoContract;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author Created by itxuye(http://itxuye.com)
 * @version 1.0.0
 */

public class GanhuoPresenter extends RxPresenter<GanhuoContract.View> implements GanhuoContract.Presenter{

  private ApiService mApiService;

  @Inject public GanhuoPresenter(ApiService apiService) {
    this.mApiService = apiService;
  }

  @Override public void loadGank(String type, int page) {
    mView.showProgressBar();
    Subscription subscribe = mApiService.getGanHuoData(type, page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<GanHuoData>() {
          @Override public void call(GanHuoData ganHuoData) {
            mView.hideProgressBar();
            if (ganHuoData.results.size() == 0) {
              mView.showNoMoreData();
            } else {
              mView.showListView(ganHuoData.results);
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            mView.hideProgressBar();
            mView.showErrorView();
          }
        });
    addSubscribe(subscribe);
  }
}
