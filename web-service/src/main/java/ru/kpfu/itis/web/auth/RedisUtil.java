package ru.kpfu.itis.web.auth;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public enum RedisUtil {
    INSTANCE;

    private final JedisPool pool;

    RedisUtil() {
        pool = new JedisPool(new JedisPoolConfig(), "localhost");
    }
//    adds the given value to the set.
    public void sadd(String key, String value) {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.sadd(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
//    removes the given value from the set.
    public void srem(String key, String value) {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            System.out.println(pool.getResource());
            System.out.println("key = " + key +" , value = " + value);
            jedis.srem(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
//    tests if the given value is in the set. It returns 1 if the value is there and 0 if it is not.
    public boolean sismember(String key, String value) {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.sismember(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
