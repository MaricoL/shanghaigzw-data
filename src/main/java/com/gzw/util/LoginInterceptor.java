package com.gzw.util;

import com.gzw.entity.system.SysUser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		SysUser user =  (SysUser) session.getAttribute("userSession");
		String urlString = request.getRequestURI();
		System.out.println(urlString);

			if(null == user) {
		 response.getWriter().print("<html><script type='text/JavaScript'> top.location.href='" + request.getContextPath() + "/'</script></html>");
            response.setContentType("text/html");
				//request.getRequestDispatcher("/login.jsp").forward(request, response);

				return false;
			}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
