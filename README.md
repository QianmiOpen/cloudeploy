> Cloudeploy基于bugatti理念的极简云部署系统。

简介
---
Cluodeploy是为解决客户私有云部署与监控，应用维护等问题统一处理平台。使用Cloudeploy可以快速创建主流云实例，安装等各种简化发布工作。

特性
---
- 支持多平台创建实例
	- 阿里云
	- Ucloud
- 极简接口，仅有3个RESTful API
- 内嵌数据库
- 可配置化
- 小巧方便，仅8M大小的jar文件

安装
---
1. Oracle官方下载最新的[JDK8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
2. [版本发布页](https://github.com/QianmiOpen/cloudeploy/releases)下载最新的 **cloudeploy-1.0-RELEASE.jar**
3. 访问 **http://[hostname]:[port]/**在你的浏览器中，默认port:4567

默认是直接访问（没做任何限制），为了安全起见请配置iptable策略。

然后你用`java -jar cloudeploy-1.0-RELEASE.jar`即可启动应用。如果是首次启动请增加`-Dinit.sql=true`参数用于初始化数据库脚本，下面一些参数做更多自定义配置：
- -Dconfig.file=[默认配置文件]
- -Ddb.file=[数据库连接]
- -Dlogback.configurationFile=[logback配置文件]

Cloudeploy的升级只需替换cloudeploy.jar。所有的数据存储在./cloudeploy.mv.db，所以你想备份cloudepoy数据，拷贝一份该文件即可。

另外./cloudeploy.mv.db默认数据路径和初始化脚本是可以自定义的，在`-Ddb.file=[数据库连接]`中设置`jdbc.url=`参数值，
##### Database URLs
*Embedded*

    `jdbc:h2:~/test` 'test' in the user home directory
    `jdbc:h2:/data/test` 'test' in the directory /data
    `jdbc:h2:./test` in the current(!) working directory
更多数据库知识请参考[H2 Database官方文档](http://www.h2database.com/)。

访问数据库：http://127.0.0.1:8082/

数据库文件./cloudeploy.mv.db应该妥善保管

接口
---
**注意：**
- 返回内容为json格式，通过响应状态码判断是否成功
- 默认值参考相关表位于`/resources/script/init.sql`
- 所有请求编码为UTF-8

### 创建新主机
```http
POST  http://localhost:4567/host/new
```
参数                          | 意义             | 备注
:--------------------------- | :--------------- | :----------------
user                         | 客户标示          | 必填
platform                     | 接口平台          | 默认为aliyun，还支持UCloud
region                       | 实例创建所在区域   | 默认为cn-hangzhou，更多参考region表id字段
osName                       | 操作系统名称       | 默认为CentOS，更多参考system_os表name字段
osBit                        | 操作系统位数       | 默认为64，更多参考system_os表bit字段
osVersion                    | 操作系统版本       | 默认为6.5，更多参考system_os表version字段
cpu                          | cpu数量           | 默认为1，更多参考system_type表cpu字段
memory                       | 内存大小          | 默认为1，更多参考system_type表memory字段
password                     | 主机root密码      | 默认为pwd123
internetChargeType           | 网络类型          | 默认为流量计费PayByTraffic，还有固定带宽计费PayByBandwidth
internetMaxBandwidthOut      | 网络出口带宽       | 默认为1，即为1M

*成功返回: status = 200*
 ```json
{
	"instanceId":"xxx"      # 实例ID,
    "ip":"xxx"              # 实例IP
}
```
*参数错误：status = 400*
```json
{
	"field1": "error_msg",
    "field2": "error_msg"
    ...
}
```
*系统异常：status = 500*
```json
{
	"code": "xxx",
	"message": "xxx"
}
```
### 查询主机状态
```http
GET  http://localhost:4567/host/status
```
参数          | 意义          | 备注
:----------- | :------------ | :----------------
instanceId   | 实例(主机)ID   | 必填，创建新主机时返回

*成功返回: status = 200*，实例状态包括：运行中`Running` 启动中`Starting` 停止`Stopped`
 ```json
{
	"instanceId":"xxx",
    "status":"xxx"
}

```
*参数错误：status = 400*
```json
{
	"instanceId": "param is not empty"
}
```
*系统异常：status = 500*
```json
{
	"code": "xxx",
	"message": "xxx"
}
```
### 执行ansible命令
```http
POST  http://localhost:4567/ansible/send
Content-Type:application/x-www-form-urlencoded
```
参数          | 意义          | 备注
:----------- | :------------ | :----------------
command      | 命令行内容      | 必填，需要Base64.encode("command", "UTF-8")编码

*成功返回: status = 200*
 ```json
{
	"result":"xxx",
}
```
*参数错误：status = 400*
```json
{
	"command": "param is not empty"
}
```
*系统异常：status = 500*
```json
{
	"error": "xxx"
}
```
