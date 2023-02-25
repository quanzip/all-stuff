//package com.example.backend.utils;
//
//import org.apache.log4j.Logger;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class RedisUtils {
//    //
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//
//package com.viettel.etc.xlibrary.core.constants;
//
//import java.util.HashSet;
//import java.util.Set;
//import org.apache.log4j.Logger;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//
//    public class RedisUtil {
//        private static final Logger LOGGER = Logger.getLogger(RedisUtil.class);
//        private static RedisUtil INSTANCE = null;
//        private JedisCluster jedisCluster;
//        private String redisAddress;
//        private int redisTimeout;
//
//        private RedisUtil(JedisCluster jedisCluster) {
//            this.jedisCluster = jedisCluster;
//        }
//
//        public RedisUtil(String redisAddress, int redisTimeout) {
//            this.redisAddress = redisAddress;
//            this.redisTimeout = redisTimeout;
//        }
//
//        public RedisUtil() {
//        }
//
//        public synchronized RedisUtil setup() {
//            if (INSTANCE == null) {
//                LOGGER.info("Start connect Redis: " + this.redisAddress);
//                Set<HostAndPort> hostAndPortNodes = new HashSet();
//                String[] hostAndPorts = this.redisAddress.split(",");
//                String[] var3 = hostAndPorts;
//                int var4 = hostAndPorts.length;
//
//                for(int var5 = 0; var5 < var4; ++var5) {
//                    String hostAndPort = var3[var5];
//                    String[] parts = hostAndPort.split(":");
//                    String host = parts[0];
//                    int port = Integer.parseInt(parts[1].trim());
//                    hostAndPortNodes.add(new HostAndPort(host, port));
//                }
//
//                INSTANCE = new RedisUtil(new JedisCluster(hostAndPortNodes, this.redisTimeout));
//            }
//
//            return INSTANCE;
//        }
//
//        public static synchronized RedisUtil getInstance() {
//            if (INSTANCE == null) {
//                INSTANCE = (new RedisUtil()).setup();
//            }
//
//            return INSTANCE;
//        }
//
//        public JedisCluster getRedis() {
//            return INSTANCE.jedisCluster;
//        }
//
//        public void setRedisAddress(String dress) {
//            this.redisAddress = dress;
//        }
//
//        public void setRedisTimeout(int timeout) {
//            this.redisTimeout = timeout;
//        }
//
//        public String getRedisAddress() {
//            return this.redisAddress;
//        }
//
//        public int getRedisTimeout() {
//            return this.redisTimeout;
//        }
//
//        public String get(String key) {
//            try {
//                JedisCluster jedis = this.getRedis();
//                return jedis.get(key);
//            } catch (Exception var3) {
//                LOGGER.error(var3.getMessage(), var3);
//                return null;
//            }
//        }
//
//        public boolean save(String key, String value) {
//            if (key == null) {
//                return false;
//            } else {
//                try {
//                    JedisCluster jedis = this.getRedis();
//                    jedis.set(key, value);
//                } catch (Exception var4) {
//                    LOGGER.error(var4.getMessage(), var4);
//                }
//
//                return true;
//            }
//        }
//
//        public Long incr(String key) {
//            if (key == null) {
//                return 0L;
//            } else {
//                try {
//                    JedisCluster jedis = this.getRedis();
//                    return jedis.exists(key) ? jedis.incr(key) : -1L;
//                } catch (Exception var3) {
//                    LOGGER.error(var3.getMessage(), var3);
//                    return 0L;
//                }
//            }
//        }
//
//        public Long incr(String key, Long increment) {
//            if (key == null) {
//                return 0L;
//            } else {
//                try {
//                    JedisCluster jedis = this.getRedis();
//                    return jedis.exists(key) ? jedis.incrBy(key, increment) : -1L;
//                } catch (Exception var4) {
//                    LOGGER.error(var4.getMessage(), var4);
//                    return 0L;
//                }
//            }
//        }
//
//        public Long decr(String key) {
//            if (key == null) {
//                return 0L;
//            } else {
//                try {
//                    JedisCluster jedis = this.getRedis();
//                    return jedis.exists(key) ? jedis.decr(key) : -1L;
//                } catch (Exception var3) {
//                    LOGGER.error(var3.getMessage(), var3);
//                    return 0L;
//                }
//            }
//        }
//
//        public Long decrBy(String key, Long decrement) {
//            if (key == null) {
//                return 0L;
//            } else {
//                try {
//                    JedisCluster jedis = this.getRedis();
//                    return jedis.exists(key) ? jedis.decrBy(key, decrement) : -1L;
//                } catch (Exception var4) {
//                    LOGGER.error(var4.getMessage(), var4);
//                    return 0L;
//                }
//            }
//        }
//
//        public boolean save(String key, String value, int expire) {
//            if (key == null) {
//                return false;
//            } else {
//                try {
//                    JedisCluster jedis = this.getRedis();
//                    jedis.set(key, value);
//                    jedis.expire(key, expire);
//                } catch (Exception var5) {
//                    LOGGER.error(var5.getMessage(), var5);
//                }
//
//                return true;
//            }
//        }
//
//        public boolean saveWithTimeExpire(String key, String value, long expireAt) {
//            if (key == null) {
//                return false;
//            } else {
//                try {
//                    JedisCluster jedis = this.getRedis();
//                    jedis.set(key, value);
//                    jedis.expireAt(key, expireAt);
//                } catch (Exception var6) {
//                    LOGGER.error(var6.getMessage(), var6);
//                }
//
//                return true;
//            }
//        }
//
//        public boolean removeObject(String key) {
//            if (key == null) {
//                return false;
//            } else {
//                try {
//                    JedisCluster jedis = this.getRedis();
//                    jedis.del(key);
//                    return true;
//                } catch (Exception var3) {
//                    LOGGER.error(var3.getMessage(), var3);
//                    return false;
//                }
//            }
//        }
//    }
//
//}
