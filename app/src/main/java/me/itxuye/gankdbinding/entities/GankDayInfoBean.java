package me.itxuye.gankdbinding.entities;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by itxuye on 2016/7/30.
 */

public class GankDayInfoBean {

  @SerializedName("error") private boolean error;
  @SerializedName("results") private ResultsBean results;
  @SerializedName("category") private List<String> category;

  public boolean isError() {
    return error;
  }

  public void setError(boolean error) {
    this.error = error;
  }

  public ResultsBean getResults() {
    return results;
  }

  public void setResults(ResultsBean results) {
    this.results = results;
  }

  public List<String> getCategory() {
    return category;
  }

  public void setCategory(List<String> category) {
    this.category = category;
  }

  public static class ResultsBean {
    /**
     * _id : 56cc6d23421aa95caa707a69
     * createdAt : 2015-08-06T07:15:52.65Z
     * desc : 类似Link Bubble的悬浮式操作设计
     * publishedAt : 2015-08-07T03:57:48.45Z
     * type : Android
     * url : https://github.com/recruit-lifestyle/FloatingView
     * used : true
     * who : mthli
     */

    @SerializedName("Android") private List<GankBean.ResultsBean> Android;
    /**
     * _id : 56cc6d1d421aa95caa707769
     * createdAt : 2015-08-07T01:32:51.588Z
     * desc : LLVM 简介
     * publishedAt : 2015-08-07T03:57:48.70Z
     * type : iOS
     * url : http://adriansampson.net/blog/llvm.html
     * used : true
     * who : CallMeWhy
     */

    @SerializedName("iOS") private List<GankBean.ResultsBean> iOS;
    /**
     * _id : 56cc6d23421aa95caa707c68
     * createdAt : 2015-08-06T13:06:17.211Z
     * desc : 听到就心情大好的歌，简直妖魔哈哈哈哈哈，原地址
     * http://v.youku.com/v_show/id_XMTQxODA5NDM2.html
     * publishedAt : 2015-08-07T03:57:48.104Z
     * type : 休息视频
     * url : http://www.zhihu.com/question/21778055/answer/19905413?utm_source=weibo&utm_medium=weibo_share&utm_content=share_answer&utm_campaign=share_button
     * used : true
     * who : lxxself
     */

    @SerializedName("休息视频") private List<GankBean.ResultsBean> 休息视频;
    /**
     * _id : 56cc6d23421aa95caa707bdf
     * createdAt : 2015-08-07T01:36:06.932Z
     * desc : Display GitHub code in tree format
     * publishedAt : 2015-08-07T03:57:48.81Z
     * type : 拓展资源
     * url : https://github.com/buunguyen/octotree
     * used : true
     * who : lxxself
     */

    @SerializedName("拓展资源") private List<GankBean.ResultsBean> 拓展资源;
    /**
     * _id : 56cc6d23421aa95caa707bd0
     * createdAt : 2015-08-07T01:52:30.267Z
     * desc : 程序员的电台FmM，这个页面chrome插件有问题啊哭，我写了回删除不了啊
     * publishedAt : 2015-08-07T03:57:48.84Z
     * type : 瞎推荐
     * url : https://cmd.fm/
     * used : true
     * who : lxxself
     */

    @SerializedName("瞎推荐") private List<GankBean.ResultsBean> 瞎推荐;
    /**
     * _id : 56cc6d23421aa95caa707c52
     * createdAt : 2015-08-07T01:21:06.112Z
     * desc : 8.7——（1）
     * publishedAt : 2015-08-07T03:57:47.310Z
     * type : 福利
     * url : http://ww2.sinaimg.cn/large/7a8aed7bgw1eutscfcqtcj20dw0i0q4l.jpg
     * used : true
     * who : 张涵宇
     */

    @SerializedName("福利") private List<GankBean.ResultsBean> 福利;

    public List<GankBean.ResultsBean> getAndroid() {
      return Android;
    }

    public void setAndroid(List<GankBean.ResultsBean> Android) {
      this.Android = Android;
    }

    public List<GankBean.ResultsBean> getIOS() {
      return iOS;
    }

    public void setIOS(List<GankBean.ResultsBean> iOS) {
      this.iOS = iOS;
    }

    public List<GankBean.ResultsBean> get休息视频() {
      return 休息视频;
    }

    public void set休息视频(List<GankBean.ResultsBean> 休息视频) {
      this.休息视频 = 休息视频;
    }

    public List<GankBean.ResultsBean> get拓展资源() {
      return 拓展资源;
    }

    public void set拓展资源(List<GankBean.ResultsBean> 拓展资源) {
      this.拓展资源 = 拓展资源;
    }

    public List<GankBean.ResultsBean> get瞎推荐() {
      return 瞎推荐;
    }

    public void set瞎推荐(List<GankBean.ResultsBean> 瞎推荐) {
      this.瞎推荐 = 瞎推荐;
    }

    public List<GankBean.ResultsBean> get福利() {
      return 福利;
    }

    public void set福利(List<GankBean.ResultsBean> 福利) {
      this.福利 = 福利;
    }
  }
}
