package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ServletImage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/png");

        String src = req.getParameter("src");

        try {
            InputStream is = getClass().getResourceAsStream("/images/" + src +".jpg");
            OutputStream os = resp.getOutputStream();
            if (is == null) {
                is = getClass().getResourceAsStream("/images/default.jpg");
            }
            if (is == null) {
                resp.setContentType("text/html;charset=utf-8");
                os.write("Not found".getBytes());
            } else {
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
