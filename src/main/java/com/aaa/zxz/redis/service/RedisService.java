package com.aaa.zxz.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

/**
 * @ProjectName: 0828redis
 * @Package: com.aaa.zxz.redis.service
 * @Author: zxz
 *          RedisService --> 需要调用JedisCluster(对redis数据库中进行增删改查)
 * @CreateDate: 2019/8/28 20:04
 * @Version: 1.0
 */
@Service
public class RedisService {

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * @author zxz
     * @description
     *      通过key获取redis中的数据
     * @param [key]
     * @date 2019/8/28
     * @return java.lang.String
     * @throws
     **/
    public String get(String key){
        return jedisCluster.get(key);
    }

    /**
     * @author zxz
     * @description
     *      向redis数据库中存入String类型的数据
     *      set的时候返回值是"OK"
     * @param [key, value]
     * @date 2019/8/28
     * @return java.lang.String
     * @throws
     **/
    public String set(String key,String value){
        return jedisCluster.set(key, value);
    }

    /**
     * @author zxz
     * @description
     *      通过key删除redis集群中的数据
     * @param [key]
     * @date 2019/8/28
     * @return java.lang.String
     * @throws
     **/
    public Long del(String key){
        return jedisCluster.del(key);
    }

    /**
     * @author zxz
     * @description
     *      通过key设置失效时间(单位是秒)
     * @param [key, seconds]
     * @date 2019/8/28
     * @return java.lang.String
     * @throws
     **/
    public Long expire(String key,Integer seconds){
        return jedisCluster.expire(key, seconds);
    }
}
