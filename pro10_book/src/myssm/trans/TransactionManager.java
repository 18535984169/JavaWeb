package myssm.trans;

import myssm.BaseDao.ConnUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @create 2023- 05- 21- 10:53
 * @desc
 */
public class TransactionManager {

    //开启事务
    public static void beginTrans() throws SQLException {
        ConnUtil.getconn().setAutoCommit(false);
    }
    //提交事务
    public static void commit() throws SQLException {
        Connection conn = ConnUtil.getconn();
        conn.commit();
        ConnUtil.CloseConn();
    }
    //回滚事务
    public static void rollback() throws SQLException {
        Connection conn = ConnUtil.getconn();
        conn.rollback();
        ConnUtil.CloseConn();
    }
}
