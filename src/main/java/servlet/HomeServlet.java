package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/managerHome")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)

public class HomeServlet extends HttpServlet {
UserManager userManager= new UserManager();
private static String UPLOAD_DIR="C:\\Java2021\\web\\TaskManagment\\userimages";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = userManager.getAllUsers();
        req.setAttribute("allusers", users);
        req.getRequestDispatcher("managerhome.jsp").forward(req, resp);


    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Part filePart = req.getPart("picture");
        String fileName = filePart.getSubmittedFileName();
        String picUrl = System.currentTimeMillis()+"_"+ fileName;
        filePart.write(UPLOAD_DIR +picUrl);


        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String picture = req.getParameter("picture");
        String biography = req.getParameter("biography");

        User user=new User();
        user.setName(name);
        user.setSurName(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setPicUrl(picUrl);
        user.setBiography(biography);
        userManager.register(user);

        req.setAttribute("message", "You have  successfully register!! You can login");
//        req.getRequestDispatcher("managerhome.jsp").forward(req, resp);
        resp.sendRedirect("/managerHome");


    }

}

