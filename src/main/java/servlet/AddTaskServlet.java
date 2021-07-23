package servlet;

import enums.Status;
import enums.UserType;
import manager.TaskManager;
import manager.UserManager;
import model.Task;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/addtask")
public class AddTaskServlet extends HttpServlet {
    TaskManager taskManager=new TaskManager();
    UserManager userManager=new UserManager();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int userid=Integer.parseInt(req.getParameter("user"));
        String status = req.getParameter("status");
        String deadline = req.getParameter("deadline");
        List<Task> tasks=taskManager.getAllTasks();

        Task task=new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setUserId(userid);
        task.setDeadline(deadline);
        task.setStatus(Status.valueOf(status));
        taskManager.addTask(task);

        req.setAttribute("message", "Your task added");
        resp.sendRedirect("/managerHome");
    }
}
