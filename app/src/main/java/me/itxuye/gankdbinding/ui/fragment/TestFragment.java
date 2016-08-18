package me.itxuye.gankdbinding.ui.fragment;

import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.base.BaseBackFragment;
import me.itxuye.gankdbinding.databinding.FragmentTestBinding;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by itxuye(http://www.itxuye.com) on 2016/8/18.
 */

public class TestFragment extends BaseBackFragment<FragmentTestBinding> {
  public static SupportFragment newInstance() {
    TestFragment firstTabFragment = new TestFragment();
    //firstTabFragment
    return firstTabFragment;
  }

  @Override protected void initViews() {

  }

  @Override protected int getContentViewId() {
    return R.layout.fragment_test;
  }
}
