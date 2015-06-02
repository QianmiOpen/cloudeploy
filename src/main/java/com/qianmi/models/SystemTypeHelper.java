package com.qianmi.models;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by li on 15/5/14.
 */
public class SystemTypeHelper extends BaseHelper {

    public SystemTypeHelper() {
        super("system_type");
    }

    public List<SystemType> all() {
        ResultSetHandler<List<SystemType>> hos = new BeanListHandler(SystemType.class);
        List<SystemType> types = null;
        try {
            types = runner.query("SELECT * FROM " + tableName, hos);
        } catch (SQLException e) {
            logger.error("select all {} error:", tableName, e);
        }
        return types;
    }

    public SystemType findOne(int cpu, int memory, String platform) {
        logger.debug("find {} By cpu:{}, memory:{}, platform:{}", tableName, cpu, memory, platform);
        ResultSetHandler<SystemType> h = new BeanHandler(SystemType.class);
        SystemType type = null;
        try {
            type = runner.query(
                    "SELECT * FROM " + tableName + " WHERE cpu=? AND memory=? AND platform=?",
                    h, cpu, memory, platform);
        } catch (SQLException e) {
            logger.error("find {} error:", tableName, e);
        }
        return type;
    }

}
