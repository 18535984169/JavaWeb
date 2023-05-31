package book.controller;

import book.pojo.Cart;
import book.pojo.User;
import book.service.CartItemService;
import book.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * @create 2023- 05- 25- 19:35
 * @desc
 */
public class UserController {
    private UserService userService;
    private CartItemService cartItemService;
    public String login (String uname, String pwd, HttpSession session){
        User user = userService.login(uname, pwd);
       if (user!=null){
           Cart cart = cartItemService.getCart(user);
           user.setCart(cart);

           session.setAttribute("currUser",user);
           return "redirect:book.do";
       }
        return "user/login";
    }

    public String regist(String uname,String pwd,String email,String verifyCode,HttpSession session){
        Object kaptchaSessionKey = (String)session.getAttribute("KAPTCHA_SESSION_KEY");
        if (kaptchaSessionKey==null || !verifyCode.equals(kaptchaSessionKey)){
            return "user/regist";
        }else {
            if (verifyCode.equals(kaptchaSessionKey)){
                userService.regist(new User(uname,pwd,email,0));
                return "user/login";
            }
        }
        return "user/login";
    }

    public String ckUname(String uname){
        User user = userService.getUser(uname);
        if (user!=null){
            //用户名已占用 不可以注册
            return "json:{'uname':'1'}";
            //return "ajax:1";
        }else {
            return "json:{'uname':'0'}";
            //return "ajax:0";
        }
    }
}
