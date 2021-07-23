package servlet;

import enums.UserType;
import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userManager.getByEmailAndPassword(email, password);
        req.getSession().setAttribute("user", user);
        if (user != null && user.getUserType().equals(UserType.MANAGER)) {
            resp.sendRedirect("/managerHome");
        } else if (user != null && user.getUserType().equals(UserType.USER)) {
            resp.sendRedirect("/userhome");
        } else {
            req.setAttribute("message", "invalid login or password");
            req.getRequestDispatcher("index.jsp").forward(req, resp);

        }

//        List<User> allUsers = userManager.getAllUsers();
//        req.setAttribute("allusers", allUsers);
//        req.getRequestDispatcher("managerhome.jsp").forward(req,resp);
    }
}
