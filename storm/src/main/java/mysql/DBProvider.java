package mysql;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author whp 18-7-4
 */
public class DBProvider {
    private static ComboPooledDataSource source ;
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://192.168.1.128:3306/domain?useUnicode=true&characterEncoding=UTF-8&useOldAliasMetadataBehavior=true";
    private static final String USER = "temp";
    private static final String PASSWORD = "databpsPass4you!";
    private static Connection connection;

    static{
        try {
            source = new ComboPooledDataSource();
            source.setDriverClass(DB_DRIVER);
            source.setJdbcUrl(DB_URL);
            source.setUser(USER);
            source.setPassword(PASSWORD);
            source.setInitialPoolSize(10);
            source.setMaxPoolSize(20);
            source.setMinPoolSize(5);
            source.setAcquireIncrement(1);
            source.setMaxIdleTime(3);
            source.setMaxStatements(3000);
            source.setCheckoutTimeout(2000);
        }  catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     */
    public Connection getConnection() throws SQLException {
        connection = source.getConnection();
        return connection;
    }


    //关闭操作
    public static void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closePreparedStatement(PreparedStatement ps){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
