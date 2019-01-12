package com.sqisland.tutorial.recipes.data.local;

import android.content.Context;

import com.sqisland.tutorial.recipes.data.model.Recipe;

import org.junit.Test;

import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.*;

public class RecipeStoreTest {
    @Test
    public void nullDirectory() {
        //GetTargetContext returns Context for the app and not test
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        RecipeStore store = new RecipeStore(context, null);
        assertNotNull(store);
        assertNotNull(store.recipes);
        assertEquals(0, store.recipes.size());
    }

    @Test
    public void count() {
        //GetTargetContext returns Context for the app and not test
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        RecipeStore store = new RecipeStore(context, "recipes");//"recipes" is the folder name under assets
        assertNotNull(store);
        assertNotNull(store.recipes);
        assertEquals(4, store.recipes.size());
    }

    @Test
    public void getChocolatePudding() {
        //GetTargetContext returns Context for the app and not test
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        RecipeStore store = new RecipeStore(context, "recipes");//"recipes" is the folder name under assets
        Recipe recipe = store.getRecipe("chocolate_pudding");
        assertNotNull(recipe);
        assertEquals("chocolate_pudding", recipe.id);
        assertEquals("Chocolate Pudding", recipe.title);
    }


}