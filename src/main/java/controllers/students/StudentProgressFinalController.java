package controllers.students;

import database.DataService;
import entity.DisciplineMark;
import entity.LinkTermDiscipline;
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
 * Created by Boris on 03.03.2016.
 */
@WebServlet(name = "StudentProgressFinalController", urlPatterns = {"/student/student-progress-final/", "/admin/student-progress-final/"})
public class StudentProgressFinalController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!((req.getParameter("term")).equals(""))) {
            String ids = req.getParameter("id");
            StringTokenizer tokenizer = new StringTokenizer(ids, "");
            DataService service = new DataService();
            Student student = null;
            while (tokenizer.hasMoreElements()) {
                int id = Integer.parseInt(tokenizer.nextToken());
                student = service.studentModifying(id);
            }
            req.setAttribute("id", student.getId());
            req.setAttribute("surname", student.getSurname());
            req.setAttribute("name", student.getName());
            req.setAttribute("groupId", student.getGroupId());
            req.setAttribute("date", student.getDate());
//FIXING
            List<LinkTermDiscipline> resultList = service.disciplinesByTermAndStudent(Integer.parseInt(req.getParameter("term")), student.getId());
            req.setAttribute("disciplinesMarks", resultList);
            double summ = 0;
            for (int i = 0; i < resultList.size(); i++) {
                summ += resultList.get(i).getMark();
            }

            if (resultList.size() > 0) {
                String result = (String.valueOf(summ / resultList.size())).substring(0, 3);
                req.setAttribute("average", result);
            }
//FIXING
            List<Term> terms = service.loadAllTerms();
            req.setAttribute("terms", terms);
            req.setAttribute("selectedTerm", Integer.parseInt(req.getParameter("term")));
            req.setAttribute("currentPage", "student-progress.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
        } else {
            String ids = req.getParameter("id");
            StringTokenizer tokenizer = new StringTokenizer(ids, "");
            DataService service = new DataService();
            Student student = null;
            while (tokenizer.hasMoreElements()) {
                int id = Integer.parseInt(tokenizer.nextToken());
                student = service.studentModifying(id);
            }
            req.setAttribute("id", student.getId());
            req.setAttribute("surname", student.getSurname());
            req.setAttribute("name", student.getName());
            req.setAttribute("groupId", student.getGroupId());
            req.setAttribute("date", student.getDate());
            List<Term> terms = service.loadAllTerms();
            req.setAttribute("terms", terms);
            req.setAttribute("currentPage", "student-progress.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
        }
    }
}
