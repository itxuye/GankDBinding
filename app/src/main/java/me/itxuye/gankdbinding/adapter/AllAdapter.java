package me.itxuye.gankdbinding.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import java.util.List;
import me.itxuye.gankdbinding.R;
import me.itxuye.gankdbinding.base.BaseRevHolder;
import me.itxuye.gankdbinding.databinding.ItemMeiziBinding;
import me.itxuye.gankdbinding.model.entity.Meizi;
import me.itxuye.gankdbinding.utils.DateUtil;

/**
 * @author Created by itxuye(http://itxuye.com)
 * @version 1.0.0
 */

public class AllAdapter extends RecyclerView.Adapter<BaseRevHolder<ItemMeiziBinding>> {
  private final LayoutInflater mLayoutInflater;

  private List<Meizi> mList;

  private Context mContext;

  public OnClickContentListener mOnClickContentListener;
  public OnClickMeiziListener mMeiziListener;

  public void setmOnClickContentListener(OnClickContentListener mOnClickContentListener) {
    this.mOnClickContentListener = mOnClickContentListener;
  }

  public void setmMeiziListener(OnClickMeiziListener mMeiziListener) {
    this.mMeiziListener = mMeiziListener;
  }

  public AllAdapter(Context context, List<Meizi> list) {
    this.mContext = context;
    mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.mList = list;
  }

  private ViewDataBinding getViewDataBinding(ViewGroup parent, int id) {
    ViewDataBinding binding;
    binding = DataBindingUtil.inflate(mLayoutInflater, id, parent, false);
    return binding;
  }

  @Override
  public BaseRevHolder<ItemMeiziBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
    ViewDataBinding binding = getViewDataBinding(parent, R.layout.item_meizi);

    return new BaseRevHolder(binding);
  }

  @Override
  public void onBindViewHolder(final BaseRevHolder<ItemMeiziBinding> holder, int position) {
    final Meizi meizi = mList.get(position);
    final ItemMeiziBinding binding = holder.getBinding();
    binding.ivMeizi.setOriginalSize(300, 150);
    binding.getRoot().setTag(meizi);
    //binding.ivMeizi.setTag(meizi);
    int red = (int) (Math.random() * 255);
    int green = (int) (Math.random() * 255);
    int blue = (int) (Math.random() * 255);
    binding.ivMeizi.setBackgroundColor(Color.argb(204, red, green, blue));
    Glide.with(mContext).load(meizi.url).crossFade().into(binding.ivMeizi);

    binding.tvWho.setText(meizi.who);
    try {
      binding.tvAvatar.setText(meizi.who.substring(0, 1).toUpperCase());
    } catch (Exception e) {
      e.printStackTrace();
    }
    binding.tvDesc.setText(meizi.desc);
    binding.tvTime.setText(DateUtil.toDateTimeStr(meizi.publishedAt));
    binding.tvAvatar.setVisibility(View.GONE);
    //binding.ivMeizi.setOnClickListener(new View.OnClickListener() {
    //  @Override public void onClick(View view) {
    //    mMeiziListener.onClick(holder, binding);
    //  }
    //});

    binding.rlGank.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        mOnClickContentListener.onClick(holder.getAdapterPosition(), holder, binding);
      }
    });
  }
  public Meizi getItem(int position) {
    return mList.get(position);
  }
  @Override public int getItemCount() {
    return mList.size();
  }

  public interface OnClickMeiziListener {
    void onClick(RecyclerView.ViewHolder holder, ItemMeiziBinding binding);
  }

  public interface OnClickContentListener {
    void onClick(int position, RecyclerView.ViewHolder holder, ItemMeiziBinding binding);
  }
}
