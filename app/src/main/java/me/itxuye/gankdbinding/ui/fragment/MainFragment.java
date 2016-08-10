package me.itxuye.gankdbinding.ui.fragment;

import android.os.Bundle;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by itxuye(http://www.itxuye.com) on 2016/8/3.
 */

public class MainFragment extends SupportFragment {

  public static MainFragment newInstance(String title) {
    MainFragment mainFragment = new MainFragment();
    Bundle bundle = new Bundle();
    bundle.putString("key", title);
    return mainFragment;
  }
}
