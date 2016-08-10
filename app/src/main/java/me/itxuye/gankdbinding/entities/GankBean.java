package me.itxuye.gankdbinding.entities;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by itxuye on 2016/7/30.
 */

public class GankBean {

  @SerializedName("error") private boolean error;
  /**
   * _id : 579aade1421aa90d39e70989
   * createdAt : 2016-07-29T09:14:09.997Z
   * desc : 类似刮刮乐擦除效果，做的挺逼真，可以可以。
   * publishedAt : 2016-07-29T09:37:39.219Z
   * source : chrome
   * type : Android
   * url : https://github.com/jackpocket/android_scratchoff
   * used : true
   * who : 代码家
   */

  @SerializedName("results") private List<ResultsBean> results;

  public boolean isError() {
    return error;
  }

  public void setError(boolean error) {
    this.error = error;
  }

  public List<ResultsBean> getResults() {
    return results;
  }

  public void setResults(List<ResultsBean> results) {
    this.results = results;
  }

  public static class ResultsBean {
    @SerializedName("_id") private String id;
    @SerializedName("createdAt") private String createdAt;
    @SerializedName("desc") private String desc;
    @SerializedName("publishedAt") private String publishedAt;
    @SerializedName("source") private String source;
    @SerializedName("type") private String type;
    @SerializedName("url") private String url;
    @SerializedName("used") private boolean used;
    @SerializedName("who") private String who;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getCreatedAt() {
      return createdAt;
    }

    public void setCreatedAt(String createdAt) {
      this.createdAt = createdAt;
    }

    public String getDesc() {
      return desc;
    }

    public void setDesc(String desc) {
      this.desc = desc;
    }

    public String getPublishedAt() {
      return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
      this.publishedAt = publishedAt;
    }

    public String getSource() {
      return source;
    }

    public void setSource(String source) {
      this.source = source;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public boolean isUsed() {
      return used;
    }

    public void setUsed(boolean used) {
      this.used = used;
    }

    public String getWho() {
      return who;
    }

    public void setWho(String who) {
      this.who = who;
    }

    @Override public String toString() {
      return "ResultsBean{" +
          "id='" + id + '\'' +
          ", createdAt='" + createdAt + '\'' +
          ", desc='" + desc + '\'' +
          ", publishedAt='" + publishedAt + '\'' +
          ", source='" + source + '\'' +
          ", type='" + type + '\'' +
          ", url='" + url + '\'' +
          ", used=" + used +
          ", who='" + who + '\'' +
          '}';
    }
  }

  @Override public String toString() {
    return "GankBean{" +
        "error=" + error +
        ", results=" + results +
        '}';
  }
}
