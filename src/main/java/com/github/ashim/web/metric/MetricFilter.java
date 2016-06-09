package com.github.ashim.web.metric;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.github.ashim.web.util.SecurityContextUtil;

@Component
public class MetricFilter implements Filter {

	@Autowired
	private IMetricService metricService;

	@Override
	public void init(final FilterConfig config) throws ServletException {
		if (metricService == null) {
			metricService = (IMetricService) WebApplicationContextUtils
					.getRequiredWebApplicationContext(config.getServletContext()).getBean("metricService");
		}
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws java.io.IOException, ServletException {

		final HttpServletRequest httpRequest = ((HttpServletRequest) request);
		final String req = getPath(httpRequest.getRequestURI());

		chain.doFilter(request, response);

		final int status = ((HttpServletResponse) response).getStatus();
		metricService.increaseCount(req, status);

		SecurityContextUtil contextUtil = new SecurityContextUtil();
		UserDetails userDetails = contextUtil.getPrincipal();

		if (userDetails != null) {
			String name = userDetails.getUsername();
			metricService.updateMetric(name, req, status);
		}

	}

	@Override
	public void destroy() {

	}

	private String getPath(String url) {
		String projectName = "spring-rest-security";

		Integer indexOf = url.indexOf(projectName) + projectName.length();
		String path = url.substring(indexOf);
		Integer lastIndexOf = path.indexOf("/", 1);

		path = (lastIndexOf > 0) ? path.substring(0, lastIndexOf) : path;

		return path;
	}
}
