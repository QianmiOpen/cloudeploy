package com.qianmi.models;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by li on 15/5/14.
 */
public class SystemOSHelper extends BaseHelper {

    public SystemOSHelper() {
        super("system_os");
    }

    public List<SystemOS> all() {
        ResultSetHandler<List<SystemOS>> h = new BeanListHandler(SystemOS.class);
        List<SystemOS> oses = null;
        try {
            oses = runner.query("SELECT * FROM " + tableName, h);
        } catch (SQLException e) {
            logger.error("select all {} error:", tableName, e);
        }
        return oses;
    }

    public SystemOS findOne(String name, String bit, String version, String platform) {
        logger.debug("find {} By name:{}, bit:{}, version:{}, platform:{}", tableName, name, bit, version, platform);
        ResultSetHandler<SystemOS> h = new BeanHandler(SystemOS.class);
        SystemOS os = null;
        try {
            os = runner.query(
                    "SELECT * FROM " + tableName + " WHERE name=? AND bit=? AND version=? AND platform=?",
                    h, name, bit, version, platform);
        } catch (SQLException e) {
            logger.error("find {} error:", tableName, e);
        }
        return os;
    }

}
