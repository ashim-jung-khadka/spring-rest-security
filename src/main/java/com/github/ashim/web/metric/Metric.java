package com.github.ashim.web.metric;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Metric {

	public String user;
	public List<ApiLog> apiLogs;

	static class ApiLog {
		public String request;
		public List<Data> datas;
	}

	static class Data {

		public Integer status;
		public Integer count;

	}

}