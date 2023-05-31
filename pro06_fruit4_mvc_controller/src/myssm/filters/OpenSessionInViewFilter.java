package myssm.filters;

import myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @create 2023- 05- 21- 10:51
 * @desc 创建一个connection
 */
@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            TransactionManager.beginTrans();
            //System.out.println("开启事务...");
            filterChain.doFilter(servletRequest,servletResponse);
            TransactionManager.commit();
            //System.out.println("结束事务...");
        }catch(Exception e){
            e.printStackTrace();
            try {
                TransactionManager.rollback();
                //System.out.println("回滚事务...");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }finally {

        }
    }

    @Override
    public void destroy() {

    }
}
