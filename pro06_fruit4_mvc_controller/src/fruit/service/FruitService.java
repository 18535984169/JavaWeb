package fruit.service;

import fruit.Pojo.Fruit;

import java.util.List;

/**
 * @create 2023- 05- 19- 18:49
 * @desc
 */
public interface FruitService {
    //获取所有的库存列表
    List<Fruit> getFruitList(String keyword, Integer pageNo);

    //根据fid获取特定的水果库存信息
    Fruit getFruitByFid(Integer fid);

    //修改指定的库存记录
    void updateFruit(Fruit fruit);

    //根据ID删除指定的库存记录
    void delFruitByFid(Integer fid);

    //添加记录
    void addFruit(Fruit fruit);

    //查询库存总记录条数
    int getPageCount(String keyword);

}
