package com.sqisland.tutorial.recipes.test;

import android.content.Intent;
import android.support.annotation.StringRes;

import com.sqisland.tutorial.recipes.ScreenRobot;
import com.sqisland.tutorial.recipes.R;
import com.sqisland.tutorial.recipes.data.local.InMemoryFavorites;
import com.sqisland.tutorial.recipes.injection.TestRecipeApplication;
import com.sqisland.tutorial.recipes.ui.recipe.RecipeActivity;

import org.junit.Before;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

public class RecipeRobot extends ScreenRobot<RecipeRobot> {

    private final InMemoryFavorites favorites;

    public RecipeRobot() {
        TestRecipeApplication application = (TestRecipeApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        favorites = (InMemoryFavorites) application.getFavorites();
        favorites.clear();
    }

    public RecipeRobot launch(ActivityTestRule rule)
    {
        rule.launchActivity(null);
        return this;
    }

    public RecipeRobot launch(ActivityTestRule rule, String id)
    {
        Intent intent=new Intent();
        intent.putExtra(RecipeActivity.KEY_ID,id);
        rule.launchActivity(intent);
        return this;
    }

    public RecipeRobot noTitle()
    {
        return checkIsHidden(R.id.title);
    }

    public RecipeRobot description(@StringRes int stringId)
    {
        return checkViewHasText(R.id.description,stringId);
    }

    public RecipeRobot setFavorite(String id)
    {
        favorites.put(id, true);
        return this;
    }

    public RecipeRobot isFavorite()
    {
        return checkIsSelected(R.id.title);
    }
}
