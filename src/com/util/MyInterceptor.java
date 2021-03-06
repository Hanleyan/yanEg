package com.util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.entity.power.Users;
 
public class MyInterceptor extends BaseController implements HandlerInterceptor {
    private final static Logger logger = Logger.getLogger(MyInterceptor.class);
    
    /**
     * 	preHandle在业务处理器处理请求之前被调用;
		postHandle在业务处理器处理请求执行完成后,生成视图之前执行;
		afterCompletion在DispatcherServlet完全处理完请求后被调用,可用于清理资源等 。afterCompletion()执行完成后开始渲染页面
		如果preHandle返回false，则请求不再往下处理。
--------------------- 
     */
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("进入 preHandle 方法..." + request.getRequestURL().toString() + "," + request.getRequestURI());
        
        request.setAttribute("request", "request");//这个是放到request范围内的，所以只能在当前请求中的request中获取到
        request.setAttribute("session", "session");//这个是放到session范围内的，如果环境允许的话它只能在局部的隔离的会话中访问，否则就是在普通的当前会话中可以访问
        request.setAttribute("globalSession", "globalSession");//如果环境允许的话，它能在全局共享的会话中访问，否则就是在普通的当前会话中访问
        
        /**1. getServletPath():获取能够与“url-pattern”中匹配的路径，注意是完全匹配的部分，*的部分不包括。 
			2. getPageInfo():与getServletPath()获取的路径互补，能够得到的是“url-pattern”中*d的路径部分 
			3. getContextPath():获取项目的根路径 
			4. getRequestURI:获取根路径到地址结尾 
			5. getRequestURL:获取请求的地址链接（浏览器中输入的地址） 
			6. getServletContext().getRealPath(“/”):获取“/”在机器中的实际地址 
			7. getScheme():获取的是使用的协议(http 或https) 
			8. getProtocol():获取的是协议的名称(HTTP/1.11) 
			9. getServerName():获取的是域名(xxx.com) 
			10. getLocalName:获取到的是IP
			***/
        
        /*if ("GET".equalsIgnoreCase(request.getMethod())) {
            //RequestUtil.saveRequest();
        	logger.info("拦截进登入  "+request.getContextPath()+"/jsp/power/powerLogin.jsp");
            return false;
        }*/
        Users obj = getSessionUser(request);
        
        //HttpSession session = request.getSession();
        // 从session当中获取特定的数据
        //Object obj = session.getAttribute("username");
        if (obj == null) {
            // 未登录，重定向到登录页面
        	response.sendRedirect(request.getContextPath()+"/jsp/power/powerLogin.jsp");
        	logger.info("拦截进登入  "+request.getContextPath()+"/jsp/power/powerLogin.jsp");
            return false;
        }
        // 已登录，继续向后调用
        logger.info(obj.getUsername()+"已登录 ");
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("进入 postHandle 方法..." + request.getRequestURL().toString() + "," + request.getRequestURI());
      
        
        
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("进入 afterCompletion 方法..." + request.getRequestURL().toString() + "," + request.getRequestURI());
    }
}

