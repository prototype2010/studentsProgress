package controllers.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

/**
 * Created by Boris on 16.02.2016.
 */
@WebServlet (name="HomeController", urlPatterns = {"/admin/home/", "/student/home/"})
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
       req.setAttribute("role", req.getSession().getAttribute("role").toString());
    }
}
