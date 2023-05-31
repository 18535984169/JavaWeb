package book.service.impl;

import book.dao.CartItemDAO;
import book.dao.OrderDAO;
import book.dao.OrderItemDAO;
import book.pojo.CartItem;
import book.pojo.OrderBean;
import book.pojo.OrderItem;
import book.pojo.User;
import book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @create 2023- 05- 26- 11:09
 * @desc
 */
public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;

    private CartItemDAO cartItemDAO;
    @Override
    public void addOrderBean(OrderBean orderBean) {
        //1.订单表添加1条记录
        orderDAO.addOrderBean(orderBean); //执行完这一步orderBean中的ID是有值的

        //2.订单详情表添加N条记录  根据购物车项转换成订单项
        User currUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = currUser.getCart().getCartItemMap();
        for (CartItem cartItem:cartItemMap.values()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDAO.addOrderItem(orderItem);
        }

        //3.购物车删除N条记录
        for (CartItem cartItem:cartItemMap.values()){
            cartItemDAO.delCartItem(cartItem);
        }
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        List<OrderBean> orderList = orderDAO.getOrderList(user);
        return orderList;
    }
}
