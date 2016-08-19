package me.itxuye.gankdbinding.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.base.BaseBackFragment;
import me.itxuye.gankdbinding.databinding.FragmentTestBinding;
import me.itxuye.gankdbinding.utils.ResourceUtils;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by itxuye(http://www.itxuye.com) on 2016/8/18.
 */

public class TestFragment extends BaseBackFragment<FragmentTestBinding> {

  private static final int REQ_MSG = 10;

  public static final int FIRST = 0;
  public static final int SECOND = 1;
  public static final int THIRD = 2;
  public static final int FOURTH = 3;

  private SupportFragment[] mFragments = new SupportFragment[4];
  private HomeFragmentAdapter adapter;

  public static SupportFragment newInstance() {
    TestFragment firstTabFragment = new TestFragment();
    //firstTabFragment
    return firstTabFragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      mFragments[FIRST] = MFirstTabFragment.newInstance();
      mFragments[SECOND] = MSecondTabFragment.newInstance();
      mFragments[THIRD] = MThirdTabFragment.newInstance();
      mFragments[FOURTH] = MFourthTabFragment.newInstance();
    } else {
      mFragments[FIRST] = findChildFragment(MFirstTabFragment.class);
      mFragments[SECOND] = findChildFragment(MSecondTabFragment.class);
      mFragments[THIRD] = findChildFragment(MThirdTabFragment.class);
      mFragments[FOURTH] = findChildFragment(MFourthTabFragment.class);
    }
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override protected void initViews() {

  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    b.tab.setTabMode(TabLayout.MODE_FIXED);
    initTab();
    setListener();
    setAdapterAndNotify();
    b.homeContent.setOffscreenPageLimit(3);
  }

  @Override protected int getContentViewId() {
    return R.layout.fragment_test;
  }

  private void setAdapterAndNotify() {
    if (null == adapter) {
      adapter = new HomeFragmentAdapter(getChildFragmentManager(), mFragments);
      b.homeContent.setAdapter(adapter);
    } else {
      adapter.notifyDataSetChanged();
    }
  }

  private void setListener() {
    //这行代码将TabLayout与ViewPager的页面切换绑定 原理很简单 看源码
    b.homeContent.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(b.tab));
    b.tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        //ViewPager切换页面无动画需要使用两个参数的方法并传入false
        b.homeContent.setCurrentItem(position, false);
        //这句别忘了 否则tab就丢失选择器效果了
        tab.getCustomView().setEnabled(true);
      }

      @Override public void onTabUnselected(TabLayout.Tab tab) {
        //别忘了
        tab.getCustomView().setEnabled(false);
      }

      @Override public void onTabReselected(TabLayout.Tab tab) {

      }
    });
  }

  //为了达到切换tab文字和icon同步变色 这里给TextView设置选择器使用Enabled属性切换
  //icon同理
  private void initTab() {
    LayoutInflater inflater = _mActivity.getLayoutInflater();
    TextView view;
    for (int i = 0; i < 4; i++) {
      view = (TextView) inflater.inflate(R.layout.tab_home_item, null);
      String text = null;
      Drawable drawable = null;
      switch (i) {
        case 0:
          text = ResourceUtils.getString(R.string.tab_main);
          view.setEnabled(true);
          drawable = ResourceUtils.getDrawable(R.drawable.tab_main);
          break;
        case 1:
          text = ResourceUtils.getString(R.string.tab_what);
          drawable = ResourceUtils.getDrawable(R.drawable.tab_what);
          break;
        case 2:
          text = ResourceUtils.getString(R.string.tab_message);
          drawable = ResourceUtils.getDrawable(R.drawable.tab_message);
          break;
        case 3:
          text = ResourceUtils.getString(R.string.tab_mine);
          drawable = ResourceUtils.getDrawable(R.drawable.tab_mine);
          break;
      }
      view.setText(text);
      drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
      view.setCompoundDrawables(null, drawable, null, null);
      TabLayout.Tab tab = this.b.tab.newTab().setCustomView(view);
      this.b.tab.addTab(tab, i == 0 ? true : false);
    }
  }

  @Override public boolean onBackPressedSupport() {
    return false;
  }
}
