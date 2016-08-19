package me.itxuye.gankdbinding.utils;

import android.graphics.drawable.Drawable;
import me.itxuye.gankdbinding.app.GankApplication;

/**
 * Created by Arron on 16/7/25.
 */
public class ResourceUtils {
    public static String getString(int id) {
        return GankApplication.getContext().getResources().getString(id);
    }

    public static Drawable getDrawable(int id) {
        return GankApplication.getContext().getResources().getDrawable(id);
    }

}
