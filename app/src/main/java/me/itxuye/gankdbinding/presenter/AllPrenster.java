package me.itxuye.gankdbinding.presenter;

import javax.inject.Inject;
import me.itxuye.gankdbinding.base.RxPresenter;
import me.itxuye.gankdbinding.model.FunnyData;
import me.itxuye.gankdbinding.model.MeiziData;
import me.itxuye.gankdbinding.network.ApiService;
import me.itxuye.gankdbinding.presenter.Contract.AllContract;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * @author Created by itxuye(http://itxuye.com)
 * @version 1.0.0
 */

public class AllPrenster extends RxPresenter<AllContract.View> implements AllContract.Presenter {

  private ApiService mApiService;

  @Inject public AllPrenster(ApiService apiService) {
    this.mApiService = apiService;
  }

  @Override public void getTodayData(int page) {
    fetchMeiziData(page);
  }

  public void fetchMeiziData(int page) {
    mView.showProgress();
    Subscription subscribe =
        Observable.zip(mApiService.getMeiziData(page), mApiService.getFunnyData(page),
            new Func2<MeiziData, FunnyData, MeiziData>() {
              @Override public MeiziData call(MeiziData meiziData, FunnyData funnyData) {
                return createMeiziDataWith休息视频Desc(meiziData, funnyData);
              }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<MeiziData>() {
              @Override public void call(MeiziData meiziData) {
                if (meiziData.results.size() == 0) {
                  mView.showNoMoreData();
                } else {
                  mView.showMeiziList(meiziData.results);
                }
                mView.hideProgress();
              }
            }, new Action1<Throwable>() {
              @Override public void call(Throwable throwable) {
                mView.showErrorView();
                mView.hideProgress();
              }
            });
    addSubscribe(subscribe);
  }

  private MeiziData createMeiziDataWith休息视频Desc(MeiziData meiziData, FunnyData funnyData) {
    int size = Math.min(meiziData.results.size(), funnyData.results.size());
    for (int i = 0; i < size; i++) {
      meiziData.results.get(i).desc =
          meiziData.results.get(i).desc + "，" + funnyData.results.get(i).desc;
      meiziData.results.get(i).who = funnyData.results.get(i).who;
    }
    return meiziData;
  }
}
