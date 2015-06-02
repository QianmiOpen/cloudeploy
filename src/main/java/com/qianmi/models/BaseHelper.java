package com.qianmi.models;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseHelper {
    protected final transient Logger logger = LoggerFactory.getLogger(getClass());

    protected QueryRunner runner = null;
    protected String tableName;

    public BaseHelper(String tableName) {
        this.tableName = tableName;
        this.runner = DBUtil.getInstance().getRunner();
    }

}
