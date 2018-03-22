package com.youxin.ymall.cache;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CacheAspect {
	@Resource
	RedisCacheBean redis;
	@Value("${rediscache}")
	private boolean enableCache;

	@Around("@annotation(com.youxin.ymall.cache.Cacheable)")
	public Object cache(ProceedingJoinPoint pjp) throws Throwable {
		if (!enableCache) {
			return pjp.proceed();
		}
		Method method = getMethod(pjp);
		Cacheable cacheable = method.getAnnotation(com.youxin.ymall.cache.Cacheable.class);
		String fieldKey = parseKey(cacheable.fieldKey(), method, pjp.getArgs());
		// 获取方法的返回类型,让缓存可以返回正确的类型
		Object result = null;
		Class returnType = ((MethodSignature) pjp.getSignature()).getReturnType();

		// 使用redis 的hash进行存取，易于管理
		if (fieldKey == null) {
			return null;
		}
		result = redis.hget(cacheable.key(), fieldKey, returnType);

		if (result == null) {
			result = pjp.proceed();
			if (result != null)
				redis.hset(cacheable.key(), fieldKey, result);
		}
		return result;
	}

	@Around(value = "@annotation(com.youxin.ymall.cache.CacheEvict)")
	public Object evict(ProceedingJoinPoint pjp) throws Throwable {
		// 和cache类似，使用Jedis.hdel()删除缓存即可...
		if (!enableCache) {
			return pjp.proceed();
		}
		Method method = getMethod(pjp);
		CacheEvict cacheevict = method.getAnnotation(com.youxin.ymall.cache.CacheEvict.class);
		String fieldKey = parseKey(cacheevict.fieldKey(), method, pjp.getArgs());
		// 获取方法的返回类型,让缓存可以返回正确的类型
		Object result = null;
		Class returnType = ((MethodSignature) pjp.getSignature()).getReturnType();

		// 使用redis 的hash进行存取，易于管理
		String key = cacheevict.key();
		redis.hdel(key, fieldKey);
		result = pjp.proceed();
		return result;
	}

	@Around(value = "@annotation(com.youxin.ymall.cache.CachePostDel)")
	public Object postdel(ProceedingJoinPoint pjp) throws Throwable {
		// 和cache类似，使用Jedis.hdel()删除缓存即可...
		if (!enableCache) {
			return pjp.proceed();
		}
		Method method = getMethod(pjp);
		CachePostDel cacheevict = method.getAnnotation(com.youxin.ymall.cache.CachePostDel.class);
		String fieldKey = parseKey(cacheevict.fieldKey(), method, pjp.getArgs());
		// 获取方法的返回类型,让缓存可以返回正确的类型
		Object result = null;
		Class returnType = ((MethodSignature) pjp.getSignature()).getReturnType();

		// 使用redis 的hash进行存取，易于管理

		result = pjp.proceed();
		String key = cacheevict.key();
		redis.hdel(key, fieldKey);
		return result;
	}

	@Around(value = "@annotation(com.youxin.ymall.cache.CachePut)")
	public Object put(ProceedingJoinPoint pjp) throws Throwable {
		// 和cache类似，使用Jedis.hdel()删除缓存即可...
		if (!enableCache) {
			return pjp.proceed();
		}
		Method method = getMethod(pjp);
		CachePut cacheput = method.getAnnotation(com.youxin.ymall.cache.CachePut.class);
		String fieldKey = parseKey(cacheput.fieldKey(), method, pjp.getArgs());
		// 获取方法的返回类型,让缓存可以返回正确的类型
		Object result = null;
		Class returnType = ((MethodSignature) pjp.getSignature()).getReturnType();

		// 使用redis 的hash进行存取，易于管理

		result = pjp.proceed();
		String key = cacheput.key();
		redis.hset(key, fieldKey, result);

		return result;
	}

	/**
	 * 获取被拦截方法对象
	 * 
	 * @param pjp
	 * @return
	 */
	public Method getMethod(ProceedingJoinPoint pjp) {
		// 获取参数的类型
		Object[] args = pjp.getArgs();
		Class[] argTypes = new Class[pjp.getArgs().length];
		for (int i = 0; i < args.length; i++) {
			if (args[i] != null) {
				argTypes[i] = args[i].getClass();
			} else {
				argTypes[i] = String.class;

			}
		}
		Method method = null;
		try {
			method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return method;

	}

	private String parseKey(String key, Method method, Object[] args) {

		// 获取被拦截方法参数名列表(使用Spring支持类库)
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] paraNameArr = u.getParameterNames(method);

		// 使用SPEL进行key的解析
		ExpressionParser parser = new SpelExpressionParser();
		// SPEL上下文
		StandardEvaluationContext context = new StandardEvaluationContext();
		// 把方法参数放入SPEL上下文中
		for (int i = 0; i < paraNameArr.length; i++) {
			context.setVariable(paraNameArr[i], args[i]);
		}
		return parser.parseExpression(key).getValue(context, String.class);
	}
}
