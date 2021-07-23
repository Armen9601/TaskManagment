package servlet;

import manager.TaskManager;
import manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete_user")
public class DeleteUsersServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserManager userManager = new UserManager();
        TaskManager taskManager= new TaskManager();
        int userId=Integer.parseInt(req.getParameter("id"));

        taskManager.deleteTaskByUserId(userId);
        userManager.deleteUser(userId);

        resp.sendRedirect("/all_users");


    }
}
