package servlets;

import data.Ingredient;
import data.Recipe;
import data.RecipeManager;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ServletIngredients extends HttpServlet {
    private RecipeManager manager;

    public ServletIngredients(RecipeManager manager) {
        this.manager = manager;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(buildJson());
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private String buildJson() {
        JSONObject jsonObject = new JSONObject();

        Set<String> names = new HashSet<String>();
        for (Recipe recipe : manager.getRecipes()) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                names.add("\"" + ingredient.getName() + "\"");
            }
        }
        jsonObject.put("names", names);

        return jsonObject.toJSONString();
    }
}
