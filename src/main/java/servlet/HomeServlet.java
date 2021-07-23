package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/managerHome")
public class HomeServlet extends HttpServlet {
UserManager userManager= new UserManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = userManager.getAllUsers();
        req.setAttribute("allusers", users);
        req.getRequestDispatcher("managerhome.jsp").forward(req, resp);


    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user=new User();
        user.setName(name);
        user.setSurName(surname);
        user.setEmail(email);
        user.setPassword(password);
        userManager.register(user);

        req.setAttribute("message", "You have  successfully register!! You can login");
//        req.getRequestDispatcher("managerhome.jsp").forward(req, resp);
        resp.sendRedirect("/managerHome");


    }

}

