package me.itxuye.gankdbinding.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.base.BaseLazyDataBindingFragment;
import me.itxuye.gankdbinding.databinding.FragmentFirstBinding;
import me.itxuye.gankdbinding.event.StartBrotherEvent;
import me.yokeyword.fragmentation.SupportFragment;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by itxuye(http://www.itxuye.com) on 2016/8/12.
 */
public class FirstTabFragment extends BaseLazyDataBindingFragment<FragmentFirstBinding> {
  public static SupportFragment newInstance() {
    FirstTabFragment firstTabFragment = new FirstTabFragment();
    //firstTabFragment
    return firstTabFragment;
  }

  @Override protected void initViews() {
    b.setPresenter(new Presenter());
  }

  @Override protected int getContentViewId() {
    return R.layout.fragment_first;
  }

  @Override protected void initLazyView(@Nullable Bundle savedInstanceState) {

  }

  public class Presenter {
    public void onClick(View view) {
      EventBus.getDefault().post(new StartBrotherEvent(TestFragment.newInstance()));
    }
  }
}
