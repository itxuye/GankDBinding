package me.itxuye.gankdbinding.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.base.BaseLazyDataBindingFragment;
import me.itxuye.gankdbinding.databinding.FragmentThirdBinding;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by itxuye(http://www.itxuye.com) on 2016/8/12.
 */
public class ThirdTabFragment extends BaseLazyDataBindingFragment<FragmentThirdBinding> {
  public static SupportFragment newInstance() {
    ThirdTabFragment firstTabFragment = new ThirdTabFragment();
    //firstTabFragment
    return firstTabFragment;
  }

  @Override protected void initViews() {

  }

  @Override protected int getContentViewId() {
    return R.layout.fragment_third;
  }

  @Override protected void initLazyView(@Nullable Bundle savedInstanceState) {

  }
}
