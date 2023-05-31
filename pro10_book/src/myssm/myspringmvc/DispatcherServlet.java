package myssm.myspringmvc;

import myssm.Util.StringUtil;
import myssm.ioc.BeanFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @create 2023- 05- 18- 18:26
 * @desc
 */

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {
    private BeanFactory beanFactory;
    public DispatcherServlet() {

    }

    public void  init() throws ServletException {
        super.init();

//         之前是在此处主动创建IOC容器
//         现在是从Application作用域中获取
//         beanFactory = new ClassPathXmlApplicationContext();

        ServletContext application = getServletContext();
        Object beanFactoryOBJ = application.getAttribute("beanFactory");
        if (beanFactoryOBJ!=null){
            beanFactory=(BeanFactory)beanFactoryOBJ;
        }else {
            throw new RuntimeException("IOC容器创建获取失败");
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  去掉 /  和去掉 .do
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int sub = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, sub);

        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }
        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods){
                if (operate.equals(method.getName())){
                    //1.同意获取请求参数
                    //获取当前方法的参数，返回参数数组
                    Parameter[] parameters = method.getParameters();
                    //用来存放参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter =parameters[i];
                        String parameterName = parameter.getName();
                        if ("request".equals(parameterName)){
                            parameterValues[i]=request;
                        }else if("response".equals(parameterName)){
                            parameterValues[i]=response;
                        }else if ("session".equals(parameterName)){
                            parameterValues[i]= request.getSession();
                        }else {
                            //从请求中获取参数
                            String  parameterValue = request.getParameter(parameter.getName());
                            String typeName = parameter.getType().getName();

                            Object parameterObj =parameterValue;
                            if (parameterObj!=null){
                                if ("java.lang.Integer".equals(typeName)) {
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }
                        parameterValues[i]=parameterObj;
                        }
                    }

                    //2.controller组件中的方法调用
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj,parameterValues);

                    //3.视图处理
                    String methodReturnStr = (String) returnObj;
                    if (StringUtil.isEmpty(methodReturnStr)){
                        return;
                    }
                    if (methodReturnStr.startsWith("redirect:")){
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        response.sendRedirect(redirectStr);
                    } else if(methodReturnStr.startsWith("json:")){
                        response.setCharacterEncoding("utf-8");
                        response.setContentType("application/json;charset=utf-8");
                        String jsonStr = methodReturnStr.substring("json:".length());
                        PrintWriter out = response.getWriter();
                        out.print(jsonStr);
                        out.flush();
                    } else{
                        super.processTemplate(methodReturnStr,request,response);
                    }
                }
            }
          } catch (Exception e) {
            e.printStackTrace();
            throw new DispatcherServletException("DispatcherServlet出错了");
        }
    }
}