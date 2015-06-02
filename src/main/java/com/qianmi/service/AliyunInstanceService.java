package com.qianmi.service;

import com.aliyun.api.ecs.ecs20130110.request.DescribeInstanceAttributeRequest;
import com.aliyun.api.ecs.ecs20130110.request.StartInstanceRequest;
import com.aliyun.api.ecs.ecs20130110.request.StopInstanceRequest;
import com.aliyun.api.ecs.ecs20130110.response.DescribeInstanceAttributeResponse;
import com.aliyun.api.ecs.ecs20130110.response.StartInstanceResponse;
import com.aliyun.api.ecs.ecs20130110.response.StopInstanceResponse;
import com.aliyun.api.ecs.ecs20140526.request.CreateInstanceRequest;
import com.aliyun.api.ecs.ecs20140526.response.CreateInstanceResponse;
import com.qianmi.DeployException;
import com.qianmi.form.InstanceStatus;
import com.taobao.api.ApiException;
import org.apache.commons.lang3.StringUtils;

/**
 * 实例
 */
public class AliyunInstanceService extends BaseService {

    /**
     * 创建实例
     * @return 实例ID
     */
    public String createInstance(CreateInstanceRequest request) throws DeployException {
        if (StringUtils.isEmpty(request.getImageId())) {
            request.setImageId(ApiUtil.getInstance().getConfigCache().get("aliyun.instance.ImageId"));
        }
        if (StringUtils.isEmpty(request.getInstanceType())) {
            request.setInstanceType(ApiUtil.getInstance().getConfigCache().get("aliyun.instance.InstanceType"));
        }
        if (StringUtils.isEmpty(request.getRegionId())) {
            request.setRegionId(ApiUtil.getInstance().getConfigCache().get("aliyun.instance.RegionId"));
        }
        if (StringUtils.isEmpty(request.getSecurityGroupId())) {
            request.setSecurityGroupId(ApiUtil.getInstance().getConfigCache().get("aliyun.instance.SecurityGroupId"));
        }
        if (StringUtils.isEmpty(request.getInternetChargeType())) {
            request.setInternetChargeType(ApiUtil.getInstance().getConfigCache().get("aliyun.instance.InternetChargeType")); // 按量付费
        }
        if (request.getInternetMaxBandwidthOut() == null) {
            request.setInternetMaxBandwidthOut(Long.parseLong(ApiUtil.getInstance().getConfigCache().get("aliyun.instance.InternetMaxBandwidthOut")));
        }
        if (StringUtils.isEmpty(request.getPassword())) {
            request.setPassword(ApiUtil.getInstance().getConfigCache().get("aliyun.instance.password"));
        }
        try {
            CreateInstanceResponse response = client.execute(request);
            if (StringUtils.isNotEmpty(response.getErrorCode())) {
                logger.error("code:{}, msg:{}", response.getErrorCode(), response.getMessage());
                throw new DeployException(response.getErrorCode(), response.getMessage());
            }
            return response.getInstanceId();
        } catch (ApiException e) {
            logger.error("code:{}, msg:{}", e.getErrCode(), e.getMessage(), e);
            throw new DeployException(e.getErrCode(), e.getErrMsg());
        }
    }

    /**
     * 启动实例
     * @param instanceId 实例ID
     * @throws DeployException
     */
    public void startInstance(String instanceId) throws DeployException {
        StartInstanceRequest request = new StartInstanceRequest();
        request.setInstanceId(instanceId);
        try {
            StartInstanceResponse response = client.execute(request);
            if (StringUtils.isNotEmpty(response.getErrorCode())) {
                logger.error("code:{}, msg:{}", response.getErrorCode(), response.getMessage());
                throw new DeployException(response.getErrorCode(), response.getMessage());
            }
        } catch (ApiException e) {
            logger.error("code:{}, msg:{}", e.getErrCode(), e.getMessage(), e);
            throw new DeployException(e.getErrCode(), e.getErrMsg());
        }
    }

    /**
     * 停止实例
     * @param instanceId 实例ID
     * @throws DeployException
     */
    public void stopInstance(String instanceId) throws DeployException {
        StopInstanceRequest request = new StopInstanceRequest();
        request.setInstanceId(instanceId);
        try {
            StopInstanceResponse response = client.execute(request);
            if (StringUtils.isNotEmpty(response.getErrorCode())) {
                logger.error("code:{}, msg:{}", response.getErrorCode(), response.getMessage());
                throw new DeployException(response.getErrorCode(), response.getMessage());
            }
        } catch (ApiException e) {
            logger.error("code:{}, msg:{}", e.getErrCode(), e.getMessage(), e);
            throw new DeployException(e.getErrCode(), e.getErrMsg());
        }
    }

    /**
     * 查询实例状态
     * @param instanceId 实例ID
     * @throws DeployException
     */
    public InstanceStatus describeInstanceAttribute(String instanceId) throws DeployException {
        logger.debug("find instanceId:{} status", instanceId);
        DescribeInstanceAttributeRequest request = new DescribeInstanceAttributeRequest();
        request.setInstanceId(instanceId);
        try {
            DescribeInstanceAttributeResponse response = client.execute(request);
            if (StringUtils.isNotEmpty(response.getErrorCode())) {
                logger.error("code:{}, msg:{}", response.getErrorCode(), response.getMessage());
                throw new DeployException(response.getErrorCode(), response.getMessage());
            }

            Object publicIp = response.getPublicIpAddress().size() > 0 ? response.getPublicIpAddress().get(0) : "";
            Object innerIp = response.getInnerIpAddress().size() > 0 ? response.getInnerIpAddress().get(0) : "";
            return new InstanceStatus(response.getInstanceId(), response.getStatus(), (String)publicIp, (String)innerIp);
        } catch (ApiException e) {
            logger.error("code:{}, msg:{}", e.getErrCode(), e.getMessage(), e);
            throw new DeployException(e.getErrCode(), e.getErrMsg());
        }
    }

}
