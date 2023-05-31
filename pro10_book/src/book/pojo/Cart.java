package book.pojo;


import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * @create 2023- 05- 25- 21:45
 * @desc
 */
public class Cart {
    private Map<Integer,CartItem> cartItemMap;  //购物车中项的集合，这个Map中的key是Book的ID
    private double totalMoney;                 //购物车中的总金额
    private Integer totalCount;                //购物车中购物项的数量
    private Integer totalBookCount;            //购物车中书本的数量

    public Cart() {
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public double getTotalMoney() {
        BigDecimal sum = new BigDecimal(""+0.0);
        totalMoney=0.0;
        if (cartItemMap!=null&&cartItemMap.size()>0){
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for (Map.Entry<Integer, CartItem> entry : entries) {
                CartItem cartItem = entry.getValue();
                sum=sum.add(new BigDecimal(""+cartItem.getXj()));
               // totalMoney=totalMoney+(cartItem.getBook().getPrice()) * (cartItem.getBuyCount());
            }
        }
        totalMoney=sum.doubleValue();
        return totalMoney;
    }
    public Integer getTotalCount() {
        totalCount=0;
        if (cartItemMap!=null&&cartItemMap.size()>0){
            totalCount = cartItemMap.size();
        }
        return totalCount;
    }

    public Integer getTotalBookCount() {
        totalBookCount=0;
        if (cartItemMap!=null&&cartItemMap.size()>0){
            for (CartItem cartItem: cartItemMap.values()){
                totalBookCount+= cartItem.getBuyCount();
            };
        }
        return totalBookCount;
    }
}
