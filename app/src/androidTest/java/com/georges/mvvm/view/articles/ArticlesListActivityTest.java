package com.georges.mvvm.view.articles;

import android.content.Intent;
import android.os.SystemClock;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.georges.mvvm.R;
import com.georges.mvvm.server.MockServer;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Objects;

import okhttp3.mockwebserver.MockWebServer;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class ArticlesListActivityTest {

    private MockWebServer mockServer;


    @Rule
    public final ActivityTestRule<ArticlesListActivity> mActivityRule = new ActivityTestRule<>(ArticlesListActivity.class, false, false);


    @Before
    public void setUp() throws IOException {
        mockServer = new MockWebServer();
        mockServer.start(8080);

        mockServer.setDispatcher(new MockServer.ResponseDispatcher());
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), ArticlesListActivity.class);
        mActivityRule.launchActivity(intent);

        SystemClock.sleep(2000);

    }

    @After
    public void tearDown() throws IOException {
        mockServer.shutdown();
    }

    @Test
    public void testRecycleViewVisible() {
        onView(withId(R.id.llError))
                .check(matches(not(isDisplayed())));

        onView(withId(R.id.pbLoading))
                .check(matches(not(isDisplayed())));

        onView(ViewMatchers.withId(R.id.rvArticles))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testCaseForRecyclerClick() {
        onView(ViewMatchers.withId(R.id.rvArticles))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mActivityRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void testCaseForRecyclerScroll() {

        // Get total item of RecyclerView
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.rvArticles);
        int itemCount = Objects.requireNonNull(recyclerView.getAdapter()).getItemCount();

        // Scroll to end of page with position
        onView(ViewMatchers.withId(R.id.rvArticles))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(mActivityRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    }

}