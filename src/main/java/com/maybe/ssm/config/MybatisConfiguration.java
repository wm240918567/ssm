package com.maybe.ssm.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

/**
 * 使用pageHelper分页的配置类
 * @author maybe
 */
@Configuration
public class MybatisConfiguration {

	@Bean
	public PageHelper pageHelper() {
		PageHelper helper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		helper.setProperties(properties);
		return helper;
	}
}
