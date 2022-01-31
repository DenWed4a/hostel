package com.epam.ds.filter;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.security.KeyStore.Entry;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.protobuf.MapEntry;

public class UrlSaverFilter implements Filter {
	private final static String QUESTION_MARK = "?";
	private final static String AMPERSAND = "&";
	private final static String EQUALS = "=";
	private final static String COMMAND = "command";
	private final static String CHANGE_LOCALE = "CHANGE_LOCALE";
	private final static String CHAGE_LOCALE_INDEX = "0";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String url;
		StringBuilder stringBuilder = new StringBuilder();

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		url = req.getRequestURL().toString();
		stringBuilder.append(url);

		Map<String, String[]> parameters = req.getParameterMap();
		if (parameters.size() != 0) {
			stringBuilder.append(QUESTION_MARK);
		}
		if (!parameters.get(COMMAND)[Integer.parseInt(CHAGE_LOCALE_INDEX)].equals(CHANGE_LOCALE)) {

			for (Map.Entry<String, String[]> pair : parameters.entrySet()) {
				String[] valueStrings = pair.getValue();

				for (int i = 0; i < valueStrings.length; i++) {
					stringBuilder.append(pair.getKey());
					stringBuilder.append(EQUALS);
					stringBuilder.append(valueStrings[i]);
					if (i != valueStrings.length - 1) {
						stringBuilder.append(AMPERSAND);
					}
				}
				stringBuilder.append(AMPERSAND);

			}
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			url = stringBuilder.toString();
			session.setAttribute("url", url);
		}

		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		Filter.super.destroy();

	}

}
