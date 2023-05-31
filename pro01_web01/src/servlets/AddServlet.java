package servlets;

import fruit.dao.FruitDAO;
import fruit.dao.impl.FruitDAOImpl;
import fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @create 2023- 05- 16- 10:39
 * @desc
 */
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");

        String fName = request.getParameter("fName");
        int price = Integer.parseInt(request.getParameter("price"));
        int Count = Integer.parseInt(request.getParameter("fCount"));
        String remark = request.getParameter("remark");

        FruitDAO fruitDAO  = new FruitDAOImpl();
        boolean b = fruitDAO.addFruit(new Fruit(0, fName, price, Count, remark));

        System.out.println(b?"添加成功":"添加失败 ");
    }
}
