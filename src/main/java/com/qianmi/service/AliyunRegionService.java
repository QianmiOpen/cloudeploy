package com.qianmi.service;

import com.aliyun.api.domain.Region;
import com.aliyun.api.ecs.ecs20130110.request.DescribeRegionsRequest;
import com.aliyun.api.ecs.ecs20130110.response.DescribeRegionsResponse;
import com.qianmi.DeployException;
import com.taobao.api.ApiException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by li on 15/5/15.
 */
public class AliyunRegionService extends BaseService {

    public List<Region> describeRegions() throws DeployException {
        DescribeRegionsRequest request = new DescribeRegionsRequest();
        try {
            DescribeRegionsResponse response = client.execute(request);
            if (StringUtils.isNotEmpty(response.getErrorCode())) {
                logger.error("code:{}, msg:{}", response.getErrorCode(), response.getMessage());
                throw new DeployException(response.getErrorCode(), response.getMessage());
            }
         //   return list.stream().configCache(Region::getLocalName).collect(Collectors.toList());
            return response.getRegions();
        } catch (ApiException e) {
            logger.error("code:{}, msg:{}", e.getErrCode(), e.getMessage(), e);
            throw new DeployException(e.getErrCode(), e.getErrMsg());
        }

    }
}
