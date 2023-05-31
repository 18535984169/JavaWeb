package Servlets;

import Dao.FruitDAO;
import Dao.impl.FruitDAOImpl;
import Pojo.Fruit;
import myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @create 2023- 05- 18- 0:50
 * @desc
 */

@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");

        String fName = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        int Count = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");

        fruitDAO.addFruit(new Fruit(0,fName,price,Count,remark));

        response.sendRedirect("index");
    }
}
