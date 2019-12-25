package servlets;

import data.Ingredient;
import data.Recipe;
import data.RecipeManager;
import data.Step;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ServletRecipe extends HttpServlet {
    private RecipeManager manager;

    public ServletRecipe(RecipeManager manager) {
        this.manager = manager;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(buildJSON(Integer.valueOf(req.getParameter("id"))));
//        List<Recipe> recipes = manager.getRecipes();
//        for (Recipe temp : recipes) {
//            if (temp.getId() == Integer.valueOf(req.getParameter("id"))) {
//                for (Step i : temp.getSteps()) {
//                    resp.getWriter().println(i.getAction() + "<br>");
//                    String www = "http://localhost/image?src=";
//                    resp.getWriter().println("<img src=\"" + www + i.getImage() + "\"/><br>");
//                }
//            }
//        }

        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private String buildJSON(int id) {
        JSONObject jsonObject = new JSONObject();
        List<Recipe> recipes = manager.getRecipes();
        for (Recipe temp : recipes) {
            if (temp.getId() == id) {
                jsonObject.put("id", temp.getId());
                jsonObject.put("category", temp.getCategory().toString());
                jsonObject.put("name", temp.getName());
                jsonObject.put("image", temp.getImage());

                JSONArray jsonIngredients = new JSONArray();
                for (Ingredient i : temp.getIngredients()) {
                    JSONObject j = new JSONObject();
                    j.put("name", i.getName());
                    j.put("quantity", i.getQuantity());
                    jsonIngredients.add(j);
                }
                jsonObject.put("ingredients", jsonIngredients);

                JSONArray jsonSteps = new JSONArray();
                for (Step i : temp.getSteps()) {
                    JSONObject j = new JSONObject();
                    j.put("action", i.getAction());
                    j.put("image", i.getImage());
                    jsonSteps.add(j);
                }
                jsonObject.put("steps", jsonSteps);
            }
        }
        return jsonObject.toJSONString();
    }
}
