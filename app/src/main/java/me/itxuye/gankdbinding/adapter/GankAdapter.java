package me.itxuye.gankdbinding.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.base.BaseRevHolder;
import me.itxuye.gankdbinding.databinding.ItemGankBinding;
import me.itxuye.gankdbinding.model.entity.Gank;
import me.itxuye.gankdbinding.utils.StringStyleUtil;

/**
 * @author Created by itxuye(http://itxuye.com)
 * @version 1.0.0
 */

public class GankAdapter extends RecyclerView.Adapter<BaseRevHolder<ItemGankBinding>>{

  private final LayoutInflater mLayoutInflater;

  private List<Gank> mList;

  private Context mContext;

  public GankAdapter(Context context,List<Gank> list) {
    this.mContext = context;
    mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.mList  =list;
  }

  private ViewDataBinding getViewDataBinding(ViewGroup parent, int id) {
    ViewDataBinding binding;
    binding = DataBindingUtil.inflate(mLayoutInflater, id, parent, false);
    return binding;
  }


  @Override
  public BaseRevHolder<ItemGankBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
    ViewDataBinding binding = getViewDataBinding(parent, R.layout.item_gank);

    return new BaseRevHolder(binding);
  }

  @Override public void onBindViewHolder(BaseRevHolder<ItemGankBinding> holder, int position) {
    Gank gank = mList.get(position);
    ItemGankBinding binding = holder.getBinding();
    binding.llGank.setTag(gank);
    if (position == 0) {
      showCategory(true, binding.tvCategory);
    } else {
      if (mList.get(position).type.equals(mList.get(position - 1).type)) {
        showCategory(false, binding.tvCategory);
      } else {
        showCategory(true, binding.tvCategory);
      }
    }
    binding.tvCategory.setText(gank.type);
    binding.tvGankDesc.setText(StringStyleUtil.getGankStyleStr(gank));
  }
  private void showCategory(boolean show, TextView tvCategory) {
    if (show) {
      tvCategory.setVisibility(View.VISIBLE);
    } else {
      tvCategory.setVisibility(View.GONE);
    }
  }

  @Override public int getItemCount() {
    return mList.size();
  }
}
