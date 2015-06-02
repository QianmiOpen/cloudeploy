package com.qianmi.proxy;

import com.aliyun.api.ecs.ecs20140526.request.CreateInstanceRequest;
import com.qianmi.Constants;
import com.qianmi.DeployException;
import com.qianmi.form.InstanceForm;
import com.qianmi.form.InstanceResult;
import com.qianmi.models.SystemOS;
import com.qianmi.models.SystemOSHelper;
import com.qianmi.models.SystemType;
import com.qianmi.models.SystemTypeHelper;
import com.qianmi.service.AliyunEipService;
import com.qianmi.service.AliyunInstanceService;
import com.qianmi.service.ApiUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实例代理
 */
public class InstanceProxy {

    private final transient Logger logger = LoggerFactory.getLogger(InstanceProxy.class);

    /**
     * 新建实例(初始化机器,分配ip,启动)
     * @return 实例ID,IP
     */
    public InstanceResult newInstance(InstanceForm instanceForm) throws DeployException {
        instanceForm.setPlatform(StringUtils.defaultString(instanceForm.getPlatform(),
                ApiUtil.getInstance().getConfigCache().get("platform.default")));

        if (StringUtils.equalsIgnoreCase(Constants.PLATFORM_ALIYUN, instanceForm.getPlatform())) {
            CreateInstanceRequest request = new CreateInstanceRequest();
            logger.info("platform:{}", Constants.PLATFORM_ALIYUN);

            // image
            SystemOS os = new SystemOSHelper().findOne(instanceForm.getOsName(), instanceForm.getOsBit(),
                    instanceForm.getOsVersion(), instanceForm.getPlatform());
            logger.debug("osName:{}, osBit:{}, osVersion:{}, platform:{}",
                    instanceForm.getOsName(), instanceForm.getOsBit(), instanceForm.getOsVersion(), instanceForm.getPlatform());
            if (os != null) {
                logger.debug("systemOs:{}", os);
                request.setImageId(os.getImage());
            }

            // instanceType
            if (StringUtils.isNumeric(instanceForm.getCpu()) && StringUtils.isNumeric(instanceForm.getMemory())) {
                logger.debug("cpu.number:{}, memory.number:{}", instanceForm.getCpu(), instanceForm.getMemory());
                SystemType type = new SystemTypeHelper().findOne(
                        Integer.parseInt(instanceForm.getCpu()), Integer.parseInt(instanceForm.getMemory()), instanceForm.getPlatform());
                if (type != null) {
                    logger.debug("systemType:{}", type);
                    request.setInstanceType(type.getCode());
                }
            }

            request.setRegionId(instanceForm.getRegion());
            request.setInternetChargeType(instanceForm.getInternetChargeType());
            request.setPassword(instanceForm.getPassword());
            if (StringUtils.isNumeric(instanceForm.getInternetMaxBandwidthOut())) {
                request.setInternetMaxBandwidthOut(Long.valueOf(instanceForm.getInternetMaxBandwidthOut()));
            }

            logger.debug("requestParam:{}", ReflectionToStringBuilder.toString(request));

            // 创建实例
            AliyunInstanceService instanceService = new AliyunInstanceService();
            String instanceId = instanceService.createInstance(request);
            logger.info("create instance({}) success", instanceId);

            // 分配公网IP
            AliyunEipService eipService = new AliyunEipService();
            String ip = eipService.allocatePublicIpAddress(instanceId);
            logger.info("allocate ip:{} to instance", ip);

            // 启动实例
            instanceService.startInstance(instanceId);
            logger.info("instance start...");

            return new InstanceResult(instanceId, ip);
        }
        // else if {  }
        return new InstanceResult(Constants.PLATFORM_UCLOUD, "暂未启动该平台api");
    }



}
