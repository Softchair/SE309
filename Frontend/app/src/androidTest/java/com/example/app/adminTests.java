package com.example.app;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class adminTests {
    public static final int DELAY = 500;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void noAdminTest () {
        String username = "noAdmin";
        String password = "test";

        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.username)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}
        onView(withText("ADMIN INFO")).perform(click());
        onView(withId(R.id.response)).check(matches(withText("You don't have the permissions to do that")));
    }

    @Test
    public void vendorPermsTest () {
        String username = "vendorTest";
        String password = "test";

        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.username)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}
        onView(withText("ADMIN INFO")).perform(click());
        onView(withId(R.id.grant_admin)).perform(click());
        onView(withId(R.id.response)).check(matches(withText("You don't have the right permission level")));
    }

    @Test
    public void grantAdminTest () {
        String username = "testingAdmin";
        String password = "test";
        String nonAdminUser = "testUser";

        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.username)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}
        onView(withText("ADMIN INFO")).perform(click());
        onView(withId(R.id.grant_admin)).perform(click());
        onView(withId(R.id.username)).perform(typeText(nonAdminUser), closeSoftKeyboard());
        onView(withId(R.id.privileges)).perform(click());
        onView(withText("Admin")).perform(click());
        onView(withId(R.id.grant_privileges)).perform(click());
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}
        onView(withId(R.id.grant_privileges)).perform(click());
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}
        onView(withId(R.id.msgResponse)).check(matches(withText("You have successfully granted permissions!")));
    }

    @Test
    public void grantUserTest () {
        String username = "testingAdmin";
        String password = "test";
        String nonAdminUser = "testUser";

        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.username)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}
        onView(withText("ADMIN INFO")).perform(click());
        onView(withId(R.id.grant_admin)).perform(click());
        onView(withId(R.id.username)).perform(typeText(nonAdminUser), closeSoftKeyboard());
        onView(withId(R.id.privileges)).perform(click());
        onView(withText("User")).perform(click());
        onView(withId(R.id.grant_privileges)).perform(click());
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}
        onView(withId(R.id.grant_privileges)).perform(click());
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}
        onView(withId(R.id.msgResponse)).check(matches(withText("You have successfully granted permissions!")));
    }
}
