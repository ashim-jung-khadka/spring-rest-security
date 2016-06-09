package com.github.ashim.web.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.ashim.web.metric.IMetricService;
import com.github.ashim.web.metric.Metric;

@RestController
@RequestMapping("/root")
public class RootController {

	@Autowired
	private IMetricService metricService;

	@RequestMapping(value = "/metric", method = RequestMethod.GET)
	public Map<String, ConcurrentHashMap<Integer, Integer>> getMetric() {
		return metricService.getFullMetric();
	}

	@RequestMapping(value = "/status-metric", method = RequestMethod.GET)
	public Map<Integer, Integer> getStatusMetric() {
		return metricService.getStatusMetric();
	}

	@RequestMapping(value = "/metric-graph", method = RequestMethod.GET)
	public Object[][] drawMetric() {
		final Object[][] result = metricService.getGraphData();
		for (int i = 1; i < result[0].length; i++) {
			result[0][i] = result[0][i].toString();
		}

		System.out.println(result);
		return result;
	}

	@RequestMapping(value = "/demo-graph", method = RequestMethod.GET)
	public Object[][] demoGraph() {
		final Object[][] result = new Object[2][3];
		result[0][0] = "200";
		result[0][1] = "400";
		result[0][2] = "500";

		result[1][0] = "45";
		result[1][1] = "40";
		result[1][2] = "42";

		return result;
	}

	@RequestMapping(value = "/demo-graph2", method = RequestMethod.GET)
	public String demoGraph2() {
		return "{\"users\":[{\"name\":\"ashim\"," + "\"data\":[{\"code\":0,\"count\":0},"
				+ "{\"code\":200,\"count\":7}," + "{\"code\":400,\"count\":8}," + "{\"code\":500,\"count\":9}]},"
				+ "{\"name\":\"sameer\"," + "\"data\":[{\"code\":0,\"count\":0}," + "{\"code\":200,\"count\":6},"
				+ "{\"code\":400,\"count\":7}," + "{\"code\":500,\"count\":5}]}]}";
	}

	@RequestMapping(value = "/metrics", method = RequestMethod.GET)
	public List<Metric> getMetrics() {
		return metricService.getAllMetrics();
	}

	@RequestMapping(value = "/metrics/{user}", method = RequestMethod.GET)
	public Metric getMetricByUser(@PathVariable String user) {
		return metricService.getMetric(user);
	}

}
