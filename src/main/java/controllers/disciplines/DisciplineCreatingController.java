package controllers.disciplines;

import database.DataService;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 * Created by Boris on 16.02.2016.
 */
@WebServlet(name = "DisciplineCreating", urlPatterns = {"/admin/discipline-creating/", "/teacher/discipline-creating/"})
public class DisciplineCreatingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("currentPage", "discipline-creating.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Discipline discipline = new Discipline();
        discipline.setName(req.getParameter("name"));
        DataService dataService = new DataService();
        dataService.createDiscipline(discipline);
        List<Discipline> disciplines = dataService.loadAllDisciplines();
        req.setAttribute("disciplines", disciplines);
        req.setAttribute("currentPage", "disciplines-list.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }
}
