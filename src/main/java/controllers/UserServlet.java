package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.UserService;

@WebServlet("/api/v1/user")
public class UserServlet extends HttpServlet {
    private final Gson gson = new Gson();
    private final UserService us = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get section by id if id is given in the request param
        if (req.getParameter("id") != null) {
            String id = req.getParameter("id");
            User user = us.findById(Integer.parseInt(id));

            String data = gson.toJson(user);

            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(data);
            out.flush();
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        if (body == null) {
            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print("{\"msg\" : \"body of the request is empty\"}");
            out.flush();
            return;
        }

        // create user the info given in the body
        User user = gson.fromJson(body, User.class);
        if (us.create(user)) {
            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print("{\"msg\" : \"" + user.getUsername() + " created\"}");
            out.flush();
            return;
        }

        // return error in the creation of the section
        PrintWriter out = resp.getWriter();
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print("{\"msg\" : \"could not create section\"}");
        out.flush();
        return;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get section by id if id is given in the request param
        if (req.getParameter("id") != null) {
            String id = req.getParameter("id");
            if (us.delete(us.findById(Integer.parseInt(id)))) {
                PrintWriter out = resp.getWriter();
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                out.print("{\"msg\" : \"deleted\"}");
                out.flush();
                return;

            }

        }

        PrintWriter out = resp.getWriter();
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print("{'msg' : 'no id given or could not delete'}");
        out.flush();
        return;
    }
}
