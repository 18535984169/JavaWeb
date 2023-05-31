package book.service.impl;

import book.dao.CartItemDAO;
import book.pojo.Book;
import book.pojo.Cart;
import book.pojo.CartItem;
import book.pojo.User;
import book.service.BookService;
import book.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @create 2023- 05- 25- 21:41
 * @desc
 */
public class CartItemServiceImpl implements CartItemService {
    private  CartItemDAO cartItemDAO;
    private BookService bookService;
    @Override
    public void addCartItem(CartItem cartItem) {
         cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public void upDateCartItem(CartItem cartItem) {
        cartItemDAO.updateCartItem(cartItem);
    }

    @Override
    public void addOrUpDateCartItem(CartItem cartItem, Cart cart) {
        //将指定的图书添加到当前用户的购物车中
        //如果已经存在，将购物车中这本图书的数量加一，否则增加一个这本图书的CartItem,数量是1

        //判断当前用户购物车中是否有这本书
        if (cart!=null){
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if (cartItemMap == null){
                cartItemMap = new HashMap<>();
            }

            if (cartItemMap.containsKey(cartItem.getBook().getId())){
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount()+1);
                upDateCartItem(cartItemTemp);
            }else {
                addCartItem(cartItem);
            }
        }else {
            addCartItem(cartItem);
        }
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        List<CartItem> cartItemList = cartItemDAO.getCartItemList(user);
        for (CartItem cartItem : cartItemList){
            Book book = bookService.getBook(cartItem.getBook().getId());
            cartItem.setBook(book);
            //目的是计算一下小计并赋值
            cartItem.getXj();
        }
        return cartItemList;
    }

    @Override
    public Cart getCart(User user) {
        List<CartItem> cartItemList = getCartItemList(user);
        Map<Integer,CartItem> cartItemMap = new HashMap<>();
        for (CartItem cartItem : cartItemList){
            cartItemMap.put(cartItem.getBook().getId(),cartItem);
        }
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);
        return cart;
    }
}
