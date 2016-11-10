package me.itxuye.gankdbinding.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by itxuye(http://www.itxuye.com) on 2016/8/23.
 */

public class TestBean {

  @SerializedName("key") private int key;
  @SerializedName("name") private String name;
  @SerializedName("age") private int age;
  @SerializedName("address") private String address;
  @SerializedName("children") private List<ChildrenBean> children;

  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    this.key = key;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<ChildrenBean> getChildren() {
    return children;
  }

  public void setChildren(List<ChildrenBean> children) {
    this.children = children;
  }

  public static class ChildrenBean {
    @SerializedName("key") private int key;
    @SerializedName("name") private String name;
    @SerializedName("age") private int age;
    @SerializedName("address") private String address;

    public int getKey() {
      return key;
    }

    public void setKey(int key) {
      this.key = key;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }
  }
}
