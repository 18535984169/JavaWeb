package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;

/**
 * @create 2023- 05- 19- 14:07
 * @desc
 */
public class Demo01Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ServletConfig servletConfig = getServletConfig();
        String s = servletConfig.getInitParameter("hello");
        System.out.println(s);

        ServletContext servletContext = getServletContext();
        String s1 = servletContext.getInitParameter("contextConfigLocation");
        System.out.println(s1);
    }
}
