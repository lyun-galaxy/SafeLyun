package com.paly.interceptor;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.InitializingBean;

import net.sf.ehcache.Cache;  
  
public class MethodCacheAfterAdvice implements AfterReturningAdvice, InitializingBean {  
      
      
    private Cache cache;  
      
    public void setCache(Cache cache) {  
        this.cache = cache;  
    }  
      
    /** 
     * @Description: 清除缓存（在目标方法执行之后，执行该方法） 
     * @throws Throwable 
     */  
    @SuppressWarnings("unchecked")  
    @Override  
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {  
        String className = target.getClass().getName();    
        List<String> cacheKeys = cache.getKeys();  
        System.out.println("[清除缓存：]" + cacheKeys);  
        for (String cacheKey : cacheKeys) {    
            if(cacheKey.startsWith(className)){    
                cache.remove(cacheKey);  
            }    
        }    
    }  
      
    @Override  
    public void afterPropertiesSet() throws Exception {  
    }  
  
}