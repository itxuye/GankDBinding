package me.itxuye.gankdbinding;

import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import me.itxuye.gankdbinding.ui.MainActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by itxuye on 2016/7/30.
 */
@RunWith(AndroidJUnit4.class) @LargeTest public class MainActivityTest {
  @Rule public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
      new ActivityTestRule<MainActivity>(MainActivity.class){
        @Override protected Intent getActivityIntent() {
          Intent result=new Intent();
          return result;
        }
      };

  @Test public void testButtonShow() {
    onView(withId(R.id.tv_test)).check(matches(withText("你妹啊QWQ")));
  }

}
