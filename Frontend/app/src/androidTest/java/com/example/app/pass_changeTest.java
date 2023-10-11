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

import java.util.Random;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class pass_changeTest {
    public static final int DELAY = 500;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void changePass () {
        String username="test";
        String password = "test";
        String newPassword = "testing";


        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.username)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}

        onView(withId(R.id.button6)).perform(click());

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}

        onView(withId(R.id.button_change_pass)).perform(click());

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}

        onView(withId(R.id.new_pass1)).perform(typeText(newPassword), closeSoftKeyboard());
        onView(withId(R.id.new_pass2)).perform(typeText(newPassword), closeSoftKeyboard());

        onView(withId(R.id.confirm_button)).perform(click());

        onView(withId(R.id.response)).check(matches(withText("You have successfully changed your password!")));
    }

    @Test
    public void changePass2 () {
        String username="test";
        String password = "testing";
        String newPassword = "test";


        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.username)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}

        onView(withId(R.id.button6)).perform(click());

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}

        onView(withId(R.id.button_change_pass)).perform(click());

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}

        onView(withId(R.id.new_pass1)).perform(typeText(newPassword), closeSoftKeyboard());
        onView(withId(R.id.new_pass2)).perform(typeText(newPassword), closeSoftKeyboard());

        onView(withId(R.id.confirm_button)).perform(click());

        onView(withId(R.id.response)).check(matches(withText("You have successfully changed your password!")));
    }

    //should send error msg if empty fields
    @Test
    public void changePassFail (){
        String username="testUser";
        String password = "testPassword";


        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.username)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}

        onView(withId(R.id.button6)).perform(click());

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}

        onView(withId(R.id.button_change_pass)).perform(click());

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}

        onView(withId(R.id.confirm_button)).perform(click());

        onView(withId(R.id.response)).check(matches(withText("Error: Haven't filled out all necessary fields")));


    }

}
