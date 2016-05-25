package com.douins.redis.service;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

import com.mango.core.common.util.GeneralProperties;
import com.mango.core.logger.SimpleLogger;
import com.mango.redis.exception.RedisExcepiton;
import com.mango.redis.interfaces.RedisService;
import com.mango.redis.util.Encode;

@Service
public class RedisCacheLocal implements RedisService {
	private String host = "";

	private int port = 6379;

	private String password = "";

	int dbNum = 0;

	private int timeout = 20000;

	private SimpleLogger logger = SimpleLogger.getLogger(RedisCacheLocal.class);

	private JedisPoolConfig poolConfig = new JedisPoolConfig();
	private JedisPool jedisPool;
	private volatile boolean isRun = Boolean.FALSE.booleanValue();

	public JedisPool getJedisPool() {
		return this.jedisPool;
	}

	public void publish(String channel, String message) {
		Jedis jedis = null;
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			jedis.publish(channel, message);
			if (this.logger.isDebugEnabled())
				this.logger.debug(" publish for channel :" + channel
						+ " message " + message);
		} catch (JedisConnectionException e) {
			this.logger.error(" publish JedisConnectionException:", e);
		} catch (JedisException e) {
			this.logger.error(" publish JedisException:", e);
		} finally {
			if (jedis != null)
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
		}
	}

	public void subScribe(JedisPubSub jedisPubSub, String[] channels) {
		Jedis jedis = null;
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			jedis.subscribe(jedisPubSub, channels);
			if (this.logger.isDebugEnabled())
				this.logger.debug(" subScribe for channel :" + channels);
		} catch (JedisConnectionException e) {
			this.logger.error(" subScribe JedisConnectionException:", e);
		} catch (JedisException e) {
			this.logger.error(" subScribe JedisException:", e);

		} finally {
			if (jedis != null)
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
		}
	}

	public boolean init(String setHost, String setPort, String setPassword) {
		this.host = setHost;
		this.port = Integer.parseInt(setPort);
		this.password = setPassword;
		try {
			String maxActive = GeneralProperties.getProperty("jedis.maxActive", "");  // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取
			String maxIdle = GeneralProperties.getProperty("jedis.maxIdle");	// pool中允许的最大空闲的 jedis 实例数
			String minIdle = GeneralProperties.getProperty("jedis.minIdle");	// 最小空闲的 jedis 实例数
			String maxWait = GeneralProperties.getProperty("jedis.maxWait");	// 引入一个 jedis 实例时，最大等待时间，超时则抛异常JedisConnectionException
			String minEvictableIdleTimeMillis = GeneralProperties
					.getProperty("jedis.minEvictableIdleTimeMillis");	// 表示一个对象至少停留在空闲状态的最短时间，然后才能被idle object evitor扫描并驱逐
			String timeBetweenEvictionRunsMillis = GeneralProperties
					.getProperty("jedis.timeBetweenEvictionRunsMillis"); // 表示idle object evitor两次扫描之间要sleep的毫秒数
			// this.poolConfig.maxActive =
			// Integer.parseInt(StringUtils.isBlank(maxActive) ? "1000" :
			// maxActive.trim());
			this.poolConfig.setMaxTotal(Integer.parseInt(StringUtils
					.isBlank(maxActive) ? "1000" : maxActive.trim()));
			this.poolConfig.setMaxWaitMillis(Integer.parseInt(StringUtils
					.isBlank(maxWait) ? "20000" : maxWait.trim()));
			this.poolConfig.setMaxIdle(Integer.parseInt(StringUtils
					.isBlank(maxIdle) ? "1000" : maxIdle.trim()));
			// this.poolConfig.minIdle =
			// Integer.parseInt(StringUtils.isBlank(minIdle) ? "10" :
			// minIdle.trim());
			this.poolConfig.setMinIdle(Integer.parseInt(StringUtils
					.isBlank(minIdle) ? "10" : minIdle.trim()));

			if (StringUtils.isNotBlank(minEvictableIdleTimeMillis))
				this.poolConfig.setMinEvictableIdleTimeMillis(Integer
						.parseInt(minEvictableIdleTimeMillis.trim()));
			if (StringUtils.isNotBlank(timeBetweenEvictionRunsMillis))
				this.poolConfig.setTimeBetweenEvictionRunsMillis(Integer
						.parseInt(timeBetweenEvictionRunsMillis.trim()));
			this.jedisPool = new JedisPool(this.poolConfig, this.host,
					this.port, this.timeout, this.password);

			return Boolean.TRUE.booleanValue();
		} catch (JedisConnectionException e) {
			this.logger.error(" JedisConnectionException :", e);
		} catch (NumberFormatException e) {
			this.logger.error(" NumberFormatException :", e);
		} catch (Exception e) {
			this.logger.error(" Exception :", e);
		}
		return Boolean.FALSE.booleanValue();
	}

	/**
	 * 选择DB
	 * @param dbNum DB的 index
	 * @return
	 */
	public boolean selectRedisDB(int dbNum) {
		Jedis jedis = null;
		start();
		try {
			if (dbNum < 0)
				return Boolean.FALSE.booleanValue();
			jedis = (Jedis) this.jedisPool.getResource();
			this.dbNum = dbNum;
			String statusCode = jedis.select(dbNum);	// 根据索引号选择数据库	
			if (statusCode.equalsIgnoreCase("ok"))
				return Boolean.TRUE.booleanValue();
			if (this.logger.isDebugEnabled())
				this.logger.debug(" selectRedisDB " + dbNum + " ");
		} catch (JedisConnectionException e) {
			this.logger.error(" selectRedisDB JedisConnectionException:", e);
		} catch (JedisException e) {
			this.logger.error(" selectRedisDB JedisException:", e);

		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);		// 释放 jedis 实例
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return Boolean.FALSE.booleanValue();
	}

	/**
	 * Set the string value as value of the key
	 */
	public boolean setStrValue(String key, String value) {
		Jedis jedis = null;
		try {
			boolean bool1;
			if (StringUtils.isBlank(key)) {
				boolean bool = Boolean.FALSE.booleanValue();
				bool1 = bool;
				return bool1;
			}
			start();
			jedis = (Jedis) this.jedisPool.getResource();
			String flag = jedis.set(key, value);	// 设置键-值 对
			if (flag.equalsIgnoreCase("OK")) {
				if (this.logger.isDebugEnabled())
					this.logger.debug(" setValue successful,key is : " + key);
				boolean bool = Boolean.TRUE.booleanValue();
				bool1 = bool;
				return bool1;
			}
		} catch (JedisConnectionException e) {
			this.logger.error(" setValue JedisConnectionException:", e);
		} catch (JedisException e) {
			this.logger.error(" setValue JedisException:", e);
		} finally {
			if (jedis != null)
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
		}

		return Boolean.FALSE.booleanValue();
	}

	public String getStrValue(String key) throws RedisExcepiton {
		Jedis jedis = null;
		try {
			if (StringUtils.isBlank(key)) {
				return null;
			}
			start();
			jedis = (Jedis) this.jedisPool.getResource();
			String str = jedis.get(key);
			String str1 = str;
			return str1;
		} catch (JedisConnectionException e) {
			throw new RedisExcepiton(" getStrValue  JedisException", e);
		} catch (JedisException e) {
			throw new RedisExcepiton("  getStrValue JedisException", e);
		} finally {
			if (jedis != null)
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
		}
	}

	public <T extends Serializable> T getValue(String key)
			throws RedisExcepiton {
		Serializable decode = null;
		Jedis jedis = null;
		try {
			if (StringUtils.isBlank(key)) {
				return null;
			}
			start();
			jedis = (Jedis) this.jedisPool.getResource();
			// byte[] byteKey = Encode.encode(key);
			byte[] byteKey = key.getBytes();
			if (jedis.exists(byteKey).booleanValue())
				decode = Encode.decode(jedis.get(byteKey));
			else
				return null;
		} catch (JedisConnectionException e) {
			throw new RedisExcepiton(
					" redis getValue JedisConnectionException", e);
		} catch (JedisException e) {
			throw new RedisExcepiton(" redis getValue JedisException", e);
		} finally {
			if (jedis != null)
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
		}

		return (T) decode;
	}

	public <T extends Serializable> boolean setValue(String key, T value) {
		Jedis jedis = null;
		try {
			boolean bool1;
			if (StringUtils.isBlank(key)) {
				boolean bool = Boolean.FALSE.booleanValue();
				bool1 = bool;
				return bool1;
			}
			start();
			jedis = (Jedis) this.jedisPool.getResource();
			// byte[] byteKey = Encode.encode(key);
			byte[] byteKey = key.getBytes();
			byte[] byteValue = Encode.encode(value);

			String flag = jedis.set(byteKey, byteValue);
			if (flag.equalsIgnoreCase("OK")) {
				if (this.logger.isDebugEnabled())
					this.logger.debug(" setValue successful,key is : " + key);
				boolean bool = Boolean.TRUE.booleanValue();
				bool1 = bool;
				return bool1;
			}
		} catch (JedisConnectionException e) {
			this.logger.error(" setValue JedisConnectionException for key:"
					+ key, e);
		} catch (JedisException e) {
			this.logger.error(" setValue JedisException for key:" + key, e);
		} catch (RedisExcepiton e) {
			this.logger.error(" setValue encode exception for key:" + key, e);
		} finally {
			if (jedis != null)
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
		}

		return Boolean.FALSE.booleanValue();
	}

	public <T extends Serializable> boolean setValue(String key, T value,
			int seconds) {
		Jedis jedis = null;
		try {
			boolean bool1;
			if (StringUtils.isBlank(key)) {
				boolean bool = Boolean.FALSE.booleanValue();
				bool1 = bool;
				return bool1;
			}
			start();
			jedis = (Jedis) this.jedisPool.getResource();
			// byte[] byteKey = Encode.encode(key);
			byte[] byteKey = key.getBytes();
			byte[] byteValue = Encode.encode(value);
			String flag = jedis.set(byteKey, byteValue);
			jedis.expire(byteKey, seconds);
			if (flag.equalsIgnoreCase("OK")) {
				if (this.logger.isDebugEnabled())
					this.logger.debug(" setValue successful,key is : " + key
							+ " and timeout is " + seconds);
				boolean bool = Boolean.TRUE.booleanValue();
				bool1 = bool;
				return bool1;
			}
		} catch (JedisConnectionException e) {
			this.logger.error(" setValue JedisConnectionException:", e);
		} catch (JedisException e) {
			this.logger.error(" setValue JedisException:", e);
		} catch (RedisExcepiton e) {
			this.logger.error(" setValue encode exception for key:" + key, e);
		} finally {
			if (jedis != null)
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
		}

		return Boolean.FALSE.booleanValue();
	}

	public boolean setKeyExpireTime(String key, int seconds) {
		Jedis jedis = null;
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			if ((StringUtils.isNotBlank(key)) && (seconds > 0)) {
				jedis.expire(key, seconds);
				boolean bool = Boolean.TRUE.booleanValue();
				boolean bool1 = bool;
				return bool1;
			}
		} catch (JedisConnectionException e) {
			this.logger.error(" setKeyExpireTime JedisConnectionException:", e);
		} catch (JedisException e) {
			this.logger.error(" setKeyExpireTime JedisException:", e);
		} finally {
			if (jedis != null)
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
		}
		return Boolean.FALSE.booleanValue();
	}

	public boolean setKeyExpireAtTime(String key, long unixTime) {
		Jedis jedis = null;
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			if ((StringUtils.isNotBlank(key)) && (unixTime > 0L)) {
				jedis.expireAt(key, unixTime);
				boolean bool = Boolean.TRUE.booleanValue();
				boolean bool1 = bool;
				return bool1;
			}
		} catch (JedisConnectionException e) {
			this.logger.error(" setKeyExpireTime JedisConnectionException:", e);
		} catch (JedisException e) {
			this.logger.error(" setKeyExpireTime JedisException:", e);
		} finally {
			if (jedis != null)
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
		}
		return Boolean.FALSE.booleanValue();
	}

	public long remove(String key) {
		long number = 0L;
		Jedis jedis = null;
		try {
			if (StringUtils.isBlank(key)) {
				return number;
			}
			start();
			jedis = (Jedis) this.jedisPool.getResource();
			byte[] byteKey = key.getBytes();
			number = jedis.del(new byte[][] { byteKey }).longValue();
		} catch (JedisConnectionException e) {
			this.logger.error(" remove JedisConnectionException:", e);
		} catch (JedisException e) {
			this.logger.error(" remove JedisException:", e);

		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return number;
	}

	public long getRemainingTimeByKey(String key) {
		long seconds = 0L;
		Jedis jedis = null;
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			byte[] byteKey = Encode.encode(key);
			if ((StringUtils.isNotBlank(key))
					&& (jedis.exists(byteKey).booleanValue()))
				seconds = jedis.ttl(key).longValue();
		} catch (JedisConnectionException e) {
			this.logger.error(
					" getRemainingTimeByKey JedisConnectionException:", e);
		} catch (JedisException e) {
			this.logger.error(" getRemainingTimeByKey JedisException:", e);
		} catch (RedisExcepiton e) {
			this.logger
					.error(" getRemainingTimeByKey encode exception for key:"
							+ key, e);
		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return seconds;
	}

	public boolean exists(String key) throws RedisExcepiton {
		Jedis jedis = null;
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			// byte[] byteKey = Encode.encode(key);
			byte[] byteKey = key.getBytes();
			if (jedis.exists(byteKey).booleanValue()) {
				boolean bool = Boolean.TRUE.booleanValue();
				boolean bool1 = bool;
				return bool1;
			}
		} catch (JedisConnectionException e) {
			throw new RedisExcepiton(" redis exists JedisConnectionException",
					e);
		} catch (JedisException e) {
			throw new RedisExcepiton(" redis exists JedisException", e);
		} finally {
			if (jedis != null)
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
		}

		return Boolean.FALSE.booleanValue();
	}

	public void increaseByKey(String key, String field) {
		Jedis jedis = null;
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();

			Long count = jedis.hincrBy(key, field, 1L);
			jedis.hset(key, field, String.valueOf(count));
		} catch (JedisConnectionException e) {
			this.logger.error(" increaseByKey JedisConnectionException:", e);
		} finally {
			if (jedis != null)
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
		}
	}

	public void delIncreaseByKey(String key, String field, Long value) {
		Jedis jedis = null;
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			Long count = jedis.hincrBy(
					key,
					field,
					value.longValue() > 0L ? 0L - value.longValue() : value
							.longValue());
			jedis.hset(key, field, String.valueOf(count));
		} catch (JedisConnectionException e) {
			this.logger.error(" delIncreaseByKey JedisConnectionException:", e);
		} finally {
			if (jedis != null)
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
		}
	}

	public Long getIncreaseByKey(String key, String field) {
		Jedis jedis = null;
		start();
		Long localLong = Long.valueOf(0L);
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			if (jedis.hexists(key, field).booleanValue())
				try {
					localLong = Long.valueOf(jedis.hget(key, field));

					return localLong;
				} catch (NumberFormatException e) {
					this.logger.error(
							" getIncreaseByKey NumberFormatException:", e);
					localLong = Long.valueOf(0L);
					Long localLong1 = localLong;
					return localLong1;
				}
		} catch (JedisConnectionException e) {
			this.logger.error(" getIncreaseByKey JedisConnectionException:", e);
		} finally {
			if (jedis != null)
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
		}

		return Long.valueOf(0L);
	}

	public Long sAdd(String key, String member) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			Long localLong = jedis.sadd(key, new String[] { member });
			Long localLong1 = localLong;
			return localLong1;
		} catch (Exception e) {
			this.logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Long sMove(String srckey, String dstkey, String member) {
		Jedis jedis = null;
		if ((StringUtils.isBlank(srckey)) || (StringUtils.isBlank(dstkey))) {
			return null;
		}
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			Long localLong = jedis.smove(srckey, dstkey, member);
			Long localLong1 = localLong;
			return localLong1;
		} catch (Exception e) {
			this.logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}

		return null;
	}

	public Long sCard(String key) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			Long localLong = jedis.scard(key);
			Long localLong1 = localLong;
			return localLong1;
		} catch (Exception e) {
			this.logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Long sRem(String key, String member) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			Long localLong = jedis.srem(key, new String[] { member });
			Long localLong1 = localLong;
			return localLong1;
		} catch (Exception e) {
			this.logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Set<String> sMembers(String key) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			Set localSet = jedis.smembers(key);
			Set localSet1 = localSet;
			return localSet1;
		} catch (Exception e) {
			this.logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Long hSet(String key, String field, String value) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			Long localLong = jedis.hset(key, field, value);
			Long localLong1 = localLong;
			return localLong1;
		} catch (Exception e) {
			this.logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Map<String, String> hgetAll(String key) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			Map localMap = jedis.hgetAll(key);
			Map localMap1 = localMap;
			return localMap1;
		} catch (Exception e) {
			this.logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Long hDel(String key, String field) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			Long localLong = jedis.hdel(key, new String[] { field });
			Long localLong1 = localLong;
			return localLong1;
		} catch (Exception e) {
			this.logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}

		return null;
	}

	public Long zAdd(String key, String score, String member) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			Long localLong = jedis.zadd(key, Double.valueOf(score)
					.doubleValue(), member);
			Long localLong1 = localLong;
			return localLong1;
		} catch (Exception e) {
			this.logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Long zRem(String key, String member) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			Long localLong = jedis.zrem(key, new String[] { member });
			Long localLong1 = localLong;
			return localLong1;
		} catch (Exception e) {
			this.logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Long zCard(String key) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			Long localLong = jedis.zcard(key);
			Long localLong1 = localLong;
			return localLong1;
		} catch (Exception e) {
			this.logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Set<String> zrange(String key, int start, int end) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			Set localSet = jedis.zrange(key, start, end);
			Set localSet1 = localSet;
			return localSet1;
		} catch (Exception e) {
			this.logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Set<String> zrevrange(String key, int start, int end) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			Set localSet = jedis.zrevrange(key, start, end);
			Set localSet1 = localSet;
			return localSet1;
		} catch (Exception e) {
			this.logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Long zRemrangeByRank(String key, int start, int end) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key)) {
			return null;
		}
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			Long localLong = jedis.zremrangeByRank(key, start, end);
			Long localLong1 = localLong;
			return localLong1;
		} catch (Exception e) {
			this.logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	public Long del(String[] keys) {
		Jedis jedis = null;
		start();
		try {
			jedis = (Jedis) this.jedisPool.getResource();
			Long localLong = jedis.del(keys);
			Long localLong1 = localLong;
			return localLong1;
		} catch (Exception e) {
			this.logger.error(" Exception", e);
		} finally {
			if (jedis != null) {
				try {
					this.jedisPool.returnResource(jedis);
				} catch (JedisException e) {
					this.logger.error(" returnResource JedisException:", e);
				}
			}
		}
		return null;
	}

	private void start() {
		if (!this.isRun) {
			init(GeneralProperties.getProperty("jedis_host", "127.0.0.1"),
					GeneralProperties.getProperty("jedis_port", "6379"),
					GeneralProperties.getProperty("jedis_password", "online"));
			this.isRun = (!this.isRun);
		}
	}
}