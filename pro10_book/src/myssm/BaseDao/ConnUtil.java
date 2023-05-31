package myssm.BaseDao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @create 2023- 05- 21- 10:59
 * @desc
 */
public class ConnUtil {
    private static  ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

//    public static  String DRIVER ;
//    public static  String URL;
//    public static  String USER ;
//    public static  String PWD  ;
    static Properties properties =new Properties();
    static {
        InputStream inputStream = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");

        try {
            properties.load(inputStream);
//            DRIVER  = properties.getProperty("jdbc.driver");
//            URL  = properties.getProperty("jdbc.url");
//            USER  = properties.getProperty("jdbc.user");
//            PWD  = properties.getProperty("jdbc.pwd");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection createConn(){
        try {
            DataSource druidDataSource = DruidDataSourceFactory.createDataSource(properties);
//            DruidDataSource druidDataSource = new DruidDataSource();
//            druidDataSource.setDriverClassName(DRIVER);
//            druidDataSource.setUrl(URL);
//            druidDataSource.setUsername(USER);
//            druidDataSource.setPassword(PWD);
            return druidDataSource.getConnection();

//            //1.加载驱动
//            Class.forName(DRIVER);
//            //2.通过驱动管理器获取连接对象
//            return DriverManager.getConnection(URL, USER, PWD);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
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
