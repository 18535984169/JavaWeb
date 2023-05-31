package Servlets;

import javafx.concurrent.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @create 2023- 05- 16- 23:35
 * @desc  会话ID
 */
public class Demo03Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //若果获取不到，就创建一个新的
        HttpSession session = req.getSession();
        System.out.println("session id:"+session.getId());
    }
}
