package com.fachrudin.project.app.presentation

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.fachrudin.project.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by achmad.fachrudin on 21-Mar-19
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testNavigationBottom() {
        delay()

        Espresso.onView(ViewMatchers.withId(R.id.menu_profile)).perform(ViewActions.click())
        delay()

        Espresso.onView(ViewMatchers.withId(R.id.menu_user)).perform(ViewActions.click())
        delay()
    }

    private fun delay() {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}