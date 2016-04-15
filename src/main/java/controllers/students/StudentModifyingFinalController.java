package controllers.students;

import database.DataService;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * Created by Boris on 01.03.2016.
 */
@WebServlet(name = "StudentModifyingFinalController", urlPatterns = {"/admin/student-modifying-final/"})
public class StudentModifyingFinalController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DataService service = new DataService();
        if (!(req.getParameter("surname").equals("") || req.getParameter("name").equals("")
                || req.getParameter("date").equals("")  || req.getParameter("groupId").equals(""))) {
            Student student = new Student();
            student.setSurname(req.getParameter("surname"));
            student.setName(req.getParameter("name"));
            student.setGroupId(req.getParameter("groupId"));
            student.setId(Integer.parseInt(req.getParameter("id")));
            try {
                student.setDate(Date.valueOf(req.getParameter("date")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            service.studentUpdate(student);
            List<Student> students = service.loadAllStudents();
            req.setAttribute("students", students);
            req.setAttribute("currentPage", "students-list.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
        } else {
            List<Student> students = service.loadAllStudents();
            req.setAttribute("students", students);
            req.setAttribute("currentPage", "students-list.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
        }
    }
}
