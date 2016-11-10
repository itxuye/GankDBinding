package me.itxuye.gankdbinding.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.event.StartBrotherEvent;
import me.itxuye.gankdbinding.event.TabSelectedEvent;
import me.itxuye.gankdbinding.ui.fragment.home.HomeFragment;
import me.itxuye.gankdbinding.ui.view.BottomBar;
import me.itxuye.gankdbinding.ui.view.BottomBarTab;
import me.yokeyword.fragmentation.SupportFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by itxuye(http://www.itxuye.com) on 2016/8/3.
 */

public class MainFragment extends SupportFragment {
  private static final int REQ_MSG = 10;

  public static final int FIRST = 0;
  public static final int SECOND = 1;
  public static final int THIRD = 2;
  public static final int FOURTH = 3;

  private SupportFragment[] mFragments = new SupportFragment[4];

  private BottomBar mBottomBar;

  public static MainFragment newInstance(String title) {
    MainFragment mainFragment = new MainFragment();
    Bundle bundle = new Bundle();
    bundle.putString("key", title);
    return mainFragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);

    if (savedInstanceState == null) {
      mFragments[FIRST] = HomeFragment.newInstance();
      mFragments[SECOND] = SecondTabFragment.newInstance();
      mFragments[THIRD] = ThirdTabFragment.newInstance();
      mFragments[FOURTH] = FourthTabFragment.newInstance();

      loadMultipleRootFragment(R.id.fl_tab_container, FIRST, mFragments[FIRST], mFragments[SECOND],
          mFragments[THIRD], mFragments[FOURTH]);
    } else {
      // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

      // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
      mFragments[FIRST] = findChildFragment(HomeFragment.class);
      mFragments[SECOND] = findChildFragment(SecondTabFragment.class);
      mFragments[THIRD] = findChildFragment(ThirdTabFragment.class);
      mFragments[FOURTH] = findChildFragment(FourthTabFragment.class);
    }

    initView(view);
    return view;
  }

  private void initView(View view) {
    EventBus.getDefault().register(this);
    mBottomBar = (BottomBar) view.findViewById(R.id.bottomBar);

    mBottomBar.addItem(new BottomBarTab(_mActivity, R.drawable.ic_home_yellow_900_24dp, "首页"))
        .addItem(new BottomBarTab(_mActivity, R.drawable.ic_home_yellow_900_24dp, "首页"))
        .addItem(new BottomBarTab(_mActivity, R.drawable.ic_home_yellow_900_24dp, "首页"))
        .addItem(new BottomBarTab(_mActivity, R.drawable.ic_home_yellow_900_24dp, "首页"));

    mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
      @Override public void onTabSelected(int position, int prePosition) {
        showHideFragment(mFragments[position], mFragments[prePosition]);
      }

      @Override public void onTabUnselected(int position) {

      }

      @Override public void onTabReselected(int position) {
        // 这里推荐使用EventBus来实现 -> 解耦
        // 在FirstPagerFragment,FirstHomeFragment中接收, 因为是嵌套的Fragment
        // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
        EventBus.getDefault().post(new TabSelectedEvent(position));
      }
    });
  }

  @Override protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
    super.onFragmentResult(requestCode, resultCode, data);
    if (requestCode == REQ_MSG && resultCode == RESULT_OK) {

    }
  }

  /**
   * start other BrotherFragment
   */
  @Subscribe public void startBrother(StartBrotherEvent event) {
    start(event.targetFragment);
  }

  @Override public void onDestroyView() {
    EventBus.getDefault().unregister(this);
    super.onDestroyView();
  }
}
