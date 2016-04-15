package controllers.marks;

import database.DataService;
import entity.*;

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
@WebServlet(name = "SetMarkController", urlPatterns = {"/admin/set-mark/", "/student/set-mark/"})
public class SetMarkController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DataService service = new DataService();
        List<Student> students = service.loadAllStudents();
        req.setAttribute("students", students);
        List<Term> terms = service.loadAllTerms();
        req.setAttribute("terms", terms);
        req.setAttribute("currentPage", "set-mark.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DataService service = new DataService();
        if (!(req.getParameter("student").equals("") || req.getParameter("terms").equals(""))) {
            String chars = req.getParameter("student").toString();
            int id = Integer.parseInt(chars.substring(0, chars.indexOf(".")));
            int termId = Integer.parseInt(req.getParameter("terms").toString());
            List<Student> students = service.loadAllStudents();
            req.setAttribute("students", students);
            List<Term> terms = service.loadAllTerms();
            req.setAttribute("terms", terms);
            req.setAttribute("selectedStudent", chars);
            req.setAttribute("selectedTerm", termId);
            List<LinkTermDiscipline> resultList = service.disciplinesByTermAndStudent(termId, id);
            String idsForMarks = "";
            for (int i = 0; i < resultList.size(); i++) {
                idsForMarks += resultList.get(i).getId() + ",";
            }
            req.setAttribute("idsForMarks", idsForMarks);
            req.setAttribute("disciplineMarks", resultList);
            req.setAttribute("currentPage", "set-mark.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
        } else {
            List<Student> students = service.loadAllStudents();
            req.setAttribute("students", students);
            List<Term> terms = service.loadAllTerms();
            req.setAttribute("terms", terms);
            req.setAttribute("currentPage", "set-mark.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
        }
    }
}






