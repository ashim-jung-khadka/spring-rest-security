package com.github.ashim.test.self;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise2 {

	public static final Integer SLEEP_TIME = 1000;

	public static void main(String[] args) {

		TTLMap ttlMap = new TTLMapImpl();
		ttlMap.put("1", "Test2", 2);
		ttlMap.put("2", "Test4", 4);
		ttlMap.put("3", "Test5", 5);
		ttlMap.put("4", "Test7", 7);
		ttlMap.put("5", "Test8", 8);

		// Check whether items are expired or not
		new Thread("Test") {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						sleep(SLEEP_TIME);

						System.out.println("" + i + ": " + ttlMap.get("1"));
						System.out.println("" + i + ": " + ttlMap.get("2"));
						System.out.println("" + i + ": " + ttlMap.get("3"));
						System.out.println("" + i + ": " + ttlMap.get("4"));
						System.out.println("" + i + ": " + ttlMap.get("5"));
						System.out.println("" + i + ": " + ttlMap.get("6"));

						System.out.println("-------------------------------------------------");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

		// Add new items in map and check for expiry
		// Also check whether new added item will join previous thread or not
		new Thread("Test") {
			@Override
			public void run() {
				try {
					sleep(SLEEP_TIME);
					sleep(SLEEP_TIME);
					sleep(SLEEP_TIME);
					ttlMap.put("6", "Test9", 5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}.start();
	}

}

interface TTLMap {
	void put(String key, Object value, long timeToLiveInSecs);

	Object get(String key);
}

class TTLMapImpl implements TTLMap {

	private Long startTime = 0L;
	private Map<String, Object> map = new HashMap<>();
	private Map<String, Long> ttlMap = new HashMap<>();

	TTLMapImpl() {
		TimeToLive ttl = new TimeToLive();
		ttl.start();
	}

	@Override
	public void put(String key, Object value, long timeToLiveInSecs) {
		map.put(key, value);
		ttlMap.put(key, timeToLiveInSecs + startTime);
	}

	@Override
	public Object get(String key) {
		return map.get(key);
	}

	class TimeToLive extends Thread {

		@Override
		public void run() {
			while (true) {

				try {
					sleep(Exercise2.SLEEP_TIME);
					++startTime;

					List<String> keys = new ArrayList<>();
					for (String key : ttlMap.keySet()) {
						if (startTime == ttlMap.get(key)) {
							map.remove(key);
							keys.add(key);
						}
					}

					keys.forEach(key -> ttlMap.remove(key));

					// Break the loop if map doesn't contain any element
					// This stop the thread after all items expired
					// Comment it for continuous expiry checking
					if (map.size() == 0) {
						break;
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}