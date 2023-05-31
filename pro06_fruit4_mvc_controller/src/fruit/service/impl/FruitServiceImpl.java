package fruit.service.impl;

import fruit.Dao.FruitDAO;
import fruit.Pojo.Fruit;
import fruit.service.FruitService;
import myssm.BaseDao.ConnUtil;

import java.util.List;

/**
 * @create 2023- 05- 19- 18:49
 * @desc
 */
public class FruitServiceImpl implements FruitService {
    private FruitDAO fruitDAO = null;

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        System.out.println("getFruitList:"+ConnUtil.getconn());
        return fruitDAO.getFruitList(keyword,pageNo);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }

    @Override
    public void delFruitByFid(Integer fid) {
        fruitDAO.delFruitByFid(fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    @Override
    public int getPageCount(String keyword) {
        System.out.println("getPageCount:"+ConnUtil.getconn());
        int getcount = fruitDAO.getPageCount(keyword);
        int pageCount = (getcount+5-1)/5;
        return  pageCount;
    }
}
