package me.itxuye.gankdbinding.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by itxuye(http://www.itxuye.com) on 2016/8/26.
 */

public class BaseAdapter extends RecyclerView.Adapter<BaseRevHolder> {
  @Override public BaseRevHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override public void onBindViewHolder(BaseRevHolder holder, int position) {

  }

  @Override public int getItemCount() {
    return 0;
  }
}
