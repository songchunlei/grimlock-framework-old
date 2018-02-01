package org.grimlock.redis.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * jedis缓存工具类,使用redisTemlate处理
 */
@Component
public class RedisUtil {

	// spring 对redis操作的封装，使用了模板模式
	@Resource
	private RedisTemplate<String, Object> template;
	

	@SuppressWarnings("unchecked")
	// 从redis中获取list数据
	public List<Object> getCacheList(String cacheKey) {
		@SuppressWarnings("rawtypes")
		BoundListOperations bound = template.boundListOps(cacheKey);
		Long size = bound.size();
		return bound.range(0, size);
	}

	@SuppressWarnings("unchecked")
	// 从redis中更新list数据
	public void updateCacheList(String cacheKey, List<Object> dataList) {
		template.delete(cacheKey);
		@SuppressWarnings("rawtypes")
		BoundListOperations bound = template.boundListOps(cacheKey);
		bound.rightPushAll(dataList.toArray());
	}

	@SuppressWarnings("unchecked")
	// 从redis中获取map数据
	public Map<String, Object> getCacheMap(String cacheKey) {
		@SuppressWarnings("rawtypes")
		BoundHashOperations bound = template.boundHashOps(cacheKey);
		return bound.entries();
	}

	@SuppressWarnings("unchecked")
	// 从redis中删除缓存数据
	public void clearCache(String cacheKey) {
		template.delete(cacheKey);
	}

	@SuppressWarnings("unchecked")
	// 从redis中获取map中的某个键值数据
	public Object getDataFromCacheMap(String cacheKey, Object key) {
		@SuppressWarnings("rawtypes")
		BoundHashOperations bound = template.boundHashOps(cacheKey);
		return bound.get(key);
	}

	@SuppressWarnings("unchecked")
	// 从redis中新增map键值数据
	public void putDataToCachemMap(String cacheKey, Object key, Object value) {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		BoundHashOperations bound = template.boundHashOps(cacheKey);
		bound.put(key, value);

	}

	// 在redis设置值
	public void putDataToCache(String key, Object value, Long expireSecond) {
		BoundValueOperations<String, Object> bvo = template.boundValueOps(key);
		bvo.set(value);
		//如果设置了过期时间大于0秒，则设计过期
		if(expireSecond > 0L){
			bvo.expire(expireSecond, TimeUnit.SECONDS);//20秒后过期
		}
	}
	// 在redis中获取某个值
	public Object getDataFromCache(String key) {
		BoundValueOperations<String, Object> bvo = template.boundValueOps(key);
		return bvo.get();
	}
	// 在redis中获取某个值
	public boolean existsKey(String key) {
		return template.hasKey(key);
	}

	// 应用场景：在redis设自增
	public Long increteData(String key,Long value) {
		BoundValueOperations<String, Object> bvo = template.boundValueOps(key);
		return bvo.increment(value);
	}

	

	// 应用场景在redis设置值
	/**
	 * list列表类型:先进后出栈形式,单个值插入
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public void lpush(String key, String[] values) {
		template.boundListOps(key).leftPushAll(values);
	}
	/**
	 * list列表类型:先进后出栈形式,单个值插入
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Object> lrange(String key, int start, int end) {
		return template.boundListOps(key).range(start, end);
	}

	/**
	 * hash类型:可同时对key设置多个值，hset只能一次设置一个
	 * 
	 * @param key
	 * @param map
	 * @return
	 */
	public void hmset(final String key, final Map<String, String> map) {

		BoundHashOperations<String, String, String> ops = template.boundHashOps(key);
		ops.putAll(map);
	}

	/**
	 * hash类型:返回 key 指定的哈希集中指定多个字段的值。
	 * 
	 * @param key
	 * @return
	 */
	public Map<String,String> hmget(String key) {
		BoundHashOperations<String, String, String> ops = template.boundHashOps(key);
		return ops.entries();
	}
	public String hmgetByfields(String key, String fields) {
		BoundHashOperations<String, String, String> ops = template.boundHashOps(key);
		return ops.get(fields);
	}
	
	
}
