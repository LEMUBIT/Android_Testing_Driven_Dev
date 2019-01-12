package com.sqisland.tutorial.recipes.data.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Recipe {
    private static final String ID = "id=";
    private static final String TITLE = "title=";
    public final String id;
    public final String title;
    public final String description;

    private Recipe(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    /*was built using TDD(Test driven development)*/
    public static Recipe readFromStream(InputStream stream) {
        String id = null;
        String title = null;
        StringBuilder descBuilder = new StringBuilder();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

        try {
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                if (line.startsWith(ID)) {
                    id = line.substring(ID.length());
                    continue;
                }
                if (line.startsWith(TITLE)) {
                    title = line.substring(TITLE.length());
                    continue;
                }

                /*New line was not automatically put when we have more than one line of description
                * So for cases where descriptions occupy more than one line, we have to add \n before
                * adding the new text
                * */
                if (descBuilder.length() > 0) {
                    descBuilder.append("\n");
                }
                descBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new Recipe(id, title, descBuilder.toString());
    }
}
