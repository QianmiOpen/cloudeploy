package com.qianmi.controller;

import com.eclipsesource.json.JsonObject;
import com.qianmi.DeployException;
import com.qianmi.form.InstanceForm;
import com.qianmi.form.InstanceResult;
import com.qianmi.form.InstanceStatus;
import com.qianmi.models.UserInstance;
import com.qianmi.models.UserInstanceHelper;
import com.qianmi.proxy.InstanceProxy;
import com.qianmi.service.AliyunInstanceService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;

import static spark.Spark.*;

public class WebController {

    protected final transient Logger logger = LoggerFactory.getLogger(getClass());

    public WebController() {

        get("/", (request, response) -> "<h1>Welcome Cloudeply!</h1>");

        /**
         * 创建新主机
         * POST /host/new
         */
        post("/host/new", (request, response) -> {
            InstanceForm form = new InstanceForm();
            form.setUser(request.queryParams("user"));
            form.setPlatform(request.queryParams("platform"));
            form.setRegion(request.queryParams("region"));
            form.setOsName(StringUtils.defaultString(request.queryParams("osName"), "CentOS").toLowerCase());
            form.setOsBit(request.queryParams("osBit"));
            form.setOsVersion(request.queryParams("osVersion"));
            form.setCpu(request.queryParams("cpu"));
            form.setMemory(request.queryParams("memory"));
            form.setPassword(request.queryParams("password"));
            form.setInternetChargeType(request.queryParams("internetChargeType"));
            form.setInternetMaxBandwidthOut(request.queryParams("internetMaxBandwidthOut"));
            logger.debug("new host param:{}", form);
            JsonObject result2json = new JsonObject();
            // validate
            if (StringUtils.isEmpty(form.getUser())) {
                result2json.add("user", "is not empty");
            }
            if (form.getOsBit() != null && !StringUtils.isNumeric(form.getOsBit())) {
                result2json.add("osBit", "is not number");
            }
            if (form.getCpu() != null && !StringUtils.isNumeric(form.getCpu())) {
                result2json.add("cpu", "is not number");
            }
            if (form.getMemory() != null && !StringUtils.isNumeric(form.getMemory())) {
                result2json.add("memory", "is not number");
            }
            if (!result2json.isEmpty()) {
                response.status(HttpStatus.BAD_REQUEST_400);
                return result2json;
            }

            InstanceProxy instanceProxy = new InstanceProxy();
            try {
                InstanceResult r = instanceProxy.newInstance(form);
                // add user record
                UserInstance u = new UserInstance();
                u.setInstanceId(r.getInstanceId());
                u.setInstanceIp(r.getIp());
                u.setUser(form.getUser());
                u.setPlatform(form.getPlatform());
                UserInstanceHelper userInstanceHelper = new UserInstanceHelper();
                userInstanceHelper.add(u);
                return result2json.add("instanceId", r.getInstanceId()).add("ip", r.getIp());
            } catch (DeployException de) {
                response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
                return result2json.add("code", de.getCode()).add("message", de.getMessage());
            }

        });

        /**
         * 查询主机状态
         * GET /host/status
         * Param: instanceId
         *
         * json: {result: Running, Starting, Stopped}
         */
        get("/host/status", (request, response) -> {
            String instanceId = request.queryParams("instanceId");
            logger.debug("param instanceId:{}", instanceId);
            JsonObject result2json = new JsonObject();
            if (StringUtils.isEmpty(instanceId)) {
                response.status(HttpStatus.BAD_REQUEST_400);
                result2json.add("instanceId", "param is not empty");
                return result2json;
            }

            AliyunInstanceService instanceService = new AliyunInstanceService();
            try {
                InstanceStatus status = instanceService.describeInstanceAttribute(instanceId);

                return result2json.add("instanceId", status.getInstanceId())
                        .add("status", status.getStatus())
                        .add("publicIp", status.getPublicIp())
                        .add("innerIp", status.getInnerIp());
            } catch (DeployException de) {
                response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
                return result2json.add("code", de.getCode()).add("message", de.getMessage());
            }
        });

        /**
         * 执行ansible命令
         * POST /ansible/send
         * Content-Type:application/x-www-form-urlencoded
         * Param: command
         *
         * Result:
         * responseStatus == 200:成功, 400:参数错误, 500:系统错误
         * responseBody
         */
        post("/ansible/send", (request, response) -> {
            JsonObject result2json = new JsonObject();
            String command = request.queryParams("command");
            logger.debug("command param:{}", command);
            if (StringUtils.isEmpty(command)) {
                response.status(HttpStatus.BAD_REQUEST_400);
                return result2json.add("command", "param is not empty");
            }
            command = StringUtils.toEncodedString(Base64.decodeBase64(command), Charset.defaultCharset());
            logger.debug("command decode:{}", command);
            ProcessBuilder pb = new ProcessBuilder();
            pb.command("/bin/sh", "-c", command);
            try {
                Process p = pb.start();
                String result = IOUtils.toString(p.getErrorStream(), Charset.defaultCharset());
                logger.info("command process:\n{}", result);
                return result2json.add("result", result);
            } catch (IOException ioe) {
                logger.error("command execute error", ioe);
                response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
                return result2json.add("error", ioe.getMessage());
            }
        });

    }

}
