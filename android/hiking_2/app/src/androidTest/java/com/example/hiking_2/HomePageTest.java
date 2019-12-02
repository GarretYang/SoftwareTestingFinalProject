package com.example.hiking_2;

import android.app.Activity;
import android.app.ListActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.hiking_2.ui.home.HomeFragment;
import com.example.hiking_2.ui.home.HomeViewModel;

import org.junit.Assert.*;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomePageTest {
    /**
     * Instrumented test, which will execute on an Android device.
     *
     * See [testing documentation](http://d.android.com/tools/testing).
     */

//    @Rule
//    public ActivityTestRule<Activity> mActivityRule = new ActivityTestRule<>(HomeFragment.class);

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testHomeContext() {
//        openContextualActionModeOverflowMenu();
        //模拟点击home
        onView(withId(R.id.navigation_home)).perform(click());
//        Assert.assertEquals(onView(withText("Home")).check(), true);
    }

    @Test
    public void testHomeCards() {
        onView(withId(R.id.searchView)).perform(click());
    }



}
