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
@WebServlet(name = "TermModifyingFinalController", urlPatterns = {"/admin/term-modifying-final/"})
public class TermModifyingFinalController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataService dataService = new DataService();
        Term term = new Term();
        term.setId(parseInt(req.getParameter(("number"))));
        term.setDuration(parseInt(req.getParameter(("duration"))));
        String[] disciplines = req.getParameterValues("disciplines");
        dataService.setZeroStatusToDisciplines(term);
        dataService.bindModifiedTermDiscipline(disciplines, term);
        dataService.updateTermDuration(parseInt(req.getParameter("duration")),parseInt(req.getParameter("number")));

        List<Term> terms = dataService.loadAllTerms();
        req.setAttribute("terms", terms);
        req.setAttribute("currentPage", "terms-list.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }
}
