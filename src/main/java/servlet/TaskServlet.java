package servlet;

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

@WebServlet(urlPatterns = "/all_tasks")
public class TaskServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskManager taskManager=new TaskManager();
        List<Task>tasks=taskManager.getAllTasks();
        req.setAttribute("allTasks", tasks);
        req.getRequestDispatcher("tasks.jsp").forward(req, resp);


    }
}
