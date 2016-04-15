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

/**
 * Created by Boris on 17.02.2016.
 */
@WebServlet(name = "TermsListController", urlPatterns = {"/student/terms-list/", "/admin/terms-list/"})
public class TermsListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataService service = new DataService();
        List<Term> terms = service.loadAllTerms();
        req.setAttribute("terms", terms);
        req.setAttribute("currentPage", "terms-list.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataService service = new DataService();

        if (!((req.getParameter("term")).equals(""))) {
            List<Term> terms = service.loadAllTerms();
            req.setAttribute("terms", terms);
            req.setAttribute("weeks", service.getTermDuration(Integer.parseInt(req.getParameter("term"))));
            List<Discipline> disciplines = service.termDisciplinesList(Integer.parseInt(req.getParameter("term")));
            req.setAttribute("disciplines", disciplines);
            req.setAttribute("selectedTerm", Integer.parseInt(req.getParameter("term")));
            req.setAttribute("currentPage", "terms-list.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
        } else {
            List<Term> termsNull = service.loadAllTerms();
            req.setAttribute("terms", termsNull);
            req.setAttribute("currentPage", "terms-list.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
        }

    }
}
