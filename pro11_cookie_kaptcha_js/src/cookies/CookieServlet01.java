package cookies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @create 2023- 05- 26- 16:02
 * @desc
 */

@WebServlet("/cookie01")
public class CookieServlet01 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1。创建一个cookie对象
        Cookie cookie = new Cookie("uname","jim");
        //2.将cookie对象添加到响应中
        response.addCookie(cookie);

        request.getRequestDispatcher("hello01.html").forward(request,response);
    }
}
