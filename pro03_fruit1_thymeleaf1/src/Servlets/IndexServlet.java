package Servlets;

import Dao.FruitDAO;
import Dao.impl.FruitDAOImpl;
import Pojo.Fruit;
import myssm.Util.StringUtil;
import myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Service;
import java.io.IOException;
import java.util.List;

/**
 * @create 2023- 05- 17- 11:52
 * @desc
 */

//servlet从3.0版本开始支持注解方式注册
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); //设置编码格式
        HttpSession session = request.getSession();
        //设置当前页默认值是1
        Integer pageNo = 1;

        //如果oper!=null 说明是通过表单查询按钮过来， 如果为空则默认的显示库存
        String oper = request.getParameter("oper");

        String keyword=null;
        if (StringUtil.isNotEmpty(oper)&&"search".equals(oper)){
            //说明是通过表单查询过来的按钮
            //此时pageNo应该还原为1 keyword应该从请求参数中获得
            pageNo = 1;
            keyword=request.getParameter("keyword");
            if (StringUtil.isEmpty(keyword)){
                //如果keyword为null 则需要设置为空 否则查询时会拼接为 %null%
                keyword="";
            }
            //将keyword覆盖到session中
            session.setAttribute("keyword",keyword);
        }else {
            //说明此处不是通过表单查询过来的
            //此时keyword应该从session作用域中获取
            String pageNoStr = request.getParameter("pageNo");
            if (StringUtil.isNotEmpty(pageNoStr)){
                pageNo=Integer.parseInt(pageNoStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj!=null){
                keyword=(String)keywordObj;
            }else {
                keyword="";
            }
        }


        session.setAttribute("pageNo",pageNo);
        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword,pageNo);

        //总记录条数 和总页数
        int fruitCount = fruitDAO.getcount(keyword);
        int pageCount = (fruitCount+4)/5;
        session.setAttribute("pageCount",pageCount);

        //保存到session作用域
        session.setAttribute("fruitList",fruitList);
        //此处的视图名称是index
        //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图名称上去
        // 逻辑视图名称：index
        // 物理视图名称：view-prefix + 逻辑视图名称 + view-suffix
        // 所以真实的视图名称是 / index .html
        super.processTemplate("index",request,response);
    }
}
