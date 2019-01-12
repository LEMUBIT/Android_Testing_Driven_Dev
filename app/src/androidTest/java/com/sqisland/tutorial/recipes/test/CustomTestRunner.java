package com.sqisland.tutorial.recipes.test;

import android.app.Application;
import android.content.Context;

import com.sqisland.tutorial.recipes.injection.TestRecipeApplication;

import androidx.test.runner.AndroidJUnitRunner;

public class CustomTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return super.newApplication(cl, TestRecipeApplication.class.getName(), context);
    }
}
