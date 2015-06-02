package com.qianmi.service;

import com.aliyun.api.ecs.ecs20130110.request.AllocatePublicIpAddressRequest;
import com.aliyun.api.ecs.ecs20130110.response.AllocatePublicIpAddressResponse;
import com.qianmi.DeployException;
import com.taobao.api.ApiException;
import org.apache.commons.lang3.StringUtils;

/**
 * 公网ip
 */
public class AliyunEipService extends BaseService {

    /**
     * 给实例分配IP
     * @param instanceId 实例ID
     * @return 分配的IP
     * @throws DeployException
     */
    public String allocatePublicIpAddress(String instanceId) throws DeployException {
        logger.debug("allocate ip to instanceId:{}", instanceId);
        AllocatePublicIpAddressRequest request = new AllocatePublicIpAddressRequest();
        request.setInstanceId(instanceId);
        try {
            AllocatePublicIpAddressResponse response = client.execute(request);
            if (StringUtils.isNotEmpty(response.getErrorCode())) {
                logger.error("code:{}, msg:{}", response.getErrorCode(), response.getMessage());
                throw new DeployException(response.getErrorCode(), response.getMessage());
            }
            return response.getIpAddress();
        } catch (ApiException e) {
            logger.error("code:{}, msg:{}", e.getErrCode(), e.getMessage(), e);
            throw new DeployException(e.getErrCode(), e.getErrMsg());
        }
    }

}
