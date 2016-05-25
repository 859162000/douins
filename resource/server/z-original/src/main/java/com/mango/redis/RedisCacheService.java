package com.mango.redis;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.mango.fortune.util.ReadConfig;
import com.mango.redis.exception.RedisExcepiton;

@Service
public class RedisCacheService {
	@Autowired
	private RedisCacheLocal redisCache;
	public String SPLIT=":";
	public static String redisCacheDebug = ReadConfig.get("redisCacheDebug");

	private  SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	
	
	
	
	public <T extends Serializable> T getValue(String key) {
		if ("true".equals(redisCacheDebug)) {
			return null;
		} else {
			try {
				return redisCache.getValue(key);
			} catch (RedisExcepiton e) {
				logger.error("RedisCacheService",e);
				return null;
			}
			
		}
	}

	public <T extends Serializable> boolean setValue(String key, T value) {
		if ("true".equals(redisCacheDebug)) {
			return false;
		} else {
			return redisCache.setValue(key, value);
		}

	}

	public <T extends Serializable> boolean setValue(String key, T value, int seconds) {
		if ("true".equals(redisCacheDebug)) {
			return false;
		} else {
			return redisCache.setValue(key, value, seconds);
		}
	}

	public boolean setKeyExpireTime(String key, int seconds) {
		if ("true".equals(redisCacheDebug)) {
			return false;
		} else {
			return redisCache.setKeyExpireTime(key, seconds);
		}

	}

	public boolean setKeyExpireAtTime(String key, long unixTime) {
		if ("true".equals(redisCacheDebug)) {
			return false;
		} else {
			return redisCache.setKeyExpireAtTime(key, unixTime);
		}

	}

	public long remove(String key) {
		if ("true".equals(redisCacheDebug)) {
			return 0L;
		} else {
			return redisCache.remove(key);
		}

	}

	public boolean exists(String key)  {
		if ("true".equals(redisCacheDebug)) {
			return false;
		} else {			
			try {
				return redisCache.exists(key);
			} catch (RedisExcepiton e) {
				logger.error("RedisCacheService exists",e);
				return false;
			}
		}

	}

	public Long del(String[] keys) {
		if ("true".equals(redisCacheDebug)) {
			return null;
		} else {
			return redisCache.del(keys);
		}

	}

}