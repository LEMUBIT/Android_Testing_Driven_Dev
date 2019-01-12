package com.sqisland.tutorial.recipes.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesFavorites implements Favorites {
    private final SharedPreferences preferences;

    public SharedPreferencesFavorites(Context context) {
        preferences = context.getSharedPreferences("favorites.xml", Context.MODE_PRIVATE);
    }

    public boolean get(String id) {
        return preferences.getBoolean(id, false);
    }

    public void put(String id, boolean favorite) {
        SharedPreferences.Editor editor = preferences.edit();
        if (favorite) {
            editor.putBoolean(id, true);
        } else {
            editor.remove(id);//no need to put false, would be false already if not available
        }

        editor.apply();
    }


    public boolean toggle(String id) {
        boolean favorite = get(id);
        put(id, !favorite);
        return !favorite;
    }
}
