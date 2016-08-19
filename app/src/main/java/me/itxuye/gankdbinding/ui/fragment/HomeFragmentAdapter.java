package me.itxuye.gankdbinding.ui.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by itxuye(http://www.itxuye.com) on 2016/8/18.
 */
public class HomeFragmentAdapter extends FragmentPagerAdapter {
  private SupportFragment[] list;

  public HomeFragmentAdapter(FragmentManager fm, SupportFragment[] list) {
    super(fm);
    this.list = list;
  }

  @Override public SupportFragment getItem(int position) {
    return list[position];
  }

  @Override public int getCount() {
    return list.length;
  }
}
