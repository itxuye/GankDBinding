package me.itxuye.gankdbinding.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.base.BaseRevHolder;
import me.itxuye.gankdbinding.databinding.ItemGanhuoBinding;
import me.itxuye.gankdbinding.model.entity.Gank;
import me.itxuye.gankdbinding.utils.StringStyleUtil;

/**
 * @author Created by itxuye(http://itxuye.com)
 * @version 1.0.0
 */

public class GanhuoAdapter extends RecyclerView.Adapter<BaseRevHolder<ItemGanhuoBinding>>{

  private final LayoutInflater mLayoutInflater;

  private List<Gank> mList;

  private Context mContext;

  public GanhuoAdapter(Context context,List<Gank> list) {
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
  public BaseRevHolder<ItemGanhuoBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
    ViewDataBinding binding = getViewDataBinding(parent, R.layout.item_ganhuo);

    return new BaseRevHolder(binding);
  }

  @Override public void onBindViewHolder(BaseRevHolder<ItemGanhuoBinding> holder, int position) {
    Gank gank = mList.get(position);
    ItemGanhuoBinding binding = holder.getBinding();
    binding.tvDescWithWho.setTag(gank);
    binding.tvDescWithWho.setText(StringStyleUtil.getGankStyleStr(gank));
  }

  @Override public int getItemCount() {
    return mList.size();
  }
}
