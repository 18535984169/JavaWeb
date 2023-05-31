package Servlets;

import jdk.nashorn.internal.ir.RuntimeNode;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @create 2023- 05- 17- 16:46
 * @desc application
 */
@WebServlet("/demo08")
public class Demo08Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext application = req.getServletContext();
        application.setAttribute("uname","hahaha");
        //客户端重定向
        resp.sendRedirect("demo09");
        //客户端转发
        //req.getRequestDispatcher("demo09").forward(req,resp);

//        request作用域
//        req.setAttribute("uname","hahaha");
//        resp.sendRedirect("demo09");

//        session作用域
//        HttpSession session = req.getSession();
//        session.setAttribute("uname","hahaha");
//        resp.sendRedirect("demo09");
    }
}
