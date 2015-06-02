package com.qianmi.models;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created by li on 15/5/14.
 */
public class UserInstanceHelper extends BaseHelper {

    public UserInstanceHelper() {
        super("user_instance");
    }

    public UserInstance findByUser(String user) {
        logger.debug("find {} By user:{}", tableName, user);
        ResultSetHandler<UserInstance> h = new BeanHandler(UserInstance.class);
        UserInstance ui = null;
        try {
            ui = runner.query("SELECT * FROM " + tableName + " WHERE user=?", h, user);
        } catch (SQLException e) {
            logger.error("findByUser {} error:", tableName, e);
        }
        return ui;
    }

    public int add(UserInstance u) {
        logger.debug("add user:{}", u);
        try {
            return runner.update("INSERT INTO " + tableName + "(user, instanceId, instanceIp, platform) VALUES(?,?,?,?)",
                    u.getUser(), u.getInstanceId(), u.getInstanceIp(), u.getPlatform());
        } catch (SQLException e) {
            logger.error("insert {} error:", tableName, e);
        }
        return 0;
    }

}
