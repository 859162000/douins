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
/*  11:    */ import org.springframework.security.access.AccessDeniedException;
/*  12:    */ import org.springframework.security.authentication.AuthenticationTrustResolver;
/*  13:    */ import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
/*  14:    */ import org.springframework.security.authentication.InsufficientAuthenticationException;
/*  15:    */ import org.springframework.security.core.AuthenticationException;
/*  16:    */ import org.springframework.security.core.context.SecurityContext;
/*  17:    */ import org.springframework.security.core.context.SecurityContextHolder;
/*  18:    */ import org.springframework.security.web.AuthenticationEntryPoint;
/*  19:    */ import org.springframework.security.web.access.AccessDeniedHandler;
/*  20:    */ import org.springframework.security.web.access.AccessDeniedHandlerImpl;
/*  21:    */ import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
/*  22:    */ import org.springframework.security.web.savedrequest.RequestCache;
/*  23:    */ import org.springframework.security.web.util.ThrowableAnalyzer;
/*  24:    */ import org.springframework.security.web.util.ThrowableCauseExtractor;
/*  25:    */ import org.springframework.util.Assert;
/*  26:    */ import org.springframework.web.filter.GenericFilterBean;
/*  27:    */ 
/*  28:    */ public class JcExceptionTranslationFilter
/*  29:    */   extends GenericFilterBean
/*  30:    */ {
/*  31: 78 */   private AccessDeniedHandler accessDeniedHandler = new AccessDeniedHandlerImpl();
/*  32:    */   private AuthenticationEntryPoint authenticationEntryPoint;
/*  33: 80 */   private AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
/*  34: 82 */   private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();
/*  35: 84 */   private RequestCache requestCache = new HttpSessionRequestCache();
/*  36:    */   
/*  37:    */   public void afterPropertiesSet()
/*  38:    */   {
/*  39: 91 */     Assert.notNull(this.authenticationEntryPoint, "authenticationEntryPoint must be specified");
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
/*  43:    */     throws IOException, ServletException
/*  44:    */   {
/*  45: 97 */     HttpServletRequest request = (HttpServletRequest)req;
/*  46: 98 */     HttpServletResponse response = (HttpServletResponse)res;
/*  47:    */     try
/*  48:    */     {
/*  49:100 */       chain.doFilter(request, response);
/*  50:101 */       if (this.logger.isDebugEnabled()) {
/*  51:102 */         this.logger.debug("Chain processed normally");
/*  52:    */       }
/*  53:    */     }
/*  54:    */     catch (IOException ex)
/*  55:    */     {
/*  56:105 */       throw ex;
/*  57:    */     }
/*  58:    */     catch (Exception ex)
/*  59:    */     {
/*  60:108 */       Throwable[] causeChain = this.throwableAnalyzer.determineCauseChain(ex);
/*  61:109 */       RuntimeException ase = (AuthenticationException)this.throwableAnalyzer.getFirstThrowableOfType(
/*  62:110 */         AuthenticationException.class, causeChain);
/*  63:111 */       if (ase == null) {
/*  64:112 */         ase = (AccessDeniedException)this.throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, 
/*  65:113 */           causeChain);
/*  66:    */       }
/*  67:115 */       if (ase != null)
/*  68:    */       {
/*  69:116 */         handleException(request, response, chain, ase);
/*  70:    */       }
/*  71:    */       else
/*  72:    */       {
/*  73:119 */         if ((ex instanceof ServletException)) {
/*  74:120 */           throw ((ServletException)ex);
/*  75:    */         }
/*  76:121 */         if ((ex instanceof RuntimeException)) {
/*  77:122 */           throw ((RuntimeException)ex);
/*  78:    */         }
/*  79:125 */         throw new RuntimeException(ex);
/*  80:    */       }
/*  81:    */     }
/*  82:    */   }
/*  83:    */   
/*  84:    */   public AuthenticationEntryPoint getAuthenticationEntryPoint()
/*  85:    */   {
/*  86:131 */     return this.authenticationEntryPoint;
/*  87:    */   }
/*  88:    */   
/*  89:    */   protected AuthenticationTrustResolver getAuthenticationTrustResolver()
/*  90:    */   {
/*  91:135 */     return this.authenticationTrustResolver;
/*  92:    */   }
/*  93:    */   
/*  94:    */   private void handleException(HttpServletRequest request, HttpServletResponse response, FilterChain chain, RuntimeException exception)
/*  95:    */     throws IOException, ServletException
/*  96:    */   {
/*  97:140 */     if ((exception instanceof AuthenticationException))
/*  98:    */     {
/*  99:141 */       if (this.logger.isDebugEnabled()) {
/* 100:142 */         this.logger.debug("Authentication exception occurred; redirecting to authentication entry point", exception);
/* 101:    */       }
/* 102:144 */       sendStartAuthentication(request, response, chain, (AuthenticationException)exception);
/* 103:    */     }
/* 104:145 */     else if ((exception instanceof AccessDeniedException))
/* 105:    */     {
/* 106:146 */       if (this.authenticationTrustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication()))
/* 107:    */       {
/* 108:147 */         if (this.logger.isDebugEnabled()) {
/* 109:148 */           this.logger.debug("Access is denied (user is anonymous); redirecting to authentication entry point", 
/* 110:149 */             exception);
/* 111:    */         }
/* 112:151 */         sendStartAuthentication(request, response, chain, new InsufficientAuthenticationException(
/* 113:152 */           "Full authentication is required to access this resource"));
/* 114:    */       }
/* 115:    */       else
/* 116:    */       {
/* 117:154 */         if (this.logger.isDebugEnabled()) {
/* 118:155 */           this.logger.debug("Access is denied (user is not anonymous); delegating to AccessDeniedHandler", 
/* 119:156 */             exception);
/* 120:    */         }
/* 121:158 */         this.accessDeniedHandler.handle(request, response, (AccessDeniedException)exception);
/* 122:    */       }
/* 123:    */     }
/* 124:    */   }
/* 125:    */   
/* 126:    */   protected void sendStartAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, AuthenticationException reason)
/* 127:    */     throws ServletException, IOException
/* 128:    */   {
/* 129:167 */     SecurityContextHolder.getContext().setAuthentication(null);
/* 130:168 */     this.requestCache.saveRequest(request, response);
/* 131:169 */     this.logger.debug("Calling Authentication entry point.");
/* 132:170 */     this.authenticationEntryPoint.commence(request, response, reason);
/* 133:    */   }
/* 134:    */   
/* 135:    */   public void setAccessDeniedHandler(AccessDeniedHandler accessDeniedHandler)
/* 136:    */   {
/* 137:174 */     Assert.notNull(accessDeniedHandler, "AccessDeniedHandler required");
/* 138:175 */     this.accessDeniedHandler = accessDeniedHandler;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint)
/* 142:    */   {
/* 143:179 */     this.authenticationEntryPoint = authenticationEntryPoint;
/* 144:    */   }
/* 145:    */   
/* 146:    */   public void setAuthenticationTrustResolver(AuthenticationTrustResolver authenticationTrustResolver)
/* 147:    */   {
/* 148:183 */     Assert.notNull(authenticationTrustResolver, "authenticationTrustResolver must not be null");
/* 149:184 */     this.authenticationTrustResolver = authenticationTrustResolver;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public void setThrowableAnalyzer(ThrowableAnalyzer throwableAnalyzer)
/* 153:    */   {
/* 154:188 */     Assert.notNull(throwableAnalyzer, "throwableAnalyzer must not be null");
/* 155:189 */     this.throwableAnalyzer = throwableAnalyzer;
/* 156:    */   }
/* 157:    */   
/* 158:    */   public void setRequestCache(RequestCache requestCache)
/* 159:    */   {
/* 160:197 */     Assert.notNull(requestCache, "requestCache cannot be null");
/* 161:198 */     this.requestCache = requestCache;
/* 162:    */   }
/* 163:    */   
/* 164:    */   private static final class DefaultThrowableAnalyzer
/* 165:    */     extends ThrowableAnalyzer
/* 166:    */   {
/* 167:    */     protected void initExtractorMap()
/* 168:    */     {
/* 169:207 */       super.initExtractorMap();
/* 170:208 */       registerExtractor(ServletException.class, new ThrowableCauseExtractor()
/* 171:    */       {
/* 172:    */         public Throwable extractCause(Throwable throwable)
/* 173:    */         {
/* 174:210 */           ThrowableAnalyzer.verifyThrowableHierarchy(throwable, ServletException.class);
/* 175:211 */           return ((ServletException)throwable).getRootCause();
/* 176:    */         }
/* 177:    */       });
/* 178:    */     }
/* 179:    */   }
/* 180:    */ }


/* Location:           F:\项目资料\framework-1.0-SNAPSHOT.jar
 * Qualified Name:     com.mango.framework.security.JcExceptionTranslationFilter
 * JD-Core Version:    0.7.0.1
 */