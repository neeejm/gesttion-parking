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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final Gson gson = new Gson();
    private final UserService us = new UserService();

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
        if (us.login(user.getUsername(), user.getPassword()) != null) {
            String data = gson.toJson(us.login(user.getUsername(), user.getPassword()));
            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(data);
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
}
