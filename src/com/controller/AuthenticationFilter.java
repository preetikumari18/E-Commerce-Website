package com.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */

public class AuthenticationFilter implements Filter {
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		if (request instanceof HttpServletRequest) {
			//get the value of the headerName 
            String headerValue = req.getHeader("referer");
            //redirect to home page if requests coming from address bar 
            if (null == req.getSession(true).getAttribute("User")) {
                if (headerValue == null) {
                    if (isSessionEliminated(request)) {
                    	   request.setAttribute("isSessionEliminated", "true");
                    	   request.getRequestDispatcher("Index.jsp").forward(request, response);  
                    }
                }
            } 
         // pass the request along the filter chain
            chain.doFilter(request, response);
        }
    }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

    private boolean isSessionEliminated(ServletRequest req) {
    	
    	HttpServletRequest request = (HttpServletRequest) req;
    	
        if (req instanceof HttpServletRequest) {
            //get the requested URL
            String uri = request.getRequestURI();
            boolean logreq = !(request.getContextPath() + "Index.jsp").equals(uri);
            if (logreq == true) {
                return true;
            }
        }
        return false;
    }
}
