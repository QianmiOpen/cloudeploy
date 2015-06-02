package com.qianmi.service;

import com.aliyun.api.AliyunClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseService {
    protected final transient Logger logger = LoggerFactory.getLogger(getClass());

    protected AliyunClient client;

    public BaseService() {
        this.client = ApiUtil.getInstance().getAliyunClient();
    }

}
