package data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeManager extends RecipeBank {

    public RecipeManager() {
        initRecipeBank();
    }

    private void initRecipeBank() {
        Map<String, Category> categoryMap = new HashMap<String, Category>();
        categoryMap.put("BREAKFAST", Category.BREAKFAST);
        categoryMap.put("LUNCH", Category.LUNCH);
        categoryMap.put("DINNER", Category.DINNER);
        categoryMap.put("DESSERT", Category.DESSERT);

        JSONObject object = null;
        try {
            InputStream is = getClass().getResourceAsStream("/recipes.json");
            object = (JSONObject) new JSONParser().parse(new InputStreamReader(is));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        JSONArray recipes = (JSONArray) object.get("recipes");
        for (Object i : recipes) {
            JSONObject recipe = (JSONObject) i;
            int id = Integer.valueOf(recipe.get("id").toString());
            Category category = categoryMap.get(recipe.get("category").toString());
            String name = recipe.get("name").toString().trim();
            String image = recipe.get("image").toString().trim();

            List<Ingredient> ingredients = new ArrayList<>();
            for (Object j : (JSONArray) recipe.get("ingredients")) {
                JSONObject ingredient = (JSONObject) j;
                ingredients.add(new Ingredient(ingredient.get("name").toString().trim(), ingredient.get("quantity").toString().trim()));
            }

            List<Step> steps = new ArrayList<>();
            for (Object j : (JSONArray) recipe.get("steps")) {
                JSONObject step = (JSONObject) j;
                steps.add(new Step(step.get("action").toString().trim(), step.get("image").toString().trim()));
            }

            addRecipe(id, name, image, ingredients, steps, category);
        }
    }
}
