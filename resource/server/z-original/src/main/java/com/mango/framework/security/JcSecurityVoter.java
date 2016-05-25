/*  1:   */ package com.mango.framework.security;
/*  2:   */ 
/*  3:   */ import com.mango.core.logger.SimpleLogger;
/*  4:   */ import java.util.Collection;
/*  5:   */ import org.springframework.security.access.AccessDecisionVoter;
/*  6:   */ import org.springframework.security.access.ConfigAttribute;
/*  7:   */ import org.springframework.security.authentication.AnonymousAuthenticationToken;
/*  8:   */ import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
/*  9:   */ import org.springframework.security.core.Authentication;
/* 10:   */ import org.springframework.security.core.userdetails.User;
/* 11:   */ 
/* 12:   */ public class JcSecurityVoter
/* 13:   */   implements AccessDecisionVoter<Object>
/* 14:   */ {
/* 15:29 */   SimpleLogger logger = SimpleLogger.getLogger(JcSecurityVoter.class);
/* 16:   */   
/* 17:   */   public boolean supports(ConfigAttribute arg0)
/* 18:   */   {
/* 19:32 */     return true;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public boolean supports(Class<?> arg0)
/* 23:   */   {
/* 24:36 */     return true;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes)
/* 28:   */   {
/* 29:40 */     int result = 0;
/* 30:41 */     for (ConfigAttribute attribute : attributes)
/* 31:   */     {
/* 32:42 */       result = -1;
/* 33:43 */       if ((!authentication.isAuthenticated()) || ((authentication instanceof AnonymousAuthenticationToken)))
/* 34:   */       {
				//没有登陆认证过，禁止访问
/* 35:44 */         this.logger.warn(" require user is not anonymousUser,but user is anonymousUser. request denied.");
/* 36:45 */         return result;
/* 37:   */       }
/* 38:47 */       if (this.logger.isDebugEnabled()) {
/* 39:48 */         this.logger.debug(" require roles " + attribute.getAttribute());
/* 40:   */       }
/* 41:49 */       if (((authentication instanceof User)) || 
/* 42:50 */         ((authentication instanceof UsernamePasswordAuthenticationToken)))
/* 43:   */       {
				//登陆认证过,可以访问
/* 44:51 */         result = 1;
/* 45:52 */         return result;
/* 46:   */       }
/* 47:   */     }
/* 48:57 */     return result;
/* 49:   */   }
/* 50:   */ }


/* Location:           F:\项目资料\framework-1.0-SNAPSHOT.jar
 * Qualified Name:     com.mango.framework.security.JcSecurityVoter
 * JD-Core Version:    0.7.0.1
 */