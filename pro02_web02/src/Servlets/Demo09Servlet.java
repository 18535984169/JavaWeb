package Servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @create 2023- 05- 17- 16:50
 * @desc
 */
@WebServlet("/demo09")
public class Demo09Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext application = req.getServletContext();
        Object uname = application.getAttribute("uname");
        System.out.println(uname);

//        request作用域
//        Object uname = req.getAttribute("uname");
//        System.out.println(uname);

//        HttpSession session = req.getSession();
//        Object uname1 = session.getAttribute("uname");
//        System.out.println(uname1);
    }
}
