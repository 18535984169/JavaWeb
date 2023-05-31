package book.dao;

import book.pojo.OrderItem;

/**
 * @create 2023- 05- 26- 10:53
 * @desc '
 */
public interface OrderItemDAO {
    //添加订单项
    void addOrderItem(OrderItem orderItem);
}
