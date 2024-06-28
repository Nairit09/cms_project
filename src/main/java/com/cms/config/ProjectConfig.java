package com.cms.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
@Component
public class ProjectConfig {

	@Bean
	ModelMapper mapper() {
		return new ModelMapper();
	}
}
