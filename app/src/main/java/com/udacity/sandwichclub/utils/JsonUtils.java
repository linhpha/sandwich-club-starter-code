package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {

            JSONObject mainObject = new JSONObject(json);
            JSONObject uniObject = mainObject.getJSONObject("name");

            String dishName = uniObject.getString("mainName");
            JSONArray alsoKnownAs = uniObject.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            }
            String placeOfOrigin = mainObject.getString("placeOfOrigin");
            String description = mainObject.getString("description");
            String imageUrl = mainObject.getString("image");
            JSONArray ingredients = mainObject.getJSONArray("ingredients");
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