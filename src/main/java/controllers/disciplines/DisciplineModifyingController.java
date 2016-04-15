package controllers.disciplines;

import database.DataService;
import entity.Discipline;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by Boris on 01.03.2016.
 */
@WebServlet(name = "DisciplineModifyingController", urlPatterns = {"/admin/discipline-modifying/", "/teacher/discipline-modifying/"})
public class DisciplineModifyingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("currentPage", "discipline-modifying.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("ids");
        StringTokenizer tokenizer = new StringTokenizer(ids, "");
        DataService service = new DataService();
        Discipline discipline = new Discipline();

        while (tokenizer.hasMoreElements()) {
        int  id = Integer.parseInt(tokenizer.nextToken());
         discipline = service.disciplineModifying(id);
        }
        req.setAttribute("disciplineName",discipline.getName());
        req.setAttribute("id",discipline.getId());
        req.setAttribute("currentPage", "discipline-modifying.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }
}
