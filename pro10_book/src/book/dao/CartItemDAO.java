package book.dao;

import book.pojo.CartItem;
import book.pojo.User;

import java.util.List;

/**
 * @create 2023- 05- 25- 21:31
 * @desc
 */
public interface CartItemDAO {
    //新增购物车箱
    void addCartItem(CartItem cartItem);
    //修改特定的购物车项
    void updateCartItem(CartItem cartItem);

    //获取用户购物车的所有项目
    List<CartItem> getCartItemList(User user);

    //删除指定的购物车项
    void delCartItem(CartItem cartItem);
}
