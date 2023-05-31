package book.dao.impl;

import book.dao.OrderItemDAO;
import book.pojo.OrderItem;
import myssm.BaseDao.BaseDAO;

/**
 * @create 2023- 05- 26- 11:02
 * @desc
 */
public class OrderItemDAOImpl extends BaseDAO<OrderItem> implements OrderItemDAO {

    @Override
    public void addOrderItem(OrderItem orderItem) {
        super.executeUpdate("insert into t_order_item values(0,?,?,?)",
                orderItem.getBook().getId(),orderItem.getBuyCount(),orderItem.getOrderBean().getId());
    }
}
