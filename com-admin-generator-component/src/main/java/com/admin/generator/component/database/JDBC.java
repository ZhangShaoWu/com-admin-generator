package com.admin.generator.component.database;

import com.admin.framework.component.utils.ListUtil;
import com.admin.framework.component.utils.NotNullVerifyUtil;
import com.admin.generator.model.bean.DatabaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ZSW
 * @date 2018/11/18
 */
public class JDBC {

    private Logger logger = LoggerFactory.getLogger(JDBC.class);

    private Connection connection;
    private String driverClass = "com.mysql.jdbc.Driver";

    private DatabaseEntity databaseEntity;
    public JDBC(DatabaseEntity databaseEntity) throws Exception{
        List<String> verify = NotNullVerifyUtil.verify(databaseEntity);
        if(!ListUtil.isEmpty(verify)){
            throw new RuntimeException(verify.get(0));
        }
        this.databaseEntity = databaseEntity;
    }

    private Connection getConn() throws Exception{
        logger.info("连接数据库");
        if(connection != null){
            return connection;
        }
        try {
            //遍历查询结果集
            //加载驱动程序
            Class.forName(driverClass);
            connection = DriverManager.getConnection(databaseEntity.getUrl(), databaseEntity.getUser(), databaseEntity.getPassword());
            return connection;
        }catch (SQLException e) {
            e.printStackTrace();
            logger.error("连接数据库失败");
            throw new RuntimeException("连接数据库失败");
        }
    }

    public List<Map> getData(String sql) throws Exception {
        Connection conn = getConn();
        if(conn == null){
            return null;
        }
        //2.创建statement类对象，用来执行SQL语句！！
        Statement statement = conn.createStatement();
        //3.ResultSet类，用来存放获取的结果集！！
        logger.info("执行sql为：" + sql);
        ResultSet rs = statement.executeQuery(sql);
        return rsToMap(rs);
    }

    /**
     * 将查询返回的结果集转换成Map
     * @param set
     * @return
     */
    private List<Map> rsToMap(ResultSet set){
        if(set == null){
            return null;
        }
        try {
            List<Map> result = new ArrayList<Map>();
            while (set.next()){
                Map m = new HashMap(16);

                ResultSetMetaData metaData = set.getMetaData();
                for(int x = 1 ; x <= metaData.getColumnCount(); x++){
                    String key = metaData.getColumnLabel(x);
                    String value = set.getString(x);
                    m.put(key.toLowerCase(),value);
                }
                result.add(m);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
}
