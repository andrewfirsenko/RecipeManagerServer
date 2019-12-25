import data.RecipeManager;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

public class Main {
    public static void main(String[] args) throws Exception {
        RecipeManager manager = new RecipeManager();

        ServletIngredients servletIngredients = new ServletIngredients(manager);
        ServletRecipe servletRecipe = new ServletRecipe(manager);
        ServletImage servletImage = new ServletImage();
        ServletRecipes servletRecipes = new ServletRecipes(manager);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(servletIngredients), "/ingredients");
        context.addServlet(new ServletHolder(servletRecipe), "/recipe");
        context.addServlet(new ServletHolder(servletRecipes), "/recipes");
        context.addServlet(new ServletHolder(servletImage), "/image");

        Server server = new Server(80);
        server.setHandler(context);

        server.start();
        server.join();
    }
}
