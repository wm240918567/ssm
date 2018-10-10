package com.maybe.ssm.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FastjsonHttpMessageConfig {

    /**
     * 使用注入bean方式集成json解析框架：fastjson
     * 可以避免单继承的缺点，配置spring-boot-starter-tomcat时使用启动类继承时冲突
     * @return HttpMessageConverters
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 先定义一个fastJsonHttpMessageConverte转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 添加fastJson的配置信息，比如是否需要格式化返回的JSON数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 解决fastJson中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        // 在fastJsonHttpMessageConverter添加fastJson的配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //返回一个消息转换器
        ParserConfig.getGlobalInstance().addAccept("com.maybe.ssm.");
        return new HttpMessageConverters(fastConverter);
    }
}
