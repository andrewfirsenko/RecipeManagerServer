package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeBank {
    private Map<Category, RecipeCategory> recipeBank;

    RecipeBank() {
        recipeBank = new HashMap<Category, RecipeCategory>();
    }

    public void addRecipe(int id, String name, String image, List<Ingredient> ingredients, List<Step> steps, Category category) {
        if (!recipeBank.containsKey(category)) {
            recipeBank.put(category, new RecipeCategory(category));
        }
        recipeBank.get(category).addRecipe(id, name, image, ingredients, steps);
    }

    public List<Recipe> getRecipesFromCategory(Category category) {
        try {
            return recipeBank.get(category).getRecipes();
        } catch (Exception e) {
            return new ArrayList<Recipe>();
        }
    }

    public List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<Recipe>();
        for (RecipeCategory recipeCategory : recipeBank.values()) {
            recipes.addAll(recipeCategory.getRecipes());
        }
        return recipes;
    }
}
