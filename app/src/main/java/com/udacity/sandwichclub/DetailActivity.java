package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final String NO_DATA_TEXT = "No data found";
    private static final int DEFAULT_POSITION = -1;
    private static Sandwich sandwich;
    public static TextView alsoKnownAsTextView;
    public static TextView placeOfOrigin;
    public static TextView description;
    public static TextView ingredientTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .error(R.drawable.sandwich_placeholder)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        alsoKnownAsTextView = findViewById(R.id.also_known_tv);
        placeOfOrigin = findViewById(R.id.origin_tv);
        description = findViewById(R.id.description_tv);
        ingredientTextView = findViewById(R.id.ingredients_tv);

        List<String> getAlsoKnownAs = sandwich.getAlsoKnownAs();
        if (getAlsoKnownAs != null || getAlsoKnownAs.size() > 0) {
            for (String name : getAlsoKnownAs) {
                alsoKnownAsTextView.setText(name + "\n");
            }
        } else {
            alsoKnownAsTextView.setText(NO_DATA_TEXT);
        }

        if (sandwich.getPlaceOfOrigin() != null || !sandwich.getPlaceOfOrigin().equals("")) {
            placeOfOrigin.setText(sandwich.getPlaceOfOrigin());
        } else {
            placeOfOrigin.setText(NO_DATA_TEXT);
        }

        if (sandwich.getDescription() != null || !sandwich.getDescription().equals("")) {
            description.setText(sandwich.getDescription());
        } else {
            description.setText(NO_DATA_TEXT);
        }

        List<String> ingredients = sandwich.getIngredients();
        if (ingredients != null || ingredients.size() > 0) {
            for (String ingredient : ingredients) {
                ingredientTextView.setText(ingredient + '\n');
            }
        } else {
            ingredientTextView.setText(NO_DATA_TEXT);
        }
    }
}
