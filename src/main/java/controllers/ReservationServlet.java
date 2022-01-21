package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import entities.Reservation;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.ReservationService;

@WebServlet("/api/v1/reserver")
public class ReservationServlet extends HttpServlet {
    private final Gson gson = new Gson();
    private final ReservationService rs = new ReservationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get reservation by user id if id is given in the request param
        if (req.getParameter("id") != null) {
            String id = req.getParameter("id");
            List<Reservation> reservation = rs.findAllByUserId(Integer.parseInt(id));

            String data = gson.toJson(reservation);

            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(data);
            out.flush();
            return;
        }

        // get all reservations
        List<Reservation> places = rs.findAll();

        String data = gson.toJson(places);
        PrintWriter out = resp.getWriter();
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(data);
        out.flush();
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

        // create reservation the info given in the body
        Reservation reservation = gson.fromJson(body, Reservation.class);
        if (rs.create(reservation)) {
            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print("{\"msg\" : \"" + reservation.getMatricule() + " created\"}");
            out.flush();
            return;
        }

        // return error in the creation of the section
        PrintWriter out = resp.getWriter();
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print("{\"msg\" : \"could not create reservation\"}");
        out.flush();
        return;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get section by id if id is given in the request param
        if (req.getParameter("place_id") != null) {
            String pid = req.getParameter("place_id");
            if (rs.cancel(rs.findById(Integer.parseInt(pid)))) {
                PrintWriter out = resp.getWriter();
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                out.print("{\"msg\" : \"canceled\"}");
                out.flush();
                return;

            }

            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print("{\"msg\" : \"could not canceled\"}");
            out.flush();
            return;

        }

        PrintWriter out = resp.getWriter();
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print("{'msg' : 'no id given'}");
        out.flush();
        return;
    }

}
