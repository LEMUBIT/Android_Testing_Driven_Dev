package com.sqisland.tutorial.recipes.ui.recipe;

import android.content.Intent;

import com.sqisland.tutorial.recipes.R;
import com.sqisland.tutorial.recipes.data.local.InMemoryFavorites;
import com.sqisland.tutorial.recipes.injection.TestRecipeApplication;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class RecipeActivityTest {

    private static final String CARROT_ID = "creamed_carrots";

    @Rule
    public ActivityTestRule<RecipeActivity> activityTestRule
            = new ActivityTestRule<>(RecipeActivity.class, true, false);

    private InMemoryFavorites favorites;

    @Before
    public void clearFavorites() {
        TestRecipeApplication application = (TestRecipeApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        favorites = (InMemoryFavorites) application.getFavorites();
        favorites.clear();
    }

    @Test
    public void recipeNotFound() {
        activityTestRule.launchActivity(null);
        onView(withId(R.id.description))
                .check(matches(withText(R.string.not_found)));

        onView(withId(R.id.title))
                .check(matches(not(isDisplayed())));
    }

    @Test
    public void clickToFavorite() {
        launchRecipe(CARROT_ID);

        onView(withId(R.id.title))
                .check(matches(withText("Creamed Carrots")))
                .check(matches(not(isSelected())))
                .perform(click())
                .check(matches(isSelected()));
    }

    @Test
    public void alreadyFavorite() {
        favorites.put(CARROT_ID, true);
        launchRecipe(CARROT_ID);
        onView(withId(R.id.title)).check(matches(isSelected()));
    }

    private void launchRecipe(String id) {
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.KEY_ID, id);
        activityTestRule.launchActivity(intent);
    }
}