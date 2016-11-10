package me.itxuye.gankdbinding.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * @author Created by itxuye(http://itxuye.com)
 * @version 1.0.0
 */

public class SnackBarUtil {

  private SnackBarUtil() {
  }

  public static void showTipWithAction(View view, String tipText, String actionText,
      View.OnClickListener listener) {
    Snackbar.make(view, tipText, Snackbar.LENGTH_INDEFINITE).setAction(actionText, listener).show();
  }

  public static void showTipWithAction(View view, String tipText, String actionText,
      View.OnClickListener listener, int duration) {
    Snackbar.make(view, tipText, duration).setAction(actionText, listener).show();
  }

  public static void showSnackTip(View view, String tipText) {
    Snackbar.make(view, tipText, Snackbar.LENGTH_SHORT).show();
  }
}
