package org.wikipedia.main

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.BuildConfig
import org.wikipedia.R
import org.wikipedia.TestUtil

@RunWith(AndroidJUnit4::class)
class LoggedInTests2 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loggedInTest2() {

        // Skip over onboarding screens
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.fragment_onboarding_skip_button),
                ViewMatchers.isDisplayed()
            )
        )
            .perform(ViewActions.click())

        TestUtil.delay(2)

        // Click the More menu
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.nav_more_container),
                ViewMatchers.isDisplayed()
            )
        )
            .perform(ViewActions.click())

        TestUtil.delay(1)

        // Click the Login menu item
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.main_drawer_login_button),
                ViewMatchers.isDisplayed()
            )
        )
            .perform(ViewActions.click())

        TestUtil.delay(2)

        // Type in correct username and password
        Espresso.onView(
            Matchers.allOf(
                TestUtil.withGrandparent(ViewMatchers.withId(R.id.create_account_username)),
                ViewMatchers.withClassName(Matchers.`is`("org.wikipedia.views.PlainPasteEditText"))
            )
        )
            .perform(
                ViewActions.replaceText("Julia"),
                ViewActions.closeSoftKeyboard()
            )

        Espresso.onView(
            Matchers.allOf(
                TestUtil.withGrandparent(ViewMatchers.withId(R.id.create_account_password_input)),
                ViewMatchers.withClassName(Matchers.`is`("org.wikipedia.views.PlainPasteEditText"))
            )
        )
            .perform(
                ViewActions.replaceText("111111"),
                ViewActions.closeSoftKeyboard()
            )
        TestUtil.delay(5)
    }
}
