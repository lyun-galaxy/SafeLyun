package com.paly.interceptor;

import java.io.Serializable;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * 对象缓存方法缓存
 * @author luohuaming
 *
 */
public class MethodCacheInterceptor implements MethodInterceptor, InitializingBean {
 
    
    private Cache cache;
 
    public void setCache(Cache cache) {
        this.cache = cache;
    }
 
    public void afterPropertiesSet() throws Exception {
    }
 
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String targetName = invocation.getThis().getClass().getName();
        String methodName = invocation.getMethod().getName();
        Object[] arguments = invocation.getArguments();
        Object result;
 
        String cacheKey = getCacheKey(targetName, methodName, arguments);
        Element element = null;
        synchronized (this) {
            element = cache.get(cacheKey);
            if (element == null) {
                System.out.println(cacheKey + "加入到缓存： " + cache.getName());
                // 调用实际的方法
                result = invocation.proceed();
                element = new Element(cacheKey, (Serializable) result);
                cache.put(element);
            } else {
            	System.out.println(cacheKey + "使用缓存： " + cache.getName());
            }
        }
        return element.getValue();
    }
 
    /**
     * <b>function:</b> 返回具体的方法全路径名称 参数
     * @author hoojo
     * @createDate 2012-7-2 下午06:12:39
     * @param targetName 全路径
     * @param methodName 方法名称
     * @param arguments 参数
     * @return 完整方法名称
     */
    private String getCacheKey(String targetName, String methodName, Object[] arguments) {
        StringBuffer sb = new StringBuffer();
        sb.append(targetName).append(".").append(methodName);
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                sb.append(".").append(arguments[i]);
            }
        }
        return sb.toString();
    }
}