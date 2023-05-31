package myssm.BaseDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @create 2023- 05- 21- 10:59
 * @desc
 */
public class ConnUtil {
    private static  ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public static final String USER = "root";
    public static final String PWD = "123456" ;

    private static Connection createConn(){
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Connection getconn(){
        Connection conn = threadLocal.get();
        if (conn==null){
            conn = ConnUtil.createConn();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }

    public static  void CloseConn() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn==null){
            return;
        }
        if (!conn.isClosed()){
            conn.close();
            //threadLocal.set(null);  都可以
            threadLocal.remove();
        }
    }
}
