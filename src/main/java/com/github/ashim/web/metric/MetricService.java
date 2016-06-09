package com.github.ashim.web.metric;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

@Service
public class MetricService implements IMetricService {

	private ConcurrentMap<String, ConcurrentHashMap<Integer, Integer>> metricMap;
	private ConcurrentMap<Integer, Integer> statusMetric;
	private ConcurrentMap<String, ConcurrentHashMap<Integer, Integer>> timeMap;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private ConcurrentMap<String, ConcurrentMap<String, ConcurrentMap<Integer, Integer>>> userMetric;

	public MetricService() {
		super();
		metricMap = new ConcurrentHashMap<String, ConcurrentHashMap<Integer, Integer>>();
		statusMetric = new ConcurrentHashMap<Integer, Integer>();
		timeMap = new ConcurrentHashMap<String, ConcurrentHashMap<Integer, Integer>>();

		userMetric = new ConcurrentHashMap<>();
	}

	// API

	@Override
	public void updateMetric(String user, String request, Integer status) {

		Integer count = 1;
		ConcurrentMap<String, ConcurrentMap<Integer, Integer>> requestMetric = userMetric.get(user);

		if (requestMetric == null) {
			requestMetric = new ConcurrentHashMap<>();

			ConcurrentMap<Integer, Integer> statusMetric = new ConcurrentHashMap<>();
			statusMetric.put(status, count);

			requestMetric.put(request, statusMetric);

		} else {
			ConcurrentMap<Integer, Integer> statusMetric = requestMetric.get(request);

			if (statusMetric == null) {
				statusMetric = new ConcurrentHashMap<>();

				statusMetric.put(status, count);

			} else {
				count = statusMetric.get(status);

				if (count == null) {
					count = 1;
				} else {
					count++;
				}

				statusMetric.put(status, count);
			}

			requestMetric.put(request, statusMetric);
		}

		userMetric.put(user, requestMetric);

	}

	@Override
	public List<Metric> getAllMetrics() {
		List<Metric> metrics = new ArrayList<>();

		for (Entry<String, ConcurrentMap<String, ConcurrentMap<Integer, Integer>>> entry : userMetric.entrySet()) {
			Metric metric = new Metric();
			metric.user = entry.getKey();

			metrics.add(metric);
		}

		return metrics;
	}

	@Override
	public Metric getMetric(String user) {

		ConcurrentMap<String, ConcurrentMap<Integer, Integer>> requestMetric = userMetric.get(user);
		List<Metric.ApiLog> apiLogs = new ArrayList<>();

		if (requestMetric == null) {
			return null;
		}

		for (Entry<String, ConcurrentMap<Integer, Integer>> entry : requestMetric.entrySet()) {
			String request = entry.getKey();
			ConcurrentMap<Integer, Integer> statusMetric = entry.getValue();

			List<Metric.Data> datas = new ArrayList<>();
			for (Entry<Integer, Integer> dataEntry : statusMetric.entrySet()) {
				Integer status = dataEntry.getKey();
				Integer count = dataEntry.getValue();

				Metric.Data data = new Metric.Data();
				data.status = status;
				data.count = count;

				datas.add(data);
			}

			Metric.ApiLog apiLog = new Metric.ApiLog();
			apiLog.request = request;
			apiLog.datas = datas;

			apiLogs.add(apiLog);
		}

		Metric metric = new Metric();
		metric.user = user;
		metric.apiLogs = apiLogs;

		return metric;
	}

	@Override
	public void increaseCount(final String request, final int status) {
		increaseMainMetric(request, status);
		increaseStatusMetric(status);
		updateTimeMap(status);
	}

	@Override
	public Map<String, ConcurrentHashMap<Integer, Integer>> getFullMetric() {
		return metricMap;
	}

	@Override
	public Map<Integer, Integer> getStatusMetric() {
		return statusMetric;
	}

	@Override
	public Object[][] getGraphData() {
		final int colCount = statusMetric.keySet().size() + 1;
		final Set<Integer> allStatus = statusMetric.keySet();
		final int rowCount = timeMap.keySet().size() + 1;

		final Object[][] result = new Object[rowCount][colCount];
		result[0][0] = "Time";

		int j = 1;
		for (final int status : allStatus) {
			result[0][j] = status;
			j++;
		}
		int i = 1;
		ConcurrentMap<Integer, Integer> tempMap;
		for (final Entry<String, ConcurrentHashMap<Integer, Integer>> entry : timeMap.entrySet()) {
			result[i][0] = entry.getKey();
			tempMap = entry.getValue();
			for (j = 1; j < colCount; j++) {
				result[i][j] = tempMap.get(result[0][j]);
				if (result[i][j] == null) {
					result[i][j] = 0;
				}
			}
			i++;
		}

		return result;
	}

	// NON-API

	private void increaseMainMetric(final String request, final int status) {
		ConcurrentHashMap<Integer, Integer> statusMap = metricMap.get(request);
		if (statusMap == null) {
			statusMap = new ConcurrentHashMap<Integer, Integer>();
		}

		Integer count = statusMap.get(status);
		if (count == null) {
			count = 1;
		} else {
			count++;
		}
		statusMap.put(status, count);
		metricMap.put(request, statusMap);
	}

	private void increaseStatusMetric(final int status) {
		final Integer statusCount = statusMetric.get(status);
		if (statusCount == null) {
			statusMetric.put(status, 1);
		} else {
			statusMetric.put(status, statusCount + 1);
		}
	}

	private void updateTimeMap(final int status) {
		final String time = dateFormat.format(new Date());
		ConcurrentHashMap<Integer, Integer> statusMap = timeMap.get(time);
		if (statusMap == null) {
			statusMap = new ConcurrentHashMap<Integer, Integer>();
		}

		Integer count = statusMap.get(status);
		if (count == null) {
			count = 1;
		} else {
			count++;
		}
		statusMap.put(status, count);
		timeMap.put(time, statusMap);
	}

}