package com.example.app;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.core.StringEndsWith.endsWith;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.core.StringEndsWith;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class concessionsTests {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
    public final int DELAY = 500;

    @Test
    public void viewVendors() {
        String username = "testUser";
        String password = "testPassword";

        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.username)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}
        onView(withId(R.id.button2)).perform(click());
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}
        onView(withText("vendorMenuTest")).check(matches(withText("vendorMenuTest")));
    }

    @Test
    public void viewMenu() {
        String username = "testUser";
        String password = "testPassword";

        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.username)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}
        onView(withId(R.id.button2)).perform(click());
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}
        onView(withText("vendorMenuTest")).perform(click());
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}
        onView(withId(1)).check(matches(withText(endsWith("4"))));
    }
}
