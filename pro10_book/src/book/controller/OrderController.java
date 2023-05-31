package book.controller;

import book.pojo.OrderBean;
import book.pojo.User;
import book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @create 2023- 05- 26- 11:23
 * @desc
 */
public class OrderController {
    private OrderService orderService;

    //结账
    public String checkout(HttpSession session){
        OrderBean orderBean = new OrderBean();
        LocalDateTime now = LocalDateTime.now();
        orderBean.setOrderNo(UUID.randomUUID().toString());//32位的全球唯一码加上当前的时间戳
        orderBean.setOrderDate(now);

        User user = (User)session.getAttribute("currUser");
        orderBean.setOrderUser(user);
        orderBean.setOrderMoney(user.getCart().getTotalMoney());
        orderBean.setOrderStatus(0 );

        orderService.addOrderBean(orderBean);

        return "index";
    }

    //查看订单列表
    public  String getOrderList(HttpSession session){
        User  currUser = (User)session.getAttribute("currUser");
        List<OrderBean> orderList = orderService.getOrderList(currUser);
        currUser.setOrderBeanList(orderList);
        session.setAttribute("currUser",currUser);
        return "order/order";
    }
}
