package com.epam.ds.filter;

import java.io.IOException;
import java.security.Provider;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.ds.controller.AdminCommand;
import com.epam.ds.controller.CommandProvider;
import com.epam.ds.controller.UsersCommand;

public class AutorizationFilter implements Filter{
	
	
	private final CommandProvider provider = new CommandProvider();
	
	
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String command = req.getParameter("command");
		String role = (String)session.getAttribute("role");
		if(provider.getCommand(command) instanceof UsersCommand && role == null) {			
			req.setAttribute("redirect", "GO_TO_LOGINATION_PAGE");
			
		}else if(provider.getCommand(command) instanceof AdminCommand && (role == null || !role.equals("Administrator"))) {
			req.setAttribute("redirect", "GO_TO_INDEX_PAGE");
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}
	
	

}
