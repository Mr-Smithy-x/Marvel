package com.smith.ufc;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.contrib.ViewPagerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;

import com.smith.ufc.ui.views.ArrowViewPager;
import com.smith.ufc.ui.views.GridRecyclerView;
import com.smith.ufc.ui.views.NonScrollableViewPager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.realm.Realm;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {



    @Rule
    public ActivityTestRule<MainActivity> mMainActivity =
            new ActivityTestRule<MainActivity>(MainActivity.class) {

                @Override
                protected void beforeActivityLaunched() {
                    super.beforeActivityLaunched();
                    // Doing this in @Before generates a race condition.
                }
            };


    @Before
    public void setUp(){
        mMainActivity.getActivity();
    }

    /**
     * On activity start click search bar and search for spider
     */
    @Test
    public void testSearchForSpider(){


        // Click on the add task button
        onView(withId(R.id.search_bar)).check(matches(isDisplayed()));

        onView(withId(R.id.search_bar)).perform(ViewActions.click());

        // Check if the add task screen is displayed
        onView(withId(R.id.search_bar)).perform(ViewActions.typeText("spider"), ViewActions.pressKey(KeyEvent.KEYCODE_ENTER), ViewActions.closeSoftKeyboard());

        // Check if the add task screen is displayed

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));



        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClickFrontAndBackArrow(){

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.arrow_front)).check(matches(isDisplayed()));

        onView(withId(R.id.arrow_front)).perform(click());

        onView(withId(R.id.arrow_front)).perform(click());


        onView(withId(R.id.arrow_back)).check(matches(isDisplayed()));


        onView(withId(R.id.arrow_back)).perform(click());

        onView(withId(R.id.arrow_back)).perform(click());


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void scrollDownToBottom(){


        while (((RecyclerView)mMainActivity.getActivity().findViewById(R.id.recycler_view)).getAdapter().getItemCount() <= 0){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(((RecyclerView)mMainActivity.getActivity().findViewById(R.id.recycler_view)).getAdapter().getItemCount() - 1));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @UiThreadTest
    public void clickComic(){
        ((GridRecyclerView)mMainActivity.getActivity().findViewById(R.id.recycler_view))
                .getChildAt(0).performClick();

    }

}
