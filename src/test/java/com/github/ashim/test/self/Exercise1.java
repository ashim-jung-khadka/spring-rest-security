package com.github.ashim.test.self;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exercise1 {

	public static void main(String[] args) {

		// Build data for testing
		List<Integer[]> rowList = buildData();

		Employee employee = buildHeirarchy(rowList);

		displayHeirarchy(employee);

	}

	/**
	 * Build binary tree from list of array of integer
	 *
	 * @param allRowsFromDatabase
	 *            take array of employee id and manager id
	 * @return Employee binary tree
	 */
	public static Employee buildHeirarchy(List<Integer[]> allRowsFromDatabase) {

		Employee rootEmployee = new Employee();

		allRowsFromDatabase.forEach(row -> {
			Integer i = 0;

			if (row.length > 2) {
				System.out.println("ERROR : Some row contains multiple items");

			} else if (row[i + 1] == null) {
				rootEmployee.id = row[i];
			}
		});

		if (rootEmployee.id == null) {
			System.out.println("ERROR : Cannot find CEO who doesn't have manager");
			return null;
		}

		rootEmployee.directReports = recursionForBuildHeirarchy(allRowsFromDatabase, rootEmployee.id);

		return rootEmployee;

	}

	static Set<Employee> recursionForBuildHeirarchy(List<Integer[]> allRowsFromDatabase, Integer root) {

		Set<Employee> emps = new LinkedHashSet<>();
		Integer index = 0;

		for (Integer[] row : allRowsFromDatabase) {
			if (root == row[index + 1]) {
				Employee emp = new Employee();
				emp.id = row[index];
				emp.directReports = recursionForBuildHeirarchy(allRowsFromDatabase, row[index]);

				emps.add(emp);
			}
		}

		return emps;
	}

	/**
	 * Display employee set as binary tree
	 *
	 * @param root
	 *            Employee association set
	 */
	public static void displayHeirarchy(Employee root) {

		if (root == null) {
			System.out.println("INFO : No data to display");
			return;
		}

		Map<Integer, List<Integer>> treeMap = new HashMap<Integer, List<Integer>>();

		// DISPLAY TREE CONSTRUCTION
		{
			List<Integer> rootList = new ArrayList<Integer>();
			Integer index = 0;

			rootList.add(root.id);
			treeMap.put(index++, rootList);

			treeMap = recursionForDisplayHeirarchy(root, treeMap, index);
		}

		// PRINT CONSTRUCTED TREE
		for (Integer index : treeMap.keySet()) {

			for (Integer node : treeMap.get(index)) {
				System.out.print(node + " ");
			}

			System.out.println();
		}
	}

	static Map<Integer, List<Integer>> recursionForDisplayHeirarchy(Employee root, Map<Integer, List<Integer>> treeMap,
			Integer index) {

		for (Employee emp : root.directReports) {
			List<Integer> nodeList = treeMap.get(index);

			if (nodeList == null) {
				nodeList = new ArrayList<>();
			}

			nodeList.add(emp.id);
			treeMap.put(index, nodeList);
		}

		for (Employee emp : root.directReports) {
			treeMap = recursionForDisplayHeirarchy(emp, treeMap, index + 1);
		}

		return treeMap;
	}

	public static List<Integer[]> buildData() {
		Integer[] row1 = { 2, null };
		Integer[] row2 = { 7, 2 };
		Integer[] row3 = { 3, 2 };
		Integer[] row4 = { 5, 2 };
		Integer[] row5 = { 9, 7 };
		Integer[] row6 = { 13, 7 };
		Integer[] row7 = { 6, 3 };
		Integer[] row8 = { 10, 5 };
		Integer[] row9 = { 11, 6 };
		Integer[] row10 = { 12, 10 };
		Integer[] row11 = { 15, 10 };
		Integer[] row12 = { 4, 2 };
		Integer[] row13 = { 1, 4 };
		Integer[] row14 = { 8, 4 };
		Integer[] row15 = { 14, 8 };

		List<Integer[]> rowList = new ArrayList<Integer[]>();
		rowList.add(row1);
		rowList.add(row2);
		rowList.add(row3);
		rowList.add(row4);
		rowList.add(row12);
		rowList.add(row5);
		rowList.add(row6);
		rowList.add(row7);
		rowList.add(row8);
		rowList.add(row13);
		rowList.add(row14);
		rowList.add(row9);
		rowList.add(row10);
		rowList.add(row11);
		rowList.add(row15);

		return rowList;
	}

}

class Employee {
	Integer id;
	Set<Employee> directReports;

	@Override
	public String toString() {
		return "Employee [id=" + id + ", directReports=" + directReports + "]";
	}

}