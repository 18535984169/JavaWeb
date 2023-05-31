package controllers;

import Dao.FruitDAO;
import Dao.impl.FruitDAOImpl;
import Pojo.Fruit;
import myssm.Util.StringUtil;
import myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @create 2023- 05- 18- 16:38
 * @desc   继承一个servlet
 */


public class fruitController extends ViewBaseServlet {

    //之前的fruitController是一个servlet组件，那么其中的init方法一定会被调用
    //之前的init方法内部会出现一句话：super.initial();

    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
        try {
            super.init(servletContext);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private FruitDAO fruitDAO = new FruitDAOImpl();

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8"); //设置编码格式

        String fName = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        int Count = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");

        fruitDAO.addFruit(new Fruit(0,fName,price,Count,remark));

        response.sendRedirect("fruit.do");
    }
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); //设置编码格式
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit",fruit);
            super.processTemplate("edit",request,response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); //设置编码格式

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
        resp.sendRedirect("fruit.do");
    }

    private void del(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); //设置编码格式
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruitByFid(fid);
        }

        resp.sendRedirect("fruit.do");
    }
}

