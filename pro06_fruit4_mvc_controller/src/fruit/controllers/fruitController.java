package fruit.controllers;


import fruit.Pojo.Fruit;
import fruit.service.FruitService;
import myssm.Util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @create 2023- 05- 18- 16:38
 * @desc   继承一个servlet
 */


public class fruitController {
    private FruitService fruitService = null;

    private String index(String oper,String keyword,Integer pageNo,HttpServletRequest request){
         HttpSession session = request.getSession();
        if (pageNo==null){
            pageNo=1;
        }
        if (StringUtil.isNotEmpty(oper)&&"search".equals(oper)){
            pageNo = 1;

            if (StringUtil.isEmpty(keyword)){
                keyword="";
            }
            session.setAttribute("keyword",keyword);
        }else {
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj!=null){
                keyword=(String)keywordObj;
            }else {
                keyword="";
            }
        }

        //重新更新当前的页面值
        session.setAttribute("pageNo",pageNo);

        List<Fruit> fruitList = fruitService.getFruitList(keyword,pageNo);
        session.setAttribute("fruitList",fruitList);
        //总记录条数 和总页数
        int pageCount = fruitService.getPageCount(keyword);
        session.setAttribute("pageCount",pageCount);
        return "index";
    }

    private String add(String fname,Integer price,Integer fcount,String remark){
        fruitService.addFruit(new Fruit(0,fname,price,fcount,remark));
        return "redirect:fruit.do";
    }
    private String edit(Integer fid,HttpServletRequest request){
        if (fid!=null){
            Fruit fruit = fruitService.getFruitByFid(fid);
            request.setAttribute("fruit",fruit);
           // super.processTemplate("edit",request,response);
            return "edit";
        }
        return "error";
    }

    private String update(Integer fid,String fname,Integer price,Integer fcount,String remark){
        fruitService.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        return "redirect:fruit.do";
    }

    private String del(Integer fid)  {
        if (fid!=null){
            fruitService.delFruitByFid(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }
}

