package com.youxin.ymall.cache;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

public class RedisCacheBean {

	@Resource(name = "stringRedisTemplate")
	private StringRedisTemplate redisTemplate;

	public void hset(String key, String field, Object o) {
		Gson gson = new Gson();

		redisTemplate.boundHashOps(key).put(field, gson.toJson(o));
	}

	public String hget(String key, String field) {
		BoundHashOperations<String, String, String> obj = redisTemplate.boundHashOps(key);
		if (obj == null)
			return null;
		else
			return (String) obj.get(field);

	}

	public <T> T hget(String key, String field, Class<T> clazz) {
		String text = hget(key, field);
		Gson gson = new Gson();

		T result = gson.fromJson(text, clazz);
		;
		return result;
	}

	public void hdel(String key, String... field) {
		this.redisTemplate.boundHashOps(key).delete(field);

	}

	public void hdel(String key) {
		this.redisTemplate.delete(key);

	}
}
