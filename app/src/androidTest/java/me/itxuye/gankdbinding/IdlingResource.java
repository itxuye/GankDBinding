package me.itxuye.gankdbinding;

/**
 * Created by itxuye on 2016/7/30.
 */

public interface IdlingResource {

  /**
   * 用来标识 IdlingResource 名称
   */
  public String getName();

  /**
   * 当前 IdlingResource 是否空闲 .
   */
  public boolean isIdleNow();

  /**
   * 注册一个空闲状态变换的ResourceCallback回调
   */
  public void registerIdleTransitionCallback(ResourceCallback callback);

  /**
   * 通知Espresso当前IdlingResource状态变换为空闲的回调接口
   */
  public interface ResourceCallback {
    /**
     * 当前状态转变为空闲时，调用该方法告诉Espresso
     */
    public void onTransitionToIdle();
  }
}
