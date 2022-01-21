package controllers;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/helloworld")
public class HelloWorld extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject json = new JsonObject();
        json.addProperty("msg", "hello world");
        String data = gson.toJson(json);

        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.getOutputStream().println(data);

        // resp.setContentType("text/html");
        // PrintWriter out = resp.getWriter();
        // out.println("<html><body>");
        // out.println("<h1>Hello Readers</h1>");
        // out.println("</body></html>");
    }

}
