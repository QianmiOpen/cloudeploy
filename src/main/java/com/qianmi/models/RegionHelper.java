package com.qianmi.models;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by li on 15/5/15.
 */
public class RegionHelper extends BaseHelper {
    public RegionHelper() {
        super("region");
    }

    public List<Region> all() {
        ResultSetHandler<List<Region>> h = new BeanListHandler(Region.class);
        List<Region> regions = null;
        try {
            regions = runner.query("SELECT * FROM " + tableName, h);
        } catch (SQLException e) {
            logger.error("select all {} error:", tableName, e);
        }
        return regions;
    }

    public Region findOne(String id, String platform) {
        logger.debug("find {} By id:{}, platform:{}", tableName, id, platform);
        ResultSetHandler<Region> h = new BeanHandler(Region.class);
        Region region = null;
        try {
            region = runner.query("SELECT * FROM " + tableName + " WHERE id=? AND platform=?",
                    h, id, platform);
        } catch (SQLException e) {
            logger.error("find {} error:", tableName, e);
        }
        return region;
    }
}
