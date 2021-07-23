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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/userhome")
public class UserHomeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskManager taskManager = new TaskManager();
        UserManager userManager = new UserManager();
        User user = (User) req.getSession().getAttribute("user");
        List<Task> tasks = taskManager.getByUserId(user.getId());


        req.setAttribute("allUserTasks", tasks);
        req.getRequestDispatcher("userhome.jsp").forward(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        TaskManager taskManager=new TaskManager();
//        User user =(User) req.getSession().getAttribute("user");
//        List<Task> tasks=new ArrayList<>();
//        Task byID = taskManager.getByID(user.getId());
//        tasks.add(byID);
//        req.setAttribute("allUserTasks", tasks);
//        req.getRequestDispatcher("userhome.jsp").forward(req, resp);
//    }
}
