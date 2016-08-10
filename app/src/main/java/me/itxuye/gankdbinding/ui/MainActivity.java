package me.itxuye.gankdbinding.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.ui.fragment.MainFragment;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends SupportActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      loadRootFragment(R.id.fl_container, MainFragment.newInstance("main"));
    }
  }

  @Override public void onBackPressedSupport() {
    super.onBackPressedSupport();
  }

  @Override public FragmentAnimator onCreateFragmentAnimator() {
    return new DefaultHorizontalAnimator();
  }
}
