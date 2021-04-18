package com.jurspring.jt.config;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.core.convert.converter.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //所有请求都允许跨域，使用这种配置方法就不能在 interceptor 中再配置 header 了
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://192.168.43.230:8080","http://localhost:8080")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "d:/workspace/img/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter());
    }

    private static String[] pattern =
            new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.S",
                    "yyyy.MM.dd", "yyyy.MM.dd HH:mm", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss.S",
                    "yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss","yyyy/MM/dd HH:mm:ss.S","yyyy-MM-dd'T'HH:mm:ss.SSSZ"};
    /**
     * 毫秒 时间戳转Date 转换器
     * @author hefeiyu
     */
    public static class StringToDateConverter implements Converter<String, Date> {

        @Override
        public Date convert(String source) {
            Date targetDate = null;
            String originDate = source;
            if(originDate.contains("Z")){
                originDate = originDate.replace("Z"," UTC");
            }
            if (StringUtils.isNotEmpty(originDate)) {
                try {
                    long longDate = Long.parseLong(originDate.trim());
                    targetDate = new Date(longDate);
                } catch (NumberFormatException e) {
                    try {
//                        targetDate = DateUtils.parseDateStrictly(originDate,pattern);
                        targetDate = DateUtils.parseDate(originDate, pattern);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            return targetDate;
        }
    }

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ((MappingJackson2HttpMessageConverter) converter).getObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            }
        }
    }




}
