/*   1:    */ package com.mango.framework.security;
/*   2:    */ 
/*   3:    */ import com.mango.core.logger.SimpleLogger;
/*   4:    */ import java.io.IOException;
/*   5:    */ import java.io.PrintWriter;
/*   6:    */ import javax.servlet.RequestDispatcher;
/*   7:    */ import javax.servlet.ServletException;
/*   8:    */ import javax.servlet.http.HttpServletRequest;
/*   9:    */ import javax.servlet.http.HttpServletResponse;
/*  10:    */ import org.springframework.security.access.AccessDeniedException;
/*  11:    */ import org.springframework.security.web.access.AccessDeniedHandler;
/*  12:    */ 
/*  13:    */ public class JcAjaxAccessDeniedHandlerImpl
/*  14:    */   implements AccessDeniedHandler
/*  15:    */ {
/*  16: 41 */   private SimpleLogger logger = SimpleLogger.getLogger(JcAjaxAccessDeniedHandlerImpl.class);
/*  17:    */   private String errorPage;
/*  18:    */   
/*  19:    */   public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
/*  20:    */     throws IOException, ServletException
/*  21:    */   {
/*  22: 53 */     if (!response.isCommitted()) {
/*  23: 54 */       if (isAjax(request))
/*  24:    */       {
/*  25: 55 */         String contentType = "application/json";
/*  26: 56 */         response.setContentType(contentType);
/*  27: 57 */         PrintWriter out = response.getWriter();
/*  28: 58 */         StringBuffer rtn = new StringBuffer("{\"success\":false,\"msg\":\"");
/*  29: 59 */         rtn.append("请求数据失败,请重新登录尝试或者联系管理员!").append("\"}").toString();
/*  30: 60 */         out.print(rtn.toString());
/*  31: 61 */         out.flush();
/*  32: 62 */         out.close();
/*  33: 63 */         if (this.logger.isDebugEnabled()) {
/*  34: 64 */           this.logger.debug(" ajax 请求权限验证失败返回错误信息....");
/*  35:    */         }
/*  36:    */       }
/*  37:    */       else
/*  38:    */       {
/*  39: 66 */         if (this.errorPage != null)
/*  40:    */         {
/*  41: 67 */           request.setAttribute("SPRING_SECURITY_403_EXCEPTION", accessDeniedException);
/*  42:    */           
/*  43: 69 */           response.setStatus(403);
/*  44:    */           
/*  45: 71 */           RequestDispatcher dispatcher = request.getRequestDispatcher(this.errorPage);
/*  46: 72 */           dispatcher.forward(request, response);
/*  47:    */         }
/*  48:    */         else
/*  49:    */         {
/*  50: 74 */           response.sendError(403, accessDeniedException.getMessage());
/*  51:    */         }
/*  52: 76 */         if (this.logger.isDebugEnabled()) {
/*  53: 77 */           this.logger.debug(" 请求权限验证失败返回错误页面....");
/*  54:    */         }
/*  55:    */       }
/*  56:    */     }
/*  57:    */   }
/*  58:    */   
/*  59:    */   private boolean isAjax(HttpServletRequest request)
/*  60:    */   {
/*  61: 83 */     return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void setErrorPage(String errorPage)
/*  65:    */   {
/*  66: 97 */     if ((errorPage != null) && (!errorPage.startsWith("/"))) {
/*  67: 98 */       throw new IllegalArgumentException("errorPage must begin with '/'");
/*  68:    */     }
/*  69:100 */     this.errorPage = errorPage;
/*  70:    */   }
/*  71:    */ }
