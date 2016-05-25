/*  1:   */ package com.mango.framework.security;
/*  2:   */ 
/*  3:   */ import java.util.Collection;
/*  4:   */ import java.util.HashSet;
/*  5:   */ import java.util.Set;
/*  6:   */ import org.springframework.security.access.ConfigAttribute;
/*  7:   */ import org.springframework.security.access.SecurityConfig;
/*  8:   */ import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
/*  9:   */ 
/* 10:   */ public class JcSecurityMetadataSource
/* 11:   */   implements FilterInvocationSecurityMetadataSource, SecurityStatus
/* 12:   */ {
/* 13:   */   public void init()
/* 14:   */     throws Exception
/* 15:   */   {
	// 读取所有的资源,和资源相关联的的权限
}
/* 16:   */   
/* 17:   */   public Collection<ConfigAttribute> getAllConfigAttributes()
/* 18:   */   {
/* 19:31 */     return getDefaultAttributes();
/* 20:   */   }
/* 21:   */   
/* 22:   */   public Collection<ConfigAttribute> getAttributes(Object arg0)
/* 23:   */     throws IllegalArgumentException
/* 24:   */   {
/* 25:35 */     return getDefaultAttributes();
/* 26:   */   }
/* 27:   */   
/* 28:   */   private Collection<ConfigAttribute> getDefaultAttributes()
/* 29:   */   {
/* 30:39 */     Set<ConfigAttribute> allAttributes = new HashSet();
/* 31:40 */     allAttributes.add(new SecurityConfig("not_anonymous"));
/* 32:41 */     return allAttributes;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public boolean supports(Class<?> arg0)
/* 36:   */   {
/* 37:45 */     return true;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public boolean clear()
/* 41:   */   {
/* 42:49 */     return false;
/* 43:   */   }
/* 44:   */ }


/* Location:           F:\项目资料\framework-1.0-SNAPSHOT.jar
 * Qualified Name:     com.mango.framework.security.JcSecurityMetadataSource
 * JD-Core Version:    0.7.0.1
 */