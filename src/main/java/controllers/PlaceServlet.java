package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import entities.Place;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.PlaceService;

@WebServlet("/api/v1/place")
public class PlaceServlet extends HttpServlet {
    private final Gson gson = new Gson();
    private final PlaceService pc = new PlaceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get place by id if id is given in the request param
        if (req.getParameter("id") != null) {
            String id = req.getParameter("id");
            Place place = pc.findById(Integer.parseInt(id));

            String data = gson.toJson(place);

            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(data);
            out.flush();
            return;
        }

        // get all places
        List<Place> places = pc.findAll();

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

        // create section the info given in the body
        Place place = gson.fromJson(body, Place.class);
        if (pc.create(place)) {
            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print("{\"msg\" : \"" + place.getNumero() + " created\"}");
            out.flush();
            return;
        }

        // return error in the creation of the section
        PrintWriter out = resp.getWriter();
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print("{\"msg\" : \"could not create place\"}");
        out.flush();
        return;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get section by id if id is given in the request param
        if (req.getParameter("id") != null) {
            String id = req.getParameter("id");
            if (pc.delete(pc.findById(Integer.parseInt(id)))) {
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
