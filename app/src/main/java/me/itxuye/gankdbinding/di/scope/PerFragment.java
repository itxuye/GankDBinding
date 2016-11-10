package me.itxuye.gankdbinding.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * @author Created by itxuye(http://itxuye.com)
 *         on   2016/9/5 16:05
 * @version 1.0.0
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
