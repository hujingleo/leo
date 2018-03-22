package com.youxin.ymall.buslog;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.rsclouds.base.SimpleNetObject;
import com.rsclouds.util.StringTool;
import com.youxin.ymall.entity.Buslog;
import com.youxin.ymall.exceptions.SessionException;
import com.youxin.ymall.service.BuslogService;
import com.youxin.ymall.utils.SessionUtil;

@Component
@Aspect
public class LogAspect {
	@Autowired
	public BuslogService busLogService;

	@Around("@annotation(com.youxin.ymall.buslog.BusLogger)")
	public Object buslog(ProceedingJoinPoint pjp) throws Throwable {

		Object result = null;
		try {
			result = pjp.proceed();
		} catch (Exception ex) {
			return result;
		}
		try {

			String type = null;
			if (result instanceof SimpleNetObject) {
				if (((SimpleNetObject) result).getResult() == 1) {
					Method method = getMethod(pjp);

					BusLogger buslogger = method.getAnnotation(com.youxin.ymall.buslog.BusLogger.class);

					String strMsg = buslogger.msg();
					/**
					 * 获取相关session参数
					 */
					RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
					String username = null;
					if (requestAttributes != null) {
						HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
								.getRequestAttributes()).getRequest();
						HttpSession session = request.getSession();
						try {
							username = SessionUtil.getCurrentUserName(session);
							type = "user";
						} catch (SessionException se) {
							username = "system";
							type = "system";
						}
					}

					String msg = parseKey(buslogger.msg(), method, pjp.getArgs());
					// 获取方法的返回类型,让缓存可以返回正确的类型
					Buslog buslog = new Buslog();
					buslog.setOperator(username);
					buslog.setOdate(new Date());
					buslog.setOtype(type);
					buslog.setMsg(msg);
					String func, module;
					if (StringTool.isNullOrEmpty(buslogger.func())) {
						func = method.getName();
					} else {
						func = buslogger.func();
					}
					if (StringTool.isNullOrEmpty(buslogger.module())) {
						module = method.getDeclaringClass().getName();
					} else {
						module = buslogger.module();
					}

					this.busLogService.info(username, module, func, msg);

				}

			}

		} catch (Exception e) {

		}
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
			argTypes[i] = args[i].getClass();
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
