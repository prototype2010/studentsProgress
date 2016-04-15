package controllers.terms;

import database.DataService;
import entity.Discipline;
import entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Boris on 16.02.2016.
 */
@WebServlet(name = "TermModifyingController", urlPatterns = {"/admin/term-modifying/"})
public class TermModifyingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataService service = new DataService();
        List<Discipline> disciplines = service.loadAllDisciplines();
        req.setAttribute("disciplines", disciplines);
        req.setAttribute("currentPage", "term-modifying.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("id");
        StringTokenizer tokenizer = new StringTokenizer(ids, "");
        DataService service = new DataService();
        int id=0;
        while (tokenizer.hasMoreElements()) {
            id = Integer.parseInt(tokenizer.nextToken());
        }
        req.setAttribute("number",id);
        int termDuration=service.getTermDuration(id);
        req.setAttribute("duration",termDuration);

        List<Discipline> disciplines = service.loadAllDisciplines();
        req.setAttribute("disciplines", disciplines);
        req.setAttribute("currentPage", "term-modifying.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }
}





















