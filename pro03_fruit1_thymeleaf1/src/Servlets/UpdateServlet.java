package Servlets;

import Dao.FruitDAO;
import Dao.impl.FruitDAOImpl;
import Pojo.Fruit;
import com.sun.org.apache.bcel.internal.generic.FCMPG;
import myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @create 2023- 05- 17- 23:27
 * @desc
 */

@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");

        int fid = Integer.parseInt(request.getParameter("fid"));
        String fName = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        int Count = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");

        fruitDAO.updateFruit(new Fruit(fid,fName,price,Count,remark));

//   资源跳转
//       super.processTemplate("index",request,resp);
//       相当于request.getRequestDispatcher("index").forward(request,resp);
        //此处需要重定向，这样获取的信息才是最新的
        resp.sendRedirect("index");
    }
}
