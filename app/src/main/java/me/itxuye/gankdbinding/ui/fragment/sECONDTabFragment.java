package me.itxuye.gankdbinding.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.base.BaseLazyDataBindingFragment;
import me.itxuye.gankdbinding.databinding.FragmentSecondBinding;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by itxuye(http://www.itxuye.com) on 2016/8/12.
 */
public class SecondTabFragment extends BaseLazyDataBindingFragment<FragmentSecondBinding> {
  public static SupportFragment newInstance() {
    SecondTabFragment firstTabFragment = new SecondTabFragment();
    //firstTabFragment
    return firstTabFragment;
  }

  @Override protected void initViews() {

  }

  @Override protected int getContentViewId() {
    return R.layout.fragment_second;
  }

  @Override protected void initLazyView(@Nullable Bundle savedInstanceState) {

  }
}
