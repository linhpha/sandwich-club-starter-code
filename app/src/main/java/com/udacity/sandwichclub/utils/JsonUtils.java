package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_ALSO_KNOW_AS = "alsoKnownAs";
    public static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_INGREDIENTS = "ingredients";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_NAME = "name";



    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject sandwichObject = new JSONObject(json);
            JSONObject nameObject = sandwichObject.getJSONObject(KEY_NAME);

            String dishName = nameObject.getString(KEY_MAIN_NAME);
            JSONArray alsoKnownAs = nameObject.getJSONArray(KEY_ALSO_KNOW_AS);
            List<String> alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            }
            String placeOfOrigin = sandwichObject.getString(KEY_PLACE_OF_ORIGIN);
            String description = sandwichObject.getString(KEY_DESCRIPTION);
            String imageUrl = sandwichObject.getString(KEY_IMAGE);
            JSONArray ingredients = sandwichObject.getJSONArray(KEY_INGREDIENTS);
            List<String> ingredientList = new ArrayList<String>();
            for (int i = 0; i < ingredients.length(); i++) {
                ingredientList.add(ingredients.getString(i));
            }
            Sandwich sandwich = new Sandwich(dishName, alsoKnownAsList, placeOfOrigin, description, imageUrl, ingredientList);
            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();

        }

        return null;
    }
}