package book.service;

import book.pojo.OrderBean;
import book.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @create 2023- 05- 26- 11:06
 * @desc
 */
public interface OrderService {
    //结账
    void addOrderBean(OrderBean orderBean);

    List<OrderBean> getOrderList(User user);
}
