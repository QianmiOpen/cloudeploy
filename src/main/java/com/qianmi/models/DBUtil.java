package com.qianmi.models;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.StringUtils;
import org.h2.jdbcx.JdbcConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库操作实例
 */
public class DBUtil {

    private final transient Logger logger = LoggerFactory.getLogger(getClass());

    private static DBUtil instance = null;

    private JdbcConnectionPool connectionPool = null;

    private DBUtil() throws IOException {
        final Properties properties = new Properties();
        String dbFile = System.getProperty("db.file");
        if (dbFile != null) {
            properties.load(new FileInputStream(new File(dbFile)));
        } else {
            properties.load(Thread.currentThread().getClass().getResourceAsStream("/db.properties"));
        }

        String url = properties.getProperty("jdbc.url");
        String initSql = System.getProperty("init.sql");

        logger.debug("jdbc.url:{}", url);
        logger.debug("init.sql:{}", initSql);

        if (initSql == null || !initSql.toLowerCase().equals("true")) {
            url = url.substring(0, url.toLowerCase().indexOf("init"));
        }
        logger.debug("convert.jdbc.url:{}", url);
        
        this.connectionPool = JdbcConnectionPool.create(
                url,
                properties.getProperty("jdbc.user"),
                properties.getProperty("jdbc.password"));
    }

    public static synchronized DBUtil getInstance() {
        if (instance == null) {
            try {
                instance = new DBUtil();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * H2内存数据库
     * @return 数据库链接,操作对象
     * @throws SQLException
     */
    public QueryRunner getRunner() {
        return new QueryRunner(connectionPool);
    }

    public static void main(String[] args) throws SQLException {
        SystemOSHelper systemOSHelper = new SystemOSHelper();
//        systemOSHelper.all().forEach(o -> System.out.println(o));

        SystemOS os = systemOSHelper.findOne(null, null, "7.0", "aliyun");
        System.out.println(os);
//
//        SystemTypeHelper systemTypeHelper = new SystemTypeHelper();
//        SystemType type = systemTypeHelper.findOne(1, 1, "aliyun");
//        System.out.println(type);
//
//        UserInstanceHelper uHelper = new UserInstanceHelper();
//        UserInstance ui = new UserInstance();
//        ui.setUser("zhangsan123");
//        ui.setInstanceId("fsdfsdf13323");
//        ui.setInstanceIp("123.902.22.222");
//        int result = uHelper.add(ui);
//        System.out.println("insert.result=" + result);
////        systemTypeHelper.all().forEach(t -> System.out.println(t));
//
//        RegionHelper regionHelper = new RegionHelper();
//        regionHelper.all().forEach(r -> System.out.println("r.id=" + r.getId()));
//        System.out.println(regionHelper.findOne("cn-qingdao", "a2liyun") == null);
    }

}
