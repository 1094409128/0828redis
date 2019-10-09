package com.aaa.zxz.redis.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: 0828redis
 * @Package: com.aaa.zxz.redis.config
 * @Author: zxz
 *          @Component:把RedisProperties作为spring的一个组件
 *           作用就是把redis所需要配置和连接信息封装进RedisProperties类中
 *          该组件为可拆卸的(面向组件的编程思想)
 *           配置该组件类的作用就是从application.properties文件中读取自定义的属性信息
 *           @ConfigurationProperties作用是通过setter方法从application.properties文件中获取数据rties类中
 *           当使用到该注解的时候必须要使用spring-boot-configuration-processor的jar包
 *           prefix = "spring.redis":作用就是选中在application.properties文件中所有以spring.redis开头的配置信息
 *           RedisProperties类中的所有属性必须要和application.properties文件中属性名进行对应(必须要一致)
 *           注意：在其他类调用这个类中的属性的时候，不能进行修改，只能读取数据
 * @CreateDate: 2019/8/28 19:21
 * @Version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
    /**
     *redis集群的节点(ip地址和端口号)
     */
    private String nodes;
    /**
     * redis的最大连接数
     */
    private String maxAttemts;
    /**
     * redis的最大超时时间
     */
    private String commandTimeout;
    /**
     * redis的失效时间
     */
    private String expire;

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getMaxAttemts() {
        return maxAttemts;
    }

    public void setMaxAttemts(String maxAttemts) {
        this.maxAttemts = maxAttemts;
    }

    public String getCommandTimeout() {
        return commandTimeout;
    }

    public void setCommandTimeout(String commandTimeout) {
        this.commandTimeout = commandTimeout;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }
}
