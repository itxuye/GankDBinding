package me.itxuye.gankdbinding.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.base.BaseLazyDataBindingFragment;
import me.itxuye.gankdbinding.databinding.FragmentMfirstBinding;
import me.itxuye.gankdbinding.event.StartBrotherEvent;
import me.yokeyword.fragmentation.SupportFragment;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by itxuye(http://www.itxuye.com) on 2016/8/12.
 */
public class MFirstTabFragment extends BaseLazyDataBindingFragment<FragmentMfirstBinding> {
  public static SupportFragment newInstance() {
    MFirstTabFragment firstTabFragment = new MFirstTabFragment();
    //firstTabFragment
    return firstTabFragment;
  }

  @Override protected void initViews() {
    b.setPresenter(new Presenter());
  }

  @Override protected int getContentViewId() {
    return R.layout.fragment_mfirst;
  }

  @Override protected void initLazyView(@Nullable Bundle savedInstanceState) {

  }

  public class Presenter {
    public void onClick(View view) {
      EventBus.getDefault().post(new StartBrotherEvent(TestFragment.newInstance()));
    }
  }

  @Override public boolean onBackPressedSupport() {
    return false;
  }
}
