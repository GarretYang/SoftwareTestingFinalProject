package com.example.hiking_2;

import android.app.Activity;
import android.app.ListActivity;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.action.ViewActions;

import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.hiking_2.ui.home.HomeFragment;
import com.example.hiking_2.ui.home.HomeViewModel;
import com.google.android.material.textfield.TextInputLayout;

import org.junit.Assert.*;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import org.hamcrest.Matcher;
@RunWith(AndroidJUnit4.class)
@LargeTest

public class DateTest {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testData_1() throws InterruptedException {

        onView(withId(R.id.navigation_add)).perform(click());
        onView(withId(R.id.select_date_text)).perform(click(),typeText("88/39/20199"),closeSoftKeyboard());
        Thread.sleep(1000);
        //TextInputLayout dateLayout = (TextInputLayout) findViewById(R.id.text_input_layout);
        onView(withText("Please enter valid date format.")).check(matches(isDisplayed()));
    }



    @Test
    public void testData() throws InterruptedException {

        onView(withId(R.id.navigation_add)).perform(click());
        onView(withId(R.id.select_date_text)).perform(click(),typeText("10/30/2019"),closeSoftKeyboard());
        Thread.sleep(1000);
        onView(withText("Please enter valid date format.")).check(doesNotExist());
    }
}