package servlets;

import data.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServletRecipes extends HttpServlet {
    private RecipeManager manager;
    private Map<String, Category> categoryMap;
    private Map<Category, String> mapCategoryString;

    public ServletRecipes(RecipeManager manager) {
        this.manager = manager;
        categoryMap = new HashMap<String, Category>();
        categoryMap.put("BREAKFAST", Category.BREAKFAST);
        categoryMap.put("LUNCH", Category.LUNCH);
        categoryMap.put("DINNER", Category.DINNER);
        categoryMap.put("DESSERT", Category.DESSERT);

        mapCategoryString = new HashMap<Category, String>();
        mapCategoryString.put(Category.BREAKFAST, "BREAKFAST");
        mapCategoryString.put(Category.LUNCH, "LUNCH");
        mapCategoryString.put(Category.DINNER, "DINNER");
        mapCategoryString.put(Category.DESSERT, "DESSERT");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String parameter = "";

        if ((parameter = req.getParameter("category")) != null) {
            Category category = categoryMap.get(req.getParameter("category"));
            resp.getWriter().println(buildJson(category));

        } else if ((parameter = req.getParameter("ingredient")) != null) {
            resp.getWriter().println(buildJson(req.getParameter("ingredient")));

        } else {

        }

        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private String buildJson(Category category) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonRecipes = new JSONArray();
        List<Recipe> recipes = manager.getRecipesFromCategory(category);
        for (Recipe temp : recipes) {
            if (temp.getCategory() == category) {
                JSONObject j = new JSONObject();
                j.put("id", temp.getId());
                j.put("name", temp.getName());
                j.put("image", temp.getImage());
                j.put("category", mapCategoryString.get(temp.getCategory()));
                jsonRecipes.add(j);
            }
        }
        jsonObject.put("recipes", jsonRecipes);
        return jsonObject.toJSONString();
    }

    private String buildJson(String ingredient) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonRecipes = new JSONArray();
        List<Recipe> recipes = manager.getRecipes();
        for (Recipe temp : recipes) {
            for (Ingredient i : temp.getIngredients()) {
                if ((i.getName().toLowerCase()).equals(ingredient.toLowerCase())) {
                    JSONObject j = new JSONObject();
                    j.put("id", temp.getId());
                    j.put("name", temp.getName());
                    j.put("image", temp.getImage());
                    j.put("category", mapCategoryString.get(temp.getCategory()));
                    jsonRecipes.add(j);
                }
            }
        }
        jsonObject.put("recipes",jsonRecipes);
        return jsonObject.toJSONString();
    }
}
