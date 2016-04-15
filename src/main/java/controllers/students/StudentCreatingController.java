package controllers.students;

import database.DataService;
import entity.Student;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 * Created by Boris on 16.02.2016.
 */
@WebServlet (name="StudentCreating", urlPatterns = {"/admin/student-creating/"})
public class StudentCreatingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("currentPage", "student-creating.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!(req.getParameter("name").equals("") || req.getParameter("surname").equals("") || req.getParameter("group").equals("") )) {
            Student student = new Student();
            student.setName(req.getParameter("name"));
            student.setSurname(req.getParameter("surname"));
            student.setGroupId(req.getParameter("group"));
            try {
                student.setDate(Date.valueOf(req.getParameter("signDate")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            DataService dataService = new DataService();
            dataService.createStudent(student);
            List<Student> students = dataService.loadAllStudents();
            req.setAttribute("students", students);
            req.setAttribute("currentPage", "students-list.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
        } else {
            req.setAttribute("currentPage", "student-creating.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req,resp);
        }
    }
}
