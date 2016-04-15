package controllers.disciplines;

import database.DataService;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Boris on 03.03.2016.
 */

@WebServlet(name = "DisciplineModifyingFinalController", urlPatterns = { "/admin/discipline-modifying-final/"})

public class DisciplineModifyingFinalController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DataService service=new DataService();
        Discipline discipline=new Discipline();
        discipline.setName(req.getParameter("disciplineName"));
        discipline.setId(Integer.parseInt(req.getParameter("id")));
        service.disciplineUpdate(discipline);
        List<Discipline> disciplines= service.loadAllDisciplines();
        req.setAttribute("disciplines",disciplines);
        req.setAttribute("currentPage", "disciplines-list.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req,resp);



    }
}
