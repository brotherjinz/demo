package com.web.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.web.info.system.SystemContext;

public class ParameterFilter implements Filter {

	public void destroy() {

	}

	/**
	 * @param request
	 * @param ServletResponse
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession session = req.getSession();
			String path = ((HttpServletRequest) request).getServletPath();
			if (path.indexOf("/resources/") != 0 && !path.equals("/")) {
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				// 打印请求路径
				System.out.print(path);
				Enumeration<String> paramNames = request.getParameterNames();
				while (paramNames.hasMoreElements()) {
					String paramName = paramNames.nextElement();
					// 打印参数名
					System.out.print(" ==> " + paramName + " [");
					String[] paramValues = request.getParameterValues(paramName);
					if (paramValues.length > 0) {
						StringBuilder sb = new StringBuilder();
						for (String param : paramValues) {
							// 打印参数值
							sb.append(param).append(" ,");
						}
						sb.delete(sb.length()-2, sb.length());
						System.out.print(sb.toString());
						System.out.print("]");
					}
				}
				System.out.println();

			}
			pageFilter(req,res,session);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			chain.doFilter(request, response);
		}
	}

	/**
	 * 分页参数过滤
	 */
	public static void pageFilter(HttpServletRequest req,HttpServletResponse res,HttpSession session){
		try {
			SystemContext.setServletContext(session.getServletContext());
			SystemContext.setRequest(req);
			SystemContext.setResponse(res);
			String page = req.getParameter("page");
			if(StringUtils.isBlank(page)) page = "1";
			String rows = req.getParameter("rows");
			if(StringUtils.isBlank(rows)) rows = "10";
			String sort = req.getParameter("sort");
			String order = req.getParameter("order");
			if(StringUtils.isBlank(sort)) sort = "id";
			if(StringUtils.isBlank(order)) order = "desc";
			
			int currentPage = Integer.parseInt(page); 
			int pageSize = Integer.parseInt(rows);
			SystemContext.setPageOffset((currentPage-1)*pageSize);
			SystemContext.setPageSize(pageSize);
			SystemContext.setCurrentPage(currentPage);
		} catch (Exception e) {
			SystemContext.setPageOffset(0);
			SystemContext.setPageSize(10);
			SystemContext.setCurrentPage(1);
		} 
	}
	public void init(FilterConfig config) throws ServletException {

	}

}
