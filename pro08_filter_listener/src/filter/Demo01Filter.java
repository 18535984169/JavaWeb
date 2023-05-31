package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @create 2023- 05- 20- 12:53
 * @desc
 */
@WebFilter("*.do")
public class Demo01Filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

     }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("method:doFilter:helloA");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("method:doFilter:helloB");
    }

    @Override
    public void destroy() {

    }
}
