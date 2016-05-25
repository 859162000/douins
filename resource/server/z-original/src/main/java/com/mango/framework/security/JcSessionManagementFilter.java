/*   1:    */ package com.mango.framework.security;
/*   2:    */ 
/*   3:    */ import java.io.IOException;
/*   4:    */ import javax.servlet.FilterChain;
/*   5:    */ import javax.servlet.ServletException;
/*   6:    */ import javax.servlet.ServletRequest;
/*   7:    */ import javax.servlet.ServletResponse;
/*   8:    */ import javax.servlet.http.HttpServletRequest;
/*   9:    */ import javax.servlet.http.HttpServletResponse;
/*  10:    */ import org.apache.commons.logging.Log;
/*  11:    */ import org.springframework.security.authentication.AuthenticationTrustResolver;
/*  12:    */ import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
/*  13:    */ import org.springframework.security.core.Authentication;
/*  14:    */ import org.springframework.security.core.context.SecurityContext;
/*  15:    */ import org.springframework.security.core.context.SecurityContextHolder;
/*  16:    */ import org.springframework.security.web.authentication.AuthenticationFailureHandler;
/*  17:    */ import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
/*  18:    */ import org.springframework.security.web.authentication.session.SessionAuthenticationException;
/*  19:    */ import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
/*  20:    */ import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
/*  21:    */ import org.springframework.security.web.context.SecurityContextRepository;
/*  22:    */ import org.springframework.security.web.session.InvalidSessionStrategy;
/*  23:    */ import org.springframework.util.Assert;
/*  24:    */ import org.springframework.web.filter.GenericFilterBean;
/*  25:    */ 
/*  26:    */ public class JcSessionManagementFilter
/*  27:    */   extends GenericFilterBean
/*  28:    */ {
/*  29:    */   static final String FILTER_APPLIED = "__spring_security_session_mgmt_filter_applied";
/*  30:    */   private final SecurityContextRepository securityContextRepository;
/*  31:    */   private SessionAuthenticationStrategy sessionAuthenticationStrategy;
/*  32: 57 */   private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
/*  33: 58 */   private InvalidSessionStrategy invalidSessionStrategy = null;
/*  34: 59 */   private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
/*  35:    */   
/*  36:    */   public JcSessionManagementFilter(SecurityContextRepository securityContextRepository)
/*  37:    */   {
/*  38: 62 */     this(securityContextRepository, new SessionFixationProtectionStrategy());
/*  39:    */   }
/*  40:    */   
/*  41:    */   public JcSessionManagementFilter(SecurityContextRepository securityContextRepository, SessionAuthenticationStrategy sessionStrategy)
/*  42:    */   {
/*  43: 67 */     Assert.notNull(securityContextRepository, "SecurityContextRepository cannot be null");
/*  44: 68 */     Assert.notNull(sessionStrategy, "SessionAuthenticationStrategy cannot be null");
/*  45: 69 */     this.securityContextRepository = securityContextRepository;
/*  46: 70 */     this.sessionAuthenticationStrategy = sessionStrategy;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
/*  50:    */     throws IOException, ServletException
/*  51:    */   {
/*  52: 75 */     HttpServletRequest request = (HttpServletRequest)req;
/*  53: 76 */     HttpServletResponse response = (HttpServletResponse)res;
/*  54: 77 */     if (request.getAttribute("__spring_security_session_mgmt_filter_applied") != null)
/*  55:    */     {
/*  56: 78 */       chain.doFilter(request, response);
/*  57: 79 */       return;
/*  58:    */     }
/*  59: 81 */     request.setAttribute("__spring_security_session_mgmt_filter_applied", Boolean.TRUE);
/*  60: 82 */     if (!this.securityContextRepository.containsContext(request))
/*  61:    */     {
/*  62: 83 */       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
/*  63: 84 */       if ((authentication != null) && (!this.trustResolver.isAnonymous(authentication)))
/*  64:    */       {
/*  65:    */         try
/*  66:    */         {
/*  67: 88 */           this.sessionAuthenticationStrategy.onAuthentication(authentication, request, response);
/*  68:    */         }
/*  69:    */         catch (SessionAuthenticationException e)
/*  70:    */         {
/*  71: 91 */           this.logger.debug("SessionAuthenticationStrategy rejected the authentication object", e);
/*  72: 92 */           SecurityContextHolder.clearContext();
/*  73: 93 */           this.failureHandler.onAuthenticationFailure(request, response, e);
/*  74:    */           
/*  75: 95 */           return;
/*  76:    */         }
/*  77:101 */         this.securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, response);
/*  78:    */       }
/*  79:105 */       else if ((request.getRequestedSessionId() != null) && (!request.isRequestedSessionIdValid()))
/*  80:    */       {
/*  81:106 */         if (this.logger.isDebugEnabled()) {
/*  82:107 */           this.logger.debug("Requested session ID " + request.getRequestedSessionId() + " is invalid.");
/*  83:    */         }
/*  84:110 */         if (this.invalidSessionStrategy != null)
/*  85:    */         {
/*  86:111 */           this.invalidSessionStrategy.onInvalidSessionDetected(request, response);
/*  87:112 */           return;
/*  88:    */         }
/*  89:    */       }
/*  90:    */     }
/*  91:117 */     chain.doFilter(request, response);
/*  92:    */   }
/*  93:    */   
/*  94:    */   @Deprecated
/*  95:    */   public void setSessionAuthenticationStrategy(SessionAuthenticationStrategy sessionAuthenticationStrategy)
/*  96:    */   {
/*  97:131 */     Assert.notNull(sessionAuthenticationStrategy, "authenticatedSessionStrategy must not be null");
/*  98:132 */     this.sessionAuthenticationStrategy = sessionAuthenticationStrategy;
/*  99:    */   }
/* 100:    */   
/* 101:    */   public void setInvalidSessionStrategy(InvalidSessionStrategy invalidSessionStrategy)
/* 102:    */   {
/* 103:145 */     this.invalidSessionStrategy = invalidSessionStrategy;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler)
/* 107:    */   {
/* 108:157 */     Assert.notNull(failureHandler, "failureHandler cannot be null");
/* 109:158 */     this.failureHandler = failureHandler;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void setTrustResolver(AuthenticationTrustResolver trustResolver)
/* 113:    */   {
/* 114:170 */     Assert.notNull(trustResolver, "trustResolver cannot be null");
/* 115:171 */     this.trustResolver = trustResolver;
/* 116:    */   }
/* 117:    */ }


/* Location:           F:\项目资料\framework-1.0-SNAPSHOT.jar
 * Qualified Name:     com.mango.framework.security.JcSessionManagementFilter
 * JD-Core Version:    0.7.0.1
 */