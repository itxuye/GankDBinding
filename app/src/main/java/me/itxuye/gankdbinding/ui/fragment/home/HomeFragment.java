package me.itxuye.gankdbinding.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.base.BaseLazyDataBindingFragment;
import me.itxuye.gankdbinding.databinding.FragmentFirstBinding;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by itxuye(http://www.itxuye.com) on 2016/8/12.
 */
public class HomeFragment extends BaseLazyDataBindingFragment<FragmentFirstBinding> {
  public static SupportFragment newInstance() {
    HomeFragment firstTabFragment = new HomeFragment();
    return firstTabFragment;
  }

  @Override protected void initViews() {
    GanHuoPagerAdapter pagerAdapter = new GanHuoPagerAdapter(getChildFragmentManager());
    b.viewpager.setAdapter(pagerAdapter);
    //b.viewpager.setOffscreenPageLimit(5);
    b.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    b.tabLayout.setupWithViewPager(b.viewpager);
  }

  @Override protected int getContentViewId() {
    return R.layout.fragment_first;
  }

  @Override protected void initLazyView(@Nullable Bundle savedInstanceState) {
    b.toolbar.setTitle("首页");
  }


  class GanHuoPagerAdapter extends FragmentStatePagerAdapter {

    String[] title = {"All","Android","iOS","前端","瞎推荐","拓展资源","App"};

    public GanHuoPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      if (position == 0) {
        return AllFragment.newInstance(title[0]);
      }
        return GanHuoFragment.newInstance(title[position]);
    }

    @Override
    public int getCount() {
      return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return title[position];
    }

  }

}
