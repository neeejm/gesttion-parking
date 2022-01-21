package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.PlaceService;
import services.ReservationService;
import services.SectionService;
import services.UserService;

@WebServlet("/api/v1/stats")
public class StatsServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("test");
        if (req.getParameter("all") != null) {
            HashMap<String, Integer> nums = new HashMap<>();
            nums.put("user", new UserService().count());
            nums.put("section", new SectionService().count());
            nums.put("place", new PlaceService().count());
            nums.put("reservation", new ReservationService().count());
            String data = gson.toJson(nums);

            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(data);
            out.flush();
            return;

        }

        HashMap<String, Integer> stats = new HashMap<>();
        ReservationService rs = new ReservationService();
        stats.putAll(rs.reservationBySection());
        String data = gson.toJson(stats);

        PrintWriter out = resp.getWriter();
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(data);
        out.flush();
        return;
    }
}
