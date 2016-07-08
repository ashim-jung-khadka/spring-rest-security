package com.github.ashim.spring.config;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.github.ashim")
@PropertySource(value = { "classpath:config/application.properties" })
public class SpringConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}

	/*
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript
	 * etc...
	 *
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		super.addViewControllers(registry);
		registry.addViewController("/graph.html");
		registry.addViewController("/graph2.html");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new MyObjectMapper());

		converters.add(converter);
	}

	class MyObjectMapper extends ObjectMapper {

		private static final long serialVersionUID = 6609796773194207691L;

		public MyObjectMapper() {
			registerModule(new MyModule());
			setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		}

	}

	class MyModule extends SimpleModule {

		private static final long serialVersionUID = 8842018254950089167L;

		public MyModule() {

			// addSerializer(Object.class, new
			// StdSerializer<Object>(Object.class) {
			// private static final long serialVersionUID =
			// 4353193990881645654L;
			//
			// @Override
			// public void serialize(Object value, JsonGenerator jgen,
			// SerializerProvider provider)
			// throws IOException {
			//// Object obj = RelationBuilder.build(value).toString();
			// jgen.writeString( RelationBuilder.build(value).toString());
			// }
			// });

			addDeserializer(String.class, new StdScalarDeserializer<String>(String.class) {
				private static final long serialVersionUID = 9044039001965469284L;

				@Override
				public String deserialize(JsonParser jp, DeserializationContext ctxt)
						throws IOException, JsonProcessingException {
					return StringUtils.trimWhitespace(jp.getValueAsString());
				}
			});
		}
	}

}