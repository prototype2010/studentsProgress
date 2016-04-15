package controllers.terms;

import database.DataService;
import entity.Discipline;
import entity.Term;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;

import static java.lang.Integer.parseInt;

/**
 * Created by Boris on 16.02.2016.
 */
@WebServlet(name = "TermCreating", urlPatterns = {"/admin/term-creating/"})
public class TermCreatingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataService service = new DataService();
        List<Discipline> disciplines = service.loadAllDisciplines();
        req.setAttribute("disciplines", disciplines);
        req.setAttribute("currentPage", "term-creating.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataService dataService = new DataService();
        Term term = new Term();
        term.setId(parseInt(req.getParameter(("number"))));
        term.setDuration(parseInt(req.getParameter(("duration"))));
        dataService.rollBackDeletedTerm(term.getId());
        dataService.setZeroStatusToDisciplines(term);
        dataService.updateTermDuration(term.getDuration(),term.getId());


        String[] disciplines = req.getParameterValues("disciplines");

        dataService.newTermCreating(term, disciplines);
        for (int i = 0; i < disciplines.length; i++) {
            dataService.newTermBind(disciplines[i], term.getId());
        }
        List<Term> terms = dataService.loadAllTerms();
        req.setAttribute("terms", terms);
        req.setAttribute("currentPage", "terms-list.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);

    }
}
