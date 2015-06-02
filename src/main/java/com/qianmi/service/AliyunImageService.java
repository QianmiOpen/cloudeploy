package com.qianmi.service;

import com.aliyun.api.domain.Image;
import com.aliyun.api.ecs.ecs20130110.request.DescribeImagesRequest;
import com.aliyun.api.ecs.ecs20130110.response.DescribeImagesResponse;
import com.qianmi.DeployException;
import com.taobao.api.ApiException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by li on 15/5/14.
 */
public class AliyunImageService extends BaseService {

    /**
     * 查询可用镜像列表
     * @param regionId 区域ID
     * @throws DeployException
     */
    public List<Image> describeImages(String regionId) throws DeployException {
        logger.debug("list images By regionId:{}", regionId);
        DescribeImagesRequest request = new DescribeImagesRequest();
        request.setRegionId(regionId);
        request.setPageSize(50L);
        try {
            DescribeImagesResponse response = client.execute(request);
            if (StringUtils.isNotEmpty(response.getErrorCode())) {
                logger.error("code:{}, msg:{}", response.getErrorCode(), response.getMessage());
                throw new DeployException(response.getErrorCode(), response.getMessage());
            }
            return response.getImages();
        } catch (ApiException e) {
            logger.error("code:{}, msg:{}", e.getErrCode(), e.getMessage(), e);
            throw new DeployException(e.getErrCode(), e.getErrMsg());
        }
    }

}
