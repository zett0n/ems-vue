package cn.edu.zjut;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author zett0n
 * @date 2021/8/2 01:23
 */
public class RedisTest extends BasicTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        this.stringRedisTemplate.opsForValue().set("name", "choushao");
    }
}
