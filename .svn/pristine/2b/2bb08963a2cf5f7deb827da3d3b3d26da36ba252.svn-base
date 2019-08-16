package com.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginAndPrivilegeFilter implements Filter {


	public void destroy() {
		// TODO Auto-generated method stub

	}


	@SuppressWarnings("unused")
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		String servletPath = request.getServletPath();
		/*User user = (User) request.getSession().getAttribute("user");
		String requestType = request.getHeader("X-Requested-With");
		if(servletPath.indexOf("index.jsp")>-1 || servletPath.indexOf("login")>-1 || servletPath.indexOf("resources")>-1||servletPath.indexOf("modeler")>-1|| servletPath.indexOf("ReportServer")>-1||servletPath.indexOf("DataReceive")>-1){
			chain.doFilter(req, res);
		}else if( !isLogin(request) ){
			//增加ajax请求判断，如果是ajax请求，则返回failure由前台进行处理		
			if(requestType != null && requestType.equalsIgnoreCase("xmlhttprequest")){
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				String s = Global.MSG_FAILURE;				
				out.print(s);
				out.flush();
			}else{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				String s = "<html><script>alert('登录超时，请重新登录!');window.parent.location='"+basePath+"';</script></html>";			
				//response.sendRedirect(basePath);	
				out.print(s);
				out.flush();
			}
			
		}else{
			chain.doFilter(req, res);//禁用功能权限
			
		}*/
		chain.doFilter(req, res);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unused")
	private boolean isLogin(HttpServletRequest request){
		//User user = (User)request.getSession().getAttribute("user");
		return true;
	}
}
