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
import org.wikipedia.R
import org.wikipedia.TestUtil

@RunWith(AndroidJUnit4::class)
class CreateAccountTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun createAccountTest() {

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

        // data creation
        Espresso.onView(
            Matchers.allOf(
                TestUtil.withGrandparent(ViewMatchers.withId(R.id.create_account_username)),
                ViewMatchers.withClassName(Matchers.`is`("org.wikipedia.views.PlainPasteEditText"))
            )
        )
            .perform(
                ViewActions.replaceText("testusernumberone"),
                ViewActions.closeSoftKeyboard()
            )

        Espresso.onView(
            Matchers.allOf(
                TestUtil.withGrandparent(ViewMatchers.withId(R.id.create_account_password_input)),
                ViewMatchers.withClassName(Matchers.`is`("org.wikipedia.views.PlainPasteEditText"))
            )
        )
            .perform(
                ViewActions.replaceText("123456789"),
                ViewActions.closeSoftKeyboard()
            )

        Espresso.onView(
            Matchers.allOf(
                TestUtil.withGrandparent(ViewMatchers.withId(R.id.create_account_password_repeat)),
                ViewMatchers.withClassName(Matchers.`is`("org.wikipedia.views.PlainPasteEditText"))
            )
        )
            .perform(
                ViewActions.replaceText("123456789"),
                ViewActions.closeSoftKeyboard()
            )

        Espresso.onView(
            Matchers.allOf(
                TestUtil.withGrandparent(ViewMatchers.withId(R.id.create_account_email)),
                ViewMatchers.withClassName(Matchers.`is`("org.wikipedia.views.PlainPasteEditText"))
            )
        )
            .perform(
                ViewActions.replaceText("testusernumberone@mail.com"),
                ViewActions.closeSoftKeyboard()
            )

        // Click the next button
        Espresso.onView(ViewMatchers.withId(R.id.create_account_submit_button))
            .perform(ViewActions.scrollTo(), ViewActions.click())

        TestUtil.delay(2)

        // check the captcha display
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.captcha_tap_text),
                ViewMatchers.withText("@string/edit_section_captcha_reload"),
                ViewMatchers.isDisplayed()
            )
        )
        TestUtil.delay(1)
    }
}
