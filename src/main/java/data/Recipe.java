package data;

import java.util.List;

public class Recipe {
    private int id;
    private String name;
    private String image;
    private List<Ingredient> ingredients;
    private List<Step> steps;
    private Category category;

    Recipe(int id, String name, String image, List<Ingredient> ingredients, List<Step> steps, Category category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.category = category;
        this.steps = steps;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Category getCategory() {
        return category;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
