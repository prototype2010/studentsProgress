package controllers.disciplines;

import database.DataService;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Boris on 17.02.2016.
 */
@WebServlet(name="DisciplinesListController", urlPatterns = {"/student/disciplines-list/", "/admin/disciplines-list/"})
public class DisciplinesListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataService service=new DataService();
        List<Discipline> disciplines= service.loadAllDisciplines();
        req.setAttribute("disciplines",disciplines);
        req.setAttribute("currentPage", "disciplines-list.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids= req.getParameter("ids");
        StringTokenizer tokenizer=new StringTokenizer(ids, ",");
        DataService service=new DataService();
        while (tokenizer.hasMoreElements()) {
            int id= Integer.parseInt(tokenizer.nextToken());
            service.deleteDiscipline(id);
        }
        List<Discipline> disciplines= service.loadAllDisciplines();
        req.setAttribute("disciplines",disciplines);
        req.setAttribute("currentPage", "disciplines-list.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req,resp);

    }
}
