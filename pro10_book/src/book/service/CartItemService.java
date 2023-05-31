package book.service;

import book.pojo.Cart;
import book.pojo.CartItem;
import book.pojo.User;

import java.util.List;

/**
 * @create 2023- 05- 25- 21:41
 * @desc
 */
public interface CartItemService {
    void addCartItem(CartItem cartItem);
    void upDateCartItem(CartItem cartItem);
    void addOrUpDateCartItem(CartItem cartItem, Cart cart);

    //获取指定用户的购物车里项的所有信息
    List<CartItem> getCartItemList(User user);

    //加载特定用户的购物车信息
    Cart getCart(User user);
}
