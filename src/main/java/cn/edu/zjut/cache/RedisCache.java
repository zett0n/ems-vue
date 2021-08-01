package cn.edu.zjut.cache;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.DigestUtils;

import cn.edu.zjut.utils.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zett0n
 * @date 2021/8/2 01:25
 */
@Slf4j
public class RedisCache implements Cache {
    private final String id;
    private RedisTemplate redisTemplate;

    // 必须存在的构造方法
    public RedisCache(String id) {
        log.debug("RedisCache id: {}", id);
        this.id = id;
    }

    private RedisTemplate getRedisTemplate() {
        if (this.redisTemplate == null) {
            // Cache的实现类由MyBatis管理，不能直接注解获得redisTemplate对象
            this.redisTemplate = (RedisTemplate)ApplicationContextUtils.getBean("redisTemplate");
            // 修改key序列化方案 String类型序列
            this.redisTemplate.setKeySerializer(new StringRedisSerializer());
            // 修改hash key序列化方案
            this.redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        }
        return this.redisTemplate;
    }

    private String getKeyToMD5(String key) {
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        log.debug("key: {}", key.toString());
        log.debug("value: {}", value);
        getRedisTemplate().opsForHash().put(this.id, getKeyToMD5(key.toString()), value);
    }

    @Override
    public Object getObject(Object key) {
        log.debug("key: {}", key.toString());
        return getRedisTemplate().opsForHash().get(this.id, getKeyToMD5(key.toString()));
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {
        log.debug("clear...");
        getRedisTemplate().delete(this.id);
    }

    @Override
    public int getSize() {
        return getRedisTemplate().opsForHash().size(this.id).intValue();
    }
}
