package controllers.marks;

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

/**
 * Created by Boris on 17.02.2016.
 */
@WebServlet(name = "SetMarkFinalController", urlPatterns = {"/admin/set-mark-final/", "/teacher/set-mark-final/"})
public class SetMarkFinalController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataService service = new DataService();
        if (!(req.getParameter("studentId").equals("") || req.getParameter("termId").equals(""))) {
            String chars = req.getParameter("studentId").toString();
            int studentId = Integer.parseInt(chars.substring(0, chars.indexOf(".")));
            int termId = Integer.parseInt(req.getParameter("termId").toString());
            String idsForMarks = req.getParameter("idsForMarks").toString();
            String cleanString = idsForMarks.substring(0, idsForMarks.lastIndexOf(","));
            String[] disciplinesIds = cleanString.split(",");
            List<LinkTermDiscipline> resultList = service.disciplinesByTermAndStudent(termId, studentId);
            for (int i = 0; i < resultList.size(); i++) {
                resultList.get(i).setMark(Integer.parseInt(req.getParameter(disciplinesIds[i])));
            }
            service.setMark(resultList, studentId);
            List<Student> students = service.loadAllStudents();
            req.setAttribute("students", students);
            List<Term> terms = service.loadAllTerms();
            req.setAttribute("terms", terms);
            req.setAttribute("selectedStudent", chars);
            req.setAttribute("selectedTerm", termId);
            List<LinkTermDiscipline> resultListView = service.disciplinesByTermAndStudent(termId, studentId);
//            /// DEBUG
            String idsForMarksView = "";
            for (int i = 0; i < resultListView.size(); i++) {
                idsForMarksView += resultListView.get(i).getId() + ",";
            }
            req.setAttribute("idsForMarks", idsForMarksView);
            req.setAttribute("disciplineMarks", resultListView);
            req.setAttribute("currentPage", "set-mark.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
        } else {
            req.setAttribute("WARNING","Select term and student please");
            List<Student> students = service.loadAllStudents();
            req.setAttribute("students", students);
            List<Term> terms = service.loadAllTerms();
            req.setAttribute("terms", terms);
            req.setAttribute("currentPage", "set-mark.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
        }
    }
}






