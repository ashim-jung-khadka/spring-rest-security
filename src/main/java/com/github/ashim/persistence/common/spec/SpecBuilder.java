package com.github.ashim.persistence.common.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.github.ashim.persistence.common.enums.SearchOperation;
import com.google.common.base.Joiner;

public class SpecBuilder<T> {

	private List<SearchCriteria> criterias;

	private SpecBuilder() {
		criterias = new ArrayList<SearchCriteria>();
	}

	public static <T> Specification<T> build(String search) {

		SpecBuilder<T> builder = new SpecBuilder<>();

		String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
		Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
		Matcher matcher = pattern.matcher(search + ",");

		while (matcher.find()) {
			builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
		}

		return builder.buildSpec();
	}

	private SpecBuilder<T> with(String key, String operation, Object value, String prefix, String suffix) {

		SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));

		if (op != null) {
			if (op == SearchOperation.EQUALITY) {
				boolean startWithAsterisk = prefix.contains("*");
				boolean endWithAsterisk = suffix.contains("*");

				if (startWithAsterisk && endWithAsterisk) {
					op = SearchOperation.CONTAINS;
				} else if (startWithAsterisk) {
					op = SearchOperation.ENDS_WITH;
				} else if (endWithAsterisk) {
					op = SearchOperation.STARTS_WITH;
				}
			}

			criterias.add(new SearchCriteria(key, op, value));
		}

		return this;
	}

	private Specification<T> buildSpec() {

		if (criterias.size() == 0) {
			return null;
		}

		List<Specification<T>> specs = new ArrayList<Specification<T>>();

		for (SearchCriteria criteria : criterias) {

			CustomSpecification<T> customSpecification = new CustomSpecification<>(criteria);
			specs.add(customSpecification);
		}

		Specification<T> result = specs.get(0);

		for (int i = 1; i < specs.size(); i++) {
			result = Specifications.where(result).and(specs.get(i));
		}

		return result;
	}

}
