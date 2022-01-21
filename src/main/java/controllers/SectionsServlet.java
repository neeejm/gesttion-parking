package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import entities.Section;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.SectionService;

@WebServlet("/api/v1/section")
public class SectionsServlet extends HttpServlet {
    private final Gson gson = new Gson();
    private final SectionService ss = new SectionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get section by id if id is given in the request param
        if (req.getParameter("id") != null) {
            String id = req.getParameter("id");
            Section section = ss.findById(Integer.parseInt(id));

            String data = gson.toJson(section);

            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(data);
            out.flush();
            return;
        }

        // generate num sections of type abc(A - B - C - ... - Z) or cx (C1 - C1 - ... -
        // Cnum)
        // if gen and num are given the request params
        if (req.getParameter("gen") != null && req.getParameter("num") != null) {
            generateSection(Integer.parseInt(req.getParameter("num")),
                    req.getParameter("gen"));
            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print("{\"msg\" : \"sections created\"}");
            out.flush();
            return;
        }

        // get all sections
        List<Section> sections = ss.findAll();

        String data = gson.toJson(sections);
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
        Section section = gson.fromJson(body, Section.class);
        if (ss.create(section)) {
            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print("{\"msg\" : \"" + section.getCode() + " created\"}");
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
            if (ss.delete(ss.findById(Integer.parseInt(id)))) {
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

    private void generateSection(int num, String type) {
        switch (type) {
            case "abc":
                for (int i = 0; i < num; i++) {
                    ss.create(new Section(Character.toString((char) (65 + i))));
                }
                break;
            default:
                for (int i = 0; i < num; i++) {
                    ss.create(new Section("C" + Integer.toString(i + 1)));
                }
        }
    }
}
