package com.situ.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Servlet Filter implementation class Encoding
 */
public class Encoding implements Filter {

    /**
     * Default constructor. 
     */
    public Encoding() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	/*public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest =(HttpServletRequest) request;
		String method = httpServletRequest.getMethod();
		if ("get".equals(method)) {
			EnhancedRequest enhancedRequest = new EnhancedRequest(httpServletRequest);
			chain.doFilter(enhancedRequest, response);
		}else if ("post".equals(method)) {
			request.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
		}
		else{
			chain.doFilter(request, response);
		}
		System.out.println("开始执行filter的方法");
		chain.doFilter(request, response);
	}

	*//**
	 * @see Filter#init(FilterConfig)
	 *//*
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

class EnhancedRequest extends HttpServletRequestWrapper{
     private HttpServletRequest request;
	public EnhancedRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	@Override
	public String getParameter(String name) {
		System.out.println("第二次执行");
	      String parameter = request.getParameter(name);
	      if (parameter != null && !"".equals(parameter)) {
			try {
				parameter = new String(parameter.getBytes("iso8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	      return parameter;
	}*/
	
	@Override
	   public void init(FilterConfig filterConfig) throws ServletException {
	   }
	   @Override
	   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	         throws IOException, ServletException {
	      HttpServletRequest httpServletRequest = (HttpServletRequest) request;
	      String method = httpServletRequest.getMethod();
	      if ("get".equalsIgnoreCase(method)) {
	         EnhancedRequest enhancedRequest = new EnhancedRequest(httpServletRequest);
	         chain.doFilter(enhancedRequest, response);
	      } else if ("post".equalsIgnoreCase(method)) {
	         request.setCharacterEncoding("UTF-8");
	         chain.doFilter(request, response);
	      } else {
	         chain.doFilter(request, response);
	      }
	      
	   }
	  
	class EnhancedRequest extends HttpServletRequestWrapper {
	   private HttpServletRequest request;
	   public EnhancedRequest(HttpServletRequest request) {
	      super(request);
	      this.request = request;
	   }
	   
	   @Override
	   public String getParameter(String name) {
	      String parameter = request.getParameter(name);//还是乱码
	      if (parameter != null && !"".equals(parameter)) {
	         try {
	            parameter = new String(parameter.getBytes("iso8859-1"), "UTF-8");
	         } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	         }
	      }
	      return parameter;
	   }

	}
}
