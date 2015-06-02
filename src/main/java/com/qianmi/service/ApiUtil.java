package com.qianmi.service;

import com.aliyun.api.AliyunClient;
import com.aliyun.api.DefaultAliyunClient;
import com.aliyun.api.ecs.ecs20140526.request.CreateInstanceRequest;
import com.qianmi.DeployException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * cloud api连接配置
 */
public class ApiUtil {

    private static ApiUtil instance = null;
    private static AliyunClient aliyunClient = null;
    private static final Map<String, String> configCache = new ConcurrentHashMap<>();

    private ApiUtil() throws IOException {
        final Properties properties = new Properties();
        String configFile = System.getProperty("config.file");
        if (configFile != null) {
            properties.load(new FileInputStream(new File(configFile)));
        } else {
            properties.load(Thread.currentThread().getClass().getResourceAsStream("/config.properties"));
        }
        // init config cache
        properties.stringPropertyNames().forEach(key -> configCache.put(key, properties.getProperty(key)));

        // aliyun client
        String serverUrl = configCache.get("aliyun.serverUrl");
        String accessKeyId = configCache.get("aliyun.accessKeyId");
        String accessKeySecret = configCache.get("aliyun.accessKeySecret");
        aliyunClient = new DefaultAliyunClient(serverUrl, accessKeyId, accessKeySecret);

        // ucloud client
    }

    public static synchronized ApiUtil getInstance() {
        if (instance == null) {
            try {
                instance = new ApiUtil();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public AliyunClient getAliyunClient() {
        return aliyunClient;
    }

    public Map<String, String> getConfigCache() {
        return configCache;
    }

    public static void main(String[] args) {

        AliyunInstanceService aliyunInstanceService = new AliyunInstanceService();
        CreateInstanceRequest request = new CreateInstanceRequest();
        request.setImageId("centos6u5_64_20G_aliaegis_20150130.vhd");
        request.setInstanceType("ecs.t1.small");
        request.setRegionId("cn-hangzhou");
        request.setSecurityGroupId("sg-237gsmw7r");
        request.setInternetChargeType("PayByTraffic"); // 按量付费
        request.setInternetMaxBandwidthOut(1L);

//        try {
//            String instanceId = aliyunInstanceService.createInstance(request);
//
//            // 分配公网IP
//            AliyunEipService eipService = new AliyunEipService();
//            String ip = eipService.allocatePublicIpAddress(instanceId);
//        } catch (DeployException e) {
//            e.printStackTrace();
//        }

        // 分配公网IP
//        AliyunEipService eipService = new AliyunEipService();
//        try {
//            String ip = eipService.allocatePublicIpAddress("");
//        } catch (DeployException e) {
//            e.printStackTrace();
//        }

        // 启动实例
        try {
            aliyunInstanceService.startInstance("i-23n9gwnle");
        } catch (DeployException e) {
            e.printStackTrace();
        }

//        try {
//            aliyunInstanceService.stopInstance("i-23k3edxjr");
//        } catch (DeployException e) {
//            // response error json
//        }

//        try {
//            InstanceStatus r = aliyunInstanceService.describeInstanceAttribute("i-23bnskcin");
//            System.out.println("r.id=" + r.getInstanceId() + ",status=" + r.getStatus());
//        } catch (DeployException e) {
//            e.printStackTrace();
//        }

//        AliyunRegionService regionService = new AliyunRegionService(client);
//        try {
//            regionService.describeRegions().forEach(r -> System.out.println("('"+r.getRegionId()+"','"+r.getLocalName()+"')"));
//        } catch (DeployException e) {
//            e.printStackTrace();
//        }

    }
}
