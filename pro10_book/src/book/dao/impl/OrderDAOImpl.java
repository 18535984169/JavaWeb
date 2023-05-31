package book.dao.impl;

import book.dao.OrderDAO;
import book.pojo.OrderBean;
import book.pojo.User;
import myssm.BaseDao.BaseDAO;

import java.util.List;

/**
 * @create 2023- 05- 26- 10:51
 * @desc
 */
public class OrderDAOImpl extends BaseDAO<OrderBean> implements OrderDAO {
    @Override
    public void addOrderBean(OrderBean orderBean) {
        int orderId =super.executeUpdate("insert into t_order values(0,?,?,?,?,?)",
                orderBean.getOrderNo(),orderBean.getOrderDate(),orderBean.getOrderUser().getId(),orderBean.getOrderMoney(),orderBean.getOrderStatus());
        orderBean.setId(orderId);
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        return executeQuery("select * from t_order where orderUser= ?",user.getId());
    }
}
