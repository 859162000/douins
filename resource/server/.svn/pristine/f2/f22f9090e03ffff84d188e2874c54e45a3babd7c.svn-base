/*  1:   */ package com.mango.framework.security;
/*  2:   */ 
/*  3:   */ import com.mango.core.logger.SimpleLogger;
/*  4:   */ import javax.servlet.http.HttpServletRequest;
/*  5:   */ import javax.servlet.http.HttpServletResponse;
/*  6:   */ import javax.servlet.http.HttpSession;
/*  7:   */ import org.springframework.security.core.Authentication;
/*  8:   */ import org.springframework.security.core.context.SecurityContextHolder;
/*  9:   */ import org.springframework.security.web.authentication.logout.LogoutHandler;
/* 10:   */ import org.springframework.util.Assert;
/* 11:   */ 
/* 12:   */ public class JcSecurityLogout
/* 13:   */   implements LogoutHandler
/* 14:   */ {
/* 15:31 */   private SimpleLogger logger = SimpleLogger.getLogger(getClass());
/* 16:33 */   private boolean invalidateHttpSession = true;
/* 17:   */   
/* 18:   */   public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
/* 19:   */   {
/* 20:49 */     Assert.notNull(request, "HttpServletRequest required");
/* 21:50 */     if (this.invalidateHttpSession)
/* 22:   */     {
/* 23:51 */       HttpSession session = request.getSession(false);
/* 24:52 */       if (session != null) {
/* 25:53 */         session.invalidate();
/* 26:   */       }
/* 27:   */     }
/* 28:56 */     SecurityContextHolder.clearContext();
/* 29:57 */     if (this.logger.isDebugEnabled()) {
/* 30:58 */       this.logger.debug(" logout handler for " + (authentication == null ? "null" : authentication.getName()));
/* 31:   */     }
/* 32:   */   }
/* 33:   */   
/* 34:   */   public boolean isInvalidateHttpSession()
/* 35:   */   {
/* 36:62 */     return this.invalidateHttpSession;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void setInvalidateHttpSession(boolean invalidateHttpSession)
/* 40:   */   {
/* 41:74 */     this.invalidateHttpSession = invalidateHttpSession;
/* 42:   */   }
/* 43:   */ }


/* Location:           F:\项目资料\framework-1.0-SNAPSHOT.jar
 * Qualified Name:     com.mango.framework.security.JcSecurityLogout
 * JD-Core Version:    0.7.0.1
 */