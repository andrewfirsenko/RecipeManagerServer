package data;

import java.util.ArrayList;
import java.util.List;

public class RecipeCategory {
    private List<Recipe> recipes;
    private Category category;

    RecipeCategory(Category category) {
        this.category = category;
        recipes = new ArrayList<Recipe>();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void addRecipe(int id, String name, String image, List<Ingredient> ingredients, List<Step> steps) {
        recipes.add(new Recipe(id, name, image, ingredients, steps, category));
    }
}
