package com.github.ashim.persistence.entity;

import com.github.ashim.persistence.common.utility.JsonFormatter;
import com.github.ashim.persistence.common.utility.RelationBuilder;

public class BaseEntity {

	@Override
	public String toString() {
		Object object = RelationBuilder.build(this);
		return JsonFormatter.toJSON(object);
	}

}