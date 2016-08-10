package me.itxuye.gankdbinding.base;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import com.afollestad.materialdialogs.MaterialDialog;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import me.itxuye.gankdbinding.R;

/**
 * Created by itxuye on 2016/7/29.
 */

public abstract class BaseDataBindingActivity<T extends ViewDataBinding>
    extends RxAppCompatActivity {
  /**
   * context
   */
  protected Context mContext = null;
  private MaterialDialog materialDialog;

  protected T b;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //get bundle
    Bundle extras = getIntent().getExtras();
    if (null != extras) {
      getBundleExtras(extras);
    }

    mContext = this;

    if (getContentViewLayoutID() != 0) {
      b = DataBindingUtil.setContentView(this, getContentViewLayoutID());
    } else {
      throw new IllegalArgumentException("You must return a right contentView layout resource Id");
    }
    initView();
    initData();
    initListener();
  }

  protected abstract void initView();

  protected abstract void initListener();

  protected abstract void initData();

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void finish() {
    super.finish();
    BaseAppManager.getInstance().removeActivity(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }

  /**
   * get bundle data
   */
  protected abstract void getBundleExtras(Bundle extras);

  /**
   * bind layout resource file
   */
  protected abstract int getContentViewLayoutID();

  /**
   * startActivity
   */
  protected void startActivity(Class<?> clazz) {
    Intent intent = new Intent(this, clazz);
    startActivity(intent);
  }

  /**
   * startActivity with bundle
   */
  protected void startActivity(Class<?> clazz, Bundle bundle) {
    Intent intent = new Intent(this, clazz);
    if (null != bundle) {
      intent.putExtras(bundle);
    }
    startActivity(intent);
  }

  /**
   * startActivity then finish
   */
  protected void startActivityThenKill(Class<?> clazz) {
    Intent intent = new Intent(this, clazz);
    startActivity(intent);
    finish();
  }

  /**
   * startActivity with bundle then finish
   */
  protected void startActivityThenKill(Class<?> clazz, Bundle bundle) {
    Intent intent = new Intent(this, clazz);
    if (null != bundle) {
      intent.putExtras(bundle);
    }
    startActivity(intent);
    finish();
  }

  /**
   * startActivityForResult
   */
  protected void startActivityForResult(Class<?> clazz, int requestCode) {
    Intent intent = new Intent(this, clazz);
    startActivityForResult(intent, requestCode);
  }

  /**
   * startActivityForResult with bundle
   */
  protected void startActivityForResult(Class<?> clazz, int requestCode, Bundle bundle) {
    Intent intent = new Intent(this, clazz);
    if (null != bundle) {
      intent.putExtras(bundle);
    }
    startActivityForResult(intent, requestCode);
  }

  /**
   * show toast with Snackbar
   */
  protected void showToast(String msg) {
    if (!TextUtils.isEmpty(msg)) {
      Snackbar.make(getWindow().getDecorView(), msg, Snackbar.LENGTH_SHORT).show();
    }
  }

  public void finishBack(Toolbar toolbar) {
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        finish();
      }
    });
  }

  public void showIndeterminateProgressDialog(boolean horizontal) {
    materialDialog = new MaterialDialog.Builder(this)
        //.title(R.string.progress_dialog)
        .content(R.string.please_wait)
        .progress(true, 0)
        .widgetColorRes(R.color.colorAccent)
        .progressIndeterminateStyle(horizontal)
        .show();
  }

  public void hideIndeterminateProgressDialog() {
    try {
      materialDialog.dismiss();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override protected void onStop() {
    super.onStop();
  }
}
