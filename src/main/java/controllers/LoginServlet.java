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
import jakarta.servlet.http.HttpSession;
import services.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService us = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession(false);
        Object username = ses.getAttribute("username");
        if (username != null) {
            req.setAttribute("username", username);
            req.getRequestDispatcher("/home.jsp").forward(req,
                    resp);
        } else {
            resp.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (us.login(username, password)) {
            HttpSession ses = req.getSession();
            ses.setAttribute("username", username);
            resp.sendRedirect("home.jsp");
        } else {
            req.setAttribute("error", true);
            req.getRequestDispatcher("/login.jsp").forward(req,
                    resp);
        }
    }
}
