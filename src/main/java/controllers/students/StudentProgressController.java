package controllers.students;

import database.DataService;
import entity.Student;
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
 * Created by Boris on 01.03.2016.
 */
@WebServlet(name = "StudentProgressController", urlPatterns = {"/student/student-progress/", "/admin/student-progress/"})
public class StudentProgressController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("currentPage", "student-progress.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("ids");
        StringTokenizer tokenizer = new StringTokenizer(ids, "");
        DataService service = new DataService();
        Student student=null;
        while (tokenizer.hasMoreElements()) {
            int id = Integer.parseInt(tokenizer.nextToken());
            student= service.studentModifying(id);
        }
        req.setAttribute("id",student.getId());
        req.setAttribute("surname",student.getSurname());
        req.setAttribute("name",student.getName());
        req.setAttribute("groupId",student.getGroupId());
        req.setAttribute("date",student.getDate());
        List<Term> terms = service.loadAllTerms();
        req.setAttribute("terms", terms);

        req.setAttribute("currentPage", "student-progress.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);

    }
}
