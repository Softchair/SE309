package com.example.app;

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
public class registerTest {
    private static final int DELAY = 2500;
    Random rand = new Random();

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void toRegisterScreen() {
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.textView3)).check(matches(withText("Password:")));
    }

    @Test
    public void register() {
        String username = "" + rand.nextInt(1000000000);;
        String password = "test";
        String email = "test@gmail.com";
        String phone = "8889990000";
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.username)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.passwordText)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText(phone), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.msgResponse)).check(matches(withText("You have successfully created a new user!")));
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {}
    }

    @Test
    public void noLengthStrings() {
        String password = "test";
        String email = "test@gmail.com";
        String phone = "8889990000";

        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.passwordText)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText(phone), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.msgResponse)).check(matches(withText("Error: Haven't filled out all necessary fields")));
    }
}
