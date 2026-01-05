package com.iti.PlacementsBackend.util;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleCORSFilter implements Filter
{
    private final Logger log;
    
    public SimpleCORSFilter() {
        (this.log = LoggerFactory.getLogger((Class)SimpleCORSFilter.class)).info("CustomeCORSFilter init");
    }
    
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) 
    		throws IOException, ServletException {
    	System.out.println("doFilter doFilter doFilter");
       
        try {
			final HttpServletRequest request = (HttpServletRequest)req;
			final HttpServletResponse response = (HttpServletResponse)res;
			
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers", "Authorization, content-type, xsrf-token");
			response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
			
			if ("OPTIONS".equals(request.getMethod())) {
			    response.setStatus(200);
			}
			else {
			    chain.doFilter((ServletRequest)request, (ServletResponse)response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void init(final FilterConfig filterConfig) {
    }
    
    public void destroy() {
    }
}
