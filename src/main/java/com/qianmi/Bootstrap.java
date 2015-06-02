package com.qianmi;

import com.qianmi.controller.WebController;
import com.qianmi.models.DBUtil;
import com.qianmi.service.ApiUtil;
import org.h2.tools.Server;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 应用入口
 */
public class Bootstrap {

    public static void main(String[] args) throws NullPointerException, IOException, SQLException {

        // db console
        Server.createWebServer().start();

        // web
        new WebController();

        ApiUtil.getInstance();
        DBUtil.getInstance();
    }

}
