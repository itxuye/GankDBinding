package me.itxuye.gankdbinding.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by itxuye on 2016/7/27.
 *
 * databinding 的 viewHolder基类
 */
public class BaseRevHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
  private T mBinding;

  public BaseRevHolder(T binding) {
    super(binding.getRoot());
    mBinding = binding;
  }

  public T getBinding() {
    return mBinding;
  }
}
