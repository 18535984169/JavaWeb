package myssm.listnters;

import myssm.ioc.BeanFactory;
import myssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @create 2023- 05- 21- 15:02
 * @desc
 */

//监听上下文启动，在上下文启动的时候去创建IOC容器，然后将其保存到Application作用域中
//然后中央控制器再从Application作用域中获取
@WebListener
public class  ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //1.创建ServletContext对象
        ServletContext application = servletContextEvent.getServletContext();
        //2.获取上下文初始化参数
        String path = application.getInitParameter("contextConfigLocation");
        //3.创建IOC容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
        //4.将IOC容器保存到application作用域
        application.setAttribute("beanFactory",beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
