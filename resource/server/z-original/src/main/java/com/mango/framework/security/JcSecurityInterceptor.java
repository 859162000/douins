 package com.mango.framework.security;

/*   3:    */ import java.io.IOException;
/*   4:    */ import javax.servlet.Filter;
/*   5:    */ import javax.servlet.FilterChain;
/*   6:    */ import javax.servlet.FilterConfig;
/*   7:    */ import javax.servlet.ServletException;
/*   8:    */ import javax.servlet.ServletRequest;
/*   9:    */ import javax.servlet.ServletResponse;
/*  10:    */ import javax.servlet.http.HttpServletRequest;
/*  11:    */ import org.springframework.security.access.SecurityMetadataSource;
/*  12:    */ import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
/*  13:    */ import org.springframework.security.access.intercept.InterceptorStatusToken;
/*  14:    */ import org.springframework.security.web.FilterInvocation;
/*  15:    */ import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
/*  16:    */ 
/*  17:    */ public class JcSecurityInterceptor
/*  18:    */   extends AbstractSecurityInterceptor
/*  19:    */   implements Filter
/*  20:    */ {
/*  21:    */   private static final String FILTER_APPLIED = "_security_filterSecurityInterceptor_filterApplied_jc";
/*  22:    */   private FilterInvocationSecurityMetadataSource securityMetadataSource;
/*  23: 43 */   private boolean observeOncePerRequest = true;
/*  24:    */   
/*  25:    */   public void init(FilterConfig arg0)
/*  26:    */     throws ServletException
/*  27:    */   {}
/*  28:    */   
/*  29:    */   public void destroy() {}
/*  30:    */   
/*  31:    */   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
/*  32:    */     throws IOException, ServletException
/*  33:    */   {
/*  34: 84 */     FilterInvocation fi = new FilterInvocation(request, response, chain);
/*  35: 85 */     invoke(fi);
/*  36:    */   }
/*  37:    */   
/*  38:    */   public FilterInvocationSecurityMetadataSource getSecurityMetadataSource()
/*  39:    */   {
/*  40: 89 */     return this.securityMetadataSource;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public Class<? extends Object> getSecureObjectClass()
/*  44:    */   {
/*  45: 93 */     return FilterInvocation.class;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public void invoke(FilterInvocation fi)
/*  49:    */     throws IOException, ServletException
/*  50:    */   {
/*  51: 97 */     if ((fi.getRequest() != null) && (fi.getRequest().getAttribute("_security_filterSecurityInterceptor_filterApplied_jc") != null) && 
/*  52: 98 */       (this.observeOncePerRequest))
/*  53:    */     {
/*  54:102 */       fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
/*  55:    */     }
/*  56:    */     else
/*  57:    */     {
/*  58:106 */       if (fi.getRequest() != null) {
/*  59:107 */         fi.getRequest().setAttribute("_security_filterSecurityInterceptor_filterApplied_jc", Boolean.TRUE);
/*  60:    */       }
/*  61:110 */       InterceptorStatusToken token = super.beforeInvocation(fi);
/*  62:    */       try
/*  63:    */       {
/*  64:113 */         fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
/*  65:    */       }
/*  66:    */       finally
/*  67:    */       {
/*  68:115 */         super.afterInvocation(token, null);
/*  69:    */       }
/*  70:    */     }
/*  71:    */   }
/*  72:    */   
/*  73:    */   public boolean isObserveOncePerRequest()
/*  74:    */   {
/*  75:121 */     return this.observeOncePerRequest;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public SecurityMetadataSource obtainSecurityMetadataSource()
/*  79:    */   {
/*  80:125 */     return this.securityMetadataSource;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource newSource)
/*  84:    */   {
/*  85:129 */     this.securityMetadataSource = newSource;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public void setObserveOncePerRequest(boolean observeOncePerRequest)
/*  89:    */   {
/*  90:133 */     this.observeOncePerRequest = observeOncePerRequest;
/*  91:    */   }
/*  92:    */ }

