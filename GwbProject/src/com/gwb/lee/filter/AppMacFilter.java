package com.gwb.lee.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gwb.lee.dao.AndroidDao;
import com.gwb.lee.service.AndroidService;
import com.gwb.lee.util.ConstantParams;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class AppMacFilter implements Filter {

	private AndroidService androidService;

	/**
	 * Default constructor.
	 */
	public AppMacFilter() {

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		Object telephone = servletRequest.getParameter("telephone");
		Object macAddress = servletRequest.getParameter("macAddress");
		if (telephone == null || macAddress == null || "".equals(telephone)
				|| "".equals(macAddress)) {
			servletRequest.getRequestDispatcher("/AndroidLoginAction?type=filterUser&filterCode=0")
					.forward(servletRequest, response);
		}else {
			int rs = androidService.filterUser(telephone, macAddress);

			if (ConstantParams.STATUS_FILTER_SUCCESS == rs) {
				chain.doFilter(request, response);
			} else if (ConstantParams.STATUS_FILTER_NO_MACADDRESS == rs) {
				servletResponse.sendRedirect(servletRequest.getContextPath()
						+ "/AndroidLoginAction?type=filterUser&filterCode=2");

			} else {
				servletResponse.sendRedirect(servletRequest.getContextPath()
						+ "/AndroidLoginAction?type=filterUser&filterCode=0");
			}
		}
//		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		androidService = new AndroidDao();
	}

}
