package controllers.terms;

import database.DataService;
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
 * Created by Boris on 02.03.2016.
 */

@WebServlet(name = "TermDeletingController", urlPatterns = { "/admin/term-deleting/"})
public class TermDeletingController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("id");
        StringTokenizer tokenizer = new StringTokenizer(ids, "");
        DataService service = new DataService();
        while (tokenizer.hasMoreElements()) {
            int id = Integer.parseInt(tokenizer.nextToken());
            service.deleteTerm(id);
        }
        List<Term> terms = service.loadAllTerms();
        req.setAttribute("terms", terms);
        req.setAttribute("currentPage", "terms-list.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);

    }
}
