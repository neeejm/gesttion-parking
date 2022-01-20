package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entities.User;
import services.UserService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserService us = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User(username, email, password);

        if (us.create(user))
            resp.sendRedirect("login.jsp");
        else {
            req.setAttribute("error", true);
            req.getRequestDispatcher("/register.jsp").forward(req,
                    resp);
        }
    }

}
