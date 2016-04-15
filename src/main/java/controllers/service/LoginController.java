package controllers.service;

import database.DataService;
import entity.Account;
import entity.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Boris on 28.02.2016.
 */

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataService dataService = new DataService();
        List<Role> list = dataService.loadAllRoles();
        req.setAttribute("roles", list);
        req.setAttribute("currentPage", "login.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/template.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        Role currentRole;

        DataService dataService = new DataService();
        Account accFromBase = dataService.loginProcess(login);


        if (password.equals(accFromBase.getPassword())) {
            currentRole = dataService.getRole(accFromBase.getId());
            if (role.equals(currentRole.getName())) {
                // set role to session
                req.getSession().setAttribute("role", currentRole.getName().toString().toLowerCase());
                String sessionRole = req.getSession().getAttribute("role").toString().toLowerCase();
                resp.sendRedirect("/" + sessionRole + "/home/");
            } else {
                // role is incorrect
                List<Role> list = dataService.loadAllRoles();
                req.setAttribute("roles", list);
                req.setAttribute("currentPage", "login.jsp");
                req.setAttribute("WARNING","Selected role doesn't match to login");
                req.getRequestDispatcher("WEB-INF/jsp/template.jsp").forward(req, resp);
            }
        } else {
            //login or password is incorrect (or both )
            List<Role> list = dataService.loadAllRoles();
            req.setAttribute("WARNING","Login or password is incorrect");
            req.setAttribute("roles", list);
            req.setAttribute("currentPage", "login.jsp");
            req.getRequestDispatcher("WEB-INF/jsp/template.jsp").forward(req, resp);
        }
    }
}
