package org.wikipedia.search

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.web.assertion.WebViewAssertions
import androidx.test.espresso.web.sugar.Web
import androidx.test.espresso.web.webdriver.DriverAtoms
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.R
import org.wikipedia.TestUtil
import org.wikipedia.main.MainActivity

@RunWith(AndroidJUnit4::class)
class SearchArticleTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)
    private lateinit var activity: MainActivity

    @Before
    fun setActivity() {
        mActivityTestRule.scenario.onActivity {
            activity = it
        }
    }

    @Test
    fun searchArticleTest() {
        // Skip over onboarding screens
        try {
            onView(allOf(withId(R.id.fragment_onboarding_skip_button), isDisplayed()))
                .perform(click())
            TestUtil.delay(2)
        } catch (e: NoMatchingViewException) {
        }

        // Click to the search field
        onView(allOf(withId(R.id.search_container), isDisplayed()))
            .perform(click())

        TestUtil.delay(2)

        // Entering the search text
        onView(allOf(withId(androidx.appcompat.R.id.search_src_text), isDisplayed()))
            .perform(
                replaceText("encapsulation"),
                pressImeActionButton()
            )

        TestUtil.delay(2)

        // Select the desired result from the list
        onView(allOf(withId(R.id.page_list_item_title), withText("Encapsulation (computer programming)")))
            .perform(click())

        TestUtil.delay(3) //

        // Closing the popup hint, if there is one
        onView(allOf(withId(R.id.buttonView)))
            .inRoot(RootMatchers.withDecorView(Matchers.not(Matchers.`is`(activity.window.decorView))))
            .perform(click())

        TestUtil.delay(2)

        //  Check that the required article is open
        Web.onWebView().withElement(DriverAtoms.findElement(Locator.CSS_SELECTOR, "h1"))
            .check(
                WebViewAssertions.webMatches(
                    DriverAtoms.getText(),
                    Matchers.`is`("Encapsulation (computer programming)")
                ))
    }
}
