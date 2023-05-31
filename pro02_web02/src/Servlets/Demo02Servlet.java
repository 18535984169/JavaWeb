package Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @create 2023- 05- 16- 21:39
 * @desc 研究生命周期
 */
public class Demo02Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("正在初始化");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("正在服务");
    }
    @Override
    public void destroy() {
        System.out.println("销毁方法");
    }
}
