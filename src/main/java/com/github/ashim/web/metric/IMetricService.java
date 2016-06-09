package com.github.ashim.web.metric;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface IMetricService {

	void increaseCount(final String request, final int status);

	Map<String, ConcurrentHashMap<Integer, Integer>> getFullMetric();

	Map<Integer, Integer> getStatusMetric();

	Object[][] getGraphData();

	public void updateMetric(String user, String request, Integer status);

	public List<Metric> getAllMetrics();

	public Metric getMetric(String user);
}
