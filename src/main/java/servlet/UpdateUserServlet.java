package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/update")
public class UpdateUserServlet extends HttpServlet {
    UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        User byID = userManager.getByID(Integer.parseInt(id));
        req.setAttribute("user",byID);
        req.getRequestDispatcher("update_user.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User build = User.builder()
                .name("name")
                .surName("surname")
                .email("email")
                .password("password")
                .picUrl("picture")
                .biography("biography")
                .build();
        userManager.updateUser(build);
resp.sendRedirect("/managerHome");
    }
}
