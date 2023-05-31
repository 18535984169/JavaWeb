package Dao.impl;

import Dao.FruitDAO;
import Pojo.Fruit;
import myssm.BaseDao.BaseDAO;

import java.util.List;

/**
 * @create 2023- 05- 17- 11:45
 * @desc FruitDAO 实现类
 */
    public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitList(String keyword,Integer pageNo) {
        return super.executeQuery("select * from t_fruit where fname like ? or remark like ? limit ? ,5","%"+keyword+"%","%"+keyword+"%",(pageNo-1)*5);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return super.load("Select * from t_fruit where fid =?",fid);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname=?,price=?,fcount=?,remark=? where fid = ?";
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
    }

    @Override
    public void delFruitByFid(Integer fid) {
        super.executeUpdate("delete from t_fruit where fid = ?",fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values (0,?,?,?,?)";
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());
    }

    @Override
    public int getcount(String keyword) {
        return ((Long)super.executeComplexQuery("select count(*) from t_fruit  where fname like ? or remark like ? ","%"+keyword+"%","%"+keyword+"%")[0]).intValue();
    }
}
