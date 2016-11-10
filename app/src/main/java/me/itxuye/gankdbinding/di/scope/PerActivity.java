package me.itxuye.gankdbinding.di.scope;

import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Created by itxuye(http://itxuye.com)
 *         on   2016/9/5 16:06
 * @version 1.0.0
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
