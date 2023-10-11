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
public class AccountDetailsTests {
    public static final int DELAY = 500;
    Random rand = new Random();

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void detailsTest (){
        String username = "" + rand.nextInt(1000000000);
        String password = "test";
        String email = "test@gmail.com";
        String phone = "8889990000";

        //register with above info
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.username)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.passwordText)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText(phone), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());

        try {
            Thread.sleep(2100);
        } catch (InterruptedException e) {}

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

        onView(withId(R.id.password_here)).check(matches(withText("test")));
        onView(withId(R.id.email_here)).check(matches(withText("test@gmail.com")));
        onView(withId(R.id.number_here)).check(matches(withText("8889990000")));


    }


}

