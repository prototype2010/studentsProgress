package controllers.students;

import database.DataService;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Boris on 17.02.2016.
 */
@WebServlet(name="StudentsListAdminController", urlPatterns = {"/admin/students-list/","/student/students-list/","/teacher/students-list/"})
public class StudentsListAdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataService service=new DataService();
        List<Student> students=service.loadAllStudents();
        req.setAttribute("students",students);
        req.setAttribute("currentPage", "students-list.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids= req.getParameter("ids");
        StringTokenizer tokenizer=new StringTokenizer(ids, ",");
        DataService service=new DataService();
        while (tokenizer.hasMoreElements()) {
            int id= Integer.parseInt(tokenizer.nextToken());
            service.deleteStudent(id);
        }
        List<Student> students=service.loadAllStudents();
        req.setAttribute("students", students);
        req.setAttribute("currentPage", "students-list.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req,resp);
    }
}
