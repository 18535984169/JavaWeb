package book.dao;

import book.pojo.OrderBean;
import book.pojo.User;

import java.util.List;

/**
 * @create 2023- 05- 26- 10:51
 * @desc
 */
public interface OrderDAO {
    //添加订单
    void addOrderBean(OrderBean orderBean);

    //获取指定用户的订单列表
    List<OrderBean> getOrderList(User user);
}
