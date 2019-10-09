package com.aaa.zxz.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @ProjectName: 0828redis
 * @Package: com.aaa.zxz.redis.config
 * @Author: zxz
 *          通过@SpringBootApplication或@Configuration注解把RedisConfiguration类标识为springboot的配置类
 *          使用Jedis的jar包操作redis集群数据库
 *  *          1.Redis:标识了redis是单节点模式(只有一台redis)
 *  *          2.RedisCluster:标识了redis的集群模式
 * @CreateDate: 2019/8/28 19:20
 * @Version: 1.0
 */
@SpringBootApplication
public class RedisConfiguration {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public JedisCluster getJedisCluster(){
        //1、获取到application.properties中配置的redis集群的端口号和ip
        String nodes = redisProperties.getNodes();
        //2、使用split进行分割nodes(以","分割)
        String[] nodesArray = nodes.split(",");
        //3.创建一个Set集合，以HostandPort对象作为泛型
        Set<HostAndPort> hostAndPortSet = new HashSet<HostAndPort>();
        //4、遍历nodesArray
        for (String node : nodesArray) {
            //5.使用split进行分割node(以":"为分隔符)
            String[] hostAndprotArray = node.split(":");
            //6、创建HostAndPort对象
            HostAndPort hostAndPort = new HostAndPort(hostAndprotArray[0],
                    Integer.parseInt(hostAndprotArray[1]));
            //7、把每一个HostAndPort对象装进set集合中
            hostAndPortSet.add(hostAndPort);
        }

        //8、返回JedisCluster对象
        return new JedisCluster(hostAndPortSet, Integer.parseInt(redisProperties.getCommandTimeout()),
                Integer.parseInt(redisProperties.getMaxAttemts()));
    }
}
