# 工厂方法模式的使用场景：

用于实现python服务无法处理或在Java侧更好处理的服务治理相关功能 
## 已实现的服务治理功能： 
> 限流(com.jdd.asp.govern.controller.GatewayController)  

## 对应Docker镜像
> dockerhub.jd.com/algorithm-service-platform/gateway-service  

## 构建镜像
> 先通过mvn package将web项目打包  
> 将打包后的gateway-service目录（放在了target目录中）放到与Dockerfile同级的目录中  
> 将要打包的所有文件放到测试环境的172.25.65.83服务器任意目录下，这台机器安装有docker环境  
> cd到Dockerfile所在目录  
docker build -t gateway-service:2.1.6 .  
docker tag gateway-service:2.1.6 dockerhub.jd.com/algorithm-service-platform/gateway-service:2.1.6  
docker push dockerhub.jd.com/algorithm-service-platform/gateway-service:2.1.6 

*注：上述命令中的版本号只是个示例，使用时务必替换成实际版本号*
