package me.itxuye.gankdbinding.base;

/**
 * @author Created by itxuye(http://itxuye.com)
 *         on   2016/11/9 2:23
 * @version 1.0.0
 */

public interface BasePresenter<T extends BaseView> {

  void attachView(T view);

  void detachView();
}
