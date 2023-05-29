package com.einfochips.ecommerce.Controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Logic to be executed before the handler method
    	System.out.println("This is PreHandler.....");
    	String name=request.getParameter("user");
    	if(name.startsWith("P")) {
    		response.setContentType("text/html");
    		response.getWriter().println("<h1>Invalid Name Please Check</h1>");
    		return false;
    	}
        return true; // Allow the execution to continue
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // Logic to be executed after the handler method
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
    	System.out.println("This is PostHandler");
        // Logic to be executed after rendering the view
    }
}
