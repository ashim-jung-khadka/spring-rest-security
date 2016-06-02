package com.github.ashim.test.util;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.github.ashim.persistence.common.config.PersistenceConfig;

@ContextConfiguration(classes = PersistenceConfig.class, loader = AnnotationConfigContextLoader.class)
public class TestModel {

}
